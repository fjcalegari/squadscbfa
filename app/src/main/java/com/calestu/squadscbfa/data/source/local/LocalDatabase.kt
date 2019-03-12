package com.calestu.squadscbfa.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.calestu.squadscbfa.data.entity.AppInfoEntity
import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.entity.PlayerEntity
import timber.log.Timber

@Database(entities = [
    AppInfoEntity::class,
    ClubEntity::class,
    PlayerEntity::class,
    CoachEntity::class
], version = 1, exportSchema = false)
@TypeConverters(LocalDbConverter::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun localDao(): LocalDao

    companion object {

        @Volatile private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) : LocalDatabase {
            Timber.d("buildDatabase: ")
            return Room.databaseBuilder(context.applicationContext,
                LocalDatabase::class.java, "sqdcbfaapp.db")
                .build()
        }
    }

}