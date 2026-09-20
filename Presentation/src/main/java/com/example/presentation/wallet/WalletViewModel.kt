package com.example.presentation.wallet

import ResultModel.DataBaseErrors
import ResultModel.Result
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.wallet.Repo.WalletRepo
import com.example.domain.wallet.Entities.Wallet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val walletRepo: WalletRepo
) : ViewModel() {

    private var _wallet = MutableStateFlow<List<Wallet>>(emptyList())
    val wallet = _wallet.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()




    fun getWallet(){
        when(val result = walletRepo.getWallets()){
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

                result.data.onEach { newListWallets ->

                    _wallet.update { newListWallets }

                    Log.i("wallet viewModel", "getWallet: data retrived succefully ")

                }.launchIn(viewModelScope)
            }
        }

    }





    fun addWallet(wallet: Wallet){

        _isLoading.value = true

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

        _isLoading.value = false

    }
}