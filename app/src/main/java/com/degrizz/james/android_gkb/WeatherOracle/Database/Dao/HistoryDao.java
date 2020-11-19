package com.degrizz.james.android_gkb.WeatherOracle.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.degrizz.james.android_gkb.WeatherOracle.Database.Model.HistoryRecord;

@Dao
public interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRecord(HistoryRecord record);

    @Update
    void updateRecord(HistoryRecord record);

    @Delete
    void deleteRecord(HistoryRecord record);

    @Query("DELETE FROM ")
    void deleteRecordById(long id);
}
