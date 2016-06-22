package com.edu.ifsp.fabio.semcrise.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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


public class InserirPostoFragment extends Fragment {
    private Context context;
    private Posto posto = new Posto();

    public InserirPostoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inserir, container, false);
        Button btGravarDepartamento = (Button) view.findViewById(R.id.btSalvarPosto);
        btGravarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPosto();
            }
        });

        // Listener Alcool
        RadioButton radioButton = (RadioButton) view.findViewById(R.id.radioButtonAlcool);

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        // Listener para Gasolina
        RadioButton radioButtonGas = (RadioButton) view.findViewById(R.id.radioButtonGasolina);

        radioButtonGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        context = container.getContext();

        return view;
    }

    public void onRadioButtonClicked(View view) {

        RadioButton radioButton = (RadioButton) getView().findViewById(view.getId());
        Log.d("Radio", radioButton.getText().toString());
        posto.setProduto(radioButton.getText().toString());

    }

    private void salvarPosto() {
        String baseURL = "http://192.168.1.102:9090/";

        EditText ePreco = (EditText) getView().findViewById(R.id.ePreco);
        EditText eNome = (EditText) getView().findViewById(R.id.eNome);
        EditText eLocal = (EditText) getView().findViewById(R.id.eLocal);


        posto.setNome(eNome.getText().toString());
        posto.setLocal(eLocal.getText().toString());
        posto.setPreco(Double.parseDouble(ePreco.getText().toString()));


//Debug
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        PostoService service = retrofit.create(PostoService.class);

        Call<Posto> call = service.novoPosto(posto);

        call.enqueue(new Callback<Posto>() {
            @Override
            public void onResponse(Call<Posto> call, Response<Posto> response) {
            }

            @Override
            public void onFailure(Call<Posto> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
