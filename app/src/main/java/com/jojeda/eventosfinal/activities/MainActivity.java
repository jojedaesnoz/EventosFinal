package com.jojeda.eventosfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.jojeda.eventosfinal.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btListado).setOnClickListener(this);
		findViewById(R.id.btMapas).setOnClickListener(this);

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
}
