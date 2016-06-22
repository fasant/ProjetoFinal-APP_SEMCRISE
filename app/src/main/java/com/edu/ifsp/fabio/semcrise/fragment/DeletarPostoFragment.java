package com.edu.ifsp.fabio.semcrise.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.edu.ifsp.fabio.semcrise.R;
import com.edu.ifsp.fabio.semcrise.domain.Posto;
import com.edu.ifsp.fabio.semcrise.service.PostoService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeletarPostoFragment extends Fragment {
    private Context context;
    private Posto posto = new Posto();

    public DeletarPostoFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deletar_posto, container, false);
        Button btGravarDepartamento = (Button) view.findViewById(R.id.btDeletarPosto);
        btGravarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarPosto();
            }
        });

        context = container.getContext();

        return view;
    }

    private void deletarPosto() {
        String baseURL = "http://192.168.1.102:9090/";

        EditText eIdPosto = (EditText) getView().findViewById(R.id.eIdPosto);
        Long id = Long.parseLong(eIdPosto.getText().toString());


//Debug
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(httpClient.build())
                .build();

        PostoService service = retrofit.create(PostoService.class);

        Call<Long> call = service.deletarPosto(id);

        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    }
