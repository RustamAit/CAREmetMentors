package kz.caremet.mentors.android_client_app.views.signIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_sign_in.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.views.MainPage.MainActivity
import kz.caremet.mentors.android_client_app.views.signIn.viewModel.SignInViewModel
import org.koin.android.ext.android.inject

class SignInActivity : AppCompatActivity() {

    val viewModel: SignInViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Вход"

        doneBtn?.setOnClickListener {
            if(validateData()){
                viewModel.signIn(
                    DataEntities.SignInData(
                    emailTextInputEditText.text.toString(),
                    passwordTextInputEditText.text.toString()
                )).observeOn(AndroidSchedulers.mainThread())
                    .subscribe { mentor ->
                        if(mentor.real_id == 0){
                            AlertDialog.Builder(this)
                                .setTitle("К сожалению произошла ошибка")
                                .setMessage("Возможно вы ввели некоректные данные, произошла ошибка на беке, либо у вас нет интернета\n" +
                                        "Нормальную обработку ошибок мы добавим потом(если я успею)")
                                .setNegativeButton("Ok") { p0, p1 ->
                                    p0.dismiss()
                                }.show()
                        }
                        else{
                            startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                        }
                    }
            }
        }

    }

    fun validateData(): Boolean{
        if(emailTextInputEditText.text.isNullOrEmpty()){
            Snackbar.make(mainContent, "Введите email", Snackbar.LENGTH_SHORT)
            return false
        }
        if(passwordTextInputEditText.text.isNullOrEmpty()){
            Snackbar.make(mainContent, "Введите пароль", Snackbar.LENGTH_SHORT)
            return false
        }
        return true
    }
}
