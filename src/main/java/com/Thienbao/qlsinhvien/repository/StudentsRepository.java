package com.Thienbao.qlsinhvien.repository;

import com.Thienbao.qlsinhvien.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Students,Long> {
}
