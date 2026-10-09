package com.example.data.wallet.Repo

import ResultModel.DataBaseErrors
import ResultModel.Result
import com.example.data.wallet.DataSource.DAOs.WalletDao
import com.example.data.wallet.mapper.toDataBaseError
import com.example.data.wallet.mapper.toDomain
import com.example.data.wallet.mapper.toWallet
import com.example.data.wallet.mapper.toWalletEntity
import com.example.domain.domain.Entities.Wallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    fun getWallets() : Result<Flow<List<Wallet>>, DataBaseErrors>{
        return try {
            val data = walletDao.getWallets()

            Result.Success(
                data.map { walletList ->
                    walletList.map { it.toWallet() }
                }
            )

        }catch (e : Exception){
            val errorType = e.toDataBaseError()

            Result.Error(errorType)
        }


    }

    suspend fun updateWallet(wallet : Wallet) : Result<Unit, DataBaseErrors>{
        return try {

            walletDao.updateWallet(wallet.toWalletEntity())

            Result.Success(data = Unit)

        }catch (e : Exception){
            val errorType = e.toDataBaseError()

            Result.Error(error = errorType)
        }

    }

    suspend fun deleteWallet(wallet : Wallet) : Result<Unit, DataBaseErrors>{
        return try {

            walletDao.deleteWallet(wallet.toWalletEntity())

             Result.Success(data = Unit)


        }catch (e : Exception){

            val errorType = e.toDataBaseError()

             Result.Error(errorType)

        }
    }

    fun getTotalBudget() : Result<Flow<Double?>, DataBaseErrors>{

         return try {
            val result = walletDao.getTotalBudget()

            Result.Success(result.map {
                it?.toDomain()
            })


        }catch (e : Exception){
            val errorType = e.toDataBaseError()

             Result.Error(errorType)

        }


    }


}