package com.example.newsapp.feature.fullpage.data.local

import com.example.newsapp.feature.fullpage.data.local.model.FullPageDao
import com.example.newsapp.feature.fullpage.data.local.model.FullPageEntity

class FullPageLocalSource(private val fullPageDao: FullPageDao) {

    suspend fun create(entity: FullPageEntity) {
        fullPageDao.create(entity)
    }

    suspend fun read(): List<FullPageEntity> {
        return fullPageDao.read()
    }

    suspend fun update(entity: FullPageEntity) {
        fullPageDao.create(entity)
    }

    suspend fun delete(entity: FullPageEntity) {
        fullPageDao.create(entity)
    }


}