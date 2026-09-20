package com.example.data.wallet.DataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.wallet.DataSource.DAOs.WalletDao
import com.example.data.wallet.DataSource.DataBaseEntitis.TransactionEntity
import com.example.data.wallet.DataSource.DataBaseEntitis.WalletEntity


@Database(entities = [WalletEntity::class, TransactionEntity::class], version = 1, exportSchema = false)
abstract class LedgerDataBase : RoomDatabase(){

    abstract fun walletDao() : WalletDao

}