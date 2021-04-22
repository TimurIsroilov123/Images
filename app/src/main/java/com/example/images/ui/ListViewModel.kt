package com.example.images.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.images.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val _liveData = MutableLiveData<List<String>>()
    val liveData get() = _liveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _liveData.postValue(Repository.loadImages())
        }
    }

    fun updateImages(view: SwipeRefreshLayout) {
        viewModelScope.launch(Dispatchers.IO) {
            _liveData.postValue(Repository.loadImages())
            view.isRefreshing = false
        }
    }
}

class ListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel() as T
    }
}