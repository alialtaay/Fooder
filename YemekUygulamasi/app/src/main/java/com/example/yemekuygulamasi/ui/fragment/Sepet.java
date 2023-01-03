package com.example.yemekuygulamasi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.databinding.FragmentSepetBinding;
import com.example.yemekuygulamasi.ui.adapter.SepetAdapter;
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewModel;
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Sepet extends Fragment {
    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet,container,false);
        binding.setSepet(this);


        viewModel.sepetListesi.observe(getViewLifecycleOwner(),liste->{
            SepetAdapter adapter = new SepetAdapter(requireContext(),liste,viewModel);
            binding.setSepetAdapter(adapter);
        });

        binding.imageViewHome.setOnClickListener(view -> {
            getActivity().onBackPressed();

        });
        binding.button.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.siparisBasarili);
        });



        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SepetViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.sepetiYukle();
    }
}