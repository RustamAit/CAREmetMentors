package kz.caremet.mentors.android_client_app.core

import java.text.SimpleDateFormat
import java.util.*

fun generateUUID(): String{
    return UUID.randomUUID().toString()
}

fun getCurrentDateISOString(): String{
    val tz = TimeZone.getTimeZone("UTC")
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'") // Quoted "Z" to indicate UTC, no timezone offset
    df.timeZone = tz
    return df.format(Date())
}

fun getDateStringFromIso(isoString: String): String{
    val df = SimpleDateFormat("yyyy-MM-dd") // Quoted "Z" to indicate UTC, no timezone offset
    val date = df.parse(isoString)
    val caledar = Calendar.getInstance()
    val currentYear = caledar.get(Calendar.YEAR)
    val month = caledar.get(Calendar.MONTH)
    val day = caledar.get(Calendar.DAY_OF_MONTH)
    val isoCalendar = Calendar.getInstance()
    date?.let {
        isoCalendar.time = date
        val dateYear = caledar.get(Calendar.YEAR)
        val datemonth = caledar.get(Calendar.MONTH)
        val dateday = caledar.get(Calendar.DAY_OF_MONTH)

        if(dateYear == currentYear){
            if(month==datemonth){
                if(day == dateday){
                    return "Сегодня"
                }
                if(day == dateday-1||(day == 29 && dateday == 30)||(day == 30 && dateday == 31)){
                    return "Завтра"
                }
            }
        }
    }

    val dt = SimpleDateFormat("dd/MM/yyyy")
    if(date!=null){
        return dt.format(date)// Quoted "Z" to indicate UTC, no timezone offset
    }
    return "Дата не установленна"


}