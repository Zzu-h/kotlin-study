package com.zzuh.filot_shoppings.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryViewModel:ViewModel() {
    var tempData = MutableLiveData<String>()
    fun setData(value: String): Unit = tempData.postValue(value)
}