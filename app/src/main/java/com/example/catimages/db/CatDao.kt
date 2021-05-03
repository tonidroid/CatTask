package com.example.catimages.db


import androidx.room.*
import com.example.catimages.models.Cat

@Dao
interface CatDao {

    @Query("SELECT * from cats")
    fun getAll(): List<Cat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cat: Cat)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cats: List<Cat>?)

    @Update //use for updateReport
    fun update(cat: Cat)

    @Query("DELETE from cats")
    fun deleteAll()

}