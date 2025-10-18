package com.AnujProject1.Anuj1.Controller;

import com.AnujProject1.Anuj1.DTO.AddStudentDTO;
import com.AnujProject1.Anuj1.DTO.StudentDTO;
import com.AnujProject1.Anuj1.Entity.Student;
import com.AnujProject1.Anuj1.Repository.StudentRepo;
import com.AnujProject1.Anuj1.Service.StudentSer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    private final StudentSer studentser;
    public StudentController(StudentSer studentser) {
        this.studentser = studentser;
    }
    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(studentser.getAllStudents());
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentser.getStudentById(id));
    }
    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid AddStudentDTO addStudentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentser.createNewStudent(addStudentDTO));
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentser.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id,@RequestBody
                                                    AddStudentDTO addStudentDTO){
        return ResponseEntity.ok(studentser.updateStudent(id,addStudentDTO));
    }
    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentDTO> patchStudent(@PathVariable Long id,
                                                   @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(studentser.patchStudent(id,updates));
    }
}
