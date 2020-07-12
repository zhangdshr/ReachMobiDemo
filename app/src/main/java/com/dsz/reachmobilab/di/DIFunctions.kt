package com.dsz.reachmobilab.di

import android.content.Context
import androidx.room.Room
import com.dsz.reachmobilab.db.SportDatabase

fun instanceOfDB(context: Context): SportDatabase {
    return Room.databaseBuilder(context, SportDatabase::class.java, "sport_db")
        .fallbackToDestructiveMigration()
        .build()
}