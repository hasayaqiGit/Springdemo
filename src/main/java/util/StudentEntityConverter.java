package util;

import entity.StudentEntity;
import vo.Student;

public class StudentEntityConverter {
    public static Student convertEntityToUser(StudentEntity studentEntity){
        if (studentEntity != null) {
            Student student = new Student();
            student.setId(studentEntity.getId());
            student.setName(studentEntity.getName());
            student.setEmail(studentEntity.getEmail());
            return student;
        } else {
            return null;
        }
    }
}
