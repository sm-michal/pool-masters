package com.example.poolmasters

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poolmasters.db.DbHelper

class PlayersList : AppCompatActivity(), NewPlayer.NewPlayerDialogListener {

    private lateinit var dbHelper: DbHelper
    private lateinit var playersDataset: ArrayList<String>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var listAdapter: PlayersListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        dbHelper = DbHelper(applicationContext)

        playersDataset = dbHelper.getPlayers()
        listAdapter = PlayersListAdapter(playersDataset)

        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.players_list_view).apply {
            setHasFixedSize(true)

            this.layoutManager = viewManager

            this.adapter = listAdapter
        }
    }

    override fun onDialogPositiveClick(newPlayerName: String) {
        playersDataset.add(newPlayerName)
        listAdapter.notifyDataSetChanged()
    }

    fun onAddClick(view: View) {
        val newPlayerFragment = NewPlayer()
        newPlayerFragment.show(supportFragmentManager, "newPlayer")
    }
}