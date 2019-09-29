package kz.caremet.mentors.android_client_app.repository.dao

import androidx.room.*
import io.reactivex.Observable
import kz.caremet.mentors.android_client_app.core.data.DataEntities

@Dao
interface MessageDao {

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateDeal(contractor: DataEntities.MessageFormDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDeal(contractor: DataEntities.MessageFormDb):Long

    @Transaction
    fun upsertDeal(entity: DataEntities.MessageFormDb) {
        var isnR = insertDeal(entity)

        if(isnR == (-1).toLong()){
            updateDeal(entity)
        }
    }

    @Query("SELECT * FROM messageformdb WHERE chatRoomId=:chatRoomId Order by created_at desc")
    fun getChatRoomMessages(chatRoomId: Int): Observable<List<DataEntities.MessageFormDb>>



    @Query("SELECT * FROM messageformdb WHERE sendStatus =:sendStatus")
    fun getChatRoomMessagesBySendStatus(sendStatus: String): Observable<List<DataEntities.MessageFormDb>>
}