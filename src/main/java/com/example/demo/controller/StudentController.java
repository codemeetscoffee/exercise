package com.example.demo.controller;

import com.example.demo.dto.PencilDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Pencil;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.PencilRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PencilRepository pencilRepository;

    @PostMapping("/student")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDto studentDto){
        Teacher teacher = new Teacher();
        teacher.setName(studentDto.getTeacher());

        teacherRepository.save(teacher);

        Student student = new Student(studentDto);
        student.setTeacher(teacher);
        student = studentRepository.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/get-students")
    public ResponseEntity<List<Student>> getAll(){
        return new ResponseEntity<>(studentRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/get-by-teacher-name")
    public ResponseEntity<List<Student>> getAll(@RequestParam String name){
        return new ResponseEntity<>(studentRepository.findByTeacherName(name), HttpStatus.OK);
    }

    @GetMapping("/get/teacher")
    public ResponseEntity<List<StudentDto>> getByAll(@RequestParam String name){
        List<Student> students = studentRepository.getStudentsByTeacherName(name);
        List<StudentDto> studentDtos = new ArrayList<>();
        students.stream().forEach(student -> {
            StudentDto s = new StudentDto();
            s.setName(student.getName());
            s.setTeacher(student.getTeacher().getName());
            studentDtos.add(s);});
        return new ResponseEntity<List<StudentDto>>(studentDtos, HttpStatus.OK);
    }

    @GetMapping("/get-teacher")
    public ResponseEntity<List<Student>> getTeacher(@RequestParam String name){
        List<Teacher> teachers = teacherRepository.findAll();
        List<Student> students = new ArrayList<>();
        for (Teacher t: teachers
             ) {
            Student s = new Student();
            s.setName(t.getStudent().getName());
            students.add(s);
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }


    @PostMapping("/save/student")
        public ResponseEntity<String> saveNewStudent(@RequestBody StudentDto studentDto){

        Teacher teacher = new Teacher();
        teacher.setName(studentDto.getTeacher());

        teacherRepository.save(teacher);


        Student student = new Student(studentDto);
        student.setTeacher(teacher);

        student = studentRepository.save(student);

        List<Pencil> pencils = new ArrayList<>();
            for (String pencil : studentDto.getPencilNames()){
                Pencil pencil1 = new Pencil();
                pencil1.setName(pencil);
                pencil1.setStudent(student);
                pencilRepository.save(pencil1);
                pencils.add(pencil1);
            }
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }
    }
