package data

import android.content.Context
import androidx.datastore.CorruptionException
import androidx.datastore.DataStore


import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.flow.*
import java.io.InputStream
import java.io.OutputStream



class AccountDataStore(context: Context) {
    private val dataStore: DataStore<UserAccount>

    init {
        dataStore = context.createDataStore(
            fileName = "data.pb",
            serializer = UserSerializer
        )
    }

    val user: Flow<UserAccount?> = dataStore.data
        .map {
            it
        }
    suspend fun storeData(fullName: String, email: String, passWord: String) {
        dataStore.updateData { preference ->
            preference.toBuilder().setFullName(fullName).setEmail(email).setPassWord(passWord).build()

        }
    }

    suspend fun setFullName(fullName: String) {
        dataStore.updateData { preference ->
            preference.toBuilder().setFullName(fullName).build()

        }
    }
    suspend fun setPassWord(passWord: String) {
        dataStore.updateData { preference ->
            preference.toBuilder().setPassWord(passWord).build()

        }
    }
    suspend fun setEmail(email: String) {
        dataStore.updateData { preference ->
            preference.toBuilder().setEmail(email).build()

        }
    }






    suspend fun getPassWord(): String? {
        val passWord = dataStore.data.first().passWord
        return passWord
    }

}




object UserSerializer : Serializer<UserAccount> {

    override fun readFrom(input: InputStream): UserAccount {
        try {
            return UserAccount.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: UserAccount, output: OutputStream) {
        t.writeTo(output)
    }


}
