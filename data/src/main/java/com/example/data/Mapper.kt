package com.example.data

interface Mapper<I, O> {
    fun map(input: I): O
}