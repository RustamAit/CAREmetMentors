package kz.caremet.mentors.android_client_app.views.MainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.views.MainPage.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainFragmentContainer,HomeFragment.newInstance())
            .commit()

    }
}
