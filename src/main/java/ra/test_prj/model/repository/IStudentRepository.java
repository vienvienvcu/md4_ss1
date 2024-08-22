package ra.test_prj.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.test_prj.model.entity.Student;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE LOWER(s.studentName) LIKE LOWER(CONCAT('%', :studentName, '%'))")
    List<Student> searchByName(@Param("studentName") String studentName);
}
