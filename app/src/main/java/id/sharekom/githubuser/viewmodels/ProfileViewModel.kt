package id.sharekom.githubuser.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.sharekom.githubuser.models.profile.Profile
import id.sharekom.githubuser.repository.ProfileRepository

class ProfileViewModel : ViewModel() {
    private var data:MutableLiveData<Profile> = MutableLiveData()

    fun setProfile(username:String){
        ProfileRepository.init(username)
        data = ProfileRepository.getProfile()
    }

    fun getProfile():LiveData<Profile>{
        return data
    }
}