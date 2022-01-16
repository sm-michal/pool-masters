package com.example.poolmasters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RankTable : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank_table)

        supportActionBar?.title = getString(R.string.table)
    }
}