package es.kab.footballproject.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.kab.footballproject.Classes.Teambase

@Dao
interface TeamsDAO {
    @Query("SELECT * FROM  TEAMS")
    suspend fun getAll(): List<Teambase>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(teambase: Teambase)

    @Delete
    suspend fun delete(teambase: Teambase)
}