package com.example.data.network

import com.example.data.model.currency.Quotes
import com.google.gson.InstanceCreator
import java.lang.reflect.Type

class CurrencyContextInstanceCreator(): InstanceCreator<Quotes> {
    override fun createInstance(type: Type?): Quotes {
        return Quotes()
    }
}
