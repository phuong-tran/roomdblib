package com.pt.room.lib.di

import android.content.Context
import androidx.room.Room
import com.pt.room.lib.db.StudentDaoProxy
import com.pt.room.lib.db.StudentDataBase
import com.pt.room.lib.db.StudentEntityConverter
import com.pt.room.lib.db.StudentEntityLookup
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    private const val databaseName = "studentDB"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StudentDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            StudentDataBase::class.java,
            databaseName
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideStudentEntityConvertor() = StudentEntityConverter()

    @Provides
    @Singleton
    fun provideStudentLookupEntity(
        studentDataBase: StudentDataBase
    ) = StudentEntityLookup(studentDataBase.studentDao())

    @Provides
    @Singleton
    fun provideStudentDaoProxy(
        entityConvertor: StudentEntityConverter,
        entityLookup: StudentEntityLookup,
        studentDataBase: StudentDataBase
    ): StudentDaoProxy {
        return StudentDaoProxy(
            entityLookup,
            entityConvertor,
            studentDataBase.studentDao()
        )
    }
}