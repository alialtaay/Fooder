package com.example.yemekuygulamasi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.data.entity.SepetYemekler;
import com.example.yemekuygulamasi.databinding.CardSepetTasarimBinding;
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.CardTasarimSepetTutucu> {
    private Context mContext;
    private List<SepetYemekler> yemeklerList;
    private SepetViewModel viewModel;

    public SepetAdapter(Context mContext, List<SepetYemekler> yemeklerList, SepetViewModel viewModel) {
        this.mContext = mContext;
        this.yemeklerList = yemeklerList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public CardTasarimSepetTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardSepetTasarimBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.card_sepet_tasarim,parent,false);
        return new CardTasarimSepetTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimSepetTutucu holder, int position) {
        SepetYemekler yemekler = yemeklerList.get(position);
        CardSepetTasarimBinding d = holder.binding;
        d.textViewSepet.setText(yemekler.getYemek_adi());
        d.tvSepetYemekAdet.setText(String.valueOf(yemekler.getYemek_siparis_adet()));
        d.textViewFiyat.setText(String.valueOf(yemekler.getYemek_fiyat()*yemekler.getYemek_siparis_adet())+"₺");

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + yemekler.getYemek_resim_adi();
        Picasso.get().load(url).into(d.imageViewSepetResim);

        d.ivSepetSil.setOnClickListener(view -> {
            Snackbar.make(view,yemekler.getYemek_adi()+ "Silinsin mi",Snackbar.LENGTH_LONG).setAction("Evet",view1 -> {
                viewModel.sil(yemekler.getSepet_yemek_id(),yemekler.getKullanici_adi());
            }).show();
        });

        d.btnSepetArti.setOnClickListener(view -> {
            yemekler.setYemek_siparis_adet(yemekler.getYemek_siparis_adet()+1);
            d.tvSepetYemekAdet.setText(String.valueOf(yemekler.getYemek_siparis_adet()));
            d.textViewFiyat.setText(String.valueOf(yemekler.getYemek_siparis_adet()*yemekler.getYemek_fiyat())+"₺");
        });

        d.btnSepetEksi.setOnClickListener(view -> {
            if(yemekler.getYemek_siparis_adet()>1){
                yemekler.setYemek_siparis_adet(yemekler.getYemek_siparis_adet()-1);
                d.tvSepetYemekAdet.setText(String.valueOf(yemekler.getYemek_siparis_adet()));
                d.textViewFiyat.setText(String.valueOf(yemekler.getYemek_siparis_adet()*yemekler.getYemek_fiyat())+"₺");

            }else {

            }
        });

    }

    @Override
    public int getItemCount() {
        return yemeklerList.size();
    }

    public class CardTasarimSepetTutucu extends RecyclerView.ViewHolder{
        private CardSepetTasarimBinding binding;
        public CardTasarimSepetTutucu( CardSepetTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}