package es.kab.footballproject.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.kab.footballproject.Classes.Teambase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Teambase::class],
    version = 1
)
abstract class AppDB:RoomDatabase() {
    abstract fun teamsDAO(): TeamsDAO

    companion object{
        private const val DATABASE_NAME = "APP_DB"


        private var instance: AppDB?=null
        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context:Context): AppDB {
            synchronized(this){
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return instance!!
        }
    }

}