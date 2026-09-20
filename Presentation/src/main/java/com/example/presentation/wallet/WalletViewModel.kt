package com.example.presentation.wallet

import ResultModel.DataBaseErrors
import ResultModel.Result
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.wallet.Repo.WalletRepo
import com.example.domain.wallet.Entities.Wallet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val walletRepo: WalletRepo
) : ViewModel() {


    fun addWallet(wallet: Wallet){
        viewModelScope.launch {
            when(val result = walletRepo.addWallet(wallet)){
                is Result.Error -> {
                    when(result.error){
                        DataBaseErrors.DATA_NOT_FOUND -> Log.i("WalletViewModel", "addWallet: data not found ")
                        DataBaseErrors.DISK_FULL -> Log.i("WalletViewModel", "addWallet: the disk is full ")
                        DataBaseErrors.CONSTRAINT_VIOLATION ->  Log.i("WalletViewModel", "addWallet: A wallet with this name already exists. ")
                        DataBaseErrors.IO_ERROR -> Log.i("WalletViewModel", "addWallet: background error")
                        DataBaseErrors.UNKNOWN -> Log.i("WalletViewModel", "addWallet: an unexpected error ")
                    }
                }
                is Result.Success -> {
                    Log.i("WalletViewModel", "addWallet: the wallet added to the dataBase  ")
                }
            }
        }
    }
}