package com.example.data.wallet.DataSource.DataBaseEntitis

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.wallet.Entities.CategoryType
import com.example.domain.wallet.Entities.TransactionType

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val transactionId : Int,
    @ColumnInfo val walletId : Int,
    @ColumnInfo val transactionType : TransactionType,
    @ColumnInfo val category : CategoryType,
    @ColumnInfo val date : String,
    @ColumnInfo val description : String,
    @ColumnInfo val transactionAmount : Long
)