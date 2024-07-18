package com.gharieb.yomicepatask.core.di.sharedPreferenceModule

import android.content.Context
import android.content.SharedPreferences
import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.APP_NAME
import com.gharieb.yomicepatask.core.common.sharedPreference.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(@ApplicationContext context: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(provideSharedPreferences(context))
    }

}