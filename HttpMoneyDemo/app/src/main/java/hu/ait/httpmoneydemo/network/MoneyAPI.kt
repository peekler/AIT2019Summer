package hu.ait.httpmoneydemo.network

import hu.ait.httpmoneydemo.data.Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.exchangeratesapi.io

// /latest
// ?
// base=USD


interface MoneyAPI {

    @GET("/latest")
    fun getRates(@Query("base") base: String): Call<Base>
}