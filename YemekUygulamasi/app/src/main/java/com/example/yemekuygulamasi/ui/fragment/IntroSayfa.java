package com.example.yemekuygulamasi.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.databinding.FragmentSplashScreenBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class IntroSayfa extends Fragment  {
    private FragmentSplashScreenBinding binding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false);
        sp = getActivity().getSharedPreferences("Giris Bilgi", Context.MODE_PRIVATE);
        editor = sp.edit();
        binding.buttonGiris.setOnClickListener(view -> {
            if (binding.editTextKullanici.getText().toString().equals("admin") && binding.editTextSifre.getText().toString().equals("123")){
                editor.putString("username",binding.editTextKullanici.getText().toString());
                editor.putString("password",binding.editTextSifre.getText().toString());
                editor.commit();
                Navigation.findNavController(view).navigate(R.id.anasayfa_gecis);

            }else{
                Toast.makeText(getActivity().getApplicationContext(),"Kullanıc adı veya Şifre hatalı",Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }


}