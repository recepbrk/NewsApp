package com.example.newsappproject.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class ResponseTopHeadLine(
    @ColumnInfo(name = "articles")
    val articles: List<Article>,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "totalResults")
    val totalResults: Int
) {
    @Parcelize
    @Entity(tableName = "articleTable")
    data class Article(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int = 0,

        @ColumnInfo(name = "author")
        val author: String?,

        @ColumnInfo(name = "content")
        val content: String?,

        @ColumnInfo(name = "description")
        val description: String?,

        @ColumnInfo(name = "publishedAt")
        val publishedAt: String?,

        @ColumnInfo(name = "source")
        val source: Source?,

        @ColumnInfo(name = "title")
        val title: String?,

        @ColumnInfo(name = "url")
        val url: String,

        @ColumnInfo(name = "urlToImage")
        val urlToImage: String?,

        val isFav: Boolean? = false

    ) : Parcelable {
        override fun hashCode(): Int {
            var result = id.hashCode()
            if (url.isNullOrEmpty()) {
                result = 31 * result + url.hashCode()
            }
            return result
        }

        @Parcelize
        data class Source(
            @ColumnInfo(name = "name")
            val name: String?
        ) : Parcelable
    }
}