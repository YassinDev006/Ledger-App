package com.example.domain.domain.useCases

import com.example.domain.domain.Entities.Validation
import com.example.domain.domain.Entities.Wallet
import com.example.domain.domain.ValidationRules.AmountValidation
import com.example.domain.domain.ValidationRules.ValidationRule
import com.example.domain.domain.ValidationRules.WalletNameValidation

class ValidationUseCase {

    private val listOfValidations = listOf(
        WalletNameValidation(),
        AmountValidation()
    )

    fun invoke(wallet : Wallet) : Validation{

        return listOfValidations.firstNotNullOfOrNull {
            it.validate(wallet)
        } ?: Validation.Valid

    }

}