package com.example.data.wallet.Repo

import ResultModel.DataBaseErrors
import ResultModel.Result
import android.util.Log
import com.example.data.wallet.DataSource.DAOs.WalletDao
import com.example.data.wallet.mapper.toDataBaseError
import com.example.data.wallet.mapper.toWalletEntity
import com.example.domain.wallet.Entities.Wallet
import javax.inject.Inject

class WalletRepo @Inject constructor(
    val walletDao : WalletDao
) {
    suspend fun addWallet(wallet: Wallet) : Result<Unit, DataBaseErrors>{
        try {
            walletDao.insertWallet(wallet.toWalletEntity())


            return Result.Success(data = Unit)


        }catch (e : Exception){

            val errorType = e.toDataBaseError()

            return Result.Error(errorType)

        }

    }
}