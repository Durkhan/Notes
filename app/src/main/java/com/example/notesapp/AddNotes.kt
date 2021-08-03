package com.example.notesapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.add_notes.*


class AddNotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_notes)

    }

    fun addfun(view: View) {
        var sqlDatabase=SqlDatabase(this)
        var values=ContentValues()
        values.put("Title",addTitle.text.toString())
        values.put("Description",adddesc.text.toString())
        var Id=sqlDatabase.Insert(values)
        if (Id>0){
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"notes add",Toast.LENGTH_LONG).show()

        }
        else
            Toast.makeText(this,"notes cant add",Toast.LENGTH_LONG).show()

    }
}