package com.vladder2312.passwordmanager.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Класс для работы с базой данных
 */
@Database(entities = [AccountEntity::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {
    abstract val accountDao: AccountDao
}