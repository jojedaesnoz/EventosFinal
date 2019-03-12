package com.jojeda.eventosfinal.base;

import java.io.Serializable;

public class Evento implements Serializable {

	private String nombre;
	private String descripcion;
	private float precio;
	private double x;
	private double y;

	public Evento(String nombre, String descripcion, float precio, double x, double y) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.x = x;
		this.y = y;
	}

	public Evento() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
