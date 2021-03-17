package com.jandres.practica1

import androidx.constraintlayout.solver.SolverVariableValues

data class User(
    val email : String? = null,
    val password : String? = null,
    var genre : String? = null,
    var hobbies : String? = null
)
/*SOBRAN LOS METODOS GET Y SET CON DATA CLASS
{
    fun getEmail()
    fun setEmail()
 }
 */