package com.jojeda.eventosfinal.util;


import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.UTMRef;

public class Util {

	public static LatLng DeUMTSaLatLng(double latitud, double longitud) {

		UTMRef utm = new UTMRef(latitud, longitud, 'N', 30);

		return utm.toLatLng();
	}
}
