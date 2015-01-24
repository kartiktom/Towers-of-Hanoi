package com.as.tohanoi;

import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class Draw extends View{

	private Stack<DiskShape> leftRod, middleRod, rightRod;
	private Stack<DiskShape> rodWithDiskSelected= null;
	private DiskShape disk= null;
	float xRatio, yRatio;
	
	int moves=0;
	float bottomLimit;
	float topLimit;
	float leftLimitMiddleRod;
	float rightLimitMiddleRod;
	float x, y;
	int no_of_disks;
	@SuppressWarnings("deprecation")
	public Draw(Context context, float _x, float _y, int _no_of_disks) {
		super(context);
		
		
		Bitmap hanoiBackground = BitmapFactory.decodeResource(getResources(),R.drawable.hanoi_background);
		setBackgroundDrawable(new BitmapDrawable(hanoiBackground));
				
		leftRod = new Stack<DiskShape>();
		middleRod = new Stack<DiskShape>();
		rightRod = new Stack<DiskShape>();
		
		xRatio=_x/480;
		yRatio=_y/320;
		no_of_disks = _no_of_disks;

		for(int i=_no_of_disks;i>=1;i--){
			leftRod.push(new DiskShape(i, xRatio, yRatio));
		}
	}
	
	public void onDraw(Canvas canvas){
		
		leftLimitMiddleRod = 165*xRatio;
		rightLimitMiddleRod  = 315*xRatio;
		bottomLimit = 20*yRatio;
		topLimit = 250*yRatio;
		//canvas.translate(0, 0);
		//canvas.save(2);
		canvas.translate(xRatio * 90, yRatio* 226);
		canvas.save();
		for (DiskShape disk : leftRod) {//		leftRod = new Stack<DiskShape>();
			disk.draw(canvas);
			canvas.translate(0, -25*yRatio);
		}
		canvas.restore();
		canvas.translate(150*xRatio, 0);
		canvas.save();
		for (DiskShape disk : middleRod) {
			disk.draw(canvas);
			canvas.translate(0, -25*yRatio);
		}
		canvas.restore();
		canvas.translate(150*xRatio, 0);
		canvas.save();
		for (DiskShape disk : rightRod) {

			disk.draw(canvas);
			canvas.translate(0, -25*yRatio);
		}
		canvas.restore();
	}
	boolean flag= true;
	
	public boolean onTouchEvent(MotionEvent event){
		if(event.getAction() == MotionEvent.ACTION_DOWN){

			x = event.getX();
			y = event.getY();
		
			if(y>bottomLimit && y<topLimit){
				flag=true;
				if(x<leftLimitMiddleRod)
					rodWithDiskSelected = leftRod;
				else if(x>=leftLimitMiddleRod && x<=rightLimitMiddleRod)
					rodWithDiskSelected = middleRod;
				else
					rodWithDiskSelected = rightRod;
				
				if(rodWithDiskSelected.size()!=0)
					rodWithDiskSelected.lastElement().select();
				
			}
			else
				flag = false;
			
			invalidate();
		}
		else if(event.getAction() == MotionEvent.ACTION_MOVE){
			if(flag==true){
				if(rodWithDiskSelected.size()!=0){

					int mX=0;int mY=0;
					mY=(int) (250*yRatio);
					mX=(int) (90*xRatio);

					if (rodWithDiskSelected == middleRod){
						//mX=240;
						mX +=150*xRatio;
						
					}
					else if(rodWithDiskSelected == rightRod){
						//mX=390;
						mX += 300*xRatio;
						//mY=191;
					}
					int mm = (int) (rodWithDiskSelected.size()*25*yRatio);

					//int x = (int) event.getX()-mX;
					x = event.getX()-mX;
					y = event.getY()-mY+mm;
					
					rodWithDiskSelected.lastElement().bound();
					rodWithDiskSelected.lastElement().getBounds().inset((int)x, (int)y);
					invalidate();
				}
			}
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			if(flag == true)
				if(rodWithDiskSelected.size()!=0){
					x = event.getX();
					y = event.getY();
					rodWithDiskSelected.lastElement().bound();
					if(y>bottomLimit && y<topLimit){
						rodWithDiskSelected.lastElement().getBounds().inset((int)x, (int)y);
						if(x<leftLimitMiddleRod){
							actionOnTouch(leftRod);}
						else if(x>=leftLimitMiddleRod && x<= rightLimitMiddleRod){
							actionOnTouch(middleRod);}
						else
							actionOnTouch(rightRod);
					}
					else
						rodWithDiskSelected.lastElement().unselect();
					invalidate();
				}
		
		}
		return true;
	}
	
	private void actionOnTouch(Stack<DiskShape> touchedRod) {		
			rodWithDiskSelected.lastElement().unselect();
			rodWithDiskSelected.lastElement().bound();
			if(touchedRod.size()==0 || rodWithDiskSelected.lastElement().size < touchedRod.lastElement().size){
				touchedRod.push(rodWithDiskSelected.pop());				
				moves++;				
			}
			rodWithDiskSelected = null;
			invalidate();

		if(rightRod.size() == no_of_disks || middleRod.size() == no_of_disks){
			switch(no_of_disks){
				case 4:
					((Easy)getContext()).change(moves);	
					break;
				case 5:
					((Medium)getContext()).change(moves);
					break;
					
				case 6:
					((Hard)getContext()).change(moves);
					break;
			}
		}
	}
}
