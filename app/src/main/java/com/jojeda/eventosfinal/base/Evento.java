package com.jojeda.eventosfinal.base;

import java.io.Serializable;

public class Evento implements Serializable {

	private String nombre;
	private String descripcion;
	private float precio;
	private double latitud;
	private double longitud;

	public Evento(String nombre, String descripcion, float precio, double latitud, double longitud) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.latitud = latitud;
		this.longitud = longitud;
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

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
}
