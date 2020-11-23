package com.degrizz.james.android_gkb.WeatherOracle.Database;

import com.degrizz.james.android_gkb.WeatherOracle.Database.Dao.HistoryDao;
import com.degrizz.james.android_gkb.WeatherOracle.Database.Model.HistoryRecord;

import java.util.List;

public class HistorySource {

    private final HistoryDao historyDao;
    private List<HistoryRecord> history;

    public HistorySource(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    public List<HistoryRecord> getHistory() {
        if (history == null) {
            loadHistory();
        }
        return history;
    }

    private void loadHistory() {
        history = historyDao.getAllRecords();;
    }

    public long getHistoryRecordsCount() {
        return historyDao.getHistoryRecordCount();
    }

    public void addHistoryRecord(HistoryRecord newRecord) {
        historyDao.insertRecord(newRecord);
        loadHistory();
    }
}
