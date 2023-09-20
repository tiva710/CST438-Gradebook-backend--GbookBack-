package com.cst438.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentDTO;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;

@RestController
@CrossOrigin 
public class AssignmentController {
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/assignment")
	public AssignmentDTO[] getAllAssignmentsForInstructor() {
		// get all assignments for this instructor
		String instructorEmail = "dwisneski@csumb.edu";  // user name (should be instructor's email) 
		List<Assignment> assignments = assignmentRepository.findByEmail(instructorEmail);
		AssignmentDTO[] result = new AssignmentDTO[assignments.size()];
		for (int i=0; i<assignments.size(); i++) {
			Assignment as = assignments.get(i);
			AssignmentDTO dto = new AssignmentDTO(
					as.getId(), 
					as.getName(), 
					as.getDueDate().toString(), 
					as.getCourse().getTitle(), 
					as.getCourse().getCourse_id());
			result[i]=dto;
		}
		return result;
	}
	
	//Create (POST)
	@PostMapping("/assignment")
	public Assignment createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
		//Check courses instructor against the instructor calling
		Assignment assignment = new Assignment();
		Optional<Course> courses = courseRepository.findById(assignmentDTO.getCourseId());				 
		Course course = courses.get();
		//add an if not found error message
		
		assignment.setCourse(course);
		assignment.setName(assignmentDTO.getAssignmentName());
		assignment.setDueDate(Date.valueOf(assignmentDTO.dueDate()));
		 
		
		return assignmentRepository.save(assignment);
	}
	
	
	//Retrieve by id(GET)
	@GetMapping("/assignment/{id}")
	public Assignment getAssignmentById(@PathVariable("id") int id) {
		//CONFIRM IT IS AN INSTRUCTOR GETTIG THE ASSIGNMENT
		return assignmentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
	}
	
	//Update (PUT)
	@PutMapping("/assignement/{id}")
	public Assignment updateAssignment(int id, @RequestBody AssignmentDTO assignmentDTO) {
		Assignment existingAssignment = assignmentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Assignment not found"));
		
		existingAssignment.setName(assignmentDTO.getAssignmentName());
		existingAssignment.setDueDate(Date.valueOf(assignmentDTO.dueDate()));
		Optional<Course> courses = courseRepository.findById(assignmentDTO.getCourseId());
		Course course = courses.get();
		
		existingAssignment.setCourse(course);
		
		return assignmentRepository.save(existingAssignment);
		
	}
	
	//Delete (DELETE)
	@DeleteMapping("/assignment/{id}")
	public void deleteAssignment(@PathVariable("id") int id){
		//CONFIRM IT IS AN INSTRUCTOR DELETING THE ASSIGNMENT
		Assignment existingAssignment = assignmentRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));

		//console log existingAssignment? Or return existing assignment?
		assignmentRepository.delete(existingAssignment);
		
	}
	
	
	
}
