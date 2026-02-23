package com.recu_examen.ra1_ra2.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recu_examen.ra1_ra2.model.Teacher;
import com.recu_examen.ra1_ra2.service.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
	private final TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping
	public ResponseEntity<List<Teacher>> getAllTeachers() {
		List<Teacher> teachers = teacherService.findAll();
		if (teachers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(teachers);
	}

	@PostMapping
	public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher) {
		teacherService.create(teacher);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Teacher creat correctament");
	}

	@PostMapping("/bulk")
	public ResponseEntity<String> createTeachersBulk(@RequestParam("file") MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El fitxer esta buit");
		}

		try {
			List<Teacher> teachers = teacherService.parseTeachersJson(file.getBytes());
			int inserted = teacherService.createAll(teachers);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Inserits " + inserted + " registres correctament");
		} catch (IOException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("JSON invalid");
		}
	}
}
