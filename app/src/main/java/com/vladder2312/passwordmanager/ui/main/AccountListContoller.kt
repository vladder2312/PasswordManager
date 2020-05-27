package com.vladder2312.passwordmanager.ui.main

import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.vladder2312.passwordmanager.R
import com.vladder2312.passwordmanager.data.db.AccountEntity
import com.vladder2312.passwordmanager.domain.Account
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class AccountListContoller(
    private val onClickAccountListener: (Account) -> Unit
) : BindableItemController<Account, AccountListContoller.AccountsListHolder>() {

    inner class AccountsListHolder(parent: ViewGroup) : BindableViewHolder<Account>(parent, R.layout.item_account){
        private val card : CardView = itemView.findViewById(R.id.account_item)
        private val siteName : TextView = itemView.findViewById(R.id.site_name_item)
        private val login : TextView = itemView.findViewById(R.id.login_item)
        private val password : TextView = itemView.findViewById(R.id.password_item)
        lateinit var account: Account

        init {
            card.setOnClickListener { onClickAccountListener(account) }
        }

        override fun bind(account: Account) {
            this.account = account
            siteName.text = account.siteName
            login.text = account.username
            password.text = account.password
        }
    }

    override fun getItemId(account: Account) = account.id

    override fun createViewHolder(parent: ViewGroup) = AccountsListHolder(parent)
}