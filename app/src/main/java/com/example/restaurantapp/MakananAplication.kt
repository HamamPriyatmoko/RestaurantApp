package com.example.restaurantapp

import android.app.Application
import com.example.restaurantapp.data.MakananContainer

class MakananAplication : Application() {

    lateinit var container: MakananContainer

    override fun onCreate() {
        super.onCreate()

        container = MakananContainer()
    }
}