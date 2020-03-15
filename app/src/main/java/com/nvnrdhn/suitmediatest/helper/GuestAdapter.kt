package com.nvnrdhn.suitmediatest.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nvnrdhn.suitmediatest.R
import com.nvnrdhn.suitmediatest.model.Model

class GuestAdapter(private var list: List<Model.Guest>): RecyclerView.Adapter<GuestAdapter.GuestViewHolder>(){

    var onItemClick: ((Model.Guest) -> Unit)? = null
    val images: List<Int> = listOf(
        R.drawable.i1,
        R.drawable.i2,
        R.drawable.i3,
        R.drawable.i4,
        R.drawable.i5,
        R.drawable.i6,
        R.drawable.i7,
        R.drawable.i8
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GuestViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val guest = list[position]
        holder.bind(guest)
    }

    fun setList(list: List<Model.Guest>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class GuestViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.guest_list, parent, false)) {
        private var mName: TextView? = null
        private var mImage: ImageView? = null

        init {
            itemView.setOnClickListener { onItemClick?.invoke(list[adapterPosition]) }
            mName = itemView.findViewById(R.id.tv_guest_name)
            mImage = itemView.findViewById(R.id.iv_guest_image)
        }

        fun bind(guest: Model.Guest) {
            mName?.text = guest.name
            mImage?.setImageResource(images.random())
        }
    }
}