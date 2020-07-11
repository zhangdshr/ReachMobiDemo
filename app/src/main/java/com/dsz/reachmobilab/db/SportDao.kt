package com.dsz.reachmobilab.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.dsz.reachmobilab.db.model.Leagues

@Dao
interface SportDao {

    @Query("SELECT * FROM leagues")
    suspend fun getLeagues(): List<Leagues>

    @Insert(onConflict = REPLACE)
    suspend fun insertLeagues(leagues: Leagues)

}