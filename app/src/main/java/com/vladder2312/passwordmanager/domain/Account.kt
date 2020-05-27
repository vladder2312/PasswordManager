package com.vladder2312.passwordmanager.domain

import com.vladder2312.passwordmanager.data.db.AccountEntity
import java.io.Serializable

data class Account(
    val id : String,
    val username : String,
    val password : String,
    val siteURL : String,
    val siteName : String,
    val comment : String
) : Serializable {
    fun transform(): AccountEntity {
        return AccountEntity(id,username,password,siteURL,siteName,comment)
    }
}