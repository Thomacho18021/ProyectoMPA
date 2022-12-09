package com.srcarrillo.proyectompa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Video extends AppCompatActivity {

    private TextView tvIdVideo,tvNombreVideo,tvDescripcionVideo;
    private Button btn1;
    private RequestQueue rq;
    private String idVIdeoYT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        tvIdVideo = findViewById(R.id.tvIdVideo);
        tvIdVideo.setText("");
        //idVIdeoYT = getIntent().getExtras().getString("idVIdeoYoutube");
        //sacarJsonInfoVideo();
        //String idVideo = tvIdVideo.getText().toString();
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);


        tvNombreVideo = findViewById(R.id.tvNombreVideo);
        rq = Volley.newRequestQueue(this);






        //Uri uri = Uri.parse("https://www.youtube.com/watch?v="+idVideo);
        //System.out.print(idVIdeoYT);

        //getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(idVIdeoYT,0);
                //youTubePlayer.loadVideo(tvIdVideo.getText().toString(), 0);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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

                    String tituloVideo=jsonArray.getJSONObject(0).getJSONObject("snippet").getString("title");
                    //video.setTitulo(tituloVideo);

                    String fechaSubida=jsonArray.getJSONObject(0).getJSONObject("snippet").getString("publishedAt");
                    String SfechaSubida=fechaSubida.substring(0, 10);
                    //video.setDiaSubida(SfechaSubida);

                    String nombreCanal=jsonArray.getJSONObject(0).getJSONObject("snippet").getString("channelTitle");
                    //video.setNombreCanal(nombreCanal);

                    String miniatura=jsonArray.getJSONObject(0).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("default").getString("url");
                    //video.setMiniatura(miniatura);



                    System.out.println(idVideo+"\n"+tituloVideo+"\n"+nombreCanal+"\n"+miniatura);

                    tvIdVideo.setText(idVideo);
                    tvNombreVideo.setText(tituloVideo);



                    /*VideoView videoView = findViewById(R.id.videoView);
                    videoView.setVideoPath("https://www.youtube.com/watch?v="+idVideo);
                    videoView.start();*/


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
