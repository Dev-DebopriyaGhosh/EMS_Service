package com.service.management.EmployeeManagementService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.model.RequestTracker;
import com.service.management.EmployeeManagementService.dao.RequestTrackerDAO;

@Service
public class RequestTrackingService {

	@Autowired
	private RequestTrackerDAO reqTrackerDao;

	public RequestTrackerDAO getReqTrackerDao() {
		return reqTrackerDao;
	}

	public void setReqTrackerDao(RequestTrackerDAO reqTrackerDao) {
		this.reqTrackerDao = reqTrackerDao;
	}
	
	public long requestTrackerSaveService(RequestTracker trckrRequest) {
		RequestTracker trckrSaveRequest = reqTrackerDao.save(trckrRequest);
		return trckrSaveRequest.getEmpId();
	}

	public boolean isRequestUnique(String requestIdentifier) {
		RequestTracker trckrRequest= getRequestUsingIdentifier(requestIdentifier);
		
		if(trckrRequest==null) {
			return true;
		}
		return false;
	}

	public RequestTracker getRequestUsingIdentifier(String requestIdentifier) {
		RequestTracker trckrRequest = reqTrackerDao.isRequestExists(requestIdentifier);
		return trckrRequest;
	}
}
