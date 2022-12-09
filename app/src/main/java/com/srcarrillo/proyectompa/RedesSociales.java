package com.srcarrillo.proyectompa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class RedesSociales extends AppCompatActivity {

    ImageButton btn_web_mpa,btn_FB_mpa,btn_FB_ccs,btn_FB_pastor,btn_YT_mpa,btn_YT_css,btn_YT_pastor,btn_insta_mpa,btn_insta_ccs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes_sociales);

        btn_web_mpa = findViewById(R.id.btn_web_mpa);
        btn_FB_mpa = findViewById(R.id.btn_FB_mpa);
        btn_FB_ccs = findViewById(R.id.btn_FB_ccs);
        btn_FB_pastor = findViewById(R.id.btn_FB_pastor);
        btn_YT_mpa = findViewById(R.id.btn_YT_mpa);
        btn_YT_css = findViewById(R.id.btn_YT_ccs);
        btn_YT_pastor = findViewById(R.id.btn_YT_pastor);
        btn_insta_mpa = findViewById(R.id.btn_insta_mpa);
        btn_insta_ccs = findViewById(R.id.btn_insta_ccs);

        //ImageButton Open url's RRSS.
        btn_web_mpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://ministeriopuertaabierta.com");
            }
        });
        btn_FB_mpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.facebook.com/Ministerio-Puerta-Abierta-105346732184170");
            }
        });
        btn_FB_ccs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.facebook.com/CadaCorazonSanadoMPA");
            }
        });
        btn_FB_pastor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.facebook.com/profile.php?id=100042290031459");
            }
        });
        btn_YT_mpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.youtube.com/c/MinisterioPuertaAbiertaChile");
            }
        });
        btn_YT_css.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.youtube.com/channel/UC7aza5691yAxaxHZbG5J5tA");
            }
        });
        btn_YT_pastor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.youtube.com/channel/UC0r6xkzAPxXQ-Fa1AsxwQmw");
            }
        });
        btn_insta_mpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.instagram.com/ministerio.puerta.abierta/");
            }
        });
        btn_insta_ccs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.instagram.com/cadacorazonsanado/");
            }
        });


    }

    private void gotourl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void volver_principal(View view){
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }

}