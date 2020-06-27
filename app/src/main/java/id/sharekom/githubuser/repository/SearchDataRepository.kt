package id.sharekom.githubuser.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.sharekom.githubuser.helpers.Values
import id.sharekom.githubuser.models.searchdata.SearchData
import id.sharekom.githubuser.networking.RetrofitApi
import id.sharekom.githubuser.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDataRepository {
    companion object{
        private var dataSet:MutableLiveData<SearchData> = MutableLiveData()

        fun init(username:String){
            val api:RetrofitApi = RetrofitClient.retrofit.create(RetrofitApi::class.java)

            val call:Call<SearchData> = api.getSearchData(username, Values.TOKEN)

            call.enqueue(object : Callback<SearchData>{
                override fun onFailure(call: Call<SearchData>, t: Throwable) {
                    Log.e("DATA", t.message.toString())
                }

                override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                    val searchData:SearchData = response.body()!!

                    dataSet.value = searchData
                }

            })
        }

        fun getSearchData(): MutableLiveData<SearchData>{
            return dataSet
        }
    }
}