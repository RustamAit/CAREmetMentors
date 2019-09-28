package kz.caremet.mentors.android_client_app.core.utils

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.input_view.view.*
import kz.caremet.mentors.android_client_app.R

class InputView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    var editText: EditText? = null
    var sendBtn: ImageButton? = null
    var voiceMessageLayout: ConstraintLayout? = null
    var voiceRecordBtn: ImageButton? = null
    var timerTextViewL: TextView? = null
    var cancelTextViewL: TextView? = null
    var progressBar: ProgressBar? = null

    init {
        View.inflate(context, R.layout.input_view,this)
        this.editText = edittext_chatbox
        this.sendBtn = button_chatbox_send
        this.voiceMessageLayout = voiceRecordLayout
        this.voiceRecordBtn = voiceMessageBtn
        this.timerTextViewL = timerTextView
        this. cancelTextViewL = cancelTextView
        this.progressBar = loadingProgressBar
        cancelTextView.text = "< Отменить влево"
    }


    fun showStandartInputLayout(){
        progressBar?.visibility = ProgressBar.GONE
        cancelTextViewL?.visibility = TextView.GONE
        timerTextViewL?.text = "00:00"
        timerTextViewL?.visibility = TextView.GONE
        val layoutParams = voiceMessageLayout?.layoutParams
        layoutParams?.width = ConstraintLayout.LayoutParams.WRAP_CONTENT
        voiceMessageLayout?.background = ContextCompat.getDrawable(context,R.drawable.back_white_rectangle_rounded)
        voiceMessageLayout?.layoutParams = layoutParams
        editText?.visibility = EditText.VISIBLE
    }
    fun showVoiceRecordLayout(){
        editText?.visibility = EditText.GONE
        val layoutParams = voiceMessageLayout?.layoutParams
        layoutParams?.width = ConstraintLayout.LayoutParams.MATCH_PARENT
        voiceMessageLayout?.background = ContextCompat.getDrawable(context, R.drawable.back_voice_message)
        voiceMessageLayout?.layoutParams = layoutParams
        cancelTextViewL?.visibility = TextView.VISIBLE
        timerTextViewL?.text = "00:00"
        timerTextViewL?.visibility = TextView.VISIBLE
    }
    fun getInputText(): String{
        return editText?.text.toString()
    }

    fun clearEditTextFocus(){
        editText?.clearFocus()
    }

    fun showLoadingLayout(){
        timerTextViewL?.visibility = TextView.GONE
        progressBar?.visibility = ProgressBar.VISIBLE
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animateVoiceRecordBtn(destinationX: Float, destinationY: Float, animatorDuration: Long) {
//        val path = Path()
//        path.moveTo(destinationX, destinationY)
//        path.lineTo(destinationX, destinationY)
//        val objectAnimator = ObjectAnimator.ofFloat(voiceRecordBtn, View.X, View.Y, path)
//        objectAnimator.duration = animatorDuration
//        objectAnimator.interpolator = LinearInterpolator()
//        objectAnimator.start()
    }
}