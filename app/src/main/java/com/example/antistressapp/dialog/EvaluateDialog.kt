package com.example.antistressapp.dialog

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.antistressapp.dialog.EvaluateDialog.EvaluateDialogListener
import android.os.Bundle
import android.view.LayoutInflater
import com.example.antistressapp.R
import android.widget.RatingBar
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.lang.Exception

class EvaluateDialog : AppCompatDialogFragment() {
    private var listener: EvaluateDialogListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        val inflator = requireActivity().layoutInflater
        val view = inflator.inflate(R.layout.dialog_layout, null)
        val ratingBar = view.findViewById<View>(R.id.stressLevelBar) as RatingBar
        builder.setView(view).setTitle("How stressed are you?")
            .setPositiveButton("ok") { dialogInterface, i ->
                val evaluation = ratingBar.rating
                listener!!.applyEvaluation(evaluation)
            }
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as EvaluateDialogListener
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface EvaluateDialogListener {
        fun applyEvaluation(evaluation: Float)
    }
}