package com.example.tempmonitor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/pomiary") // Dokładnie taka ścieżka jak w @RequestMapping w Springu
    Call<List<Dane>> getWszystkiePomiary();
}
