package com.nvnrdhn.suitmediatest.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nvnrdhn.suitmediatest.R
import com.nvnrdhn.suitmediatest.model.Model

class EventAdapter(private val list: List<Model.Event>): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var onItemClick: ((Model.Event) -> Unit)? = null

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event: Model.Event = list[position]
        holder.bind(event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventViewHolder(inflater, parent)
    }

    inner class EventViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.event_list, parent, false)) {
        private var mName: TextView? = null
        private var mDate: TextView? = null
        private var mImage: ImageView? = null

        init {
            itemView.setOnClickListener { onItemClick?.invoke(list[adapterPosition]) }
            mName = itemView.findViewById(R.id.tv_event_name)
            mDate = itemView.findViewById(R.id.tv_event_date)
            mImage = itemView.findViewById(R.id.iv_event_image)
        }

        fun bind(event: Model.Event) {
            mName?.text = event.nama
            mDate?.text = event.tanggal
            mImage?.setImageResource(event.image)
        }
    }

}