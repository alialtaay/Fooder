package com.example.yemekuygulamasi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemekuygulamasi.R;
import com.example.yemekuygulamasi.data.entity.Yemekler;
import com.example.yemekuygulamasi.databinding.CardTasarimBinding;
import com.example.yemekuygulamasi.ui.fragment.AnasayfaDirections;
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnasayfaAdapter extends RecyclerView.Adapter<AnasayfaAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<Yemekler> yemeklerList;
    private AnasayfaViewModel viewModel;

    public AnasayfaAdapter(Context mContext, List<Yemekler> yemeklerList, AnasayfaViewModel viewModel) {
        this.mContext = mContext;
        this.yemeklerList = yemeklerList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardTasarimBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.card_tasarim,parent,false);
        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Yemekler yemek = yemeklerList.get(position);
        CardTasarimBinding t = holder.binding;
        t.yemekisim.setText(yemek.getYemek_adi());
        t.yemekFiyat.setText(String.valueOf(yemek.getYemek_fiyat())+" ₺ ");

        t.cardView.setOnClickListener(view -> {
            AnasayfaDirections.YemekDetayGecis gecis = AnasayfaDirections.yemekDetayGecis(yemek);
            Navigation.findNavController(view).navigate(gecis);
        });

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + yemek.getYemek_resim_adi();
        Picasso.get().load(url).into(t.imageView7);




    }

    @Override
    public int getItemCount() {
        return yemeklerList.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardTasarimBinding binding;
        public CardTasarimTutucu(CardTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
