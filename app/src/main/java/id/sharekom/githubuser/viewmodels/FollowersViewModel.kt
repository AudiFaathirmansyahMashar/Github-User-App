package id.sharekom.githubuser.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.sharekom.githubuser.models.searchdata.SearchDataItem
import id.sharekom.githubuser.repository.FollowersRepository

class FollowersViewModel : ViewModel(){
    private var data: MutableLiveData<List<SearchDataItem>> = MutableLiveData()

    fun setFollowers(username:String){
        FollowersRepository.init(username)
        data = FollowersRepository.getFollowers()
    }

    fun getFollowers(): LiveData<List<SearchDataItem>> {
        return data
    }
}