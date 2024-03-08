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
    suspend fun insert(info: Info)

    @Update
    suspend fun update(info: Info)

    @Delete
    suspend fun delete(info: Info)

    @Query("SELECT * from info")
    suspend fun getAllInfo(): List<Info>
}
