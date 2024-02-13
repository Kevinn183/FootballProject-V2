package es.kab.footballproject.Classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Teambase(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "team")
    val team:String,

    @ColumnInfo(name = "pos")
    val pos:String,

    @ColumnInfo(name = "points")
    val points:Int,

    @ColumnInfo(name = "shield")
    val shield:String,

    @ColumnInfo(name = "wins")
    val wins:Int,

    @ColumnInfo(name = "draws")
    val draws:Int,

    @ColumnInfo(name = "losses")
    val losses : Int,

    @ColumnInfo(name = "form")
    val form:String,

    @ColumnInfo(name = "gf")
    val gf:String,

    @ColumnInfo(name = "ga")
    val ga:String)
