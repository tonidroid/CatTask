package com.example.catimages.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catimages.models.Cat

@Database(entities = [Cat::class], version = 1, exportSchema = false)
abstract class CatDatabase : RoomDatabase() {
    abstract fun taskDao(): CatDao

}