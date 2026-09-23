package com.example.data.wallet.Injection

import android.content.Context
import androidx.room.Room
import com.example.data.wallet.DataSource.DAOs.WalletDao
import com.example.data.wallet.DataSource.LedgerDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LedgerDataBaseModule {


    @Provides
    @Singleton
    fun provideLedgerDataBase(
        @ApplicationContext context : Context
    ) : LedgerDataBase{

        return Room.databaseBuilder<LedgerDataBase>(
            context = context,
            name = "Ledger_DataBase"
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    @Singleton
    fun provideWalletDao(
        ledgerDataBase: LedgerDataBase
    ) : WalletDao{
        return ledgerDataBase.walletDao()
    }

}