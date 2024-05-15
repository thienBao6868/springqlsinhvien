package com.Thienbao.qlsinhvien.controller;

import com.Thienbao.qlsinhvien.dto.StudentDto;
import com.Thienbao.qlsinhvien.model.Students;
import com.Thienbao.qlsinhvien.service.StudentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllStudent(@PathVariable Long id){
        return new ResponseEntity<>(studentsService.getStudentById(id), HttpStatus.OK);
    };

    @PostMapping("")
    public ResponseEntity<?> createStudent(@RequestBody Students student){
        return new ResponseEntity<>(studentsService.createStudent(student), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDto studentDto){
      return new ResponseEntity<>(studentsService.updateStudent(studentDto),HttpStatus.OK);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        return new ResponseEntity<>(studentsService.deleteStudent(id),HttpStatus.OK);
    };

}
