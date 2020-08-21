package com.yethiha.roomdatabaseview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yethiha.roomdatabaseview.R
import com.yethiha.roomdatabaseview.model.Book
import kotlinx.android.synthetic.main.iten_book.view.*

class BookAdapter: RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

   private  var books = emptyList<Book>()
    var clicklistener:ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener){
        this.clicklistener = clickListener
    }

    inner class BookViewHolder(itemview:View): RecyclerView.ViewHolder(itemview),View.OnClickListener{
        init {
            itemview.setOnClickListener(this)
        }
        lateinit var book: Book
        fun bindBook(book:Book){
            this.book = book
            itemView.txtBookName.text = book.bookName
            itemView.txtBookPrice.text = book.bookprice
        }

        override fun onClick(p0: View?) {
        clicklistener?.click(book)
        }

    }

    fun addbook(books:List<Book>){
        this.books = books
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.iten_book,parent,false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindBook(books[position])
    }
    interface ClickListener{
        fun click(book: Book)
    }
}