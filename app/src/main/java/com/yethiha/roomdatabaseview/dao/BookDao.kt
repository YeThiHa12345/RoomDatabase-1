package com.yethiha.roomdatabaseview.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yethiha.roomdatabaseview.model.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table ORDER BY book_name ASC")
    fun getAllBook(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (book: Book)

    @Query("DELETE FROM book_table")
    suspend fun delete()

    @Query("DELETE FROM book_table WHERE book_name= :name")
    suspend fun  deleteItem(name:String)
    @Query("UPDATE book_table SET book_name =:updateName WHERE book_name =:name")
    suspend fun updateItem(updateName:String,name: String)

}