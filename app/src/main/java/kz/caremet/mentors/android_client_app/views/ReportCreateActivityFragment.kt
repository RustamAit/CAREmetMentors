package kz.caremet.mentors.android_client_app.views

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.caremet.mentors.android_client_app.R

/**
 * A placeholder fragment containing a simple view.
 */
class ReportCreateActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report_create, container, false)
    }
}
