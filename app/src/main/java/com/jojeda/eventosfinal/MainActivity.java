package com.jojeda.eventosfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
		ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
		switch (item.getItemId()) {
			case R.id.id1:
				System.out.println("ITEM1");
				break;
			case R.id.id2:
				System.out.println("ITEM2");
				break;
			case R.id.id3:
				System.out.println("ITEM3");
				break;
		}
		return true;
	}
}
