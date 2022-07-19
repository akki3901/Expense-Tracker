package com.webtomob.expensetracker.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.webtomob.expensetracker.R
import com.webtomob.expensetracker.databinding.DataItemBinding
import com.webtomob.expensetracker.room.model.ExpensesData

class DataAdatptar(private val dataList: List<ExpensesData.Expenses>, private val listener:OnItemClickListener) :
    RecyclerView.Adapter<DataAdatptar.DataVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdatptar.DataVHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        val binding : DataItemBinding = DataItemBinding.inflate(layoutInflater, parent, false)
        return DataVHolder(binding)

    }

    override fun onBindViewHolder(holder: DataVHolder, position: Int) {
        holder.title.text= dataList[position].title
        holder.category.text= dataList[position].category
        holder.price.text= dataList[position].price
        holder.date.text= dataList[position].date
    }

    override fun getItemCount(): Int = dataList.size
    inner class DataVHolder(var binding: DataItemBinding) : RecyclerView.ViewHolder(binding.root) {
            val title = binding.title as TextView
            val category = binding.cat as TextView
            val price = binding.price as TextView
            val date = binding.date as TextView

            val imageView= binding.imageViewID as ImageView
            init {
                imageView.setOnClickListener {
                    listener.itemClick(it, dataList[position].id)
                }
            }

    }
    interface OnItemClickListener{
        fun itemClick(view: View,position: Int)
    }
}