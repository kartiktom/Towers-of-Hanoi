package com.as.tohanoi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

public class Hard extends Activity{
	int moves;
	float width, height;
	public void onCreate(Bundle b){
		super.onCreate(b);
		Display dis = getWindowManager().getDefaultDisplay(); 
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		width = displaymetrics.widthPixels;
		height = displaymetrics.heightPixels;
		setContentView(new Draw(this,width,height,6));	
		

	}
	public void change(int k){
		moves=k;
		showDialog(1);
	}
	public Dialog onCreateDialog(int i, Bundle b) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		if(i == 1) {
			alert.setTitle("Game Over");
			if(moves>63)
				alert.setMessage("Least possible moves are 63, you made "+moves+".");
			else
				alert.setMessage("You won, congrats!!!!");
			alert.setPositiveButton("Back", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					finish();
				}
			});
		}
		Dialog dialog = alert.create();
		return dialog;
	}
}
