package com.example.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.history_activity.*
import lab4.R

class HistoryActivity : AppCompatActivity() {

    companion object {
        var OPERATIONS = "OPERATIONS"
    }


    private var operations: ArrayList<String> = ArrayList()
    var historyList = ArrayList<HistoricItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        recycler_view.adapter = HistoricAdapter(historyList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        operations = intent.getStringArrayListExtra(OPERATIONS)!!

        operations.forEach {
            historyList.add(0, HistoricItem(operation = it))//,result = result))
            recycler_view.adapter?.notifyItemInserted(0)
        }


    }

}