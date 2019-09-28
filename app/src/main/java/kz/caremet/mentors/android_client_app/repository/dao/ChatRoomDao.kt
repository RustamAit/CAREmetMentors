package kz.caremet.mentors.android_client_app.repository.dao

import androidx.room.*
import io.reactivex.Observable
import kz.caremet.mentors.android_client_app.core.data.DataEntities

@Dao
interface ChatRoomDao{

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateDeal(contractor: DataEntities.ChatRoomFromDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDeal(contractor: DataEntities.ChatRoomFromDb):Long

    @Transaction
    fun upsertDeal(entity: DataEntities.ChatRoomFromDb) {
        var isnR = insertDeal(entity)

        if(isnR == (-1).toLong()){
            updateDeal(entity)
        }
    }


    @Query("SELECT * FROM chatroomfromdb WHERE userId =:senderId")
    fun getUserChatRooms(senderId: String): Observable<List<DataEntities.ChatRoomFromDb>>



}