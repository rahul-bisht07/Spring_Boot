package com.AnujProject1.Anuj1.Repository;

import com.AnujProject1.Anuj1.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
}
