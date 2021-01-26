package com.example.data.mapper

abstract class BaseDataMapper<M, DM> {
    abstract fun transform(dataModel: DM): M

    fun transform(dataModels: List<DM>): List<M> {
        val domainModelList: MutableList<M> = ArrayList(dataModels.size)
        for (dataModel in dataModels) {
            domainModelList.add(transform(dataModel))
        }
        return domainModelList
    }
}