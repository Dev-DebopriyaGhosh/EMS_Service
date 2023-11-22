package com.service.management.EmployeeManagementService.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.model.IncomingRequest;

@Repository
public interface IncomingRequestDAO extends CrudRepository<IncomingRequest, Long>{

	@Query("select e from IncomingRequest e where e.status= ?1")
	public IncomingRequest searchEntityByUniqueIdentifier(String name);
}
