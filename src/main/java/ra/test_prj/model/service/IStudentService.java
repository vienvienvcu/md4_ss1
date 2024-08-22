package ra.test_prj.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.test_prj.model.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    Student saveOrUpdate(Student student);
    void deleteStudentById(Integer id);
    List<Student> searchStudentsByName(String studentName);
    List<Student> sortStudentsByName(String direction);
    List<Student> sortByNameAndAge(String directionName, Integer age);
    Page<Student> pageStudents(Pageable pageable);




}
