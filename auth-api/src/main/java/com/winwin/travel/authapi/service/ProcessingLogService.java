package com.winwin.travel.authapi.service;

import com.winwin.travel.authapi.model.ProcessingLog;
import com.winwin.travel.authapi.model.User;

public interface ProcessingLogService {
    ProcessingLog saveProcessingLog(User user, String text, String result);
}
