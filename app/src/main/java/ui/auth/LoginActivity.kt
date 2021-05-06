package ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.week3.R
import com.example.week3.databinding.ActivityLoginBinding
import data.AccountDataStore
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginViewModelFactory: LoginViewModelFactory
    private lateinit var accountDataStore: AccountDataStore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginViewModelFactory = LoginViewModelFactory(email = "",passWord = "")
        loginViewModel = ViewModelProvider(this,loginViewModelFactory).get(LoginViewModel::class.java)
        accountDataStore = AccountDataStore(this)
        // read data from Singleton

        binding.apply {
            btnLogin.setOnClickListener{
                // get value from two inputs
                val email = edtEmail.text.toString().trim()
                val password  = edtPassWord.text.toString().trim()
                loginViewModel.setAccountInfo(email,password)

            }
        }
        binding.account = loginViewModel.account.value

        loginViewModel.account.observe(this, Observer { account->

            accountDataStore.user.asLiveData().observe(this, Observer {
                if (it?.email == account?.email && it?.passWord == account.password){
                    val intent = Intent(this@LoginActivity,RestaurantListActivity::class.java)
                    startActivity(intent)
                }else{
                    if (account?.email != "" || account?.password != ""){
                        Toast.makeText(applicationContext,"Email or Password is incorrect",Toast.LENGTH_LONG).show();
                    }

                }
            })
        })


    }
}


