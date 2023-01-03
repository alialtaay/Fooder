package com.example.yemekuygulamasi.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yemekuygulamasi.data.entity.SepetYemekler;
import com.example.yemekuygulamasi.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {

    private YemeklerDaoRepository krepo;
    public MutableLiveData<List<SepetYemekler>> sepetListesi = new MutableLiveData();

    @Inject
    public SepetViewModel(YemeklerDaoRepository krepo) {
        this.krepo = krepo;
        sepetiYukle();
        sepetListesi = krepo.sepetiGetir();

    }

     public void sil(int sepet_yemek_id,String kullanici_adi) {
        krepo.sepetYemekSil(sepet_yemek_id,kullanici_adi);
    }

    public void sepetiYukle(){
        krepo.tumSepetiGoster();
    }
}





