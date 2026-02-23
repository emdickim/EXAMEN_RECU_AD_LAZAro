package com.recu_examen.ra1_ra2.model;

import java.time.LocalDateTime;

public class Teacher {
	private Long id;
	private String nom;
	private String gmail;
	private boolean teMalaLlet;
	private String materia;
	private LocalDateTime dataCreated;
	private LocalDateTime dataUpdated;

	public Teacher() {
	}

	public Teacher(Long id, String nom, String gmail, boolean teMalaLlet, String materia,
			LocalDateTime dataCreated, LocalDateTime dataUpdated) {
		this.id = id;
		this.nom = nom;
		this.gmail = gmail;
		this.teMalaLlet = teMalaLlet;
		this.materia = materia;
		this.dataCreated = dataCreated;
		this.dataUpdated = dataUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public boolean isTeMalaLlet() {
		return teMalaLlet;
	}

	public void setTeMalaLlet(boolean teMalaLlet) {
		this.teMalaLlet = teMalaLlet;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public LocalDateTime getDataCreated() {
		return dataCreated;
	}

	public void setDataCreated(LocalDateTime dataCreated) {
		this.dataCreated = dataCreated;
	}

	public LocalDateTime getDataUpdated() {
		return dataUpdated;
	}

	public void setDataUpdated(LocalDateTime dataUpdated) {
		this.dataUpdated = dataUpdated;
	}
}
