package com.jojeda.eventosfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.jojeda.eventosfinal.base.Evento;
import com.jojeda.eventosfinal.util.EventoAdapter;

import java.util.ArrayList;

import static com.jojeda.eventosfinal.util.Constantes.EVENTO;

public class ListadoActivity extends AppCompatActivity implements View.OnClickListener {

	private EventoAdapter adapter;
	private RecyclerView lista;
	public static ArrayList<Evento> eventos;

	static {
		eventos = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			eventos.add(new Evento("Evento número " + i,
					"Se llevarán a cabo una serie de actividades relacionadas " +
							"con el ocio, la cultura y el deporte", 0,0, 0));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado);

		lista = findViewById(R.id.recyclerView);
		adapter = new EventoAdapter(eventos);
		lista.setLayoutManager(new LinearLayoutManager(this));
		lista.setAdapter(adapter);

		findViewById(R.id.floatingActionButton).setOnClickListener(this);
		registerForContextMenu(lista);
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(this, EventoActivity.class);
		startActivity(intent);
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
				onBackPressed();
				break;
			case R.id.borrarEvento:
				eventos.remove(evento);
				break;
			case R.id.modificarEvento:
				Intent intent = new Intent(this, EventoActivity.class);
				intent.putExtra(EVENTO, evento);
				startActivity(intent);
				break;
			default:
				return super.onContextItemSelected(item);
		}
		return true;
	}
}
