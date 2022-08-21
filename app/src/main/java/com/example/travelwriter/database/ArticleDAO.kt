package com.example.travelwriter.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDAO {
    @Query("SELECT * FROM drafts WHERE id = :id")
    fun getArticle(id: Int): Article?

    @Query("SELECT * FROM drafts ORDER BY id DESC")
    fun getAllArticles(): LiveData<List<Article>>

    @Insert
    fun insertArticle(article: Article)

    @Update
    fun updateArticle(article: Article)

    @Delete
    fun deleteArticle(article: Article)

    @Transaction
    suspend fun deleteArticleWithId(articleId: Int) {
        val article = getArticle(articleId)
        if(article != null) {
            deleteArticle(article)
        }
    }
}