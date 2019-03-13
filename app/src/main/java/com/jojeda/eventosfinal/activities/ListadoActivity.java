package com.jojeda.eventosfinal.activities;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jojeda.eventosfinal.R;
import com.jojeda.eventosfinal.base.Evento;
import com.jojeda.eventosfinal.util.EventoAdapter;

import java.util.ArrayList;
import java.util.Random;

import static com.jojeda.eventosfinal.activities.MainActivity.eventos;
import static com.jojeda.eventosfinal.util.Constantes.EVENTO;

public class ListadoActivity extends AppCompatActivity implements View.OnClickListener {

	public static final int MODIFICAR_EVENTO = 0;
	private static final int CREAR_EVENTO = 1;
	private EventoAdapter adapter;
	private ListView lista;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado);

		lista = findViewById(R.id.listView);
		adapter = new EventoAdapter(this, R.layout.item_lista_eventos, eventos);
		lista.setAdapter(adapter);

		findViewById(R.id.floatingActionButton).setOnClickListener(this);

		// Si no esta en modo edicion, no crea el menu contextual
		if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("opcion_edicion", false))
			registerForContextMenu(lista);
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(this, EventoActivity.class);
		startActivityForResult(intent, CREAR_EVENTO);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.lista_context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

		int position = menuInfo.position;
		Evento evento = eventos.get(position);

		switch (item.getItemId()) {
			case R.id.salir:
				break;
			case R.id.borrarEvento:
				eventos.remove(evento);
				adapter.notifyDataSetChanged();
				break;
			case R.id.modificarEvento:
				Intent intent = new Intent(this, EventoActivity.class);
				intent.putExtra(EVENTO, evento);
				startActivityForResult(intent, MODIFICAR_EVENTO);
				break;
			default:
				return super.onContextItemSelected(item);
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (resultCode == RESULT_OK) {
			adapter.notifyDataSetChanged();
		}
	}

	private void refrescarLista() {
		adapter.notifyDataSetChanged();
	}
}
