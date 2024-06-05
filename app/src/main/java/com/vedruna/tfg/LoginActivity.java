package com.vedruna.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vedruna.tfg.Interfaces.AuthInterface;
import com.vedruna.tfg.Network.RetrofitClient;


import com.vedruna.tfg.Auth.AuthResponse;
import com.vedruna.tfg.Auth.LoginRequest;
import com.vedruna.tfg.Auth.RegisterRequest;
import com.vedruna.tfg.Utils.TokenManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdt, passwordEdt;
    private TextView loginFailMsg;
    private ImageView imageViewProfile1, imageViewProfile2, imageViewProfile3, imageViewProfile4;
    private int selectedAvatarId;
    private Dialog dialog;  // Dialog for the registration form

    private AuthInterface authInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authInterface = RetrofitClient.getAuthInterface(this);

        usernameEdt = findViewById(R.id.editTextUsername);
        passwordEdt = findViewById(R.id.editTextPassword);
        loginFailMsg = findViewById(R.id.loginFallo);

        Button loginBtn = findViewById(R.id.buttonLogin);
        loginBtn.setOnClickListener(this::login);

        TextView registerLink = findViewById(R.id.textViewRegister);
        registerLink.setOnClickListener(v -> showRegisterDialog());
    }

    private void login(View view) {
        String username = usernameEdt.getText().toString().trim();
        String password = passwordEdt.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
        } else {
            performLogin(username, password);
        }
    }

    private void performLogin(String username, String password) {
        //Clear any existing token before attempting to log in
        TokenManager.getInstance(LoginActivity.this).clearToken();
        LoginRequest loginRequest = new LoginRequest(username, password);
        authInterface.login(loginRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TokenManager.getInstance(LoginActivity.this).saveToken(response.body().getToken());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    loginFailMsg.setText("Login failed: " + response.message());
                    loginFailMsg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showRegisterDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.register_form);  // Ensure this layout is correctly defined

        EditText editTextRegisterName = dialog.findViewById(R.id.editTextRegisterName);
        EditText editTextRegisterEmail = dialog.findViewById(R.id.editTextRegisterEmail);
        EditText editTextRegisterPassword = dialog.findViewById(R.id.editTextRegisterPassword);


        imageViewProfile1 = dialog.findViewById(R.id.imageViewProfile1);
        imageViewProfile2 = dialog.findViewById(R.id.imageViewProfile2);
        imageViewProfile3 = dialog.findViewById(R.id.imageViewProfile3);
        imageViewProfile4 = dialog.findViewById(R.id.imageViewProfile4);

        setupAvatarClicks();
        // Find the register button in the dialog and set its OnClickListener
        Button registerBtn = dialog.findViewById(R.id.buttonRegister);
        registerBtn.setOnClickListener(view -> register(editTextRegisterName, editTextRegisterEmail, editTextRegisterPassword));

        dialog.show();
    }

    private void setupAvatarClicks() {
        View.OnClickListener listener = view -> {
            resetAvatarsBackground();
            view.setBackgroundResource(R.drawable.fondo_seleccionado);
            // Obtener el tag como String
            String tag = (String) view.getTag();
            // Convertir el tag a int
            selectedAvatarId = Integer.parseInt(tag);
            Toast.makeText(LoginActivity.this, "Selected Avatar ID: " + selectedAvatarId, Toast.LENGTH_SHORT).show();

        };

        imageViewProfile1.setOnClickListener(listener);
        imageViewProfile2.setOnClickListener(listener);
        imageViewProfile3.setOnClickListener(listener);
        imageViewProfile4.setOnClickListener(listener);
    }


    private void resetAvatarsBackground() {
        imageViewProfile1.setBackgroundResource(R.drawable.fondo_avatar);
        imageViewProfile2.setBackgroundResource(R.drawable.fondo_avatar);
        imageViewProfile3.setBackgroundResource(R.drawable.fondo_avatar);
        imageViewProfile4.setBackgroundResource(R.drawable.fondo_avatar);
    }

    public void register(EditText editTextRegisterName, EditText editTextRegisterEmail, EditText editTextRegisterPassword) {
        // Clear any existing token before attempting to log in
        TokenManager.getInstance(LoginActivity.this).clearToken();

        String name = editTextRegisterName.getText().toString().trim();
        String email = editTextRegisterEmail.getText().toString().trim();
        String password = editTextRegisterPassword.getText().toString().trim();

        Log.d("RegisterActivity", "Name: " + name);
        Log.d("RegisterActivity", "Email: " + email);
        Log.d("RegisterActivity", "Password: " + password);

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else {
            // Obtener el id del avatar seleccionado
            int avatarId = selectedAvatarId; // Esto depende de cómo obtengas el id del avatar seleccionado

            // Crear el objeto RegisterRequest con el id del avatar
            RegisterRequest registerRequest = new RegisterRequest(name, email, password, avatarId);

            // Realizar la solicitud de registro
            authInterface.register(registerRequest).enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        String errorMessage = "Registration failed: " + response.code();
                        if (response.errorBody() != null) {
                            try {
                                errorMessage += " - " + response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        Log.e("RegisterError", errorMessage);
                    }
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
