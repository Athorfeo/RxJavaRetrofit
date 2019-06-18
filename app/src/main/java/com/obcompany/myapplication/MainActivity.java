package com.obcompany.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;

import java.util.List;
import java.util.Observable;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final API api = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build()
                .create(API.class);

        final Single<Response<List<User>>> observable = api.getUsers();
        observable.
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<List<User>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("User: ","onSubscribe");
                    }

                    @Override
                    public void onSuccess(Response<List<User>> listResponse) {
                        Log.i("User: ","onSuccess");
                        if(listResponse.body() != null){
                            for(User user: listResponse.body()){
                                user.toString();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("User: ","onError: " + e.toString());
                    }
                });
    }
}
