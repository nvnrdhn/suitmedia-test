package com.nvnrdhn.suitmediatest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

const val NAME_INTENT = "name_intent"

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.title = "Home"
        bt_next.setOnClickListener {
            val nama = et_name.text.toString()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(NAME_INTENT, nama)
            }
            startActivity(intent)
        }
    }
}
