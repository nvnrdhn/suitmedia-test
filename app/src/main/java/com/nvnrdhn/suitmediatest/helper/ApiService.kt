package com.nvnrdhn.suitmediatest.helper

import com.nvnrdhn.suitmediatest.model.Model
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("596dec7f0f000023032b8017")
    fun getGuests(): Observable<List<Model.Guest>>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.mocky.io/v2/")
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}