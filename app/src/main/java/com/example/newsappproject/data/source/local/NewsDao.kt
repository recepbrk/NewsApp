package com.example.newsappproject.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsappproject.data.model.ResponseTopHeadLine

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: ResponseTopHeadLine.Article): Long

    @Query("SELECT * FROM articleTable")
    fun getFavArticles(): LiveData<List<ResponseTopHeadLine.Article>>

    @Query("SELECT title FROM articleTable")
    fun getArticleTitles(): List<String>

    @Delete
    suspend fun deleteArticle(article: ResponseTopHeadLine.Article)
}