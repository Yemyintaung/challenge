package com.example.myapplication.exception

object ErrorMessageFactory {
    fun create(throwable: Throwable): String {
        if(throwable is HomeException){
            if(throwable is EmptyAccountNameException) return "Username is empty"
            if(throwable is EmptyAccountNameException) return "Username is empty"
        }
        return throwable.message.orEmpty()
    }
}