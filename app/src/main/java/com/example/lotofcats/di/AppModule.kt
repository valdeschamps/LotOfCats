package com.example.lotofcats.di

import com.example.lotofcats.repositories.Repository
import org.koin.dsl.module

val appmodule = module{
    single { Repository() }
}