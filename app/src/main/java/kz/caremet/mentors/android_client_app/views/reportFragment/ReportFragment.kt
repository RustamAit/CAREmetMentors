package kz.caremet.mentors.android_client_app.views.reportFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_report.*

import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.repository.services.ChatRoomService
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import kz.caremet.mentors.android_client_app.views.adapters.ReportAdapter
import org.koin.android.ext.android.inject


class ReportFragment : Fragment() {

    val sharedPref: LocalSharedPref by inject()
    val chatRoomService: ChatRoomService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rRecList?.layoutManager = LinearLayoutManager(activity)

        chatRoomService.getReports(sharedPref.getCurrentRealMentorId()).onErrorReturn { emptyList() }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { it ->
                rRecList?.adapter = ReportAdapter(it)
            }
    }

}
