package com.vedruna.tfg.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.vedruna.tfg.DTO.ClassAdDTO;
import com.vedruna.tfg.Utils.TokenManager;
import com.vedruna.tfg.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        final TextView titleLabel = binding.titleLabel;
        final EditText titleInput = binding.titleInput;
        final EditText descriptionInput = binding.descriptionInput;
        final EditText priceInput = binding.priceInput;
        final Button submitButton = binding.submitButton;

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), titleLabel::setText);

        submitButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String price = priceInput.getText().toString();

            if (title.isEmpty() || description.isEmpty() || price.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                ClassAdDTO newAd = new ClassAdDTO();
                newAd.setTitle(title);
                newAd.setDescription(description);
                newAd.setPrice(Double.parseDouble(price));

                // Obtener el ID del usuario del token JWT

                Long user_id = Long.parseLong(TokenManager.getInstance(requireContext()).getUserId());

                // Establecer el ID del usuario en el objeto ClassAdDTO
                newAd.setUserId(user_id);

                Log.e("DESCRIPCION", description);
                Log.e("PRICE: ", price);
                Log.e("TITLE: ", title);


                // Llamar al método createClassAd de NotificationsViewModel
                notificationsViewModel.createClassAd(newAd, requireContext());
            }
        });

        notificationsViewModel.getCreationResult().observe(getViewLifecycleOwner(), success -> {
            if (success) {
                Toast.makeText(getContext(), "Class Ad created successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to create Class Ad", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
