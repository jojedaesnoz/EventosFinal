package com.jojeda.eventosfinal.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jojeda.eventosfinal.R;
import com.jojeda.eventosfinal.base.Evento;

import java.util.ArrayList;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

	private ArrayList<Evento> eventos;

	public EventoAdapter(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	@NonNull
	@Override
	public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.item_lista_eventos, viewGroup, false);
		return new EventoViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull EventoViewHolder viewHolder, int i) {
		Evento evento = eventos.get(i);

		viewHolder.nombre.setText(evento.getNombre());
		viewHolder.descripcion.setText(evento.getDescripcion());
		viewHolder.precio.setText(String.valueOf(evento.getPrecio()));
		String coordenadas = evento.getX() + ", " + eventos.get(i).getY();
		viewHolder.coordenadas.setText(coordenadas);
	}

	@Override
	public int getItemCount() {
		return eventos.size();
	}

	public class EventoViewHolder extends RecyclerView.ViewHolder {
		private TextView nombre, descripcion, precio, coordenadas;

		public EventoViewHolder(@NonNull View itemView) {
			super(itemView);

			this.nombre = itemView.findViewById(R.id.nombre);
			this.precio = itemView.findViewById(R.id.precio);
			this.descripcion = itemView.findViewById(R.id.descripcion);
			this.coordenadas = itemView.findViewById(R.id.coordenadas);
		}
	}
}
