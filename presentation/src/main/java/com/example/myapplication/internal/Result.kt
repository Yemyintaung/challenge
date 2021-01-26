package com.example.myapplication.internal

public sealed class Result<out T: Any> {
    public object Loading: Result<Nothing>()
    public data class Error(val throwable: Throwable): Result<Nothing>()
    public data class Content<out T: Any>(val content: T): Result<T>()
}