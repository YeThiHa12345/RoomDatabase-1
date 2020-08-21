package com.yethiha.roomdatabaseview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yethiha.roomdatabaseview.dao.BookDao
import com.yethiha.roomdatabaseview.model.Book

@Database (entities = arrayOf(Book::class),version = 2)
abstract class BookDatabase :RoomDatabase(){
    abstract fun bookdao():BookDao


    companion object{
        private var INSTANCE:BookDatabase? = null

        fun getDatabase(context: Context):BookDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,BookDatabase::class.java,"book_database").build()

                INSTANCE = instance
                return instance
            }



        }
    }
}