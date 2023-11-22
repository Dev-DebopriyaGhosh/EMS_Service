package com.service.management.EmployeeManagementService.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.model.RequestTracker;

@Repository
public interface  RequestTrackerDAO extends CrudRepository<RequestTracker, Long>{

	@Query("select rt from RequestTracker rt where rt.requestIdentifier= ?1")
	RequestTracker isRequestExists(String requestIdentifier);
	
}
