package com.troy.pokemon.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface InfoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(infoEntity: InfoEntity)

    @Update
    suspend fun update(infoEntity: InfoEntity)

    @Delete
    suspend fun delete(infoEntity: InfoEntity)

    @Query("SELECT * from info")
    suspend fun getAllInfo(): List<InfoEntity>
}
