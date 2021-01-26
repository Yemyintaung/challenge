package com.example.myapplication.exception

open class HomeException : Exception()
class EmptyAccountNameException : HomeException()
class EmptyAccountPasswordException : HomeException()
