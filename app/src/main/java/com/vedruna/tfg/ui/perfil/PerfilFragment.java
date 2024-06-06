package com.vedruna.tfg.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.vedruna.tfg.DTO.UserDTO;
import com.vedruna.tfg.LoginActivity;
import com.vedruna.tfg.Utils.TokenManager;
import com.vedruna.tfg.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel perfilViewModel;
    private static final String TAG = "PerfilFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Log.d(TAG, "Initializing PerfilViewModel");
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        Log.d(TAG, "Loading user profile");
        perfilViewModel.loadUserProfile(requireContext());

        final ImageView avatar1 = binding.imageViewProfile1;
        final ImageView avatar2 = binding.imageViewProfile2;
        final ImageView avatar3 = binding.imageViewProfile3;
        final ImageView avatar4 = binding.imageViewProfile4;
        final TextView userName = binding.username;
        final TextView email = binding.email;
        final TextView description = binding.description;
        final Button logoutButton = binding.logoutButton;

        perfilViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<UserDTO>() {
            @Override
            public void onChanged(UserDTO user) {
                if (user != null) {
                    Log.d(TAG, "User profile data received: " + user.toString());
                    userName.setText(user.getUsername());
                    email.setText(user.getEmail());
                    description.setText(user.getDescription());
                    showUserAvatar(user.getIdAvatar());
                } else {
                    Log.d(TAG, "User profile data is null");
                    userName.setText("No user data available");
                    email.setText("");
                    description.setText("");
                    hideAllAvatars();
                }
            }

            private void showUserAvatar(int idAvatar) {
                hideAllAvatars();
                switch (idAvatar) {
                    case 1:
                        avatar1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        avatar2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        avatar3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        avatar4.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            private void hideAllAvatars() {
                avatar1.setVisibility(View.GONE);
                avatar2.setVisibility(View.GONE);
                avatar3.setVisibility(View.GONE);
                avatar4.setVisibility(View.GONE);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Logout button clicked");
                TokenManager.getInstance(requireContext()).clearToken();
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView called");
        binding = null;
    }
}
