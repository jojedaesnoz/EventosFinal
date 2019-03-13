package com.jojeda.eventosfinal.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jojeda.eventosfinal.R;
import com.jojeda.eventosfinal.base.Evento;

import java.util.List;

public class EventoAdapter extends BaseAdapter {

	private Context context;
	private final int layout;
	private final List<Evento> eventos;
	
	public EventoAdapter(Context context, int layout, List<Evento> eventos) {
		this.context = context;

		this.layout = layout;
		this.eventos = eventos;
	}


	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		
		EventosViewHolder holder = null;
		
		if (view == null) {
			view = LayoutInflater.from(context).inflate(layout, null);
			
			holder = new EventosViewHolder();
			holder.nombre = view.findViewById(R.id.nombre);
			holder.precio = view.findViewById(R.id.precio);
			holder.descripcion = view.findViewById(R.id.descripcion);
			holder.coordenadas = view.findViewById(R.id.coordenadas);
			
			view.setTag(holder);
		} else {
			holder = (EventosViewHolder) view.getTag();
		}

		Evento evento = eventos.get(i);
		holder.nombre.setText(evento.getNombre());
		holder.descripcion.setText(evento.getDescripcion());
		holder.precio.setText(String.valueOf(evento.getPrecio()));
		String coordenadas = evento.getLatitud() + ", " + eventos.get(i).getLongitud();
		holder.coordenadas.setText(coordenadas);

		return view;
	}
	
	private static class EventosViewHolder {
		private TextView nombre, descripcion, precio, coordenadas;
	}

	@Override
	public int getCount() {
		return eventos.size();
	}

	@Override
	public Object getItem(int i) {
		return eventos.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

}
