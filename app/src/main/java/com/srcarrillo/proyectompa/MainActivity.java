package com.srcarrillo.proyectompa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static int slideUp = R.anim.slide_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.actionbar_title_layout);

        setContentView(R.layout.activity_main);

        SwitchMaterial switchBtn = findViewById(R.id.switch1);

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //buttonView.setText("Oscuro");
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //buttonView.setText("Claro");
                }
            }
        });
        sacarJsonInfoVideo();
    }

    public void open_tv(View view){
        Intent tv = new Intent(this, Video.class);
        startActivity(tv);
    }
    public void open_rrl(View view){
        Intent rrl = new Intent(this, Radio.class);
        startActivity(rrl);
    }
    public void open_biblia(View view){
        Intent biblia = new Intent(this, Biblia.class);
        startActivity(biblia);
    }
    public void open_rrss(View view){
        Intent rrss = new Intent(this, RedesSociales.class);
        startActivity(rrss);
    }

    public void sacarJsonInfoVideo(){

        String url = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyBfUh189fQbDBq-qTRnysEYrilrZ5Tgnjo&channelId=UC-dqqDXVY4I4kXIiuT2M0Iw&part=snippet,id&order=date&maxResults=1";
        RequestQueue request = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString(0));

                    JSONArray jsonArray=jsonObject.getJSONArray("items");

                    String idVideo=jsonArray.getJSONObject(0).getJSONObject("id").getString("videoId");
                    //video.setIdVideo(idVideo);


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Holi", Toast.LENGTH_LONG).show();
                }
                System.out.println(" Video que se va a reproducir YA: ");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.add(jsonObjectRequest);
    }
}