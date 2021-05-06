package ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.week3.databinding.ActivityProfileBinding
import com.example.week3.R
import data.AccountDataStore
import androidx.lifecycle.asLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityProfileBinding
    private  lateinit var profilViewModel : ProfileViewModel
    private  lateinit var profileViewModelFactory: ProfileViewModelFactory
    private lateinit var accountDataStore: AccountDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile)
        profileViewModelFactory = ProfileViewModelFactory(fullName = "",email = "",passWord = "")
        profilViewModel = ViewModelProvider(this,profileViewModelFactory).get(ProfileViewModel::class.java)
        accountDataStore = AccountDataStore(this)
        observeData()

        binding.apply {
            btnEdit.setOnClickListener{
                val email = edtEmail.getText().toString()
                val fullName = edtFullName.getText().toString()

                lifecycleScope.launch {
//                    val passWord = accountDataStore.getPassWord().toString()
//                    accountDataStore.storeData(fullName,email,passWord)
                    accountDataStore.setFullName(fullName)
                    accountDataStore.setEmail(email)
                }
                val intent = Intent(this@ProfileActivity,LoginActivity::class.java)
                startActivity(intent)
            }
        }


    }
    private fun observeData() {

        accountDataStore.user.asLiveData().observe(this, Observer {
            binding.edtEmail.setText(it?.email)
            binding.edtFullName.setText(it?.fullName)
        })
    }
}