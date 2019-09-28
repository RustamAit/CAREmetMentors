package kz.caremet.mentors.android_client_app.views.signUp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_about_info.*

import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.DataEntities
import kz.caremet.mentors.android_client_app.views.signUp.viewModel.FragmentSharedViewModel
import kz.caremet.mentors.android_client_app.views.signUp.viewModel.InfoFragmentsViewModel
import org.koin.android.ext.android.inject


class AboutInfoFragment : Fragment() {

    val sharedViewModel: FragmentSharedViewModel by inject()
    val viewModel: InfoFragmentsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getAboutInfoData()?.let {
            coreActivityInputEditText?.setText(it.coreActivity)
            positionTextInputEditText?.setText(it.position)
            hobbiesTextInputEditText?.setText(it.hobbies)
            aboutTextInputEditText?.setText(it.aboutYourSelf)
            expaskidamiTextInputEditText?.setText(it.experienceWithKids)
        }

        if(sharedViewModel.getGeneralInfoData() == null){
            activity?.onBackPressed()
        }

        doneBtn?.setOnClickListener {
            if(validateData()){
                sharedViewModel.setAboutInfoData(
                    DataEntities.AboutInfo(
                        coreActivityInputEditText?.text.toString(),
                        positionTextInputEditText?.text.toString(),
                        aboutTextInputEditText?.text.toString(),
                        hobbiesTextInputEditText?.text.toString(),
                        expaskidamiTextInputEditText?.text.toString()
                    )
                )

                sharedViewModel.getQuestionaryData()?.let { data ->
                    viewModel.signIn(data).observeOn(AndroidSchedulers.mainThread())
                        .subscribe { it ->
                            Log.d("HEHEHEHEHE", "HAHAHAHAHA")
                            if(it.id == 0){
                                activity?.let {
                                    AlertDialog.Builder(activity!!)
                                        .setTitle("К сожалению произошла ошибка")
                                        .setMessage("Возможно анкета уже была отправленна, нас нет в городе, либо у вас нет интернета\n" +
                                                "Нормальную обработку ошибок мы добавим потом(если я успею)")
                                        .setNegativeButton("Ok") { p0, p1 ->
                                            p0.dismiss()
                                        }
                                        .show()
                                }

                            }
                            else{
                                (activity as? GeneralInfoInteractionListener)?.startSuccessFragment()
                            }
                        }

                }

            }

            else{
                showToast("Проверьте данные")
            }
        }
    }

    fun validateData():Boolean{

        if(coreActivityInputEditText.text.isNullOrEmpty()){
            return false
        }

        if(positionTextInputEditText.text.isNullOrEmpty()){
            return false
        }

        if(hobbiesTextInputEditText.text.isNullOrEmpty()){
            return false
        }

        if(coreActivityInputEditText.text.isNullOrEmpty()){
            return false
        }

        if(expaskidamiTextInputEditText.text.isNullOrEmpty()){
            return false
        }

        return true
    }



    fun showToast(s: String){
        activity?.let {
            Toast.makeText(it,s, Toast.LENGTH_LONG).show()
        }
    }



}
