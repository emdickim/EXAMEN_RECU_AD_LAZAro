package com.recu_examen.ra1_ra2.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recu_examen.ra1_ra2.model.Teacher;
import com.recu_examen.ra1_ra2.repository.TeacherRepository;

@Service
public class TeacherService {
	private final TeacherRepository teacherRepository;
	private final ObjectMapper objectMapper;

	public TeacherService(TeacherRepository teacherRepository, ObjectMapper objectMapper) {
		this.teacherRepository = teacherRepository;
		this.objectMapper = objectMapper;
	}

	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	public void create(Teacher teacher) {
		LocalDateTime now = LocalDateTime.now();
		teacher.setDataCreated(now);
		teacher.setDataUpdated(now);
		teacherRepository.save(teacher);
	}

	public int createAll(List<Teacher> teachers) {
		if (teachers == null || teachers.isEmpty()) {
			return 0;
		}
		LocalDateTime now = LocalDateTime.now();
		for (Teacher teacher : teachers) {
			teacher.setDataCreated(now);
			teacher.setDataUpdated(now);
		}
		return teacherRepository.saveAll(teachers).length;
	}

	public List<Teacher> parseTeachersJson(byte[] jsonBytes) throws IOException {
		return objectMapper.readValue(
				jsonBytes,
				objectMapper.getTypeFactory().constructCollectionType(List.class, Teacher.class));
	}
}
