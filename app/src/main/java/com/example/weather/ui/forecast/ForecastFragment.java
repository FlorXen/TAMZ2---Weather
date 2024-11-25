package com.example.weather.ui.forecast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.FragmentForecastBinding;
import com.example.weather.ui.ForecastItemAdapter;
import com.example.weather.ui.SharedViewModel;

public class ForecastFragment extends Fragment {

    private FragmentForecastBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ForecastViewModel forecastViewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        forecastViewModel.fetchForecast(getContext());

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getTrigger().observe(getViewLifecycleOwner(), trigger -> {
            if (trigger != null && trigger) {
                forecastViewModel.fetchForecast(getContext());

                sharedViewModel.setTrigger(false);
            }
        });

        binding = FragmentForecastBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recycler_view = binding.recyclerView;
        forecastViewModel.getList().observe(getViewLifecycleOwner(), newList -> {
            recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
            ForecastItemAdapter adapter = new ForecastItemAdapter(getContext(), newList);
            recycler_view.setAdapter(adapter);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}