package com.persnal.boardback.common.logger;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class DBLogger {
    private static volatile DBLogger _instance;
    private Logger logger = LoggerFactory.getLogger(DBLogger.class);

    public static DBLogger getInstance() {
        if (_instance == null) {
            synchronized (DBLogger.class) {
                if (_instance == null) {
                    _instance = new DBLogger();
                }
            }
        }
        return _instance;
    }

}
