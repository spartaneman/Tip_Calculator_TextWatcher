package com.example.tip_calculator_textwatcher



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.tip_calculator_textwatcher.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private  val tipCalc =  TipCalculator()

    var money = NumberFormat.getCurrencyInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.amountBill.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) { calculate() }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })
        binding.amountTipPercent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) { calculate() }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

    }
    private fun calculate() {
        var billAmount: Double = 0.0
        var billTip: Double = 0.0
        val amountBill1 = binding.amountBill
        val stringBillAmount = amountBill1.text.toString()
        if (stringBillAmount.isEmpty())
            billAmount = 0.0
        else
            billAmount = stringBillAmount.toDouble()
        tipCalc.bill = billAmount.toFloat()
        val amountTip1 = binding.amountTipPercent
        var stringTipAmount = amountTip1.text.toString()
        if (stringTipAmount.isEmpty())
            billTip = 0.0
        else if(stringTipAmount.toString().startsWith("."))
        {
            stringTipAmount = "0$stringTipAmount"
            billTip = stringTipAmount.toDouble()
        }
        else
            billTip = stringTipAmount.toDouble()
        tipCalc.tip = billTip.toFloat()
// tipCalc.bill=billAmount
// ask Model to calculate tip and total amounts
        val tip = tipCalc.tipAmount()
        val total = tipCalc.totalAmount()
// update the View with formatted tip and total amounts
        binding.amountTip.setText(money.format(tip.toDouble()))
        binding.amountTotal.setText(money.format(total.toDouble()))
    }
}