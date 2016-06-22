package com.edu.ifsp.fabio.semcrise.presenter;

import android.content.Context;
import android.widget.ListView;

import com.edu.ifsp.fabio.semcrise.adapter.PostoAdapter;
import com.edu.ifsp.fabio.semcrise.domain.Posto;
import com.edu.ifsp.fabio.semcrise.service.PostoService;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListarPostosPresenter {
    public static String baseURL = "http://192.168.1.102:9090/";

    public void listarPostos(final Context context, final ListView listView, final int ordem ){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostoService service = retrofit.create(PostoService.class);
        Call<List<Posto>> call;
        if(ordem == 0) { call = service.listarPostoId();}
        else if (ordem == 1){ call = service.listarPostoPreco(); }
        else if (ordem == 2){call = service.listarPostoLocal();}
        else{ call = service.listarPosto();}

        call.enqueue(new Callback<List<Posto>>() {
            @Override
            public void onResponse(Call<List<Posto>> call, Response<List<Posto>> response) {
                List<Posto> list = response.body();

                listView.setAdapter(new PostoAdapter(context, list));
            }

            @Override
            public void onFailure(Call<List<Posto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
