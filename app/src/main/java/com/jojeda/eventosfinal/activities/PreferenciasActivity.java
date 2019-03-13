package com.jojeda.eventosfinal.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jojeda.eventosfinal.R;

public class PreferenciasActivity extends PreferenceActivity {

	public static SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.layout.activity_preferencias);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
	}
}
