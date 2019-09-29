package kz.caremet.mentors.android_client_app.views.MainPage.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.views.adapters.NotificationAdapter
import kz.caremet.mentors.android_client_app.views.adapters.PostAdapter
import kz.caremet.mentors.android_client_app.views.adapters.ReportAdapter

class HomeFragment : Fragment() {
//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataset = ArrayList<DataEntities.Post>()

        for(i in 0..40){
            dataset.add(DataEntities.Post(1,"Очень интересная история", "Очень описание истории в которой расказщик восторгается эмоциям полученным во время встречи с ребенком", null))
        }
        recList?.layoutManager = LinearLayoutManager(activity)
        recList?.adapter = PostAdapter(dataset)

        val notificationDataset = ArrayList<DataEntities.Notification>()

            notificationDataset.add(
                DataEntities.Notification("У вас новое сообщение",
                    "message",
                    "В чатах вы можете найти все последние сообщения")
               )
            notificationDataset.add(
                DataEntities.Notification("Сегодня у вас встреча",
                    "important",
                    "Сегодня у вас назначена встреча с ребенком")
            )

            notificationDataset.add(
                DataEntities.Notification("Ваш отчет прошел проверку",
                    "accept",
                    "если имеются вопросы можете обратится к психологу за помощью")
            )

            notificationDataset.add(
                DataEntities.Notification("Ваш отчет не прошел проверку",
                    "lose",
                    "Вы можете увидеть комментарии и замечания проверяющего")
            )


        notificationRecList?.layoutManager = LinearLayoutManager(activity)
        notificationRecList?.adapter = NotificationAdapter(notificationDataset,context!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
