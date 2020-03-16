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
import androidx.recyclerview.widget.LinearSnapHelper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nvnrdhn.suitmediatest.helper.EventHorizontalAdapter
import com.nvnrdhn.suitmediatest.helper.SnapOnScrollListener
import com.nvnrdhn.suitmediatest.helper.SnapOnScrollListener.OnSnapPositionChangeListener
import com.nvnrdhn.suitmediatest.helper.attachSnapHelperWithListener
import com.nvnrdhn.suitmediatest.model.Model
import kotlinx.android.synthetic.main.fragment_event_map.rv_event

class EventMapFragment : Fragment(), OnMapReadyCallback, OnSnapPositionChangeListener {
    private lateinit var gMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment).getMapAsync(this)
        rv_event.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = EventHorizontalAdapter(dummy()).apply {
                onItemClick = {
                    val data = Intent().apply { data = Uri.parse(it.nama) }
                    activity?.setResult(Activity.RESULT_OK, data)
                    activity?.finish()
                }
            }
            attachSnapHelperWithListener(
                LinearSnapHelper(),
                SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                this@EventMapFragment)
        }
    }

    override fun onMapReady(mMap: GoogleMap) {
        gMap = mMap
    }

    override fun onSnapPositionChange(position: Int) {
        gMap.clear()
        val adapter = rv_event.adapter as EventHorizontalAdapter
        val event = adapter.getEventAt(position)
        gMap.addMarker(
            MarkerOptions()
                .title(event.nama)
                .position(event.latLng!!)
        )
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(event.latLng, 17.5F))
    }

    private fun dummy(): List<Model.Event> {
        return listOf(
            Model.Event(R.drawable.i1, "Event A", "21 November 1998", LatLng(-7.283283, 112.797336)),
            Model.Event(R.drawable.i2, "Event B", "22 November 1998", LatLng(-7.285108, 112.795979)),
            Model.Event(R.drawable.i3, "Event C", "23 November 1998", LatLng(-7.286537, 112.794013)),
            Model.Event(R.drawable.i4, "Event D", "24 November 1998", LatLng(-7.279664, 112.797424)),
            Model.Event(R.drawable.i5, "Event E", "25 November 1998", LatLng(-7.280803, 112.796051)),
            Model.Event(R.drawable.i6, "Event F", "26 November 1998", LatLng(-7.284006, 112.794082)),
            Model.Event(R.drawable.i7, "Event G", "27 November 1998", LatLng(-7.281681, 112.794141)),
            Model.Event(R.drawable.i8, "Event H", "28 November 1998", LatLng(-7.280343, 112.792457))
        )
    }
}
