package com.example.domain.wallet.Entities

import android.net.Uri

data class Wallet(
    val id : Int,
    val name : String,
    val amount : Double,
    val image : Uri?
)