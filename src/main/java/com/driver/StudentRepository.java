package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Repository
public class StudentRepository {

    HashMap<String, Student> studentmap;
    HashMap<String, Teacher> teachermap;
    HashMap<String, List<String>> pairmap;

    public StudentRepository()
    {
        studentmap = new HashMap<>();
        teachermap = new HashMap<>();
        pairmap = new HashMap<>();
    }

    public void addStudent(Student student)
    {
        studentmap.put(student.getName(), student);
    }
    public void addTeacher(Teacher teacher)
    {
        teachermap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student , String teacher)
    {
        List<String> list = null;
        if(!pairmap.containsKey(teacher))
        {
            list = new ArrayList<>();
            list.add(student);
            pairmap.put(teacher,list);
            return;
        }
        list = pairmap.get(teacher);
        for(String strstudent : list)
        {
            if(strstudent.equalsIgnoreCase(student))
            {
                return;
            }
        }
        list.add(student);
        pairmap.put(teacher,list);
    }
    public Student getStudentByName(String student)
    {
        return studentmap.getOrDefault(student,null);
    }
    public Teacher getTeacherByName(String teacher)
    {
        return teachermap.getOrDefault(teacher,null);
    }
    public List<String> getStudentsByTeacherName(String teacher)
    {
        return pairmap.getOrDefault(teacher,null);
    }
    public List<String> getAllStudents()
    {
        List<String> list = new ArrayList<>();
        for(String student : studentmap.keySet())
        {
            list.add(student);
        }
        return list;
    }
    public void deleteTeacherByName(String teacher)
    {
        if(teachermap.containsKey(teacher))
        {
            teachermap.remove(teacher);
            if(pairmap.containsKey(teacher))
            {
                List<String> list = pairmap.get(teacher);
                for(String student : list)
                {
                    studentmap.remove(student);
                }
                pairmap.remove(teacher);
            }
        }
    }
    public void deleteAllTeachers()
    {
        teachermap = new HashMap<>();
        for(String teacher : pairmap.keySet())
        {
            List<String> list = pairmap.get(teacher);
            for(String student : list)
            {
                studentmap.remove(student);
            }

        }
        pairmap = new HashMap<>();
    }

}
