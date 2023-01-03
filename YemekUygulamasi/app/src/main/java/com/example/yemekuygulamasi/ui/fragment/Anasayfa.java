package com.example.yemekuygulamasi.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemekuygulamasi.MainActivity;
import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.databinding.FragmentAnasayfaBinding;
import com.example.yemekuygulamasi.ui.adapter.AnasayfaAdapter;
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Anasayfa extends Fragment  {
    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,false);
        binding.setAnasayfa(this);

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(),liste ->{
            AnasayfaAdapter adapter = new AnasayfaAdapter(requireContext(),liste,viewModel);
            binding.setAnasayfaAdapter(adapter);

        });
        binding.imageViewSepet.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.sepetSayfaGecis);

        });





        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
    }


}
