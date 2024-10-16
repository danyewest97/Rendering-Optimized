package main;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import main.Settings;
// import main.Renderer;

public class Point {
	public double x;
	public double y;
	public double z;
	public double camX;
	public double camY;
	public double camZ;
	public Color color;
	public double zSensitivity = Settings.s.zSensitivity;
	public double scaleSensitivity = Settings.s.scaleSensitivity;
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.camX = 0;
		this.camY = 0;
		this.camZ = 0;
		this.color = Color.black;
	}
	
	public Point(double x, double y, double z, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.camX = 0;
		this.camY = 0;
		this.camZ = 0;
		this.color = color;
	}
	
	public Point(double x, double y, double z, double camX, double camY, double camZ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.camX = camX;
		this.camY = camY;
		this.camZ = camZ;
		this.color = Color.black;
	}
	
	public Point(double x, double y, double z, double camX, double camY, double camZ, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.camX = camX;
		this.camY = camY;
		this.camZ = camZ;
		this.color = color;
	}
	
	
	public double[] xy() {
		double rx = camX;
		double ry = camY;
		
		double p = Math.exp(-z * zSensitivity);
		
		rx += (x - camX) * p;
		ry += (y - camY) * p;
		
		double[] result = {rx, ry};
		
		return result;
	}
	
	public int[] xyInt() {
		double rx = camX;
		double ry = camY;
		
		double p = Math.exp(-z * zSensitivity);
		
		rx += (x - camX) * p;
		ry += (y - camY) * p;
		
		int[] result = {(int) rx, (int) ry};
		
		return result;
	}
	
	public String toString() {
		return "" + this.x + ", " + this.y + ", " + this.z;
	}
}