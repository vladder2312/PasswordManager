package com.vladder2312.passwordmanager.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladder2312.passwordmanager.domain.Account

/**
 * Описание сущности "Аккаунт"
 */
@Entity
data class AccountEntity(
    @PrimaryKey var id : String,
    @ColumnInfo(name = "username") var username : String,
    @ColumnInfo(name = "password") var password : String,
    @ColumnInfo(name = "siteURL") var siteURL : String,
    @ColumnInfo(name = "siteName") var siteName : String,
    @ColumnInfo(name = "comment") var comment : String
)  {
    fun transform(): Account{
        return Account(id,username,password,siteURL,siteName,comment)
    }
}