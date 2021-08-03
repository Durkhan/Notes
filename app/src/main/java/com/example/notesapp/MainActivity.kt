 package com.example.notesapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listviewitem.view.*

class MainActivity : AppCompatActivity() {
    var listnodes=ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent

       LoadQuery("%")



    }

    fun LoadQuery(title: String) {
            var sqlDatabase=SqlDatabase(this)
            var cursor=sqlDatabase.Query()
            listnodes.clear()
        if (cursor.moveToFirst()) {
            do {

                var id = cursor.getInt(cursor.getColumnIndex("ID"))
                var title = cursor.getString(cursor.getColumnIndex("Title"))
                var desc = cursor.getString(cursor.getColumnIndex("Description"))
                listnodes.add(Note(id, title, desc))

            } while (cursor.moveToNext())

        }
            var myAdapter = myAdapter(this,listnodes)
            nodes.adapter = myAdapter




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val sview=menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val smanager=getSystemService(SEARCH_SERVICE) as SearchManager
        sview.setSearchableInfo(smanager.getSearchableInfo(componentName))
        sview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
               Toast.makeText(applicationContext,p0,Toast.LENGTH_LONG).show()
                LoadQuery("%"+ p0 +"%")
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addmenu->{
                var intent=Intent(this,AddNotes::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
    inner class myAdapter:BaseAdapter{
        var litnodesAdabter=ArrayList<Note>()
        var context:Context?=null
        constructor(context: Context,litnodesAdabter:ArrayList<Note>):super(){
            this.litnodesAdabter=litnodesAdabter
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
              var myview=layoutInflater.inflate(R.layout.listviewitem,null)
              var node=litnodesAdabter[p0]
              myview.title.text=node.nodesname
              myview.desc.text=node.nodesdesc
              myview.delete.setOnClickListener{
                  var sqlDatabase=SqlDatabase(context!!)
                  sqlDatabase.Delete("ID=?", arrayOf(node.nodesid.toString()))
                  LoadQuery("%")

              }
            myview.edit.setOnClickListener{
                var intent=Intent(context,Editting::class.java)
                intent.putExtra("title",myview.title.text)
                intent.putExtra("desc",myview.desc.text)
                intent.putExtra("id",node.nodesid.toString())
                startActivity(intent)

            }

            return myview

        }
        override fun getCount(): Int {
           return litnodesAdabter.size
        }

        override fun getItem(p0: Int): Any {
           return litnodesAdabter[p0]
        }

        override fun getItemId(p0: Int): Long {
           return p0.toLong()
        }



    }
}