package com.example.data.wallet.DataSource.DataBaseEntitis

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallets")
data class WalletEntity (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo val name : String,
    @ColumnInfo val amount : Long,
)