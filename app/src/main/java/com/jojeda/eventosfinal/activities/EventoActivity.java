package com.jojeda.eventosfinal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jojeda.eventosfinal.R;
import com.jojeda.eventosfinal.base.Evento;

import static com.jojeda.eventosfinal.activities.MainActivity.eventos;
import static com.jojeda.eventosfinal.util.Constantes.EVENTO;

public class EventoActivity extends AppCompatActivity implements View.OnClickListener {

	EditText etNombre;
	EditText etDescripcion;
	EditText etPrecio;
	EditText etX;
	EditText etY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evento);

		findViewById(R.id.btCancelar).setOnClickListener(this);
		findViewById(R.id.btRegistrar).setOnClickListener(this);

		etNombre = findViewById(R.id.etNombre);
		etDescripcion = findViewById(R.id.etDescripcion);
		etPrecio = findViewById(R.id.etPrecio);
		etX = findViewById(R.id.etX);
		etY = findViewById(R.id.etY);

		if (getIntent().getSerializableExtra(EVENTO) != null) {
			Evento evento = (Evento) getIntent().getSerializableExtra(EVENTO);
			etNombre.setText(evento.getNombre());
			etDescripcion.setText(evento.getDescripcion());
			etPrecio.setText(String.valueOf(evento.getPrecio()));
			etX.setText(String.valueOf(evento.getLatitud()));
			etY.setText(String.valueOf(evento.getLongitud()));
		}

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btRegistrar:
				guardarEvento();
				break;
			case R.id.btCancelar:
				onBackPressed();
				break;
		}
	}

	private void guardarEvento() {
		if (etNombre.getText().toString().isEmpty()) {
			Toast.makeText(this, "El campo nombre es obligatorio", Toast.LENGTH_SHORT).show();
			return;
		}

		Evento evento;
		// Si ha recibido un evento para modificar, lo borra y lo guarda
		if (getIntent().getSerializableExtra(EVENTO) != null) {
			evento = (Evento) getIntent().getSerializableExtra(EVENTO);
		} else {
			// Si no, guarda uno nuevo
			evento = new Evento();
		}

		evento.setNombre(etNombre.getText().toString());
		evento.setDescripcion(etDescripcion.getText().toString());

		evento.setPrecio(!etPrecio.getText().toString().isEmpty() ?
				Float.parseFloat(etPrecio.getText().toString()) : 0);

		evento.setLatitud(!etX.getText().toString().isEmpty() ?
				Double.parseDouble(etX.getText().toString()) : 0);

		evento.setLongitud(!etY.getText().toString().isEmpty() ?
				Double.parseDouble(etY.getText().toString()) : 0);


		eventos.add(evento);
		MainActivity.TareaGuardarEvento tareaGuardarEvento = new MainActivity.TareaGuardarEvento(evento);
		tareaGuardarEvento.execute();

		setResult(RESULT_OK);
		finish();
	}
}
