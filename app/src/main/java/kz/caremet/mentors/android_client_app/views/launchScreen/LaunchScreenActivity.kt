package kz.caremet.mentors.android_client_app.views.launchScreen

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import kz.caremet.mentors.android_client_app.R

import kotlinx.android.synthetic.main.activity_launch_screen.*
import kz.caremet.mentors.android_client_app.views.signIn.SignInActivity
import kz.caremet.mentors.android_client_app.views.signUp.SignUpActivity

class LaunchScreenActivity : AppCompatActivity() {

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
    }

}
