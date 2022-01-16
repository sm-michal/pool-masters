package com.example.poolmasters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NewGameCreator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_creator)

        supportActionBar?.title = getString(R.string.new_session)
    }
}