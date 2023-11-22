package com.service.management.EmployeeManagementService.interceptor;


import java.io.StringWriter;
import java.util.Date;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.interceptor.EndpointInterceptorAdapter;
import org.springframework.ws.soap.SoapMessage;

import com.management.model.IncomingRequest;
import com.service.management.EmployeeManagementService.services.EmployeeCreateService;

@Component
public class RequestLoggingInterceptor extends EndpointInterceptorAdapter  {
	
	@Autowired
	private EmployeeCreateService empService;

	public EmployeeCreateService getEmpService() {
		return empService;
	}

	public void setEmpService(EmployeeCreateService empService) {
		this.empService = empService;
	}
	
	private long reqId;

	public long getReqId() {
		return reqId;
	}

	public void setReqId(long reqId) {
		this.reqId = reqId;
	}

	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint)  {
		
		SoapMessage soapMessage = (SoapMessage) messageContext.getRequest();
		String xmlReq=null;
		String error=null;
		try {
			xmlReq = messageToXmlString(soapMessage);
		} catch (Exception e) {
			error= e.getMessage();
		}
		storeIncomingRequest(xmlReq,"Receive_Success",error);
		messageContext.setProperty("REQ_ID", getReqId());
		return true;
	}

/*	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		// TODO Auto-generated method stub
		//Extraction of Soap Result XML
		SoapMessage soapResp = (SoapMessage) messageContext.getResponse();
		String respStr =messageToXmlString(soapResp);
		
		//CReation of Unmarshaller
		JAXBContext jaxbContext= JAXBContext.newInstance(Acknowledgement.class);
		Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
		
		//Unmarshalling and changing the value of response object
		Acknowledgement resp = (Acknowledgement) unMarshaller.unmarshal(new StringReader(respStr));
		resp.setEmployeeId((int) getEmpId());
		
		//Converting back to soap response xml
		StringWriter respWriter = new StringWriter();
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(resp, new StreamResult(respWriter));

        // Create a new SOAP message
        SoapMessage soapMessage = xmlStringToMessage(respWriter.toString());
        messageContext.setResponse(soapMessage);
		return super.handleResponse(messageContext, endpoint);
	}*/


	private void storeIncomingRequest(String xmlReq, String status, String error) {
		if(xmlReq!=null) {
			IncomingRequest request = new IncomingRequest();
			request.setRequest(xmlReq);
			request.setStatus(status);
			request.setType("create");
			request.setRequestReceivedDate(new Date());
			String errorMsg = (error!=null || error=="")? error: "NO_ERROR";
			request.setError(errorMsg);
			setReqId(empService.createRequestSaveService(request));
		}
	}

	private String messageToXmlString(SoapMessage soapMessage) throws Exception {
		// TODO Auto-generated method stub
		Source source = soapMessage.getPayloadSource();
		StringWriter writer = new StringWriter();
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.transform(source, new StreamResult(writer));
		return writer.toString();
	}
}
