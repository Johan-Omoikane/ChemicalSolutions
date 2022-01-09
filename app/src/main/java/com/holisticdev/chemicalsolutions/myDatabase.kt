package com.holisticdev.chemicalsolutions

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class myDatabase: SQLiteAssetHelper {
    constructor(context: Context) : super(context, DATABASE_NAME,context.getExternalFilesDir(null)!!.absolutePath,null, DATABASE_VERSION) {}

    companion object{
        private val DATABASE_NAME ="database.db"
        private val DATABASE_VERSION = 1
    }
}