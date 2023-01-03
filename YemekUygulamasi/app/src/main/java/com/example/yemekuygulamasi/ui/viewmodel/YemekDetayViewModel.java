package com.example.yemekuygulamasi.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.yemekuygulamasi.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class YemekDetayViewModel extends ViewModel {
    private YemeklerDaoRepository krepo;

    @Inject
    public YemekDetayViewModel(YemeklerDaoRepository krepo){
        this.krepo = krepo;
    }


    public void kayit(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet,String kullanici_adi){
       krepo.yemekSepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);
    }
}
