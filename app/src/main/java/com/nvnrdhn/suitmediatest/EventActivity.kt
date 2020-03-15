package com.nvnrdhn.suitmediatest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nvnrdhn.suitmediatest.helper.EventAdapter
import com.nvnrdhn.suitmediatest.model.Model
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        supportActionBar?.title = "Event"
        rv_event.apply {
            layoutManager = LinearLayoutManager(this@EventActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@EventActivity, LinearLayoutManager.VERTICAL))
            adapter = EventAdapter(dummy()).apply {
                onItemClick = {
                    val data = Intent().apply { data = Uri.parse(it.nama) }
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }
            }
        }
    }

    fun dummy(): List<Model.Event> {
        return listOf(
            Model.Event(R.drawable.i1, "Event A", "21 November 1998"),
            Model.Event(R.drawable.i2, "Event B", "22 November 1998"),
            Model.Event(R.drawable.i3, "Event C", "23 November 1998"),
            Model.Event(R.drawable.i4, "Event D", "24 November 1998"),
            Model.Event(R.drawable.i5, "Event E", "25 November 1998"),
            Model.Event(R.drawable.i6, "Event F", "26 November 1998"),
            Model.Event(R.drawable.i7, "Event G", "27 November 1998"),
            Model.Event(R.drawable.i8, "Event H", "28 November 1998")
        )
    }
}
