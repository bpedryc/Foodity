package com.haxos.foodity.ui.main.tools.timer

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.haxos.foodity.R

class FoodityTimerAdapter(
        adapterContext: Context,
        private val timers: MutableList<FoodityTimer>
) : ArrayAdapter<FoodityTimer>(adapterContext, 0, timers) {

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, 1000)
            if (timers.isEmpty()) {
                return
            }
            timers.removeIf { it.secondsLeft <= 0L }
            notifyDataSetChanged()
        }
    }
    init {
        runnable.run()
    }

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val timer = timers[position]

        val view: View = recycledView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_timer, parent, false)

        val timerTime = view.findViewById<TextView>(R.id.time)
        var seconds = timer.secondsLeft

        val hours = seconds / 3600
        seconds -= hours * 3600

        val minutes = seconds / 60
        seconds -= minutes * 60

        timerTime.text = "$hours:$minutes:$seconds"

        val timerTitle = view.findViewById<TextView>(R.id.title)
        timerTitle.text = timer.title

        val cancelButton = view.findViewById<ImageButton>(R.id.action_cancel)
        cancelButton.setOnClickListener {
            timers.remove(timer)
            notifyDataSetChanged()
        }

        return view
    }

    override fun isEnabled(position: Int): Boolean {
        return false
    }
}
