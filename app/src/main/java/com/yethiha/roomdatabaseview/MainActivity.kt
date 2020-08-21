package com.yethiha.roomdatabaseview

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yethiha.roomdatabaseview.ViewModel.BookViewModel
import com.yethiha.roomdatabaseview.adapter.BookAdapter
import com.yethiha.roomdatabaseview.model.Book
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_update.view.*


class MainActivity : AppCompatActivity(),BookAdapter.ClickListener {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookAdapter: BookAdapter
    private val addBookResultcode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        bookAdapter = BookAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookAdapter
        }
        bookAdapter.setOnClickListener(this)
        bookViewModel.allBook.observe(this, Observer {
            Log.d("books", it.toString())
            bookAdapter.addbook(it)

        })

        btnAdd.setOnClickListener {
            val intent = Intent(this, Add_Book::class.java)
            startActivityForResult(intent, addBookResultcode)
        }
        btnDelete.setOnClickListener {
            bookViewModel.delete()
        }
        bookAdapter.setOnClickListener(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == addBookResultcode && resultCode == Activity.RESULT_OK) {
            /*data?.getStringExtra(Add_Book.EXTRA_REPLY)?.let {

             val book = Book(it)


             bookViewModel.insert(book)
         }*/
            var bookName = data?.getStringExtra(Add_Book.EXTRA_REPLY)
            var bookprice = data?.getStringExtra(Add_Book.EXTRA_KEY)

            val book = Book(bookName!!, bookprice!!)
            bookViewModel.insert(book)
        }


    }


    override fun click(book: Book)
    {
        Log.d("click", book.toString())

        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Delete item")
            setMessage("Are you sure to delete")
            setIcon(android.R.drawable.ic_dialog_alert)
            setPositiveButton("Yes")
            { dialogInterface, i ->
                bookViewModel.deleteItem(book.bookName)
            }
            setNegativeButton("No")
            { dialogInterface, i ->
                Toast.makeText(applicationContext, "Delete cancel", Toast.LENGTH_LONG).show()
            }
            setNeutralButton("Update") { dialogInterface, i ->
                val updateBuilder = AlertDialog.Builder(context)
                val dialogLayout = layoutInflater.inflate(R.layout.dialog_update, null)
                updateBuilder.apply{
                    setTitle("Update")
                    setView(dialogLayout)
                    setPositiveButton("Ok")
                    { dialogInterface, i ->
                        val updateText = dialogLayout.edtUpdateName.text.toString()
                        bookViewModel.updateItem(updateText, book.bookName)
                    }
                }
                val updateDialog: AlertDialog = updateBuilder.create()
                updateDialog.show()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}