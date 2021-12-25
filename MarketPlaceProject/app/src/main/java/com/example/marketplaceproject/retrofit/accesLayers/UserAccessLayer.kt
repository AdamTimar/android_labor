package com.example.marketplaceproject.retrofit.accesLayers

import android.util.Log
import com.example.marketplaceproject.retrofit.Proxy
import com.example.marketplaceproject.retrofit.models.UserDetailsResult
import com.example.marketplaceproject.retrofit.models.LoginResult
import com.example.marketplaceproject.retrofit.models.RegistrationResult
import com.example.marketplaceproject.retrofit.models.UpdateUserResult
import io.reactivex.Single

object UserAccessLayer {
    fun getRegistrationObservable(email: String, userName: String, password:String): Single<RegistrationResult> {
        return Single.create { emitter ->
            val response = Proxy.register(email, userName, password)

            Log.d("response", response.toString())
            if (response == null) {
                emitter.onError(Exception("Failed to register user"))
            } else {
                when (response.code) {
                    303 -> emitter.onError(Exception("User already exists"))
                    200 -> emitter.onSuccess(response)
                    else -> emitter.onError(Exception("Failed to register user"))
                }
            }
        }
    }

    fun getLoginObservable(userName: String, password: String): Single<LoginResult>{
        return Single.create { emitter ->
            val response = Proxy.login(userName,password)

            Log.d("response",response.toString())
            if (response == null){
                emitter.onError(Exception("Failed to login"))
            }
            else {
                if(response.token.isNotEmpty()) {
                    emitter.onSuccess(response)
                }
                else
                    emitter.onError(Exception("Failed to login"))
            }
        }
    }

    fun getResetPasswordObservable(userName: String, password: String): Single<Boolean>{
        return Single.create { emitter ->
            val response = Proxy.resetPassword(userName,password)

            Log.d("response",response.toString())
            if (response == null){
                emitter.onError(Exception("Failed to reset password"))
            }
            else {
                if (response.code != 200)
                    emitter.onError(Exception("Failed to reset password"))
                else
                    emitter.onSuccess(true)
            }
        }
    }

    fun getUserDetailsObservable(userName: String): Single<UserDetailsResult>{
        return Single.create { emitter ->
            val response = Proxy.getUserDetails(userName)

            Log.d("response",response.toString())
            if (response == null){
                emitter.onError(Exception("Failed get details"))
            }
            else {
                if (response.code != 200)
                    emitter.onError(Exception("Failed to reset password"))
                else
                    emitter.onSuccess(response.result[0])
            }
        }
    }

    fun getUpdateUserDetailsObservable(userName: String, phoneNumber: String, token: String): Single<UpdateUserResult>{
        return Single.create { emitter ->
            val response = Proxy.updateUserDetails(userName, phoneNumber, token)

            Log.d("response",response.toString())
            if (response == null){
                emitter.onError(Exception("Failed to update details"))
            }
            else {
                if (response.code != 200)
                    emitter.onError(Exception("Failed to update password"))
                else
                    emitter.onSuccess(response.result)
            }
        }
    }

    fun getResetPasswordWithTokenObservable(token: String, newPassword: String): Single<Boolean>{
        return Single.create { emitter ->
            val response = Proxy.resetPasswordWithToken(token, newPassword)

            Log.d("response",response.toString())
            if (response == null){
                emitter.onError(Exception("Failed to update password"))
            }
            else {
                if (response.code != 200)
                    emitter.onError(Exception("Failed to update password"))
                else
                    emitter.onSuccess(true)
            }
        }
    }

}