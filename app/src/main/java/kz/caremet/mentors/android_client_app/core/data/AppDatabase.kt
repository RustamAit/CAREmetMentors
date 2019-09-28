package kz.caremet.mentors.android_client_app.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.repository.dao.ChatRoomDao
import kz.caremet.mentors.android_client_app.repository.dao.EventDao
import kz.caremet.mentors.android_client_app.repository.dao.MentorDao
import kz.caremet.mentors.android_client_app.repository.dao.MessageDao


@Database(entities = [DataEntities.Post::class,
    DataEntities.Mentor::class, DataEntities.ChatRoomFromDb::class,
    DataEntities.EventFromDb::class,
    DataEntities.MessageFormDb::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMentorDao(): MentorDao
    abstract fun getChatRoomDao(): ChatRoomDao
    abstract fun getMessagesDao(): MessageDao
    abstract fun getEventDao(): EventDao
}