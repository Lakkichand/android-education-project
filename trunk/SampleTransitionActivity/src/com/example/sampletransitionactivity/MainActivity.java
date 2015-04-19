package com.example.sampletransitionactivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	View imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = findViewById(R.id.imageView1);
		View startView = findViewById(R.id.start);
		startView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, DetailActivity.class);
				ActivityOptions options = ActivityOptions
						.makeSceneTransitionAnimation(MainActivity.this,
								imageView, "ball");
				startActivity(i, options.toBundle());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
