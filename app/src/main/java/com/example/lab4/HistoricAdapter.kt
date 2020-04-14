package com.example.lab4


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.historic_cell.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class HistoricAdapter(private val historicList: List<HistoricItem>) :

    RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.historic_cell, parent, false)
        return HistoricViewHolder(item)
    }

    override fun getItemCount(): Int {
        return historicList.count()
    }

    override fun onBindViewHolder(holder: HistoricViewHolder, position: Int) {
        val currentItem = historicList[position]
        holder.bindHistoric(currentItem)

    }

    class HistoricViewHolder(v: View) : RecyclerView.ViewHolder(v){
        private var view: View = v
        private var item: HistoricItem? = null

        init {}

        fun bindHistoric(item: HistoricItem) {
            this.item = item
            view.operationTextView2.text = item.operation
            val toastResult = evalLeftToRight(item.operation)
            view.setOnClickListener {
                Toast.makeText(view.context, toastResult.toString(), Toast.LENGTH_SHORT).show()
            }

        }
        fun evalLeftToRight(text: String): Double {
            var result = 0.0
            var actualOperant = ' '
            val pattern: Pattern = Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/]))")
            val m: Matcher = pattern.matcher(text)
            while (m.find()) {
                val part: String = m.group()
                if (part == "+") actualOperant = '+' else if (part == "-") actualOperant =
                    '-' else if (part == "*") actualOperant =
                    '*' else if (part == "/") actualOperant = '/' else {
                    val actualNumber = part.toDouble()
                    when (actualOperant) {
                        ' ' -> result = actualNumber
                        '+' -> result += actualNumber
                        '-' -> result -= actualNumber
                        '*' -> result *= actualNumber
                        '/' -> result /= actualNumber
                    }
                }
            }
            return result
        }
    }


}


data class HistoricItem(val operation: String){}