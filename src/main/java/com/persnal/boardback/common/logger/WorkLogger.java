package com.persnal.boardback.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkLogger {
    private static volatile WorkLogger _instance;
    private Logger logger = LoggerFactory.getLogger(WorkLogger.class);

    public static WorkLogger getInstance() {
        if (_instance == null) {
            synchronized (WorkLogger.class) {
                if (_instance == null) {
                    _instance = new WorkLogger();
                }
            }
        }
        return _instance;
    }

    public Logger getLogger() {
        return logger;
    }
}
