package com.example.newsappproject.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappproject.data.model.ResponseTopHeadLine

@Database(entities = [ResponseTopHeadLine.Article::class], version = 2, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getArticleFromDao(): NewsDao
}