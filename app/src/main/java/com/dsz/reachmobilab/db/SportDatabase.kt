package com.dsz.reachmobilab.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dsz.reachmobilab.db.model.Leagues

@Database(entities = [Leagues::class], version = 1)
abstract class SportDatabase : RoomDatabase() {

    abstract fun sportDao(): SportDao

    companion object {

        const val DB_NAME = "sport_db"

        private lateinit var instance: SportDatabase

        fun getInstance(context: Context): SportDatabase {
            if (!::instance.isInitialized) {
                synchronized(SportDatabase::class) {
                    instance = Room.databaseBuilder(context, SportDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }

}