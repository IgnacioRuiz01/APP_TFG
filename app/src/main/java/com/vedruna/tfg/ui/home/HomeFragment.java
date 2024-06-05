package com.vedruna.tfg.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.vedruna.tfg.Adapter.ClassAdAdapter;
import com.vedruna.tfg.DTO.ClassAdDTO;
import com.vedruna.tfg.R;
import com.vedruna.tfg.Utils.TokenManager;

import java.util.List;

public class HomeFragment extends Fragment {

    private ListView listView;
    private HomeViewModel viewModel;
    private TokenManager tokenManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        listView = root.findViewById(R.id.listView);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        tokenManager = TokenManager.getInstance(requireContext());

        viewModel.getClassAds().observe(getViewLifecycleOwner(), classAds -> {
            // Actualiza el ListView con los datos de classAds
            if (classAds != null) {
                ClassAdAdapter adapter = new ClassAdAdapter(requireContext(), classAds);
                listView.setAdapter(adapter);
            } else {
                Log.e("HomeFragment", "ClassAds es null");
            }
        });

        viewModel.updateClassAds(viewModel.getClassAds().getValue());
    }
}
