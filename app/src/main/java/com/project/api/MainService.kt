package com.project.api

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface MainService {

    @GET("https://my-json-server.typicode.com/typicode/demo/posts")
    fun demoAPI(): Single<ResponseBody>

}