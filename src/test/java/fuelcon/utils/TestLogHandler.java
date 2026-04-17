package fuelcon.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TestLogHandler extends Handler {

    private final List<LogRecord> records = new ArrayList<>();

    @Override
    public void publish(LogRecord logRecord){
        records.add(logRecord);
    }

    @Override
    public void flush(){
        //Not needed for testing
    }

    @Override
    public void close() throws SecurityException{
        //Not needed for testing
    }

    public List<LogRecord> getRecords(){
        return records;
    }
}
