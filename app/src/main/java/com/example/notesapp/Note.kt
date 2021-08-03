package com.example.notesapp

class Note {
    var nodesid:Int?=null
    var nodesname:String?=null
    var nodesdesc:String?=null

    constructor(nodesid:Int,nodesname:String,nodesdesc:String){
        this.nodesid=nodesid
        this.nodesdesc=nodesdesc
        this.nodesname=nodesname
    }
}