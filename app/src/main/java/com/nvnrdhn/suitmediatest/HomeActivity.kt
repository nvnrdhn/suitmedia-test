package com.nvnrdhn.suitmediatest

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

const val NAME_INTENT = "name_intent"

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        bt_next.setOnClickListener { checkPalindrome(et_name.text.toString()) }
    }

    private fun checkPalindrome(string: String) {
        val res = string.replace("\\s".toRegex(), "")
        AlertDialog.Builder(this)
            .setTitle("Palindrome Check")
            .setMessage(if (res == res.reversed()) "isPalindrome" else "not palindrome")
            .setPositiveButton("Next") { dialogInterface, _ ->
                dialogInterface.dismiss()
                val nama = et_name.text.toString()
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(NAME_INTENT, nama)
                }
                startActivity(intent)
            }
            .show()
    }
}
