package kz.caremet.mentors.android_client_app.repository.dao

import androidx.room.*
import io.reactivex.Observable
import kz.caremet.mentors.android_client_app.core.data.DataEntities

@Dao
interface MentorDao {


    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateDeal(contractor: DataEntities.Mentor)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDeal(contractor: DataEntities.Mentor):Long

    @Transaction
    fun upsertDeal(entity: DataEntities.Mentor) {
        var isnR = insertDeal(entity)

        if(isnR == (-1).toLong()){
            updateDeal(entity)
        }
    }

    @Query("SELECT * FROM Mentor where id =:id")
    fun getMentor(id: String): Observable<DataEntities.Mentor>

}