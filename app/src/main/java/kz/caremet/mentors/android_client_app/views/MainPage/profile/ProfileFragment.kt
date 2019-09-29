package kz.caremet.mentors.android_client_app.views.MainPage.profile


import android.app.LauncherActivity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile.*

import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import kz.caremet.mentors.android_client_app.views.launchScreen.LaunchScreenActivity
import org.koin.android.ext.android.inject


class ProfileFragment : Fragment() {

    val sharedPref: LocalSharedPref by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exitBtn.setOnClickListener {
            sharedPref.clearAllData()
            startActivity(Intent(activity,LaunchScreenActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
    }


}
