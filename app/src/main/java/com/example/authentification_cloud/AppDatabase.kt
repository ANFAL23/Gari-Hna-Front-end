package com.example.authentification_cloud

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
var Base_Name = "anfel"

@Database(entities = [Reservation::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getReservationDao(): ReservationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, Base_Name)
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}