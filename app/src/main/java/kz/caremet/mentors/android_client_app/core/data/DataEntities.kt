package kz.caremet.mentors.android_client_app.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

object DataEntities {

    @Entity
    data class Post(
        @PrimaryKey val id: Int,
        val title: String?,
        val text: String,
        val photo: String?
    )

    @Entity
    data class Mentor(
        @PrimaryKey val id: String,
        val real_id: Int,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String?,
        val patronymic: String?,
        val phone_number: String?,
        val email: String?,
        val status: String?,
        val type: String?
       // val city: String?
    )

    data class SignUpData(
        val access_token: String,
        val id: String,
        val real_id: Int
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
            fun getQuestionaryDataFromInfoClasses(generalInfo: GeneralInfo, aboutInfo: AboutInfo): QuestionaryData {
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

    data class ChatRoomFromApi(
        val id: Int,
        val name: String,
        val type: String?,
        val messages: List<MessageFromApi>
    ){
        fun serializeForDb(currentUserId: String): ChatRoomFromDb {
            return ChatRoomFromDb(
                id, name, type, currentUserId
            )
        }
    }

    @Entity
    data class ChatRoomFromDb(
        @PrimaryKey val id: Int,
        val name: String,
        val type: String?,
        val userId: String
    )

    data class City(
        val id: Int,
        val name: String
    )

    @Entity
    data class MessageFormDb(
        @PrimaryKey val uuid: String,
        val id: Int?,
        val chatRoomId: Int,
        val sender_id: String,
        val text: String,
        val sender_name: String,
        val sender_type: String?,
        val created_at: String,
        val sendStatus: String
    )

    data class MessageFromApi(
        @PrimaryKey val uuid: String?,
        val id: Int?,
        val chat_room_id: Int,
        val sender: Mentor,
        val text: String,
        val created_at: String

    ){
        fun serializeForDb(): MessageFormDb {
            return MessageFormDb(
                uuid?:id.toString(),
                id,
                chat_room_id,
                sender.id,
                text,
                sender.firstName + sender.lastName,
                sender.type,
                created_at,
                StatusConstants.DELIVIRED
            )
        }
    }

    data class MessageForPost(
        val uuid: String,
        val text: String,
        val sender_id: Int
    )

    data class Event(
        @PrimaryKey val id: Int,
        val type: String?,
        val date: String?,
        val attendee: Mentor?,
        val title: String,
        val description: String?,
        val created_at: String
    ){
        fun serializeForDb(): EventFromDb{
            return EventFromDb(
                this.id,this.type,this.date,this.title,this.description,this.attendee?.id,this.created_at
            )
        }
    }

    @Entity
    data class EventFromDb(
        @PrimaryKey val id: Int,
        val type: String?,
        val date: String?,
        val title: String,
        val description: String?,
        val userId: String?,
        val created_at: String
    )
    object StatusConstants{
        const val NOT_CREATED = "NOT_CREATED"
        const val CREATED = "CREATED"
        const val DELIVIRED = "DELIVERED"
        const val FAILED = "FAILED"
    }


    @Entity
    data class Report(
        @PrimaryKey val id: Int,
        val title: String,
        val created_at: String?,
        val event_id: Int,
        val text: String,
        val status: String?
    )

    data class ReportForPost(
        val title: String,
        val text: String
    )

}