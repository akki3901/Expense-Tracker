package com.webtomob.expensetracker.util
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import com.webtomob.expensetracker.R


class DropdownAdapter(context: Context, items: List<String>) :
    ArrayAdapter<String>(context, R.layout.item_exposed_dropdown_default, items) {

    private val noOpFilter = object : Filter() {
        private val noOpResult = FilterResults()
        override fun performFiltering(constraint: CharSequence?) = noOpResult
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {}
    }

    override fun getFilter() = noOpFilter
}

class KeyValueDropdownAdapter(context: Context, items: List<KeyValue>) :
    ArrayAdapter<KeyValue>(context, R.layout.item_exposed_dropdown_default, items) {

    private val noOpFilter = object : Filter() {
        private val noOpResult = FilterResults()
        override fun performFiltering(constraint: CharSequence?) = noOpResult
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {}
    }

    override fun getFilter() = noOpFilter
}

class KeyValue(
    var key: String,
    var value: String
) {
    override fun toString(): String {
        return value
    }
}