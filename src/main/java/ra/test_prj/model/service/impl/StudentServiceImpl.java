package ra.test_prj.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.test_prj.model.entity.Student;
import ra.test_prj.model.repository.IStudentRepository;
import ra.test_prj.model.service.IStudentService;
import java.util.List;
@Service

public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);

    }

    @Override
    public List<Student> searchStudentsByName(String studentName) {
        return studentRepository.searchByName(studentName);
    }

    @Override
    public List<Student> sortStudentsByName(String direction) {
        if (direction.equals("asc")) {
            return studentRepository.findAll(Sort.by("studentName").ascending());
        }else {
            return studentRepository.findAll(Sort.by("studentName").descending());
        }

    }

    @Override
    public List<Student> sortByNameAndAge(String directionName, Integer studentAge) {
       if (directionName.equals("asc")) {
           if (studentAge.compareTo(0) < 0) {
               return studentRepository.findAll(Sort.by("studentName").and(Sort.by("studentAge")));
           }else {
               return studentRepository.findAll(Sort.by("studentName").and(Sort.by("studentAge").descending()));
           }
       }else {
           if (studentAge.compareTo(0) < 0) {
               return studentRepository.findAll(Sort.by("studentName").descending().and(Sort.by("studentAge")));
           }else {
               return studentRepository.findAll(Sort.by("studentName").descending().and(Sort.by("studentAge").descending()));
           }
       }
    }

    @Override
    public Page<Student> pageStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }


}
