package com.mhfran.imcmobilecalculator

import androidx.cardview.widget.CardView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    /*
    1. Ambas variables view no son accesibles desde fuera de esa clase
    2. Lateinit es un modificador para variables no nulas (no aceptan NULL). La inicialización de la variable se
       realizará posteriormente, útil cuando no se puede inicializar la variable en el momento de su declaración.
    3. Var porque son variables que su valor puede cambiar después de iniciarse.
    */

    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners() // son las funciones que están escuchando
        initUI() // inicia la interfaz
    }

    /*
    1. La función initComponents es un método privado que se encarga de inicializar las View.
    2. findViewById es una función que busca una vista con el IPD especificado y devuelve una referencia.
    3. Al estar las variables declaradas con el modificador "lateinit", deben inicializar antes de su uso.
     */
    private fun initComponents(){
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
    }

    // Cada vez que pulsemos en Las View llamaremos al método setGenderColor para cambiar el color del fondo
    private fun initListeners() {
       viewMale.setOnClickListener{
           changeGender() // se llama primero a este para que cambie el color del back dependiendo de si es true o false
           setGenderColor() }
       viewFemale.setOnClickListener {
           changeGender()
           setGenderColor() }
    }

    /* Este método se va a llamar antes que el setGenderColor para alternar la selección de género */
    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected

    }

    /* Llama a la función para saber cuál es el color que está seleccionado */
    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackGroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackGroundColor(isFemaleSelected))
    }


    /* Escogemos un color u otro dependiendo si el componente está o no seleccionado */
    private fun getBackGroundColor(isSelectedComponent:Boolean):Int{
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }
    /*  */
    private fun initUI() {
        setGenderColor()
    }
}