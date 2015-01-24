package com.as.tohanoi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.YuvImage;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.widget.Toast;

public class DiskShape extends ShapeDrawable{
	private static float[] diskOuterRadius = new float[] { 25, 25, 25, 25, 0,
		0, 0, 0 };
	int size;
	float xRatio,yRatio;
	public DiskShape(int _size, float _x, float _y) {

		super(new RoundRectShape(diskOuterRadius, null, null));
		xRatio = _x;
		yRatio = _y;
		this.unselect();
		
		this.size = (int) (_size * 24*xRatio);
		bound();}
	
	public void bound() {
		this.setBounds(0, 0, this.size, (int) (24*yRatio));
		
	}
	public void select() {
		this.getPaint().setColor(0x88FF8844);
	}
	public void unselect() {
		this.getPaint().setColor(0xFFFF8844);
	}
	protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
		
		canvas.save();
		
		// translates the half of the size to the left, to draw
		// the disk on the center of the rod
		canvas.translate(-size / 2, 0);
		shape.draw(canvas, paint);

		canvas.restore();
	}
}
