package kz.caremet.mentors.android_client_app.views.signUp

import android.app.DatePickerDialog
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_general_info.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.views.signUp.viewModel.FragmentSharedViewModel
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class GeneralInfoFragment : Fragment() {

    val sharedViewModel: FragmentSharedViewModel by inject()
    var dateOfBirth: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getGeneralInfoData()?.let {
            nameTextInputEditText?.setText(it.firstName)
            surnameTextInputEditText?.setText(it.lastName)
            patronymicTextInputEditText?.setText(it.patronymic)
            phoneNumberTextInputEditText?.setText(it.phoneNumber)
            cityTextInputEditText?.setText(it.city)
            emailTextInputEditText?.setText(it.email)
            dateOfBirthTextView.text = "Дата рожденья - ${getSimpleDateFromISOString(it.birthData)}"
        }

        datePickerDialogBtn.setOnClickListener {
            getCalendar()
        }

        doneBtn.setOnClickListener {
            if(validateData()){
                sharedViewModel.setGeneralInfoData(
                    DataEntities.GeneralInfo(
                        nameTextInputEditText?.text.toString(),
                        surnameTextInputEditText?.text.toString(),
                        patronymicTextInputEditText?.text.toString(),
                        phoneNumberTextInputEditText?.text.toString(),
                        emailTextInputEditText?.text.toString(),
                        dateOfBirth,
                        cityTextInputEditText?.text.toString(),
                        addressTextInputEditText?.text.toString(),
                        getDataFromRadioButton()
                    )
                )
                (activity as? GeneralInfoInteractionListener)?.startAboutInfoFragment()
            }
        }

    }

    fun validateData(): Boolean{
        if(nameTextInputEditText?.text.isNullOrEmpty()){
            showToast("Заполните имя")
            return false
        }
        if(surnameTextInputEditText?.text.isNullOrEmpty()){
            showToast("Заполните Фамилию")
            return false
        }
        if(phoneNumberTextInputEditText?.text.isNullOrEmpty()){
            showToast("Заполните номер")
            return false
        }
        if(emailTextInputEditText?.text.isNullOrEmpty()){
            showToast("Заполните email")
            return false
        }
        if(dateOfBirth.isNullOrEmpty()){
            showToast("Заполните дату рожденья")
            return false
        }

        if(dateOfBirth == "maloi"){
            activity?.let {
                AlertDialog.Builder(it)
                    .setTitle("К сожалению вы слишком молоды для нашего проекта")
                    .setMessage("Минимальный возраст кандидата 24 года")
                    .setNegativeButton("Ok") { p0, p1 ->
                        p0.dismiss()
                    }
                    .show()
                return false

            }
        }

        if(cityTextInputEditText?.text.isNullOrEmpty()){
            showToast("Заполните город")
            return false
        }
        if(!cityTextInputEditText?.text.isNullOrEmpty()){
            val city = cityTextInputEditText.text.toString()
            val cities = arrayOf("москва", "санкт-петербург", "самара", "новосибирск")
            Log.d("cities_tag", city)
            if(!cities.contains(city.toLowerCase())){
                activity?.let {
                    AlertDialog.Builder(it)
                        .setTitle("К сожалению нас нет в вашем городе")
                        .setMessage("Мы сообщим вам по почте как только приложение станет доступно у вас")
                        .setNegativeButton("Ok") { p0, p1 ->
                            p0.dismiss()
                        }
                        .show()
                }
                return false
            }
        }
        return true
    }

    fun getDataFromRadioButton(): String{
        if(marriegeRadioGroup.checkedRadioButtonId == R.id.yes){
            return "В браке"
        }
        else{
            return "Не в браке"
        }
    }

    private fun getCalendar(){
        val caledar = Calendar.getInstance()
        val currentYear = caledar.get(Calendar.YEAR)
        val month = caledar.get(Calendar.MONTH)
        val day = caledar.get(Calendar.DAY_OF_MONTH)
        context?.let{
            val datePickerDialog = DatePickerDialog(it,
                DatePickerDialog.OnDateSetListener { p0, p1, p2, p3 ->
                    dateOfBirthTextView.visibility = TextView.VISIBLE
                    dateOfBirthTextView.setText("Дата рожденья - $p2/$p3/$p1")
                    showToast("${currentYear}, ${currentYear - p1}")
                    if((currentYear - p1)<24){
                        dateOfBirth = "maloi"
                    }
                    else{
                        dateOfBirth = getDateISOStringFromDYM(p2,p3,p1)
                    }
                },currentYear,month,day )
            datePickerDialog.show()
        }

    }

    fun getDateISOStringFromDYM(day: Int, month: Int, year: Int): String{
        val inputDateString = "$day/$month/$year"
        val formatSample = SimpleDateFormat("MM/dd/yy")
        val date = formatSample.parse(inputDateString)
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        df.timeZone = TimeZone.getTimeZone("UTC")
        return  df.format(date)
    }

    fun showToast(s: String){
        activity?.let {
            Toast.makeText(it,s, Toast.LENGTH_LONG).show()
        }
    }

    fun getSimpleDateFromISOString(isoString: String?): String{
        isoString?.let {
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = df.parse(isoString)
            val formatSample = SimpleDateFormat("MM/dd/yy")
            date?.let {
                return formatSample.format(date)
            }
            return ""
        }
        return ""
    }

}
