package com.holisticdev.chemicalsolutions

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logo_activity)

        val handler = Handler()
        handler.postDelayed({
            setContentView(R.layout.main_activity)
            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            val navController = findNavController(R.id.fragmentContainerView2)
            bottomNavigationView.setupWithNavController(navController)
                            },3000)


    }
    //FIRST FRAGMENT METHODS-------------------
    public fun ClearEdittext(){
        Log.d("activity","Activity working!")
        findViewById<EditText>(R.id.editTextText1).setText("")
        findViewById<EditText>(R.id.editTextText2).setText("")
        findViewById<EditText>(R.id.editTextText3).setText("")
        findViewById<EditText>(R.id.editTextText4).setText("")
        findViewById<TextView>(R.id.textViewVar).setText("")
        findViewById<TextView>(R.id.textViewResult).setText("")
    }

    public fun Calculate(){
        val data = listOf<String>(findViewById<EditText>(R.id.editTextText1).text.toString(),
            findViewById<EditText>(R.id.editTextText2).text.toString(),
            findViewById<EditText>(R.id.editTextText3).text.toString(),
            findViewById<EditText>(R.id.editTextText4).text.toString()
        )
        var counter: Int = 0

        for( i in data){
            if (i!=""){
                counter++
            }
        }
        if (counter==3){
            Toast.makeText(this, "Datos Correctos", Toast.LENGTH_SHORT).show()
            var result: Double
            if (data[0]==""){
                findViewById<TextView>(R.id.textViewVar).setText("Concentración inicial es:")
                result = data[2].toDouble()*data[3].toDouble()/data[1].toDouble()
                findViewById<TextView>(R.id.textViewResult).setText(result.toString())
            }
            else if(data[1]==""){
                findViewById<TextView>(R.id.textViewVar).setText("Volumen inicial es:")
                result = data[2].toDouble()*data[3].toDouble()/data[0].toDouble()
                findViewById<TextView>(R.id.textViewResult).setText(result.toString())
            }
            else if(data[2]==""){
                findViewById<TextView>(R.id.textViewVar).setText("Concentración final es:")
                result = data[0].toDouble()*data[1].toDouble()/data[3].toDouble()
                findViewById<TextView>(R.id.textViewResult).setText(result.toString())
            }
            else if(data[3]==""){
                findViewById<TextView>(R.id.textViewVar).setText("Volumen final es:")
                result = data[0].toDouble()*data[1].toDouble()/data[2].toDouble()
                findViewById<TextView>(R.id.textViewResult).setText(result.toString())
            }

        }else if(counter==4){
            Toast.makeText(this, "Debe digitar 3 datos", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Datos incompletos", Toast.LENGTH_SHORT).show()
        }
        //findViewById<TextView>(R.id.textViewResult).setText(data[0])



    }

    //SECOND FRAGMENT METHODS-------------------
    @RequiresApi(Build.VERSION_CODES.N)
    public fun test(data: String){
        //findViewById<EditText>(R.id.editTextTextFormulate).text = Html.fromHtml(data,Html.FROM_HTML_MODE_COMPACT) as Editable?

    }
}











