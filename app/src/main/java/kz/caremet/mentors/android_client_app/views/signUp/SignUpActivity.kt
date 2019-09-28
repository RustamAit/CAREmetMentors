package kz.caremet.mentors.android_client_app.views.signUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import kz.caremet.mentors.android_client_app.R

import kotlinx.android.synthetic.main.activity_sign_up.*
import kz.caremet.mentors.android_client_app.views.launchScreen.SliderFragment
import kz.caremet.mentors.android_client_app.views.launchScreen.SliderFragmentType

class SignUpActivity : AppCompatActivity(), GeneralInfoInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Регистрация"
        supportFragmentManager.beginTransaction()
            .add(R.id.signUpFragmentContainer, GeneralInfoFragment())
            .commit()
    }

    override fun startAboutInfoFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.signUpFragmentContainer, AboutInfoFragment())
            .addToBackStack("das")
            .commit()
    }

    override fun startSuccessFragment(){
        supportFragmentManager.popBackStack()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.signUpFragmentContainer, SliderFragment.newInstance(
                "Спасибо мы с вами свяжемся",
            "Наши специалисты проверят ваши данные, затем свяжутся с вами\n" +
                    "я знаю что картинка зашакалена, если успею сделаю нормально",
                SliderFragmentType.SUCCESS
            ))
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
