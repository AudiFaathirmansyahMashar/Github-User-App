package id.sharekom.githubuser.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.sharekom.githubuser.models.searchdata.SearchDataItem
import id.sharekom.githubuser.repository.FollowingRepository

class FollowingViewModel : ViewModel() {
    private var data: MutableLiveData<List<SearchDataItem>> = MutableLiveData()

    fun setFollowing(username:String){
        FollowingRepository.init(username)
        data = FollowingRepository.getFollowing()
    }

    fun getFollowing(): LiveData<List<SearchDataItem>> {
        return data
    }
}