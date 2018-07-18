package com.example.isabe.ramemichiraku.activities;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.isabe.ramemichiraku.R;
import com.example.isabe.ramemichiraku.fragments.ListaCardapioFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_cadastro_prato) ImageView btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fazendo troca de fragments
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layout_troca_fragment, new ListaCardapioFragment());
        ft.commit();

        ButterKnife.bind(this);

        //fazendo troca de activity
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CadastroNovoPrato.class);
                startActivity(i);

            }
        });
    }
}
