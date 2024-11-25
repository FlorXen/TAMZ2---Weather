package com.example.weather.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather.R;
import com.example.weather.databinding.FragmentHomeBinding;
import com.example.weather.ui.SharedViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setContext(getContext());

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView txt_temp = binding.txtTemp;
        homeViewModel.getM_temperature().observe(getViewLifecycleOwner(), newText -> {
            txt_temp.setText(String.format("%s°C", String.valueOf(newText)));
        });
        final TextView txt_desc = binding.txtDesc;
        homeViewModel.getM_description().observe(getViewLifecycleOwner(), txt_desc::setText);
        final ImageView img_icon = binding.imgIcon;
        homeViewModel.getM_icon().observe(getViewLifecycleOwner(), newIconId -> {

            String iconName = "icon_" + newIconId;
            Context context = getContext();
            assert context != null;
            int resourceId = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
            if (resourceId != 0) {
                img_icon.setImageResource(resourceId);
            } else {
                img_icon.setImageResource(R.drawable.icon_01d);
            }
        });
        final EditText input_location = binding.inputLocation;
        homeViewModel.getM_city().observe(getViewLifecycleOwner(), input_location::setText);
        final Button btn_ok = binding.btnOk;
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.fetchWeather(String.valueOf(input_location.getText()));
                sharedViewModel.setTrigger(true);
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