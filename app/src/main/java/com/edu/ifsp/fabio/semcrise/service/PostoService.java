package com.edu.ifsp.fabio.semcrise.service;

import com.edu.ifsp.fabio.semcrise.domain.Posto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostoService {
    @GET("posto/list")
    Call<List<Posto>> listarPosto();
    @GET("posto/list/ordemId")
    Call<List<Posto>> listarPostoId();
    @GET("posto/list/ordemPreco")
    Call<List<Posto>> listarPostoPreco();
    @GET("posto/list/ordemLocal")
    Call<List<Posto>> listarPostoLocal();

    @GET("posto/delete/{id}")
    Call<Long> deletarPosto(long id);

    @POST("posto/new")
    Call<Posto> novoPosto(@Body Posto posto);
}
