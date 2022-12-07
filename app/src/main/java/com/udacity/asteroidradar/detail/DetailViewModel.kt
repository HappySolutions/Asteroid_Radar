package com.udacity.asteroidradar.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel: ViewModel() {

    private val _viewExplainDialog = MutableLiveData<Boolean>()
    val displayExplanationDialog: LiveData<Boolean>
        get() = _viewExplainDialog

    fun viewExplaination(){
        _viewExplainDialog.value = true
    }

    fun closeExplanationDialog(){
        _viewExplainDialog.value = false
    }
}