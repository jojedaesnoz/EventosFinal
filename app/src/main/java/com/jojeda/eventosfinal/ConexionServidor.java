package com.jojeda.eventosfinal;

import android.os.AsyncTask;

import com.jojeda.eventosfinal.base.Evento;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jojeda.eventosfinal.util.Constantes.URL_SERVIDOR;

public class ConexionServidor {
    List<Evento> eventos = new ArrayList<>();

    private List<Evento> getEventosFromServer() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Evento[] eventosArray = restTemplate.getForObject(URL_SERVIDOR + "/eventos", Evento[].class);
        return Arrays.asList(eventosArray);
    }

    private void sendEventoToServer(Evento evento) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getForObject(URL_SERVIDOR +
                "/add_evento?nombre=" + evento.getNombre() +
                "&descripcion=" + evento.getDescripcion() +
                "&precio=" + evento.getPrecio() +
                "&latitud=" + evento.getLatitud() +
                "&longitud=" + evento.getLongitud(), Void.class);
    }


}
