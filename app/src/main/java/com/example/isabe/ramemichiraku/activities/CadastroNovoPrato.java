package com.example.isabe.ramemichiraku.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.isabe.ramemichiraku.R;
import com.example.isabe.ramemichiraku.model.Cardapio;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CadastroNovoPrato extends AppCompatActivity {

    @BindView(R.id.txt_novo_nome_prato) TextInputEditText nomePrato;
    @BindView(R.id.txt_novo_descricao_prato) TextInputEditText  descricaoPrato;
    @BindView(R.id.txt_novo_preco_cardapio) TextInputEditText  precoPrato;
    @BindView(R.id.txt_novo_url_prato) TextInputEditText  urlPrato;
    @BindView(R.id.btn_novo_cadastrar_prato) Button btnCadastro;

    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_prato);

        ButterKnife.bind(this); //Importante


        //escuta para clique do botão
        btnCadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                ChecaCampos(nomePrato);
                ChecaCampos(descricaoPrato);
                ChecaCampos(precoPrato);
                ChecaCampos(urlPrato);
                // opens "ichirakurealm.realm"
                Realm realm = Realm.getDefaultInstance();

                try {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Cardapio cardapio = realm.createObject(Cardapio.class, UUID.randomUUID().toString());
                            cardapio.setNome(String.valueOf(nomePrato.getText()));
                            cardapio.setDescricao(String.valueOf(descricaoPrato.getText()));
                            cardapio.setPreco(Double.valueOf(precoPrato.getText().toString()));
                            cardapio.setUrlFoto(String.valueOf(urlPrato.getText()));
                        }
                    });

                } finally {
                    if(!realm.isClosed()){
                        CadastroNovoPrato.this.finish();

                    }
                }
            }

        });

    }


    //valida os campos de texto (parou de pegar após realm)
    public void ChecaCampos(TextInputEditText texto){
        nome = texto.getText().toString();

        if(nome.isEmpty()){
            btnCadastro.setClickable(false);
            Toast.makeText(CadastroNovoPrato.this, "Todos os campos são obrigatórios.", Toast.LENGTH_SHORT).show();
        }else{
            btnCadastro.setClickable(true);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(CadastroNovoPrato.this, MainActivity.class);
        CadastroNovoPrato.this.startActivity(intent);
    }
}
