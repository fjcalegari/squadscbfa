package com.calestu.squadscbfa.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.calestu.squadscbfa.data.entity.*

@Database(entities = [
    AppVersionEntity::class,
    CoachEntity::class,
    PlayerEntity::class,
    CurrentSquadEntity::class,
    SquadEntity::class,
    PlayerSquadEntity::class
], version = 1, exportSchema = false)
@TypeConverters(LocalDbConverter::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun localDao(): LocalDao
    abstract fun squadDao(): SquadDao

    companion object {

        @Volatile private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) : LocalDatabase {
            return Room.databaseBuilder(context.applicationContext,
                LocalDatabase::class.java, "sqdcbfaapp.db")
                .build()
        }
    }

}