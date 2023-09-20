package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.controllers.AssignmentController;
import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentDTO;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class JUnitTestAssignment {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@MockBean
	private CourseRepository courseRepository;
	
	@InjectMocks
	private AssignmentController assignmentController; 
	
	
	@Test
	public void testCreateAssignment() throws Exception{
		MockHttpServletResponse response;
		AssignmentDTO assignment = new AssignmentDTO("Sample Assignment","2023-09-19", "Sample Course",123);

		
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(assignment);
		
		response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/assignment")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		
		response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/assignment?id=" + assignment.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		assertEquals("Sample Assignment", assignment.getAssignmentName());
		assertEquals("2023-09-19", assignment.getAssignmentDueDate());
		assertEquals("Sample Course", assignment.getCourseTitle());
		
	}
	
	@Test
	public void testGetAssignmentById() throws Exception{
		MockHttpServletResponse response;
		
		AssignmentDTO assignment = new AssignmentDTO("db design","2021-09-01", "CST 363 - Introduction to Database Systems" ,31045);

				
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(assignment);
		
		response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/assignment")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/assignment?id=" + assignment.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		assertEquals("db design", assignment.getAssignmentName());
		assertEquals("2021-09-01", assignment.getAssignmentDueDate());
		assertEquals("CST 363 - Introduction to Database Systems", assignment.getCourseTitle());
		assertEquals(31045, assignment.getCourseId());
		
		
	}
	
	@Test
	public void testUpdateAssignment() throws Exception{
//		Update(noAssignment/notInstructor)
		AssignmentDTO assignment = new AssignmentDTO("db design","2021-09-01","CST 363 - Introduction to Database Systems" ,31045);

		
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(assignment);
		
		MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/assignment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                		.andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		
		assignment = new AssignmentDTO("requirements","2021-09-02", "CST 363 - Introduction to Database Systems" ,31045);
		
		
		String updatedRequestBody = objectMapper.writeValueAsString(assignment);
		
		response = mvc.perform(
                MockMvcRequestBuilders
                        .put("/assignment/{id}", assignment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedRequestBody)
                        .accept(MediaType.APPLICATION_JSON))
                		.andReturn().getResponse();
		
		String jsonResponse = response.getContentAsString();
		ObjectMapper responseMapper = new ObjectMapper();
		Assignment updatedAssignment = responseMapper.readValue(jsonResponse, Assignment.class);
		
		String origName = updatedAssignment.getName();
		assertEquals("requirements", origName);
	}
	
	@Test
	public void testDeleteAssignment() throws Exception{
		AssignmentDTO assignment = new AssignmentDTO("requirements","2021-09-02", "CST 363 - Introduction to Database Systems",31045);

		
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(assignment);
		
		MockHttpServletResponse response = mvc.perform(
		        MockMvcRequestBuilders
		                .post("/assignment")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(requestBody)
		                .accept(MediaType.APPLICATION_JSON))
		        		.andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		
		response = mvc.perform(
				MockMvcRequestBuilders
					.delete("/assignment/{id}", assignment.getId())
					.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		assertEquals(null, assignment.getAssignmentName());
		//this would be null right? 
	}

}
