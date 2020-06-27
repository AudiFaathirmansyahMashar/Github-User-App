package id.sharekom.githubuser.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.sharekom.githubuser.models.searchdata.SearchData
import id.sharekom.githubuser.repository.SearchDataRepository

class SearchDataViewModel : ViewModel() {
    private var searchData:MutableLiveData<SearchData> = MutableLiveData()

    fun setSearchData(username:String){
        SearchDataRepository.init(username)
        searchData = SearchDataRepository.getSearchData()
    }

    fun getSearchData():LiveData<SearchData>{
        return searchData
    }
}