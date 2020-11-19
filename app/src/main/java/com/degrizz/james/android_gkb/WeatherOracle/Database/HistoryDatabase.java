package com.degrizz.james.android_gkb.WeatherOracle.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.degrizz.james.android_gkb.WeatherOracle.Database.Dao.HistoryDao;
import com.degrizz.james.android_gkb.WeatherOracle.Database.Model.HistoryRecord;

@Database(entities = {HistoryRecord.class})
@TypeConverters()
public abstract class HistoryDatabase extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();
}
