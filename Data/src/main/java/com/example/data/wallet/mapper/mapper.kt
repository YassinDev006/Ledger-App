package com.example.data.wallet.mapper

import com.example.data.wallet.DataSource.DataBaseEntitis.WalletEntity
import com.example.domain.wallet.Entities.Wallet
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDiskIOException
import android.database.sqlite.SQLiteFullException
import ResultModel.DataBaseErrors

fun Long.toDomain(): Double = this / 100.0

fun Double.toEntity(): Long = (this * 100).toLong()



fun WalletEntity.toWallet(): Wallet = Wallet(
    name = name,
    amount = amount.toDomain()
)

fun Wallet.toWalletEntity(): WalletEntity = WalletEntity(
    name = name,
    amount = amount.toEntity(),
    id = 0
)




fun Throwable.toDataBaseError(): DataBaseErrors {
    return when (this) {
        is SQLiteFullException -> DataBaseErrors.DISK_FULL
        is SQLiteConstraintException -> DataBaseErrors.CONSTRAINT_VIOLATION
        is SQLiteDiskIOException -> DataBaseErrors.IO_ERROR
        is NoSuchElementException -> DataBaseErrors.DATA_NOT_FOUND
        else -> DataBaseErrors.UNKNOWN
    }
}