package com.Thienbao.qlsinhvien.service;

import com.Thienbao.qlsinhvien.dto.StudentDto;
import com.Thienbao.qlsinhvien.model.Students;
import com.Thienbao.qlsinhvien.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;

    public ResponseEntity<?> getStudentById (Long idStudent){
        try {
            Students student = studentsRepository.findById(idStudent)
                    .orElseThrow(() -> new RuntimeException("Student not found with id: " + idStudent));
            return new ResponseEntity<>(student,HttpStatus.OK);
        } catch (RuntimeException ex) {
            String errorMessage = ex.getMessage();
            return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
        }
    }

    // Sử dụng kiểu dữ liệu của Entity Entity Student
    public ResponseEntity<?> createStudent(Students student){
        try{
            if( student.getEmail() == null || student.getEmail().isEmpty()){
                throw new RuntimeException("email not empty or null");
            }
            if(student.getName() == null || student.getName().isEmpty() ){
                throw new RuntimeException("name Student not empty or null");
            }
            if(student.getDob() == null){
                throw new RuntimeException("dob not null or empty ");
            }
            Students result = studentsRepository.save(student);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (RuntimeException ex){
            String err = ex.getMessage();
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateStudent(StudentDto studentDto){

        try {
            if(studentDto.getIdStudent() == null) throw new RuntimeException("student id not null");
            if( studentDto.getStudentEmail() == null || studentDto.getStudentEmail().isEmpty()){
                throw new RuntimeException("email not empty or null");
            }
            if(studentDto.getStudentName() == null || studentDto.getStudentName().isEmpty() ){
                throw new RuntimeException("name Student not empty or null");
            }
            if(studentDto.getDob() == null ){
                throw new RuntimeException("Dob Student not empty or null");
            }
            Students student = studentsRepository.findById(studentDto.getIdStudent()).orElseThrow(()-> new RuntimeException("Student not found with id" + studentDto.getIdStudent()));

            student.setName(studentDto.getStudentName());
            student.setEmail(studentDto.getStudentEmail());
            student.setDob(studentDto.getDob());



             return new ResponseEntity<>(studentsRepository.save(student),HttpStatus.OK);
        }catch (RuntimeException ex){
            String err = ex.getMessage();
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

        }

    };
    public ResponseEntity<?> deleteStudent(Long id){
        try {
             studentsRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id"));
            studentsRepository.deleteById(id);
           return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        }catch (RuntimeException ex){
            String err = ex.getMessage();
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

    };
}
