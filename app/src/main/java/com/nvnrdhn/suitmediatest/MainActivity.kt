package com.nvnrdhn.suitmediatest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nvnrdhn.suitmediatest.helper.ApiService
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

const val RC_EVENT = 100
const val RC_GUEST = 101

val apiService by lazy {
    ApiService.create()
}

var disposable: Disposable? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Main"
        tv_name.text = intent.getStringExtra(NAME_INTENT)
        bt_event.setOnClickListener {
            startActivityForResult(Intent(this, EventActivity::class.java), RC_EVENT)
        }
        bt_guest.setOnClickListener {
            startActivityForResult(Intent(this, GuestActivity::class.java), RC_GUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_EVENT && resultCode == Activity.RESULT_OK) {
            bt_event.text = data?.dataString
        }
        else if (requestCode == RC_GUEST && resultCode == Activity.RESULT_OK) {
            bt_guest.text = data?.getStringExtra(GUEST_NAME)
            val date = data?.getStringExtra(GUEST_BIRTHDATE)
            Toast.makeText(this, birthCheck(date), Toast.LENGTH_LONG).show()
        }
    }

    private fun birthCheck(date: String?): String? {
        val res = date?.replace("[^0-9]".toRegex(), " ")?.split(" ")?.get(2)?.toInt()
        if (res != null) {
            when {
                res % 6 == 0 -> return "iOS"
                res % 3 == 0 -> return "android"
                res % 2 == 0 -> return "blackberry"
            }
        }
        return "feature phone"
    }
}
