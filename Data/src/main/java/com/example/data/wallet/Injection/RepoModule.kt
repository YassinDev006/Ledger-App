package com.example.data.wallet.Injection

import com.example.data.wallet.DataSource.DAOs.WalletDao
import com.example.data.wallet.Repo.WalletRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideWalletRepo(walletDao: WalletDao) : WalletRepo{
        return WalletRepo(walletDao)
    }
}