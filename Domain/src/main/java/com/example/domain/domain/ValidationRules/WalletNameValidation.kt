package com.example.domain.domain.ValidationRules

import com.example.domain.domain.Entities.Validation
import com.example.domain.domain.Entities.Wallet

class WalletNameValidation() : ValidationRule {

    override fun validate(wallet: Wallet): Validation? {

        val walletName = wallet.name

        if (walletName.isEmpty() || walletName.isBlank()){
            return Validation.WalletNameInValid
        }

        return null

    }


}