package com.sathish.pmapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



abstract class BaseViewModel : ViewModel() {

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    val successMessage: MutableLiveData<String> = MutableLiveData()
}
