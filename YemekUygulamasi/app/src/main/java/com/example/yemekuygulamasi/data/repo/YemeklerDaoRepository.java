package com.example.yemekuygulamasi.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.yemekuygulamasi.data.entity.CRUDCevap;
import com.example.yemekuygulamasi.data.entity.SepetYemekler;
import com.example.yemekuygulamasi.data.entity.SepetYemeklerCevap;
import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.data.entity.YemeklerCevap;
import com.example.yemekuygulamasi.retrofit.YemeklerDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    private YemeklerDao ydao;
    private MutableLiveData<List<Yemekler>> yemeklerListesi;
    private MutableLiveData<List<SepetYemekler>> sepetListesi;

    public YemeklerDaoRepository(YemeklerDao ydao) {
        this.ydao = ydao;
        yemeklerListesi = new MutableLiveData();
        sepetListesi = new MutableLiveData();
    }


    public MutableLiveData<List<Yemekler>> yemekleriGetir(){
        return yemeklerListesi;
    }

    public MutableLiveData<List<SepetYemekler>> sepetiGetir(){return sepetListesi;}



    public void yemekSil(int yemek_id){
        Log.e("Kişi Sil",String.valueOf(yemek_id));
    }

    public void yemekSepeteEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi){
        ydao.yemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                    int basari = response.body().getSuccess();
                    String mesaj = response.body().getMessage();
                    Log.e("Yemek sepete ekle",+basari+ " - "+mesaj);
                }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }


    public void tumYemekleriAl(){
        ydao.tumYemekler().enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {

                List<Yemekler> liste = response.body().getYemekler();
                yemeklerListesi.setValue(liste);

            }

            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {}
        });



    }

    public void sepetYemekSil(int sepet_yemek_id,String kullanici_adi){
        ydao.sepettenYemekSil(sepet_yemek_id,"ali_altay").enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                    tumSepetiGoster();

            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }

    public void tumSepetiGoster(){
        ydao.sepettekiYemekler("ali_altay").enqueue(new Callback<SepetYemeklerCevap>() {
            @Override
            public void onResponse(Call<SepetYemeklerCevap> call, Response<SepetYemeklerCevap> response) {
                List<SepetYemekler> liste = response.body().getSepet_yemekler();
                sepetListesi.setValue(liste);
            }

            @Override
            public void onFailure(Call<SepetYemeklerCevap> call, Throwable t) {

            }
        });
    }





}



