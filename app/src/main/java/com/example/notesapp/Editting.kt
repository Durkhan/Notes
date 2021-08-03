package com.example.notesapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_editting.*

class Editting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editting)
        intent
        updateTitle.setText(intent.getStringExtra("title"))
        updatedesc.setText(intent.getStringExtra("desc"))
    }

    fun updatefun(view: View) {
        var sqlDatabase=SqlDatabase(this)
        var listnotes=ArrayList<Note>()

        var values=ContentValues()
        values.put("Title",updateTitle.text.toString())
        values.put("Description",updatedesc.text.toString())
        sqlDatabase.Update(values,"ID=?", arrayOf(intent.getStringExtra("id")))
        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

}