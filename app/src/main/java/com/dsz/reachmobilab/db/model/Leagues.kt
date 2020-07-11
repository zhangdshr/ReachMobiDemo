package com.dsz.reachmobilab.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class Leagues(

    @PrimaryKey
    @ColumnInfo(name = "league_id")
    var leagueId: String,

    @ColumnInfo(name = "league_name")
    var leagueName: String

)