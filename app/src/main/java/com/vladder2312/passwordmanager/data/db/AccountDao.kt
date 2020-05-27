package com.vladder2312.passwordmanager.data.db

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface AccountDao {

    @Query("SELECT * FROM accountEntity")
    fun getAll(): Flowable<List<AccountEntity>>

    @Query("Select * From accountEntity Where siteName Like :text")
    fun getByName(text: String) : Flowable<List<AccountEntity>>

    @Query("Select * From accountEntity Where id=:id")
    fun getById(id : String) : Flowable<AccountEntity>

    @Insert
    fun insert(account : AccountEntity)

    @Query("Delete From accountentity Where id=:id")
    fun delete(id: String)

    @Update
    fun update(account: AccountEntity)
}