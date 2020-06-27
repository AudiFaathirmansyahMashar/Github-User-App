package id.sharekom.githubuser.networking

import id.sharekom.githubuser.helpers.Values
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Values.MAIN_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}