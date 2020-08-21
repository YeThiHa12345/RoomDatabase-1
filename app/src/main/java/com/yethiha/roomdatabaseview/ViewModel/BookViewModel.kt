package com.yethiha.roomdatabaseview.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.yethiha.roomdatabaseview.database.BookDatabase
import com.yethiha.roomdatabaseview.model.Book
import com.yethiha.roomdatabaseview.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(application: Application):AndroidViewModel(application) {

    private  val respository : BookRepository
    val allBook: LiveData<List<Book>>
    init {
        val bookDao = BookDatabase.getDatabase(application).bookdao()
        respository = BookRepository(bookDao)
        allBook = respository.allBook
    }
    fun insert(book:Book)=viewModelScope.launch {
        Log.d("bookview",book.toString())
        respository.insert(book)
    }


    fun delete() = viewModelScope.launch {
        respository.delete()
    }

    fun deleteItem(name:String) = viewModelScope.launch {
        respository.deleteItem(name)
    }

    fun updateItem (updateName:String, name: String)= viewModelScope.launch {
        respository.updateItem(updateName,name)
    }


}