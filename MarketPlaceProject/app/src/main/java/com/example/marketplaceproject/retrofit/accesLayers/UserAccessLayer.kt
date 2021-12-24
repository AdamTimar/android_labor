package com.example.marketplaceproject.retrofit.accesLayers

import android.util.Log
import com.example.marketplaceproject.retrofit.Proxy
import com.example.marketplaceproject.retrofit.models.LoginResponse
import com.example.marketplaceproject.retrofit.models.PasswordResetResponse
import com.example.marketplaceproject.retrofit.models.RegistrationResponse
import io.reactivex.Single

object UserAccessLayer {
    fun getRegistrationObservable(email: String, userName: String, password:String): Single<RegistrationResponse> {
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

    fun getLoginObservable(userName: String, password: String): Single<LoginResponse>{
        return Single.create { emitter ->
            val response = Proxy.login(userName,password)

            Log.d("response",response.toString())
            if (response == null){
                emitter.onError(Exception("Failed to login"))
            }
            else {
                if(response.token.isNotEmpty())
                    emitter.onSuccess(response)
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

}