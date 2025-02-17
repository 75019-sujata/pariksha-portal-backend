package com.example.demo.RestComponents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Models.ResultModel;
import com.example.demo.Models.StudentModel;
import com.example.demo.ServicesForRest.ResultModelService;
import com.example.demo.ServicesForRest.StudentService;
import com.example.demo.jpaRepositories.ResultModelRepository;

@RestController
@RequestMapping("/api")
public class StudentsRest {
     
	@Autowired
	private StudentService studentservice;	
	
	@PostMapping("/saveStudent")
	public ResponseEntity<StudentModel> saveStudent(@RequestBody StudentModel Student){
		System.out.println("saveStudent url");
		StudentModel studSave=studentservice.saveStudent(Student);
		return ResponseEntity.ok(studSave);
	}
	@GetMapping("/getAllStudent")
	public  ResponseEntity<List<StudentModel>> getAllStudent(){
		  List<StudentModel> students = studentservice.getAllStudents();
	        return ResponseEntity.ok(students);
	}
	@PutMapping("/updateStudent")
	public ResponseEntity<StudentModel> updateStudent(@RequestBody StudentModel Student){
		 studentservice.updateStudent(Student);
	        return ResponseEntity.ok(Student);
	}
//	@DeleteMapping("/delete")
//	 public ResponseEntity<String> deleteStudent(@RequestBody StudentModel Student) {
//		studentservice.deleteStudentByPrn(Student.getStudentPrn());
//  return ResponseEntity.ok("Student with PRN " + Student.getStudentName() + " deleted successfully.");
//  
//}
	
	@DeleteMapping("/deleteStudent/{prn}")
    public ResponseEntity<String> deleteStudentByPRN(@PathVariable String prn) {
        try {
            // Call the service to delete the student by PRN
        	studentservice.deleteStudentByPRN(prn);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
	
	@Autowired
	ResultModelService resultService;
	
	@GetMapping("/getStudentByPrn/{prn}")
	 public ResponseEntity<StudentModel> getStudentByPrn(@PathVariable() String prn,@RequestParam String examId,@RequestParam String studentPrn ) {
		StudentModel studentValid=studentservice.findStudentbyPrn(prn);
		if (studentValid != null) {
	        return ResponseEntity.ok(studentValid);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@DeleteMapping("/deleteAll")
	 public ResponseEntity<String> deleteAllStudents() {
		studentservice.deleteAllStudents();
 return ResponseEntity.ok("All students are deleted successfully.");
 
}
	 @PostMapping("/upload")
	    public String uploadFile(@RequestParam("file") MultipartFile file) {
	       studentservice.processAndSaveFile(file);
		 return "File uploaded successfully";
	    }
	
}