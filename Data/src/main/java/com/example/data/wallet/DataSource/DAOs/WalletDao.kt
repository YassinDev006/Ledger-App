package com.example.data.wallet.DataSource.DAOs

import ResultModel.DataBaseErrors
import ResultModel.Result
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.wallet.DataSource.DataBaseEntitis.WalletEntity
import com.example.domain.wallet.Entities.Wallet
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWallet(wallet : WalletEntity)

    @Delete
    suspend fun deleteWallet(wallet: WalletEntity)

    @Update
    suspend fun updateWallet(wallet: WalletEntity)

    @Query("SELECT SUM(amount) FROM wallets")
    fun getTotalBudget() : Flow<Long?>

    @Query("SELECT * FROM wallets")
    fun getWallets() : Flow<List<WalletEntity>>

    @Query("SELECT * FROM wallets WHERE id = :id")
    fun getWalletId(id : Int) : Flow<WalletEntity>



}