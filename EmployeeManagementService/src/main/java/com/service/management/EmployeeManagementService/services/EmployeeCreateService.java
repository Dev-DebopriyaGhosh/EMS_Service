package com.service.management.EmployeeManagementService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.model.IncomingRequest;
import com.service.management.EmployeeManagementService.dao.IncomingRequestDAO;

@Service
public class EmployeeCreateService {

	@Autowired
	private IncomingRequestDAO reqDao;


	public IncomingRequestDAO getReqDao() {
		return reqDao;
	}

	public void setReqDao(IncomingRequestDAO reqDao) {
		this.reqDao = reqDao;
	}
	
	public long createRequestSaveService(IncomingRequest request) {
		IncomingRequest savedRequest = reqDao.save(request);
		return savedRequest.getEmpId();
	}
}
