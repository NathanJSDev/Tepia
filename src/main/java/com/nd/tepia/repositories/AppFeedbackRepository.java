package com.nd.tepia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nd.tepia.entities.AppFeedback;
import com.nd.tepia.entities.PK.AppFeedbackPK;

public interface AppFeedbackRepository extends JpaRepository<AppFeedback, AppFeedbackPK>{
}