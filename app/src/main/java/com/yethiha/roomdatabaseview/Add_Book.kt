package com.yethiha.roomdatabaseview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_add__book.*

class Add_Book : AppCompatActivity() {
    lateinit var bookName: String
    lateinit var bookPrice: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__book)

        btnsave.setOnClickListener {
            val replyIntent = Intent()
            bookName = edtbookname.text.toString()
            bookPrice = edtbookprice.text.toString()
            if (TextUtils.isEmpty(edtbookname.text) && TextUtils.isEmpty(edtbookprice.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                Log.d("book", bookName)
                replyIntent.putExtra(EXTRA_REPLY, bookName)
                replyIntent.putExtra(EXTRA_KEY,bookPrice)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

    companion object {
        const val EXTRA_REPLY = "REPLY_BOOK"
        const val EXTRA_KEY = "REPLY_PRICE"
    }
}
