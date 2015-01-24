package com.as.tohanoi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Levels extends Activity{
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.levels);
	}
	public void easy(View v){
		Intent i = new Intent(this, Easy.class);
		startActivity(i);
	}
	public void medium(View v){
		Intent i = new Intent(this, Medium.class);
		startActivity(i);
	}
	public void hard(View v){
		Intent i = new Intent(this, Hard.class);
		startActivity(i);
	}
}
