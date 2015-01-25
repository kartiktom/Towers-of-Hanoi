package com.as.tohanoi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {
	
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.home);
	}

	public void game(View v) {
		Intent i = new Intent(this, Levels.class);
		startActivity(i);
	}

	public void instruct(View v) {
		Intent i = new Intent(this, Instruct.class);
		startActivity(i);
	}

	public void exit(View v) {
		finish();
		System.exit(0);
	}
}