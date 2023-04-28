package com.example.newsapp.feature.fullpage.data.local.model

import androidx.room.*
import com.example.newsapp.feature.fullpage.di.FULL_PAGE_TABLE

@Dao
interface FullPageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: FullPageEntity)

    @Query("SELECT * FROM $FULL_PAGE_TABLE")
    suspend fun read(): List<FullPageEntity>

    @Update
    suspend fun update(entity: FullPageEntity)

    @Delete
    suspend fun delete(entity: FullPageEntity)

    @Query("DELETE FROM $FULL_PAGE_TABLE")
    suspend fun wipeData()
}