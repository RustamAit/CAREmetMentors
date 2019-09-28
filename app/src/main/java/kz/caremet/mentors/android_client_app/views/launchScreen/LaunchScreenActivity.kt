package kz.caremet.mentors.android_client_app.views.launchScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import kz.caremet.mentors.android_client_app.R

import kotlinx.android.synthetic.main.activity_launch_screen.*
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import kz.caremet.mentors.android_client_app.views.MainPage.MainActivity
import kz.caremet.mentors.android_client_app.views.signIn.SignInActivity
import kz.caremet.mentors.android_client_app.views.signUp.SignUpActivity
import org.koin.android.ext.android.inject

class LaunchScreenActivity : AppCompatActivity() {

    val sharedPref: LocalSharedPref by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        pager.adapter = SliderPageAdapter(supportFragmentManager, 4)
        tabDots.setupWithViewPager(pager)

        signUpBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        signInBtn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        if(sharedPref.getCurrentRealMentorId() != 0 && sharedPref.getCurrentMentorId() != null){
            Log.d("DATA_TEST", sharedPref.getCurrentRealMentorId().toString())
            startActivity(Intent(this, MainActivity::class.java))
        }


    }

}
