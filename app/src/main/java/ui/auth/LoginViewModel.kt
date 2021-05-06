package ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.Account

class LoginViewModel(email: String, passWord: String): ViewModel() {
    var account: MutableLiveData<Account> = MutableLiveData()
//    var email1 : MutableLiveData<String> = MutableLiveData()
//    var pas : MutableLiveData<String> = MutableLiveData()
    init{
        account.value = Account(fullname = null, email = email, password = passWord)

    }
    fun setAccountInfo(email: String, passWord: String){
        account.value?.email = email
        account.value?.password = passWord
        account.postValue(account.value)
    }

}