package com.nd.tepia.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nd.tepia.entities.reports.AppFeedbackReport;

public interface AppFeedbackReportRepository extends MongoRepository<AppFeedbackReport, Long>{
}