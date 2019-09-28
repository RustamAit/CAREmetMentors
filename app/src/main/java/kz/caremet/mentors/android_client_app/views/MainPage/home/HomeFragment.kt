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

        for(i in 0..3){
            notificationDataset.add(
                DataEntities.Notification("Очень важное уведомление",
                "notmalniy",
                "Это очень важное уведомление в котором мы пытаемся донести очень важную и полезную инфу"))
        }
        notificationRecList?.layoutManager = LinearLayoutManager(activity)
        notificationRecList?.adapter = NotificationAdapter(notificationDataset)

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
