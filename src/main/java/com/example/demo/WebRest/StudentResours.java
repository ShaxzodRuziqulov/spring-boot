package com.example.demo.WebRest;

import com.example.demo.Model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class
StudentResours {
    @GetMapping("/students/all")
    public ResponseEntity getAll() {
        Student student = new Student(1, "Shaxzod", "Ruziqulov", "Tugatgan");
        Student student2 = new Student(2, "Bahodir", "Ruziqulov", "3-kurs");
        Student student3 = new Student(3, "Sherzod", "Toshboyev", "4-kurs");

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity getOne(@PathVariable int id) {
        Student student = new Student(id, "" +
                "Bahodir", "Ruziqulov", "3-kurs");
        return ResponseEntity.ok(student);
    }
    @GetMapping("/students")
    public ResponseEntity getOne(@RequestParam int id,
                                 @RequestParam String name,
                                 @RequestParam String lastName,
                                 @RequestParam String course) {
        Student student = new Student(id, name, lastName, course);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student) {
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Student studentNew) {
        Student student = new Student(1, "Aziz", "Azizov", "1-kurs");
        student.setId(id);
        student.setName(studentNew.getName());
        student.setSurname(studentNew.getSurname());
        student.setCourse(studentNew.getCourse());

        return ResponseEntity.ok(student);
    }
    @DeleteMapping("students/{id}")
    public ResponseEntity delate(@PathVariable int id){
        return ResponseEntity.ok("Malumotlar o'chirildi");
    }


}
