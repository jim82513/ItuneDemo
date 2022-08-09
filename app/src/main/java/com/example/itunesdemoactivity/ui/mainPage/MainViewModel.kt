package com.example.itunesdemoactivity.ui.mainPage

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesdemoactivity.data.ItunesInfo
import com.example.itunesdemoactivity.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var filterWord = ObservableField("")

    private val _res = MutableLiveData<List<ItunesInfo>>()
    val res : LiveData<List<ItunesInfo>>
        get() = _res


    fun getItunesInfo(searching:String)  = viewModelScope.launch {
        repository.getEmployee(searching).let {
            if (it.isSuccessful){
               _res.postValue(it.body()!!.infoList)
            }else{
                Log.d("MainViewModel","Failed")
            }
        }
    }
}