package com.yethiha.roomdatabaseview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
class Book (

    @PrimaryKey
    @ColumnInfo(name = "book_name")
    val bookName:String,

   @ColumnInfo(name = "book_price")
   val bookprice:String

)