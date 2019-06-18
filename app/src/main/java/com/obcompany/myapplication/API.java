package com.obcompany.myapplication;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

interface API {
    @GET("/users")
    Single<Response<List<User>>> getUsers();
}
