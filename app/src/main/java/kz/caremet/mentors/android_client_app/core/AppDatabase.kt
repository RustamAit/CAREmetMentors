package kz.caremet.mentors.android_client_app.core

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.caremet.mentors.android_client_app.repository.dao.MentorDao


@Database(entities = [DataEntities.Post::class, DataEntities.Mentor::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMentorDao(): MentorDao
}