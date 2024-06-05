package com.vedruna.tfg.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.vedruna.tfg.Adapter.ClassAdAdapter;
import com.vedruna.tfg.DTO.ClassAdDTO;
import com.vedruna.tfg.Utils.Constants;
import com.vedruna.tfg.databinding.FragmentDashboardBinding;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ListView listView;
    private DashboardViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(DashboardViewModel.class);
        viewModel.setContext(requireContext()); // Set the context for ViewModel

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listView = binding.listView;
        EditText searchField = binding.searchField;
        Button searchButton = binding.searchButton;

        searchButton.setOnClickListener(v -> {
            String query = searchField.getText().toString();
            Log.d("DashboardFragment", "Se presionó el botón de búsqueda. Query: " + query);
            viewModel.searchClassAdsByTitle(query);
        });

        setupObservers();

        return root;
    }

    private void setupObservers() {
        viewModel.getClassAds().observe(getViewLifecycleOwner(), classAds -> {
            if (classAds != null) {
                Log.d("DashboardFragment", "Clases observadas: " + classAds.size());
                ClassAdAdapter classAdAdapter = new ClassAdAdapter(requireContext(), classAds);
                listView.setAdapter(classAdAdapter);
                for (ClassAdDTO classAd : classAds) {
                    Log.i("Clases: ", classAd.toString());
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
