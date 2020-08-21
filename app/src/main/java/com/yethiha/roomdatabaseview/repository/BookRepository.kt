package com.yethiha.roomdatabaseview.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.yethiha.roomdatabaseview.dao.BookDao
import com.yethiha.roomdatabaseview.model.Book

class BookRepository (private  val bookDao: BookDao){
    val allBook: LiveData<List<Book>> = bookDao.getAllBook()

    suspend fun insert(book: Book){
        Log.d("bookrepo",book.toString())
        bookDao.insert(book)
    }
   suspend fun delete(){
        bookDao.delete()
    }

    suspend fun deleteItem(name:String){
        bookDao.deleteItem(name)
    }

    suspend fun updateItem(updateName:String,name:String){
        bookDao.updateItem(updateName,name)
    }
}