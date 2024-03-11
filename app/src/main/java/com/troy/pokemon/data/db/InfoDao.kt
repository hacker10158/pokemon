package com.troy.pokemon.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InfoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(infoEntity: InfoEntity)

    @Query("SELECT * from info")
    suspend fun getAllInfo(): List<InfoEntity>
}
