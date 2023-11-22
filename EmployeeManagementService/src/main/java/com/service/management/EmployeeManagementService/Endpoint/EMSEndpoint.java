package com.service.management.EmployeeManagementService.Endpoint;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.management.model.Acknowledgement;
import com.management.model.CreateEmployeeRequest;
import com.management.model.RequestTracker;
import com.service.management.EmployeeManagementService.services.RequestTrackingService;

@Endpoint
public class EMSEndpoint {
	
	@Autowired
	private RequestTrackingService reqTrckrServc;

	private static final String NAMESPACE="http://www.employeeManagement.service/soap/createEmployee";
	
	@PayloadRoot(namespace=NAMESPACE, localPart="createEmployeeRequest")
	@ResponsePayload
	public Acknowledgement createEmployee(@RequestPayload CreateEmployeeRequest empReq, MessageContext messageContext) throws XmlMappingException, IOException {
		
		long requestID = (long) messageContext.getProperty("REQ_ID");
		boolean isReqUnique = isRequestUnique(empReq.getRequestIdentifier());

		String transactionMsg=null;
		long empId=0;
		if(isReqUnique) {
			transactionMsg="RECEIVE_SUCCESS";
			RequestTracker reqTrckr = new RequestTracker();
			reqTrckr.setRequestIdentifier(empReq.getRequestIdentifier());
			reqTrckr.setReqId(requestID);
			empId = reqTrckrServc.requestTrackerSaveService(reqTrckr)+requestID+10000;
		}else {
			transactionMsg="RECORD_ALREADY_PRESENT";
			RequestTracker reqTrckr = reqTrckrServc.getRequestUsingIdentifier(empReq.getRequestIdentifier());
			empId=reqTrckr.getEmpId()+requestID+10000;
		}
		Acknowledgement resp = new Acknowledgement();
		resp.setEmployeeId((int) empId);
		resp.setTransactionMessage(transactionMsg);
		return resp;
	}

	private boolean isRequestUnique(String requestIdentifier) {
		return reqTrckrServc.isRequestUnique(requestIdentifier);
	}
}
