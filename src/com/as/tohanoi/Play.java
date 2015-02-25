package com.as.tohanoi;

import java.math.BigDecimal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class Play extends Activity {

	private int totalMoves, minPossibleMoves;

	public void onCreate(Bundle b) {
		super.onCreate(b);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

		setContentView(new Draw(this, displaymetrics.widthPixels,
				displaymetrics.heightPixels, getIntent().getExtras().getInt(
						"numofdisks")));

		// Possible min moves (2^n - 1); n number of disks
		minPossibleMoves = new BigDecimal(2).pow(
				getIntent().getExtras().getInt("numofdisks")).intValue() - 1;
	}

	public void gameOver(int moves) {
		totalMoves = moves;
		showDialog();
	}

	public void showDialog() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Game Over");
		
		if (totalMoves > minPossibleMoves)
			alert.setMessage("Least possible moves are " + minPossibleMoves
					+ ", you made " + totalMoves + ".");
		else
			alert.setMessage("You won, congrats!!!!");

		alert.setPositiveButton("Back", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}
		});
		
		alert.create().show();
	}
}