package com.example.yemekuygulamasi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.databinding.FragmentYemekDetayBinding;
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewModel;
import com.example.yemekuygulamasi.ui.viewmodel.YemekDetayViewModel;
import com.squareup.picasso.Picasso;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class YemekDetay extends Fragment {
    private FragmentYemekDetayBinding binding;
    private YemekDetayViewModel viewModel;
    private int adet = 1 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay,container,false);
        binding.setYemekDetay(this);


        YemekDetayArgs bundle = YemekDetayArgs.fromBundle(getArguments());
        Yemekler gelenYemekler = bundle.getYemek();

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + gelenYemekler.getYemek_resim_adi();
        Picasso.get().load(url).into(binding.imageViewDetay);


        binding.imageViewHome.setOnClickListener(view -> {
            getActivity().onBackPressed();

        });

        binding.imageViewSepet.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.DetaySepetGecis);
        });
        binding.textViewDetay.setText(gelenYemekler.getYemek_adi());
        binding.tvYemekFiyat.setText(String.valueOf(gelenYemekler.getYemek_fiyat())+"₺");
        binding.textViewDetaysayi.setText(String.valueOf(adet));


        binding.addBtn.setOnClickListener(view -> {
            adet++;
            binding.textViewDetaysayi.setText(String.valueOf(adet));
            binding.tvYemekFiyat.setText(String.valueOf(adet*gelenYemekler.getYemek_fiyat())+"₺");
        });

        binding.minusBtn.setOnClickListener(view -> {
            if (adet>1){
                adet--;
                binding.textViewDetaysayi.setText(String.valueOf(adet));
                binding.tvYemekFiyat.setText(String.valueOf(adet*gelenYemekler.getYemek_fiyat())+"₺");
            }
        });


        binding.buttonSepeteEkle.setOnClickListener(view -> {
            kayit(gelenYemekler.getYemek_adi(),gelenYemekler.getYemek_resim_adi(),gelenYemekler.getYemek_fiyat(),adet,"ali_altay");
        });



        return binding.getRoot();
    }
    public void kayit(String yemek_adi, String yemek_resim_adi,int yemek_fiyat,int yemek_siparis_adet,String kullanici_adi){
        viewModel.kayit(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(YemekDetayViewModel.class);
    }
}