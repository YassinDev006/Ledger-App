package com.example.domain.domain.ValidationRules

import com.example.domain.domain.Entities.Validation
import com.example.domain.domain.Entities.Wallet

class AmountValidation() : ValidationRule {

    override fun validate(wallet: Wallet): Validation? {
        val walletAmount = wallet.amount

        val regex = Regex("^\\d+(\\.\\d+)?$")

        if (!regex.matches(walletAmount.toString())) {

            return Validation.WalletAmountInValid

        }

        return null

    }
}