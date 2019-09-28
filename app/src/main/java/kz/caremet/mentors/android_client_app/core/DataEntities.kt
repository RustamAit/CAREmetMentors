package kz.caremet.mentors.android_client_app.core

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

object DataEntities {

    @Entity
    data class Post(
        @PrimaryKey val id: Int,
        val title: String?,
        val text: String,
        val photo: String?
    )

    @Entity
    data class Event(
        @PrimaryKey val id: Int,
        val title: String,
        val description: String?,
        val photo: String?,
        val status: String?,
        val date: String,
        val type: String,
        val userId: String
    )

    @Entity
    data class Mentor(
        @PrimaryKey val id: Int,
        @SerializedName("first_name") val firstName: String?,
        @SerializedName("last_name") val lastName: String?,
        val patronymic: String?,
        val phone_number: String?,
        val email: String?,
        val status: String?
       // val city: String?
    )

    @Entity
    data class SignInData(
        val email: String,
        val password: String
    )

    data class Notification(
        val title: String,
        val type: String,
        val description: String?
    )

    data class QuestionaryData(
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String,
        @SerializedName("patronymic") val patronymic: String?,
        @SerializedName("phone_number") val phoneNumber: String,
        @SerializedName("email") val email: String,
        @SerializedName("birth_date") val birthData: String?,
        @SerializedName("city") val city: String,
        @SerializedName("address") val address: String,
        @SerializedName("kids") val kids: Boolean,
        @SerializedName("core_activity") val coreActivity: String,
        @SerializedName("position") val position: String,
        @SerializedName("experience_with_kids") val experienceWithKids: String,
        @SerializedName("about") val aboutYourSelf: String,
        @SerializedName("car") val car: Boolean,
        @SerializedName("marital_status") val maritalStatus: String,
        @SerializedName("hobbies") val hobbies: String
    ){
        companion object {
            fun getQuestionaryDataFromInfoClasses(generalInfo: GeneralInfo, aboutInfo: AboutInfo): QuestionaryData{
                return QuestionaryData(
                    generalInfo.firstName,
                    generalInfo.lastName,
                    generalInfo.patronymic,
                    generalInfo.phoneNumber,
                    generalInfo.email,
                    generalInfo.birthData,
                    generalInfo.city,
                    generalInfo.address,
                    false,
                    aboutInfo.coreActivity,
                    aboutInfo.position,
                    aboutInfo.experienceWithKids,
                    aboutInfo.aboutYourSelf,
                    false,
                    generalInfo.maritalStatus,
                    aboutInfo.hobbies
                )
            }
        }

    }

    data class GeneralInfo(
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String,
        @SerializedName("patronymic") val patronymic: String?,
        @SerializedName("phone_number") val phoneNumber: String,
        @SerializedName("email") val email: String,
        @SerializedName("birth_date") val birthData: String?,
        @SerializedName("city") val city: String,
        @SerializedName("address") val address: String,
        @SerializedName("marital_status") val maritalStatus: String
        )

    data class AboutInfo(
        @SerializedName("core_activity") val coreActivity: String,
        @SerializedName("position") val position: String,
        @SerializedName("about") val aboutYourSelf: String,
        @SerializedName("hobbies") val hobbies: String,
        @SerializedName("experience_with_kids") val experienceWithKids: String

        )


}