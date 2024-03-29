package com.yonasoft.jadedictionary.data.db.word

import androidx.room.Dao
import androidx.room.Query
import com.yonasoft.jadedictionary.data.models.Word
import kotlinx.coroutines.flow.Flow


@Dao
interface WordDAO {
    @Query(
        """
    SELECT * FROM words 
    WHERE simplified LIKE '%' || :query || '%' OR traditional LIKE '%' || :query || '%'
    ORDER BY 
        CASE 
            WHEN simplified = :query OR traditional = :query THEN 1
            WHEN simplified LIKE '%' || :query || '%' OR traditional LIKE '%' || :query || '%' THEN 2
            ELSE 3 
        END, 
        LENGTH(simplified) ASC, LENGTH(traditional) ASC
"""
    )
    fun searchWordByHanZi(query: String): Flow<List<Word>>

    @Query(
        """
    SELECT *, 
        CASE 
            WHEN definition = :query THEN 3 
            WHEN definition LIKE :query || '%' THEN 2 
            WHEN definition LIKE '%' || :query || '%' THEN 1 
            ELSE 0 
        END as relevance 
    FROM words 
    WHERE definition LIKE '%' || :query || '%' 
    ORDER BY relevance DESC, LENGTH(definition) ASC
"""
    )
    fun searchWordByDefinition(query: String): Flow<List<Word>>

    @Query("""
        SELECT *, 
            CASE 
                WHEN pinyin = :query THEN 1 
                ELSE 0 
            END as relevance 
        FROM words 
        WHERE pinyin LIKE '%' || :query || '%' 
        ORDER BY relevance DESC, LENGTH(pinyin) ASC
    """)
    fun searchWordByPinYin(query: String): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE id = :id")
    suspend fun getWordById(id: Long): Word?

}