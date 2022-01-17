package com.example.poolmasters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.poolmasters.db.DbHelper
import java.lang.ClassCastException
import java.lang.IllegalStateException

class NewPlayer : DialogFragment() {

    private lateinit var listener: NewPlayerDialogListener

    private lateinit var dbHelper: DbHelper

    interface NewPlayerDialogListener {
        fun onDialogPositiveClick(newPlayerName: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            dbHelper = DbHelper(it.applicationContext)

            val builder = AlertDialog.Builder(it)

            val view =  requireActivity()
                    .layoutInflater
                    .inflate(R.layout.dialog_new_player, null)

            builder
                .setView(view)
                .setPositiveButton(R.string.add) { dialog, id ->
                    val newPlayerName = view.findViewById<EditText>(R.id.player_name).text.toString()
                    dbHelper.addPlayer(newPlayerName)
                    listener.onDialogPositiveClick(newPlayerName)
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                    dialog?.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NewPlayerDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement NewPlayerDialogListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_new_player, container, false)
    }
}