package kz.caremet.mentors.android_client_app.views

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers
import kz.caremet.mentors.android_client_app.R

import kotlinx.android.synthetic.main.activity_report_create.*
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.repository.services.ChatRoomService
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import org.koin.android.ext.android.inject

class ReportCreateActivity : AppCompatActivity() {

    var eventId: Int = 0
    var eventTitle: String? = null

    val sharedPref: LocalSharedPref by inject()
    val ChatRoomService: ChatRoomService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_create)
        setSupportActionBar(toolbar)

        eventId = intent.getIntExtra("eventId", 0)
        eventTitle = intent.getStringExtra("eventTitle")

        reportPo.text = "Отчет по: $eventTitle"

        doneBtn.setOnClickListener {
            if(!titleTextInputEditText.text.isNullOrEmpty() && !desTextInputEditText.text.isNullOrEmpty()){
                ChatRoomService.postReport(sharedPref.getAccessToken(),
                    sharedPref.getCurrentRealMentorId(),
                    eventId, DataEntities.ReportForPost(
                        titleTextInputEditText.text.toString(),
                        desTextInputEditText.text.toString()
                    )).onErrorReturn {
                    Toast.makeText(this,"Ошибка при отправке",Toast.LENGTH_LONG).show()
                    DataEntities.Report(1,"asdd",null,1,"sad",null)
                }.observeOn(AndroidSchedulers.mainThread()).subscribe{ it ->
                    onBackPressed()
                }
            }
        }

    }

}
