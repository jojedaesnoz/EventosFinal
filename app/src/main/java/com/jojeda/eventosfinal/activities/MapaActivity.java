package com.jojeda.eventosfinal.activities;


import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.jojeda.eventosfinal.R;
import com.jojeda.eventosfinal.base.Evento;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationServices;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import static com.jojeda.eventosfinal.activities.MainActivity.eventos;

public class MapaActivity extends AppCompatActivity implements View.OnClickListener {

	MapView mapaView;
	MapboxMap mapa;
	LocationServices servicioUbicacion;
	View eventoMapaLayout;
	Marker ultimoMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MapboxAccountManager.start(this, "pk.eyJ1Ijoiam9qZWRhZXNub3oiLCJhIjoiY2pwdTY2Z2doMGNiYjQzcDhob28waHA3cSJ9.V5oRJW-2XAeiAxGTKsGnlA");

		setContentView(R.layout.activity_mapa);

		mapaView = findViewById(R.id.mapaView);
		mapaView.onCreate(savedInstanceState);
		eventoMapaLayout = findViewById(R.id.include);

		servicioUbicacion = LocationServices.getLocationServices(this);

		final boolean modoEdicion = PreferenceManager.getDefaultSharedPreferences(
				this).getBoolean("opcion_edicion", false);

		mapaView.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(@NonNull MapboxMap mapboxMap) {
				if (mapa == null && modoEdicion) {
					mapa = mapboxMap;
					mapa.setOnMapLongClickListener(new MapboxMap.OnMapLongClickListener() {
						@Override
						public void onMapLongClick(@NonNull LatLng point) {
							eventoMapaLayout.setVisibility(View.VISIBLE);
							ultimoMarker = mapa.addMarker(new MarkerOptions().position(point));
						}
					});
				}

				double latitud = 40.414443;
				double longitud = -3.701045;
				LatLng madrid = new LatLng(latitud, longitud);

				for (Evento evento : eventos) {
					LatLng posicion = new LatLng(evento.getLatitud(), evento.getLongitud());
					mapa.addMarker(new MarkerOptions().position(posicion)
							.title(evento.getNombre()).snippet(evento.getDescripcion()));
				}

				CameraPosition position = new CameraPosition.Builder()
						.target(madrid) // Fija la posición
						.zoom(17) // Fija el nivel de zoom
						.tilt(30) // Fija la inclinación de la cámara
						.build();

				mapa.animateCamera(CameraUpdateFactory
						.newCameraPosition(position), 7000);

				mapaView.onResume();
			}
		});

		findViewById(R.id.btUbicacion).setOnClickListener(this);
		findViewById(R.id.include).setVisibility(View.GONE);
		eventoMapaLayout.findViewById(R.id.btGuardar).setOnClickListener(this);
	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.btUbicacion && mapa != null) {
			Location lastLocation = servicioUbicacion.getLastLocation();
			if (lastLocation != null)
				mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation), 16));

			mapa.setMyLocationEnabled(true);
		} else if (view.getId() == R.id.btGuardar){
			EditText etNombre = eventoMapaLayout.findViewById(R.id.etNombre);
			EditText etDescripcion = eventoMapaLayout.findViewById(R.id.etDescripcion);
			EditText etPrecio = eventoMapaLayout.findViewById(R.id.etPrecio);

			// Crear el evento y guardarlo
			Evento evento = new Evento();
			evento.setNombre(etNombre.getText().toString());
			evento.setDescripcion(etDescripcion.getText().toString());
			if (etPrecio.getText().toString().isEmpty())
				evento.setPrecio(0);
			else
				evento.setPrecio(Float.parseFloat(etPrecio.getText().toString()));
			evento.setLatitud(ultimoMarker.getPosition().getLatitude());
			evento.setLongitud(ultimoMarker.getPosition().getLongitude());
			eventos.add(evento);
			new MainActivity.TareaGuardarEvento(evento).execute();

			// Colocar la informacion en el marker
			ultimoMarker.setTitle(evento.getNombre());
			ultimoMarker.setSnippet(evento.getDescripcion());

			// Limpiar las cajas de texto
			etNombre.setText("");
			etDescripcion.setText("");
			etPrecio.setText("");

			eventoMapaLayout.setVisibility(View.GONE);
		}
	}

}
