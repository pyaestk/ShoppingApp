package com.example.shoppingapp

import android.app.Application
import com.example.shoppingapp.di.RemoteDatasourceModule
import com.example.shoppingapp.di.RepositoryModule
import com.example.shoppingapp.di.UseCaseModule
import com.example.shoppingapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ShoppingApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ShoppingApplication)
            modules(
                listOf(
                    RepositoryModule,
                    UseCaseModule,
                    RemoteDatasourceModule,
                    viewModelModule
                )
            )
        }
    }
}