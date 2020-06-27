package id.sharekom.githubuser.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.sharekom.githubuser.helpers.Values
import id.sharekom.githubuser.models.searchdata.SearchDataItem
import id.sharekom.githubuser.networking.RetrofitApi
import id.sharekom.githubuser.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingRepository {
    companion object{
        private val data: MutableLiveData<List<SearchDataItem>> = MutableLiveData()

        fun init(username: String){
            val api: RetrofitApi = RetrofitClient.retrofit.create(RetrofitApi::class.java)

            val call: Call<List<SearchDataItem>> = api.getFollowing(username, Values.TOKEN)

            call.enqueue(object : Callback<List<SearchDataItem>> {
                override fun onFailure(call: Call<List<SearchDataItem>>, t: Throwable) {
                    Log.e("Following", t.message.toString())
                }

                override fun onResponse(call: Call<List<SearchDataItem>>, response: Response<List<SearchDataItem>>) {
                    data.value = response.body()
                }

            })
        }

        fun getFollowing(): MutableLiveData<List<SearchDataItem>> {
            return data
        }
    }
}