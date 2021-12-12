package com.bignerdranch.android.androidwithkotlin.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.rxjava3.core.Single

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<T>): Single<List<Long>>

    @Update
    fun update(entity: T): Single<Int>

    @Update
    fun update(entities: List<T>): Single<Int>

    @Delete
    fun delete(entity: T): Single<Int>

    @Delete
    fun delete(entities: List<T>): Single<Int>
}
