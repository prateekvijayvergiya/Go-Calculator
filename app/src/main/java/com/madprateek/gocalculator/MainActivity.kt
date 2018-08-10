package com.madprateek.gocalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        one.setOnClickListener{appendExpression("1",true)}
        two.setOnClickListener{appendExpression("2",true)}
        three.setOnClickListener{appendExpression("3",true)}
        four.setOnClickListener{appendExpression("4",true)}
        five.setOnClickListener{appendExpression("5",true)}
        six.setOnClickListener{appendExpression("6",true)}
        seven.setOnClickListener{appendExpression("7",true)}
        eight.setOnClickListener{appendExpression("8",true)}
        nine.setOnClickListener{appendExpression("9",true)}
        zero.setOnClickListener{appendExpression("0",true)}


        add.setOnClickListener{appendExpression("+",false)}
        sub.setOnClickListener{appendExpression("-",false)}
        multiply.setOnClickListener{appendExpression("*",false)}
        divide.setOnClickListener{appendExpression("/",false)}
        open.setOnClickListener{appendExpression("(",false)}
        close.setOnClickListener{appendExpression(")",false)}

        clear.setOnClickListener {
            expressionText.text = ""
            resultText.text = ""
        }

        back.setOnClickListener {
            var string = expressionText.text.toString()
            if (string.isNotEmpty()){
                expressionText.text = string.substring(0,string.length-1)
            }
            resultText.text = ""
        }

        equals.setOnClickListener {
            try{
                val expression = ExpressionBuilder(expressionText.text.toString()).build()
                val result = expression.evaluate()
                var longResult = result.toLong()
                if(result == longResult.toDouble()){
                    resultText.text = longResult.toString()
                }else
                    resultText.text = result.toString()
            }catch (e:Exception){
                Log.d("Exception ", e.message)
            }

        }
    }

    fun appendExpression(string: String, clear: Boolean){

        if (resultText.text.isNotEmpty()){
            expressionText.text = ""
        }

        if(clear){
            resultText.text = ""
            expressionText.append(string)
        }else{
            expressionText.append(resultText.text)
            expressionText.append(string)
            resultText.text = ""
        }
    }
}
