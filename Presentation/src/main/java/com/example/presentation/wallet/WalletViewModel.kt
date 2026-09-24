package com.example.presentation.wallet

import ResultModel.DataBaseErrors
import ResultModel.Result
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.wallet.Repo.WalletRepo
import com.example.domain.wallet.Entities.Wallet
import com.example.presentation.wallet.WalletNavigation.WalletNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private var _wallets = MutableStateFlow<List<Wallet>>(emptyList())
    val wallets = _wallets.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var _navigate = MutableSharedFlow<WalletNavigation>()
    val navigate = _navigate.asSharedFlow()




    fun getWallet(){
        when(val result = walletRepo.getWallets()){
            is Result.Error -> {
                logDataBaseErrors(errorType = result.error)
            }
            is Result.Success -> {

                result.data.onEach { newListWallets ->

                    _wallets.update { newListWallets }

                    Log.i("wallet viewModel", "getWallet: data retrived succefully ")

                }.launchIn(viewModelScope)
            }
        }

    }


    fun updateWallet(wallet: Wallet){
        _isLoading.value = true
        viewModelScope.launch {
            when(val result = walletRepo.updateWallet(wallet)){
                is Result.Error -> {
                    logDataBaseErrors(errorType = result.error)
                }
                is Result.Success -> {
                    Log.i("UpdateWallet", "updateWallet: wallet updated")
                    _navigate.emit(WalletNavigation.NavigateToWalletScreen)
                }
            }
            _isLoading.value = false
        }
    }

    fun deleteWallet(wallet: Wallet){
        _isLoading.value = true

        viewModelScope.launch {
            when(val result = walletRepo.deleteWallet(wallet)){
                is Result.Error -> {
                    logDataBaseErrors(errorType = result.error)
                }
                is Result.Success -> {
                    Log.i("UpdateWallet", "updateWallet: wallet deleted")
                    _navigate.emit(WalletNavigation.NavigateToWalletScreen)
                }
            }
            _isLoading.value = false
        }


    }

    fun addWallet(wallet: Wallet){

        _isLoading.value = true

        viewModelScope.launch {
            when(val result = walletRepo.addWallet(wallet)){
                is Result.Error -> {
                    logDataBaseErrors(errorType = result.error)
                }
                is Result.Success -> {
                    Log.i("WalletViewModel", "addWallet: the wallet added to the dataBase  ")
                    _navigate.emit(WalletNavigation.NavigateToWalletScreen)
                }
            }
            _isLoading.value = false
        }

    }

    private fun logDataBaseErrors(errorType : DataBaseErrors){
        when(errorType){
            DataBaseErrors.DATA_NOT_FOUND -> Log.i("WalletViewModel", "addWallet: data not found ")
            DataBaseErrors.DISK_FULL -> Log.i("WalletViewModel", "addWallet: the disk is full ")
            DataBaseErrors.CONSTRAINT_VIOLATION ->  Log.i("WalletViewModel", "addWallet: A wallet with this name already exists. ")
            DataBaseErrors.IO_ERROR -> Log.i("WalletViewModel", "addWallet: background error")
            DataBaseErrors.UNKNOWN -> Log.i("WalletViewModel", "addWallet: an unexpected error ")
        }
    }
}