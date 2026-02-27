package com.winwin.travel.authapi.repository;

import com.winwin.travel.authapi.model.ProcessingLog;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProcessingLogRepository extends CrudRepository<ProcessingLog, UUID> {


}
