package com.example.domain.domain.Entities

sealed interface Validation {

    data object Valid : Validation

    data object WalletNameInValid : Validation

    data object WalletAmountInValid : Validation

    data object Nothing : Validation
}