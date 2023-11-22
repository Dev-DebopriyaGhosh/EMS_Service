package com.service.management.EmployeeManagementService.config;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import com.service.management.EmployeeManagementService.interceptor.RequestLoggingInterceptor;
import com.service.management.EmployeeManagementService.services.EmployeeCreateService;

@Configuration
@EnableWs
public class EMSSoapConfig extends WsConfigurerAdapter{
	
	@Autowired
	private EmployeeCreateService empService;

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet,"/ems/*");
	}
	
	@Bean(name="acceptEmployee")
	public DefaultWsdl11Definition defaultWsdl11Defination(XsdSchemaCollection schema) {
		DefaultWsdl11Definition defaultWsdl11Defination = new DefaultWsdl11Definition();
		defaultWsdl11Defination.setPortTypeName("EMSService");
		defaultWsdl11Defination.setLocationUri("/ems/acceptEmployee");
		defaultWsdl11Defination.setTargetNamespace("http://www.employeeManagement.service/soap/createEmployee");
		defaultWsdl11Defination.setSchemaCollection(schema);
		return defaultWsdl11Defination;
	}
	
	@Bean
	public XsdSchemaCollection schema() {
		CommonsXsdSchemaCollection xsds = new CommonsXsdSchemaCollection(new ClassPathResource("createEmployee.xsd"));
		xsds.setInline(true);
		return xsds;
	}
	
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		RequestLoggingInterceptor interceptor =new RequestLoggingInterceptor();
		interceptor.setEmpService(empService);
		interceptors.add(interceptor);
	}
}
