package com.jandres.practica1

import androidx.constraintlayout.solver.SolverVariableValues

data class User(
    val email : String? = null,
    val password : String? = null,
    var genre : String? = null,
    var hobbies : String? = null,
    var birthday : String? = null,
    var city : String? = null
)
