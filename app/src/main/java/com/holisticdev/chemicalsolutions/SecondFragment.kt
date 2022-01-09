package com.holisticdev.chemicalsolutions

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import java.io.IOException
import java.lang.Exception
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment(),View.OnClickListener {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var spinnerList = ArrayList<CharSequence>()
    public var changer: Boolean = true
    //var spinnerList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        try {
            val openHelper = myDatabase(this.requireContext())
            val db = openHelper.readableDatabase

            val c = db.rawQuery("select name,formula from compounds order by name ASC", arrayOf())
            while (c.moveToNext()){
                spinnerList.add(Html.fromHtml("${c.getString(0)} - ${c.getString(1).replace("2","<sub>2</sub>").replace("3","<sub>3</sub>").replace("4","<sub>4</sub>") }"))
                Log.d("database",c.getString(1))
            }

        }catch (e: IOException){
            Log.d("error",e.toString())
        }
    }

    @SuppressLint("ResourceType", "Recycle")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_second, container, false)
        // Inflate the layout for this fragment
        val spinner: Spinner = v.findViewById(R.id.spinner)


        //spinnerList.add(Html.fromHtml("H<sub>2</sub>SO<sub>4</sub>"))
        //spinnerList.add(Html.fromHtml("HNO<sub>3</sub>"))
        // Create an ArrayAdapter using the string array and a default spinner layout
        spinner.adapter = ArrayAdapter<CharSequence>(this.requireContext(),android.R.layout.simple_spinner_dropdown_item,spinnerList)



        val btn: Button = v.findViewById(R.id.buttonTest)

        btn.setOnClickListener(this)
        v.findViewById<EditText>(R.id.editTextTextPersonName2).addTextChangedListener(textWatcher)

        return v
    }




    companion object {


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonTest ->{

                try {
                        Log.d("info","ok")
                        //(activity as MainActivity).test()
                }catch (e:Exception){
                    Log.d("error",e.toString())}
            }

        }
    }

    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val charactersUP: String = "HNCOPFASLM"
            val characteLOW: String = "aeoulgnr"
            val numbers: String = "1234567890"
            if (changer){changer= false}else{changer=true}
            var char = s.toString()
            if(char.length>0 && false){



                if (char.last() in (charactersUP+characteLOW+numbers)){
                    Log.d("info",char)
                    if (char.length>1 && char.last() in numbers){
                        var subindex = char.last()
                        char.drop(1)
                        char += "<sub>$subindex</sub>"
                        (activity as MainActivity).test(char)

                    }
                    //(activity as MainActivity).test(char)
                }else{
                    (activity as MainActivity).test(char.drop(1))
                }
            }

            //(activity as MainActivity).test(s.toString())


        }

        override fun afterTextChanged(s: Editable?) {

        }



    }






}




