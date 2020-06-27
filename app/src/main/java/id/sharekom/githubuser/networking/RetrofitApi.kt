package id.sharekom.githubuser.networking

import id.sharekom.githubuser.models.profile.Profile
import id.sharekom.githubuser.models.searchdata.SearchData
import id.sharekom.githubuser.models.searchdata.SearchDataItem
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApi {
    @GET("search/users")
    fun getSearchData(@Query("q") username:String, @Header("Authorization") token:String):Call<SearchData>

    @GET("users/{username}")
    fun getProfile(@Path("username") username: String, @Header("Authorization") token:String):Call<Profile>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String, @Header("Authorization") token:String):Call<List<SearchDataItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String, @Header("Authorization") token:String):Call<List<SearchDataItem>>
}