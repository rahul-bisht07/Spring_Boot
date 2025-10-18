package com.AnujProject1.Anuj1.Service;

import com.AnujProject1.Anuj1.DTO.AddStudentDTO;
import com.AnujProject1.Anuj1.DTO.StudentDTO;
import com.AnujProject1.Anuj1.Entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentSer {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createNewStudent(AddStudentDTO addStudentDTO);

    void deleteStudentById(Long id);

    StudentDTO updateStudent(Long id, AddStudentDTO addStudentDTO);

    StudentDTO patchStudent(Long id, Map<String, Object> updates);
}
