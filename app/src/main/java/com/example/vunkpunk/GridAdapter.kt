package com.example.vunkpunk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class GridAdapter(private val context: Context, private val cards: List<Card>) :
    BaseAdapter() {

    override fun getCount(): Int = cards.size

    override fun getItem(position: Int): Any = cards[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.grid_card_example, parent, false)

        val cardImg: ImageView = view.findViewById(R.id.card_image)
        val cardTitle: TextView = view.findViewById(R.id.card_title)
        val cardDormitory: TextView = view.findViewById(R.id.card_dormitory)
        val cardPrice: TextView = view.findViewById(R.id.card_price)
        val cardRating: TextView = view.findViewById(R.id.card_rating)


        val gridItem = cards[position]
        cardImg.setImageResource(gridItem.imageId)
        cardTitle.text = gridItem.title
        cardDormitory.text = gridItem.dormitory
        cardPrice.text = gridItem.price
        cardRating.text = gridItem.rating.toString()

        return view
    }
}
