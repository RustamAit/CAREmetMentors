package kz.caremet.mentors.android_client_app.views.MainPage.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_event.*

import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.repository.dao.EventDao
import kz.caremet.mentors.android_client_app.repository.services.ChatRoomService
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import kz.caremet.mentors.android_client_app.views.MainPage.MainPageFragmentListener
import kz.caremet.mentors.android_client_app.views.adapters.EventAdapter
import kz.caremet.mentors.android_client_app.views.adapters.ItemEventListener
import org.koin.android.ext.android.inject


class EventFragment : Fragment(), ItemEventListener {


    val service: ChatRoomService by inject()
    val sharedPref: LocalSharedPref by inject()
    val eventDao: EventDao by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsRecList?.layoutManager = LinearLayoutManager(activity)

        service.getEvents(sharedPref.getAccessToken()!!,sharedPref.getCurrentRealMentorId()).subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).onErrorReturn {
            emptyList()
        }.subscribe { data ->
            data.forEach {
                Single.fromCallable {
                    eventDao.upsertDeal(it.serializeForDb())
                }.subscribeOn(Schedulers.io()).subscribe()
            }

        }

        eventDao.getUserChatRooms().observeOn(AndroidSchedulers.mainThread()).subscribe {
            eventsRecList?.adapter = EventAdapter(it,this)
        }


    }
    override fun startReportCreateActivity(eventId: Int, eventTitle: String) {
        (activity as? MainPageFragmentListener)?.startReportCreateActivity(eventId, eventTitle)
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            EventFragment()
    }
}
