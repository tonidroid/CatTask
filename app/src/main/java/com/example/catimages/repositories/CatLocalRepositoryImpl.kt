package com.example.catimages.repositories


import com.example.catimages.db.CatDao

class CatLocalRepositoryImpl (catDao: CatDao) : CatLocalRepository {

    val catDao = catDao

    override suspend fun getLocalCats() = catDao.getAll()
}