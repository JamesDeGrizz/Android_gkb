package com.degrizz.james.android_gkb.WeatherOracle.Database.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {HistoryRecord.LOCATION, HistoryRecord.TEMPERATURE, HistoryRecord.DATE})})
public class HistoryRecord {
    public final static String ID = "id";
    public final static String LOCATION = "location";
    public final static String TEMPERATURE = "temperature";
    public final static String DATE = "date";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    public long id;

    @ColumnInfo(name = LOCATION)
    public String location;

    @ColumnInfo(name = TEMPERATURE)
    public String temperature;

    @ColumnInfo(name = DATE)
    public String date;
}
