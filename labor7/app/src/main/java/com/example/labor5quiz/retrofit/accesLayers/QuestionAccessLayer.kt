package com.example.labor5quiz.retrofit.accesLayers

import com.example.labor5quiz.retrofit.Proxy
import com.example.labor5quiz.retrofit.models.BackEndResponse
import com.example.labor5quiz.retrofit.models.Quiz
import io.reactivex.Single

object QuestionAccessLayer {
    fun getQuestionsObservable(amount: Int): Single<BackEndResponse<List<Quiz>>> {
        return Single.create { emitter ->
            val response = Proxy.getQuestions(amount)

            if (response == null || response.responseCode!=0) {
                emitter.onError(Exception("Failed to get questions"))
            } else {
                emitter.onSuccess(response)
            }
        }
    }

}