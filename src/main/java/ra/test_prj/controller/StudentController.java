package ra.test_prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.test_prj.model.entity.Student;
import ra.test_prj.model.service.IStudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId") Integer studentId) {
        return studentService.getStudentById(studentId);
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveOrUpdate(student);
    }
    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        Student studentUpdate = studentService.getStudentById(studentId);
        studentUpdate.setStudentName(student.getStudentName());
        studentUpdate.setStudentAge(student.getStudentAge());
        studentUpdate.setStudentBirthday(student.getStudentBirthday());
        studentUpdate.setStatus(student.getStatus());
        //cap nhat
        return studentService.saveOrUpdate(studentUpdate);
    }
    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        studentService.deleteStudentById(studentId);
    }

    // Thêm phương thức tìm kiếm
    @GetMapping("/search")
    public List<Student> searchStudentsByName(@RequestParam("studentName") String studentName) {
        return studentService.searchStudentsByName(studentName);
    }

    // sort theo name student

    @GetMapping("/sortByName")
    public ResponseEntity<List<Student>> sortByName(@RequestParam("direction") String direction) {
        List<Student> studentList = studentService.sortStudentsByName(direction);
        return new ResponseEntity<>(studentList, HttpStatus.OK);

    }
    @GetMapping("/sortByNameAndAge")
    public ResponseEntity<List<Student>> sortByNameAndAge(@RequestParam("directionName") String directionName,
                                                          @RequestParam("studentAge") Integer studentAge) {
        List<Student> studentList = studentService.sortByNameAndAge(directionName,studentAge);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }
    @GetMapping("getMapping")
    public ResponseEntity<Map<String,Object>> getPageStudents(@RequestParam(value = "page",defaultValue = "0") int page,
                                                              @RequestParam(value = "size",defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> studentPage = studentService.pageStudents(pageable);

        Map<String,Object> map = new HashMap<>();
//        Lấy danh sách sinh viên trong trang hiện tại.
        map.put("students",studentPage.getContent());
//        Lấy kích thước của trang hiện tại (số lượng sinh viên trên mỗi trang).
        map.put("total",studentPage.getSize());
//        Lấy tổng số sinh viên có trong cơ sở dữ liệu.
        map.put("totalItems",studentPage.getTotalElements());
//         Lấy tổng số trang có sẵn.
        map.put("totalPages",studentPage.getTotalPages());
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @GetMapping("getMappingSorting")
    public ResponseEntity<Map<String,Object>> getPageStudentsSortByName(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "3") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        // Kiểm tra giá trị của direction
        Sort.Direction sortDirection;
        if (direction.equalsIgnoreCase("asc")) {
            sortDirection = Sort.Direction.ASC;
        } else if (direction.equalsIgnoreCase("desc")) {
            sortDirection = Sort.Direction.DESC;
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Hoặc cung cấp thông báo lỗi chi tiết
        }
        Sort.Order order = new Sort.Order(sortDirection, "studentName");
        Pageable pageable = PageRequest.of(page,size, Sort.by(order));
        Page<Student> studentPage = studentService.pageStudents(pageable);

        Map<String,Object> map = new HashMap<>();
//        Lấy danh sách sinh viên trong trang hiện tại.
        map.put("students",studentPage.getContent());
//        Lấy kích thước của trang hiện tại (số lượng sinh viên trên mỗi trang).
        map.put("total",studentPage.getSize());
//        Lấy tổng số sinh viên có trong cơ sở dữ liệu.
        map.put("totalItems",studentPage.getTotalElements());
//         Lấy tổng số trang có sẵn.
        map.put("totalPages",studentPage.getTotalPages());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
