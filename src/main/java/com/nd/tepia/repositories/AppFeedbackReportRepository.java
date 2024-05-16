package com.nd.tepia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nd.tepia.entities.reports.AppFeedbackReport;

public interface AppFeedbackReportRepository extends JpaRepository<AppFeedbackReport, Long>{
}