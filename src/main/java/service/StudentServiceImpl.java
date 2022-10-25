package service;

import dao.StudentRepository;
import entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.StudentEntityConverter;
import vo.Student;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();

        return students.stream().map(e -> new Student(e.getId(), e.getName(), e.getEmail()))
                .collect(Collectors.toList());
    }

    public Student findById(long id) {
        StudentEntity studentEntity = (StudentEntity) studentRepository.findById(id).orElse(null);
        return StudentEntityConverter.convertEntityToUser(studentEntity);
    }

    @Transactional
    public Student saveStudent(Student student) {
        StudentEntity studentEntity = studentRepository.save(new StudentEntity(student.getId(), student.getName(), student.getEmail()));
        return StudentEntityConverter.convertEntityToUser(studentEntity);
    }



    public Student updateStudent(Student student) {
        StudentEntity studentEntity = studentRepository.saveAndFlush(new StudentEntity(student.getId(), student.getName(), student.getEmail()));
        return StudentEntityConverter.convertEntityToUser(studentEntity);
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

}
