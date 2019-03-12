package com.jojeda.eventosfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jojeda.eventosfinal.base.Evento;

import java.util.Comparator;
import java.util.function.Function;

import static com.jojeda.eventosfinal.util.Constantes.EVENTO;

public class EventoActivity extends AppCompatActivity implements View.OnClickListener {

	private Evento evento;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evento);

		findViewById(R.id.btCancelar).setOnClickListener(this);
		findViewById(R.id.btGuardar).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btGuardar:
				guardarEvento();
				break;
			case R.id.btCancelar:
				onBackPressed();
				break;
		}
	}

	private void guardarEvento() {
		EditText etNombre = findViewById(R.id.etNombre);
		if (etNombre.getText().toString().isEmpty()) {
			Toast.makeText(this, "El campo nombre es obligatorio", Toast.LENGTH_SHORT).show();
			return;
		}

		EditText etDescripcion = findViewById(R.id.etDescripcion);
		EditText etPrecio = findViewById(R.id.etPrecio);
		EditText etX = findViewById(R.id.etX);
		EditText etY = findViewById(R.id.etY);

		// Si ha recibido un evento para modificar, lo borra y lo guarda
		if (getIntent().getSerializableExtra(EVENTO) != null) {
			evento = (Evento) getIntent().getSerializableExtra(EVENTO);
			ListadoActivity.eventos.remove(evento);
		} else {
			// Si no, guarda uno nuevo
			evento = new Evento();
		}

		evento.setNombre(etNombre.getText().toString());
		evento.setDescripcion(etDescripcion.getText().toString());
		evento.setPrecio(Float.parseFloat(etPrecio.getText().toString()));
		evento.setX(Float.parseFloat(etX.getText().toString()));
		evento.setY(Float.parseFloat(etY.getText().toString()));

		ListadoActivity.eventos.add(evento);
	}
}
