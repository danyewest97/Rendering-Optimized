package main;
import main.Point;
import main.Main;
import java.util.*;

public class Face {
	public Point a;
	public Point b;
	public Point c;
	public Face(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public ArrayList<Point> points() {
		ArrayList<Point> result = new ArrayList<Point>();
		
		// int numPoints = (int) Math.abs((Main.dist(a.x, a.y, a.z, b.x, b.y, b.z) * Main.dist(a.x, a.y, a.z, c.x, c.y, c.z)) / 2);
		
		int layers = 0;
		
		Point center = new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3, (a.z + b.z + c.z) / 3);
		
		if ((int) Math.abs((Main.dist(a.x, a.y, a.z, center.x, center.y, center.z))) > layers) layers = (int) Math.abs((Main.dist(a.x, a.y, a.z, center.x, center.y, center.z)));
		if ((int) Math.abs((Main.dist(b.x, b.y, b.z, center.x, center.y, center.z))) > layers) layers = (int) Math.abs((Main.dist(b.x, b.y, b.z, center.x, center.y, center.z)));
		if ((int) Math.abs((Main.dist(c.x, c.y, c.z, center.x, center.y, center.z))) > layers) layers = (int) Math.abs((Main.dist(c.x, c.y, c.z, center.x, center.y, center.z)));
		
		double numLayers = (double) layers; //casting layers into a double so that the lines are generated properly (so that layers/i doesn't always 
		
		for (int i = 0; i < layers; i++) {
			//using Main.camXYZ for camXYZ
			double camX = Main.camX;
			double camY = Main.camY;
			double camZ = Main.camZ;
			
			int[][] line1 = Main.generateLineArrayInt(a.x + (center.x - a.x) * (i / numLayers), a.y + (center.y - a.y) * (i / numLayers), a.z + (center.z - a.z) * (i / numLayers), b.x + (center.x - b.x) * (i / numLayers), b.y + (center.y - b.y) * (i / numLayers), b.z + (center.z - b.z) * (i / numLayers));
			int[][] line2 = Main.generateLineArrayInt(b.x + (center.x - b.x) * (i / numLayers), b.y + (center.y - b.y) * (i / numLayers), b.z + (center.z - b.z) * (i / numLayers), c.x + (center.x - c.x) * (i / numLayers), c.y + (center.y - c.y) * (i / numLayers), c.z + (center.z - c.z) * (i / numLayers));
			int[][] line3 = Main.generateLineArrayInt(c.x + (center.x - c.x) * (i / numLayers), c.y + (center.y - c.y) * (i / numLayers), c.z + (center.z - c.z) * (i / numLayers), a.x + (center.x - a.x) * (i / numLayers), a.y + (center.y - a.y) * (i / numLayers), a.z + (center.z - a.z) * (i / numLayers));
			
			for (int[] point : line1) {
				result.add(new Point(point[0], point[1], point[2], camX, camY, camZ, a.color));
			}
			
			for (int[] point : line2) {
				result.add(new Point(point[0], point[1], point[2], camX, camY, camZ, b.color));
			}
			
			for (int[] point : line3) {
				result.add(new Point(point[0], point[1], point[2], camX, camY, camZ, c.color));
			}
			
		}
		
		return result;
	}
	
	
	public static ArrayList<Point> generatePoints(Point a, Point b, Point c) {
		return new Face(a, b, c).points();
	}
}