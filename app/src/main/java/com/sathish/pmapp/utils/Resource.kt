package com.sathish.pmapp.utils

import android.content.Context

class Resource(val context: Context){
    fun getString(resourceID: Int):String = context.getString(resourceID)

}