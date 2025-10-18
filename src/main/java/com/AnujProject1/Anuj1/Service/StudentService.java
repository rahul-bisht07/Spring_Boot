package com.AnujProject1.Anuj1.Service;

import com.AnujProject1.Anuj1.DTO.AddStudentDTO;
import com.AnujProject1.Anuj1.DTO.StudentDTO;
import com.AnujProject1.Anuj1.Entity.Student;
import com.AnujProject1.Anuj1.Repository.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class StudentService implements StudentSer {
private final StudentRepo studentRepo;
private final ModelMapper modelMapper;
public StudentService(StudentRepo studentRepo, ModelMapper modelMapper){
    this.studentRepo = studentRepo;
    this.modelMapper=modelMapper;
}

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students=studentRepo.findAll();
        List<StudentDTO> studentDTOList=students
                .stream().map(Student -> new StudentDTO(Student.getId(),Student.getName(),Student.getEmail()))
                .toList();
        return studentDTOList;
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student=studentRepo.findById(id).orElse(null);
        StudentDTO studentDTO=modelMapper.map(student,StudentDTO.class);
        return studentDTO ;
    }

    @Override
    public StudentDTO createNewStudent(AddStudentDTO addStudentDTO) {
    Student newStudent=modelMapper.map(addStudentDTO,Student.class);
    Student student=studentRepo.save(newStudent);
        return modelMapper.map(student,StudentDTO.class);
    }

    @Override
    public void deleteStudentById(Long id) {
      if(!studentRepo.existsById(id)){
          throw new IllegalArgumentException("Student does not exist you understand it");

      }
      studentRepo.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentDTO addStudentDTO) {
    Student student=studentRepo.findById(id).orElseThrow(
            ()-> new IllegalArgumentException("Student does not exist :"+id));
    modelMapper.map(addStudentDTO,student);
    student=studentRepo.save(student);
    return modelMapper.map(student,StudentDTO.class);
}

    @Override
    public StudentDTO patchStudent(Long id, Map<String, Object> updates) {
    Student student=studentRepo.findById(id).orElseThrow(
            ()-> new IllegalArgumentException("Student id is incorrect : "+id));
    updates.forEach((field, Value)->{
        switch (field){
            case "name":student.setName((String) Value); break;
            case "email":student.setEmail((String) Value);break;
            default:
                throw new IllegalArgumentException("Field is not present in the database");
        }
    });
    Student savedstudent=studentRepo.save(student);

        return modelMapper.map(savedstudent,StudentDTO.class);
    }
}
