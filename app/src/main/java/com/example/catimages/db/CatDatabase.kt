package com.example.catimages.db


import android.content.Context
import androidx.room.*
import com.example.catimages.models.Cat
import com.example.catimages.utils.BreedsConverters
import com.example.catimages.utils.CategoriesConverters


@Database(entities = [Cat::class], version = 1, exportSchema = false)
@TypeConverters(*[CategoriesConverters::class, BreedsConverters::class])
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {
        @Volatile private var instance: CatDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CatDatabase::class.java, "cat_database"
        )
            .build()
    }
}
