package com.example.newsappproject.data.source.local

import androidx.room.TypeConverter
import com.example.newsappproject.data.model.ResponseTopHeadLine

class TypeConvertor {

    @TypeConverter
    fun convertSourceType(source: ResponseTopHeadLine.Article.Source): String? {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): ResponseTopHeadLine.Article.Source {
        return ResponseTopHeadLine.Article.Source(name)
    }
}