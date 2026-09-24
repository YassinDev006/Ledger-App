package com.example.data.wallet.mapper

import com.example.data.wallet.DataSource.DataBaseEntitis.WalletEntity
import com.example.domain.wallet.Entities.Wallet
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDiskIOException
import android.database.sqlite.SQLiteFullException
import ResultModel.DataBaseErrors
import androidx.core.net.toUri

fun Long.toDomain(): Double = this / 100.0

fun Double.toEntity(): Long = (this * 100).toLong()



fun WalletEntity.toWallet(): Wallet = Wallet(
    id = this.id,
    name = this.name,
    amount = this.amount.toDomain(),
    image = this.image?.toUri()
)

fun Wallet.toWalletEntity(): WalletEntity = WalletEntity(
    name = this.name,
    amount = this.amount.toEntity(),
    id = this.id,
    image = this.image.toString()
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