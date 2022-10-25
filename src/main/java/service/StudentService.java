package service;

import vo.Student;

import java.util.List;

public interface StudentService {
    static Student findById(long id) {
        return null;
    }

    Student saveStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudentById(long id);

    List<Student> findAllStudents();

}
