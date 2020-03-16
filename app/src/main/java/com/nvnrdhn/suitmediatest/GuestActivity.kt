package com.nvnrdhn.suitmediatest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.nvnrdhn.suitmediatest.helper.GuestAdapter
import com.nvnrdhn.suitmediatest.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_guest.*

const val GUEST_NAME = "guest_name"
const val GUEST_BIRTHDATE = "guest_birthdate"
const val GUEST_MONTHPRIME = "guest_monthprime"

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
                        putExtra(GUEST_MONTHPRIME, isPrime(it.birthdate))
                    }
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }
            }
        }
        loadData()
        swiper.setOnRefreshListener { loadData() }
    }

    private fun loadData() {
        swiper.isRefreshing = false
        pb_loading.visibility = View.VISIBLE
        rv_guest.visibility = View.GONE
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val cache = sharedPref.getString(getString(R.string.local_data_key), "")
        if (cache.isNullOrEmpty()) {
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
        else setData(Gson().fromJson(cache, Array<Model.Guest>::class.java).asList())
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun setData(list: List<Model.Guest>) {
        val gAdapter: GuestAdapter = rv_guest.adapter as GuestAdapter
        gAdapter.setList(list)
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(getString(R.string.local_data_key), Gson().toJson(list))
            commit()
        }
        pb_loading.visibility = View.GONE
        rv_guest.visibility = View.VISIBLE
    }

    private fun isPrime(date: String): Boolean {
        val month = date.replace("[^0-9]".toRegex(), " ").split(" ")[1].toInt()
        if (month == 1) return true
        for (i in 2 until month) if (month % i == 0) return false
        return true
    }
}
