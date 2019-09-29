package kz.caremet.mentors.android_client_app.repository.dao

import androidx.room.*
import io.reactivex.Observable
import kz.caremet.mentors.android_client_app.core.data.DataEntities

@Dao
interface EventDao{

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateDeal(contractor: DataEntities.EventFromDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDeal(contractor: DataEntities.EventFromDb):Long

    @Transaction
    fun upsertDeal(entity: DataEntities.EventFromDb) {
        var isnR = insertDeal(entity)

        if(isnR == (-1).toLong()){
            updateDeal(entity)
        }
    }


    @Query("SELECT * FROM EventFromDb")
    fun getUserChatRooms(): Observable<List<DataEntities.EventFromDb>>


}