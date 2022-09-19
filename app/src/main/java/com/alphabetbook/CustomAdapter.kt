package com.alphabetbook
/**
 * The adapter for grid view
 * @author Thabang Thubane
 * @since 16 Sep 2022
 *
 */
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter: BaseAdapter {

    var nameList = ArrayList<Item>()
    var context: Context? = null

    constructor(nameList: ArrayList<Item>, context: Context?) : super() {
        this.nameList = nameList
        this.context = context
    }

    override fun getCount(): Int {
        return nameList.size
    }

    override fun getItem(p0: Int): Any {
        return nameList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val nameGridList = this.nameList[p0]
        val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        as LayoutInflater

        val nameView = inflator.inflate(R.layout.item, null)

        nameView.findViewById<ImageView>(R.id.image).setImageResource(nameGridList.image!!)
        nameView.findViewById<TextView>(R.id.textView).text = nameGridList.name!!

        return nameView

    }
}