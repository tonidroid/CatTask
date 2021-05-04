package com.example.catimages.db


import androidx.room.*
import com.example.catimages.models.Cat

@Dao
interface CatDao {

    @Query("SELECT * from cats")
    suspend fun getAll(): List<Cat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: Cat)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cats: List<Cat>?)

    @Update //use for updateReport
    suspend fun update(cat: Cat)

    @Query("DELETE from cats")
    suspend fun deleteAll()

    @Query("DELETE from cats where id = :id")
    suspend fun deleteById(id: String)

}