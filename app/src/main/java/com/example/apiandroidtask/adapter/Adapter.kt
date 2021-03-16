package com.example.apiandroidtask.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.apiandroidtask.R
import com.example.apiandroidtask.model.RecyclerData
import com.squareup.picasso.Picasso

class Adapter(
    private val context: Context,
    var list: List<RecyclerData>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val name: TextView = view.findViewById(R.id.idView)
        val id: TextView = view.findViewById(R.id.nameView)
        val priceUsd: TextView = view.findViewById(R.id.priceView)
        val changePercent24Hr: TextView = view.findViewById(R.id.changeView)
        val symbol: ImageView = view.findViewById(R.id.imageView)
        val recyclerView: ConstraintLayout = view.findViewById(R.id.cryptConstraint)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition, v)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, v: View?)
    }

    fun setDataList(data: List<RecyclerData>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.crypt_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        val price: String = String.format("%.4f", data.priceUsd?.toFloat())
        val change: String = String.format("%.2f", data.changePercent24Hr?.toFloat())
        if (change.startsWith("-")) {
            holder.changePercent24Hr.setTextColor(Color.RED)
            holder.changePercent24Hr.text = "${change}%"
        } else {
            holder.changePercent24Hr.setTextColor(Color.GREEN)
            holder.changePercent24Hr.text = "+${change}%"
        }

        holder.name.text = data.name
        holder.priceUsd.text = "$${price}"
        holder.id.text = data.symbol

        if (position % 2 != 0) {
            holder.recyclerView.setBackgroundColor(Color.parseColor("#1e2126"))
        } else {
            holder.recyclerView.setBackgroundColor(Color.parseColor("#161a1d"))
        }
        Picasso.get()
            .load("https://static.coincap.io/assets/icons/${data.symbol?.toLowerCase()}@2x.png")
            .noPlaceholder()
            .error(R.drawable.ic_launcher_foreground)
            .fit()
            .into(holder.symbol)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

}