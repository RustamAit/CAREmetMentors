package kz.caremet.mentors.android_client_app.views.MainPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.views.BaseActivity
import kz.caremet.mentors.android_client_app.views.MainPage.chat.ChatFragment
import kz.caremet.mentors.android_client_app.views.MainPage.home.HomeFragment
import kz.caremet.mentors.android_client_app.views.chat.ChatActivity

class MainActivity : BaseActivity(), MainPageFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainFragmentContainer,HomeFragment.newInstance())
            .commit()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navChat -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainFragmentContainer,ChatFragment.newInstance())
                        .commit()
                }
                R.id.navMainScreen -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainFragmentContainer,HomeFragment.newInstance())
                        .commit()
                }
            }
            true
        }


    }

    override fun startChatActivity(chatRoomId: Int) {
        startActivity(Intent(this, ChatActivity::class.java).putExtra("chatRoomId", chatRoomId))
    }
}
