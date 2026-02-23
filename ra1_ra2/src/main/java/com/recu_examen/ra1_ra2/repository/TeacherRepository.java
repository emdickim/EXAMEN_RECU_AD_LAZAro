package com.recu_examen.ra1_ra2.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.recu_examen.ra1_ra2.model.Teacher;

@Repository
public class TeacherRepository {
	private final JdbcTemplate jdbcTemplate;

	public TeacherRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final class TeacherRowMapper implements RowMapper<Teacher> {
		@Override
		public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
			Teacher teacher = new Teacher();
			teacher.setId(rs.getLong("id"));
			teacher.setNom(rs.getString("nom"));
			teacher.setGmail(rs.getString("gmail"));
			teacher.setTeMalaLlet(rs.getBoolean("te_mala_llet"));
			teacher.setMateria(rs.getString("materia"));
			teacher.setDataCreated(rs.getTimestamp("data_created").toLocalDateTime());
			teacher.setDataUpdated(rs.getTimestamp("data_updated").toLocalDateTime());
			return teacher;
		}
	}

	public List<Teacher> findAll() {
		String sql = "SELECT id, nom, gmail, te_mala_llet, materia, data_created, data_updated FROM teacher";
		return jdbcTemplate.query(sql, new TeacherRowMapper());
	}

	public int save(Teacher teacher) {
		String sql = "INSERT INTO teacher (nom, gmail, te_mala_llet, materia, data_created, data_updated) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,
				teacher.getNom(),
				teacher.getGmail(),
				teacher.isTeMalaLlet(),
				teacher.getMateria(),
				Timestamp.valueOf(teacher.getDataCreated()),
				Timestamp.valueOf(teacher.getDataUpdated()));
	}

	public int[] saveAll(List<Teacher> teachers) {
		String sql = "INSERT INTO teacher (nom, gmail, te_mala_llet, materia, data_created, data_updated) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		List<Object[]> batch = new ArrayList<>();
		for (Teacher teacher : teachers) {
			batch.add(new Object[] {
					teacher.getNom(),
					teacher.getGmail(),
					teacher.isTeMalaLlet(),
					teacher.getMateria(),
					Timestamp.valueOf(teacher.getDataCreated()),
					Timestamp.valueOf(teacher.getDataUpdated())
			});
		}
		return jdbcTemplate.batchUpdate(sql, batch);
	}
}
