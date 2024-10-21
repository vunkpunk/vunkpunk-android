package com.example.vunkpunk

import android.os.Bundle
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var gridView: GridView
    lateinit var adapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gridView = findViewById(R.id.grid_view_cards)

        // Create list of cards
        val cards = listOf(
            Card(R.drawable.test_img, "Продам Смайлик", "21 общ", "шоколвдка",
            2.1),
                    Card(R.drawable.test_img, "Продам Смайлик", "21 общ", "шоколвдка",
            2.1),
                    Card(R.drawable.test_img, "Продам Смайлик", "21 общ", "шоколвдка",
            2.1),
                    Card(R.drawable.test_img, "Продам Смайлик", "21 общ", "шоколвдка",
            2.1),
                    Card(R.drawable.test_img, "Продам Смайлик", "21 общ", "шоколвдка",
            2.1),
                    Card(R.drawable.test_img, "Продам Смайлик", "21 общ", "шоколвдка",
            2.1),
                    Card(R.drawable.test_img, "Продам Смайлик", "21 общ", "шоколвдка",
            2.1),
                    Card(R.drawable.test_img, "Продам Смайлик", "21 общ", "шоколвдка",
            2.1),

        )
        adapter = GridAdapter(this, cards)
        gridView.adapter = adapter
    }
}