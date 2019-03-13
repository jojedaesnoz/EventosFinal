package com.jojeda.eventosfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jojeda.eventosfinal.base.Evento;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.jojeda.eventosfinal.util.Constantes.URL_SERVIDOR;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	public static ArrayList<Evento> eventos;

	static {
		eventos = new ArrayList<>();
		generarEventosDesdeCliente();
	}

	private static void generarEventosDesdeCliente() {

		double latitudBase = 40.414443;
		double longitudBase = -3.701045;
		Random random = new Random();
		int masMenos = 1;

		for (int i = 0; i < 20; i++) {
			String nombre = "Evento número " + i;
			String descripcion = "Se llevarán a cabo una serie de actividades relacionadas " +
					"con el ocio, la cultura y el deporte";
			float precio = random.nextFloat() * 20;
			masMenos = random.nextBoolean() ? 1 : -1;
			double latitud = latitudBase + (random.nextDouble()/20 * masMenos);
			masMenos = random.nextBoolean() ? 1 : -1;
			double longitud = longitudBase + (random.nextDouble()/20 * masMenos);
			eventos.add(new Evento(nombre, descripcion, precio, latitud, longitud));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btListado).setOnClickListener(this);
		findViewById(R.id.btMapas).setOnClickListener(this);


		TareaDescargaEventos tareaDescarga = new TareaDescargaEventos();
		tareaDescarga.execute();
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent();

		switch (view.getId()) {
			case R.id.btListado:
				intent.setClass(this, ListadoActivity.class);
				startActivity(intent);
				break;
			case R.id.btMapas:
				intent.setClass(this, MapaActivity.class);
				startActivity(intent);
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(this, PreferenciasActivity.class);
		startActivity(intent);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
		String nombreUsuario = preferencias.getString("opcion_nombre_user", "Usuario/a");
		String mensaje = "Bienvenido/a " + nombreUsuario;

		setTitle(preferencias.getString("opcion_nombre_app", "Eventos de Madrid"));
		Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
	}

	public static class TareaDescargaEventos extends AsyncTask<Void, Void, Void> {
		List<Evento> eventos;

		@Override
		protected void onPostExecute(Void aVoid) {
			MainActivity.eventos = (ArrayList<Evento>) this.eventos;
		}

		@Override
		protected Void doInBackground(Void... voids) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			Evento[] eventosArray = restTemplate.getForObject(URL_SERVIDOR + "/eventos", Evento[].class);
			eventos =  Arrays.asList(eventosArray);
			return null;
		}
	}

	public static class TareaGuardarEvento extends AsyncTask<Void, Void, Void> {
		Evento evento;

		public TareaGuardarEvento(Evento evento) {
			this.evento = evento;
		}


		@Override
		protected Void doInBackground(Void... voids) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getForObject(URL_SERVIDOR +
					"/add_evento?nombre=" + evento.getNombre() +
					"&descripcion=" + evento.getDescripcion() +
					"&precio=" + evento.getPrecio() +
					"&latitud=" + evento.getLatitud() +
					"&longitud=" + evento.getLongitud(), Void.class);
			return null;
		}
	}
}
