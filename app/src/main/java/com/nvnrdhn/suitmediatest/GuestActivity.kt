package com.nvnrdhn.suitmediatest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.nvnrdhn.suitmediatest.helper.GuestAdapter
import com.nvnrdhn.suitmediatest.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_guest.*

const val GUEST_NAME = "guest_name"
const val GUEST_BIRTHDATE = "guest_birthdate"

class GuestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        supportActionBar?.title = "Guest"
        rv_guest.apply {
            layoutManager = GridLayoutManager(this@GuestActivity, 2)
            adapter = GuestAdapter(emptyList()).apply {
                onItemClick = {
                    val data = Intent().apply {
                        putExtra(GUEST_NAME, it.name)
                        putExtra(GUEST_BIRTHDATE, it.birthdate)
                    }
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }
            }
        }
        loadData()
    }

    private fun loadData() {
        pb_loading.visibility = View.VISIBLE
        rv_guest.visibility = View.GONE
        disposable = apiService.getGuests()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> setData(result) },
                { error ->
                    run {
                        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                        error.printStackTrace()
                        pb_loading.visibility = View.GONE
                        rv_guest.visibility = View.VISIBLE
                    }
                }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun setData(list: List<Model.Guest>) {
        val gAdapter: GuestAdapter = rv_guest.adapter as GuestAdapter
        gAdapter.setList(list)
        pb_loading.visibility = View.GONE
        rv_guest.visibility = View.VISIBLE
    }
}
