package com.example.isabe.ramemichiraku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.isabe.ramemichiraku.model.Cardapio;
import com.example.isabe.ramemichiraku.R;
import com.example.isabe.ramemichiraku.utils.FragmentCommunication;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by isabe on 02/03/2018.
 */
public class CardapioAdapter extends RecyclerView.Adapter<CardapioAdapter.CardapioViewHolder> {
    private final FragmentCommunication mCommunicator;
    List<Cardapio> arrayCardapio = new ArrayList<>();
    private Context mContext;

    public CardapioAdapter(Context context, List<Cardapio> array, FragmentCommunication communication){
        this.arrayCardapio = array;
        mContext = context;
        mCommunicator=communication;
    }

    @Override
    public CardapioAdapter.CardapioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Chama o layout de design da lista
        View view_design = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_lista, parent, false);

        CardapioViewHolder viewHolder = new CardapioViewHolder(view_design, mCommunicator);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardapioAdapter.CardapioViewHolder holder, int position) {
        holder.bindCardapio(arrayCardapio.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayCardapio.size();
    }

    public class CardapioViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_descricao_lista_cardapio) TextView descricao;
        @BindView(R.id.txt_preco_lista_cardapio) TextView preco;
        @BindView(R.id.txt_titulo_lista_cardapio) TextView titulo;
        @BindView(R.id.img_lista_cardapio) ImageView imagem_lista;
        private Context mContext;
        FragmentCommunication mComminication;
        
        public CardapioViewHolder(View itemView, FragmentCommunication Communicator){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mComminication=Communicator;
        }

        //metodo pra linkar açoes do java pro XML
        public void bindCardapio(Cardapio cardapio) {
            Glide.with(mContext)
                    .load(cardapio.getUrlFoto().toString())
                    .into(imagem_lista);

            titulo.setText(cardapio.getNome());
            descricao.setText(cardapio.getDescricao());
            preco.setText("R$ " + cardapio.getPreco().toString());
            imagem_lista.setOnClickListener(new View.OnClickListener() {
                @Override //passando pro fragment na interface
                public void onClick(View view) {
                    mComminication.respond(getAdapterPosition(),arrayCardapio
                            .get(getAdapterPosition()).getNome(),arrayCardapio
                            .get(getAdapterPosition()).getDescricao(), arrayCardapio
                            .get(getAdapterPosition()).getPreco().toString(), arrayCardapio
                            .get(getAdapterPosition()).getUrlFoto().toString());
                }
            });
        }
    }

}
