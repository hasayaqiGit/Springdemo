package controller;

import exception.StudentNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.StudentService;
import util.Constants;
import vo.ErrorResponse;
import vo.ResponseMessage;
import vo.Student;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@Api(value = "Student", description = "REST API for Students", tags={"Student"})

public class RestController {
    private static Logger logger = LoggerFactory.getLogger(RestController.class);

    StudentService studentService;

    Constants messages;

    @Autowired
    public RestController(StudentService studentService,Constants messages) {
        this.studentService = studentService;
        this.messages = messages;
    }
    /**
     * retrives single student
     *
     **/
    @ApiOperation(value = "gets a single student")
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudent(@PathVariable("id") long id) throws Exception {
        Student student = StudentService.findById(id);
        if (student == null) {
            throw new StudentNotFoundException(messages.getMessage("Student_NOT_FOUND"));
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    //http://localhost:8009/swagger-ui.html#/

    /** create a student **/
    @ApiOperation(value = "create a student")
    @RequestMapping(value = "/student", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ResponseMessage> createUser(@Validated @RequestBody Student student, UriComponentsBuilder ucBuilder) {
        Student savedStudent = studentService.saveStudent(student);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("STUDENT_CREATED",savedStudent), headers, HttpStatus.CREATED);
    }

    /**
     * update a student
     *
     **/
    @ApiOperation(value = "update a student")
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateUser(@PathVariable("id") long id, @RequestBody Student user){
        Student currentStudent = StudentService.findById(id);

        if (currentStudent == null) {
            throw new StudentNotFoundException("STUDENT_NOT_FOUND");
        }
        currentStudent.setId(user.getId());
        currentStudent.setName(user.getName());
        currentStudent.setEmail(user.getEmail());

        studentService.updateStudent(currentStudent);
        return new ResponseEntity<Student>(currentStudent, HttpStatus.OK);
    }

    /**
     * delete a student
     *
     * @throws Exception
     **/
    @ApiOperation(value = "delete a student")
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteStudent(@PathVariable("id") long id) {

        Student student = StudentService.findById(id);
        if (student == null) {
            throw new StudentNotFoundException("STUDENT_NOT_FOUND");
        }
        studentService.deleteStudentById(id);
        return new ResponseEntity<ResponseMessage>(HttpStatus.OK);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerUserNotFound(Exception ex) {
        logger.error("Cannot find student");
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
