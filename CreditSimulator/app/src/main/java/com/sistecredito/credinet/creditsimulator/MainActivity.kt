package com.sistecredito.credinet.creditsimulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var inputEditTextCredit: TextInputEditText
    lateinit var inputEditTextQuotes: TextInputEditText
    lateinit var buttonCalculate: Button
    var credit: Int = 0
    var quota: Int = 0
    var quotaList: MutableList<Double> = emptyList<Double>().toMutableList()
    val interest = 0.01
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        inputEditTextCredit = findViewById(R.id.text_input_edit_text_valor_del_credito)
        inputEditTextQuotes = findViewById(R.id.text_input_edit_text_numero_de_cuotas)
        buttonCalculate = findViewById(R.id.button_calculate)
        listView = findViewById(R.id.list_view)
        buttonCalculate.setOnClickListener {
            credit = inputEditTextCredit.text.toString().toInt()
            quota = inputEditTextQuotes.text.toString().toInt()
            calculate()
        }
    }

    private fun calculate() {
        val quoteWithoutInterest = credit / quota
        while (credit > 0) {
            credit = credit - quoteWithoutInterest
            quotaList.add((quoteWithoutInterest + (interest * (credit))))
        }
        val adapter: ArrayAdapter<Double> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, quotaList)
        listView.adapter = adapter
        //quotaList.removeAll(quotaList)
    }
}