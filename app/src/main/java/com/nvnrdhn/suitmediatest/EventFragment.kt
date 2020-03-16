package com.nvnrdhn.suitmediatest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nvnrdhn.suitmediatest.helper.EventAdapter
import com.nvnrdhn.suitmediatest.model.Model
import kotlinx.android.synthetic.main.fragment_event.*

class EventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_event.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = EventAdapter(dummy()).apply {
                onItemClick = {
                    val data = Intent().apply { data = Uri.parse(it.nama) }
                    activity?.setResult(Activity.RESULT_OK, data)
                    activity?.finish()
                }
            }
        }
    }

    private fun dummy(): List<Model.Event> {
        return listOf(
            Model.Event(R.drawable.i1, "Event A", "21 November 1998", null),
            Model.Event(R.drawable.i2, "Event B", "22 November 1998", null),
            Model.Event(R.drawable.i3, "Event C", "23 November 1998", null),
            Model.Event(R.drawable.i4, "Event D", "24 November 1998", null),
            Model.Event(R.drawable.i5, "Event E", "25 November 1998", null),
            Model.Event(R.drawable.i6, "Event F", "26 November 1998", null),
            Model.Event(R.drawable.i7, "Event G", "27 November 1998", null),
            Model.Event(R.drawable.i8, "Event H", "28 November 1998", null)
        )
    }
}
