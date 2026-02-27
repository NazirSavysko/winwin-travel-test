package com.winwin.travel.authapi.service;

import com.winwin.travel.authapi.model.ProcessingLog;
import com.winwin.travel.authapi.model.UserEntity;

public interface ProcessingLogService {
    ProcessingLog saveProcessingLog(UserEntity user, String text, String result);
}
