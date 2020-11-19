package com.example.firstexample.data;

import com.example.firstexample.data.models.Student;
import com.example.firstexample.data.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class FakeApi {

    private  static FakeApi instance;

    private List<Student> students = new ArrayList<Student>();

    private FakeApi(){}

    public static FakeApi getInstance(){
        if(instance == null){
            instance = new FakeApi();
            instance.students.add(new Student("121111","Student1Name","Student1Surname", Constants.loremIpsum));
            instance.students.add(new Student("123123","Student2Name","Student2Surname", Constants.loremIpsum));
            instance.students.add(new Student("112221","Student3Name","Student3Surname", Constants.loremIpsum));
            instance.students.add(new Student("113321","Student4Name","Student4Surname",Constants.loremIpsum));
            instance.students.add(new Student("142231","Student5Name","Student5Surname",Constants.loremIpsum));
        }

        return instance;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addNewStudent(Student student) {
        this.students.add(student);
    }

    public void deleteStudent(Student student){
        this.students.remove(student);
    }
}
