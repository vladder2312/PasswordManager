package com.vladder2312.passwordmanager.data.repositories

import android.util.Log
import com.vladder2312.passwordmanager.data.db.AccountDao
import com.vladder2312.passwordmanager.data.db.AccountEntity
import com.vladder2312.passwordmanager.domain.Account
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.intellij.lang.annotations.Flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AccountsRepository @Inject constructor(val accountDao: AccountDao) {

    fun saveAccount(account: Account) {
        accountDao.insert(account.transform())
    }

    fun updateAccount(account: Account){
        accountDao.update(account.transform())
    }

    fun deleteAccount(id: String){
        accountDao.delete(id)
    }

    fun getById(id: String) : Flowable<Account>{
        return accountDao.getById(id).map {
            it.transform()
        }
    }

    fun getAllAccounts() : Flowable<List<Account>> {
        return accountDao.getAll().map { list ->
            list.map {
                it.transform()
            }
        }
    }

    fun getAccountsByName(text: String) : Flowable<List<Account>>{
        return accountDao.getByName(text).map { list ->
            list.map {
                it.transform()
            }
        }
    }
}