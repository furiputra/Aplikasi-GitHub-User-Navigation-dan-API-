package com.example.submission_github.ui.main.data.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.coroutines.coroutineContext

@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    companion object{
        var INSTANCE : UserDatabase? = null

    fun getDatabase(context:Context): UserDatabase? {
        if (INSTANCE==null){
            synchronized(UserDatabase::class){
                INSTANCE = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user_database").build()
                }
            }
        return INSTANCE
        }
    }
    abstract  fun favoriteUserDao(): FavoriteUserDao
}