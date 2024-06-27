package com.mhfran.imcmobilecalculator

import android.icu.text.DecimalFormat
import androidx.cardview.widget.CardView
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {

    /*
    1. Ambas variables view no son accesibles desde fuera de esa clase
    2. Lateinit es un modificador para variables no nulas (no aceptan NULL). La inicialización de la variable se
       realizará posteriormente, útil cuando no se puede inicializar la variable en el momento de su declaración.
    3. Var porque son variables que su valor puede cambiar después de iniciarse.
    */

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 50
    private var currentHeight: Int = 120
    private var currentAge: Int = 30


    // lateinit porque la variable no se inicia al declararla, pero se hará antes de su ejecución.
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge:TextView
    private lateinit var btnCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners() // son las funciones que están escuchando
        initUI() // inicia la interfaz
    }

    /*
    1. La función initComponents es un método privado que se encarga de inicializar las View.
    2. findViewById es una función que busca una vista con el ID especificado y devuelve una referencia.
    3. Al estar las variables declaradas con el modificador "lateinit", deben inicializar antes de su uso.
     */
    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight =
            findViewById(R.id.tvHeight) // buscamos el valor del Texto que va encima del RangeSlader
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    // Cada vez que pulsemos en Las View llamaremos al método setGenderColor para cambiar el color del fondo
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender() // se llama primero a este para que cambie el color del back dependiendo de si es true o false
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ -> // cada vez que el usuario cambia el valor del Slider, se actualiza para mostrar el nuevo.
            val df = DecimalFormat("#.##") // formateamos el número con máximo 2 decimales
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight += -1
            setWeight()
        }

        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {

    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100  * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    /* Este método se va a llamar antes que el setGenderColor para alternar la selección de género */
    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected

    }

    /* Llama a la función para saber cuál es el color que está seleccionado */
    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackGroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackGroundColor(isFemaleSelected))
    }


    /* Escogemos un color u otro dependiendo si el componente está o no seleccionado */
    private fun getBackGroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    /*  */
    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}