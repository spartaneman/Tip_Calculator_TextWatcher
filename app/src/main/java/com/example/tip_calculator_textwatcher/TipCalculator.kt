package com.example.tip_calculator_textwatcher

class TipCalculator(newTip: Float = 0.0f, newBill: Float= 0.0f) {
    var tip: Float = newTip
        get() = field
        set(value){
            field = value
        }
    var bill: Float =  newBill
        get() = field
        set(value){
            field = value
        }

    fun tipAmount(): Float{
        return bill*tip
    }

    fun totalAmount(): Float{
        return bill + tipAmount()
    }
}