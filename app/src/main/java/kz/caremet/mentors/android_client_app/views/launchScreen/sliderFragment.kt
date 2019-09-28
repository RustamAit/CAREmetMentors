package kz.caremet.mentors.android_client_app.views.launchScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_slider.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.GlideApp

private const val TITLE_TEXT = "text"
private const val FRAGMENT_TYPE = "fragmentType"
private const val DESCRIPTION_TEXT = "description_text"

open class SliderFragment : Fragment() {
    private var titelText: String? = null
    private var fragmentType: String? = null
    private var descriptionText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            titelText = it.getString(TITLE_TEXT)
            fragmentType = it.getString(FRAGMENT_TYPE)
            descriptionText = it.getString(DESCRIPTION_TEXT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleTextView.text = titelText
        descriptionTextView.text = descriptionText

        when(fragmentType){
            SliderFragmentType.QUESTION -> {
                descriptionTextView.minLines = 4
                GlideApp.with(this)
                    .load(R.drawable.ic_acceptance)
                    .placeholder(R.color.colorWhite)
                    .into(slideImage)
            }
            SliderFragmentType.CONTROL -> GlideApp.with(this)
                .load(R.drawable.slider_ic_control)
                .placeholder(R.color.colorWhite)
                .into(slideImage)
            SliderFragmentType.AVAILAABILITY -> GlideApp.with(this)
                .load(R.drawable.slider_ic_availability)
                .placeholder(R.color.colorWhite)
                .into(slideImage)

            SliderFragmentType.ACCEPTANCE -> GlideApp.with(this)
                .load(R.drawable.slider_ic_comunity)
                .placeholder(R.color.colorWhite)
                .into(slideImage)

            SliderFragmentType.SUCCESS -> {
                GlideApp.with(this)
                    .load(R.drawable.ic_check_black_24dp)
                    .placeholder(R.color.colorWhite)
                    .into(slideImage)
            }
        }


    }
    companion object {
        @JvmStatic
        fun newInstance(titleText: String,descriptionText: String ,imageType: String) =
            SliderFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE_TEXT, titleText)
                    putString(FRAGMENT_TYPE, imageType)
                    putString(DESCRIPTION_TEXT, descriptionText)
                }
            }
    }
}

object SliderFragmentType{
    const val QUESTION: String = "question"
    const val CONTROL: String = "control"
    const val AVAILAABILITY: String = "availability"
    const val DEMO: String = "demo"
    const val ACCEPTANCE: String = "acceptance"
    const val SUCCESS: String = "success"
}
