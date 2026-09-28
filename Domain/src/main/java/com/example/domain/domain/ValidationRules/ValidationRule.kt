package com.example.domain.domain.ValidationRules

import com.example.domain.domain.Entities.Validation
import com.example.domain.domain.Entities.Wallet

interface ValidationRule {
    fun validate(wallet : Wallet) : Validation?
}