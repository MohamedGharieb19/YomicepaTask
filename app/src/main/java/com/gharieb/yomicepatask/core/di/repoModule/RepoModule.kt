package com.gharieb.yomicepatask.core.di.repoModule

import com.gharieb.yomicepatask.core.common.sharedPreference.SharedPreferencesHelper
import com.gharieb.yomicepatask.data.remote.authentication.LoginApiService
import com.gharieb.yomicepatask.data.remote.main.PharmaciesApiService
import com.gharieb.yomicepatask.data.remote.main.ReturnRequestsApiService
import com.gharieb.yomicepatask.data.repo.authentication.LoginRepoImpl
import com.gharieb.yomicepatask.data.repo.main.PharmaciesRepoImpl
import com.gharieb.yomicepatask.data.repo.main.ReturnRequestsRepoImpl
import com.gharieb.yomicepatask.domain.repo.authentication.LoginRepo
import com.gharieb.yomicepatask.domain.repo.main.PharmaciesRepo
import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideLoginRepo(
        loginApiService: LoginApiService,
        sharedPreferencesHelper: SharedPreferencesHelper
    ): LoginRepo {
        return LoginRepoImpl(
            loginApiService,
            sharedPreferencesHelper = sharedPreferencesHelper
        )
    }

    @Provides
    fun providePharmaciesRepo(
        pharmaciesApiService: PharmaciesApiService,
        sharedPreferencesHelper: SharedPreferencesHelper
    ): PharmaciesRepo {
        return PharmaciesRepoImpl(
            pharmaciesApiService,
            sharedPreferencesHelper = sharedPreferencesHelper
        )
    }

    @Provides
    fun provideReturnRequestsRepo(
        returnRequestsApiService: ReturnRequestsApiService,
        sharedPreferencesHelper: SharedPreferencesHelper
    ): ReturnRequestsRepo {
        return ReturnRequestsRepoImpl(
            returnRequestsApiService,
            sharedPreferencesHelper = sharedPreferencesHelper
        )
    }


}