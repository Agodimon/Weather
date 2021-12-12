package com.bignerdranch.android.androidwithkotlin.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.androidwithkotlin.data.database.entities.HistoryEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface HistoryDao : BaseDao<HistoryEntity> {

    @Query("SELECT * FROM HistoryEntity")
    fun getAllHistory(): Single<List<HistoryEntity>>

    @Query("SELECT * FROM HistoryEntity WHERE city = :city")
    fun getHistoryByCity(city: String): Single<List<HistoryEntity>>

    @Query("DELETE FROM HistoryEntity WHERE city = :city")
    fun deleteByCity(city: String): Single<Unit>
}
