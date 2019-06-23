package com.socgen.employee.controller;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.socgen.employee.model.Employee;
import com.socgen.employee.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService empService;

	@Test
	public void testRegisterEmployee() throws Exception {
		Employee mockEmployee = new Employee();
		mockEmployee.setDateOfBirth("1992-10-04");
		mockEmployee.setDepartment("LoanIQ");
		mockEmployee.setFirstName("Anil");
		mockEmployee.setGender("Male");
		mockEmployee.setLastName("Pargond");

		String exampleCourseJson = "{\"dateOfBirth\":\"1992-01-04\",\"department\":\"LoanIQ\",\"firstName\":\"abhay\",\"gender\": \"Male\",\"lastName\":\"deshmukh\"}";
		Mockito.when(empService.registerEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register/employee")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void testGetEmployees() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Employee e1 = new Employee();
		e1.setDateOfBirth("1992-10-04");
		e1.setDepartment("LoanIQ");
		e1.setFirstName("Anil");
		e1.setGender("Male");
		e1.setLastName("Pargond");

		Employee e2 = new Employee();
		e2.setDateOfBirth("1992-10-14");
		e2.setDepartment("LoanIQ");
		e2.setFirstName("abhay");
		e2.setGender("Male");
		e2.setLastName("deshmukh");
		List<Employee> mockEmployees = new ArrayList();
		mockEmployees.add(e2);
		mockEmployees.add(e1);

		Mockito.when(empService.getAllBy(Mockito.anyString())).thenReturn(mockEmployees);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/register/employee?sortDirection=firstName").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expected = "[\r\n" + "  {\r\n" + "    \"firstName\": \"abhay\",\r\n"
				+ "    \"lastName\": \"deshmukh\",\r\n" + "    \"gender\": \"Male\",\r\n"
				+ "    \"dateOfBirth\": \"1992-10-14\",\r\n" + "    \"department\": \"LoanIQ\"\r\n" + "  },\r\n"
				+ "  {\r\n" + "    \"firstName\": \"Anil\",\r\n" + "    \"lastName\": \"Pargond\",\r\n"
				+ "    \"gender\": \"Male\",\r\n" + "    \"dateOfBirth\": \"1992-10-04\",\r\n"
				+ "    \"department\": \"LoanIQ\"\r\n" + "  }\r\n" + "]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
