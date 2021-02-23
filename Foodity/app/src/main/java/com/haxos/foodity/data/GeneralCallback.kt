package com.haxos.foodity.data


interface GeneralCallback {
    fun onSuccess()
    fun onError(error: Int)
}