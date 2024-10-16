package main;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;
import main.Point;
import main.Face;

public class Main {
	
	public static JFrame window;
	// public static double angle = 0;
	public static double camX = 250;
	public static double camY = 250;
	public static double camZ = 0;
	
	public static ArrayList<BasicObject> objects = new ArrayList<BasicObject>();
	public static ArrayList<Face> faces = new ArrayList<Face>();
	public static ArrayList<Point> points = new ArrayList<Point>();
	
	public static double scale = 1;
	public static double size = 1;
	
	public static double zSensitivity = 0.001;
	public static double scaleSensitivity = 1;
	
	public static double millis = 0;
	
	public static double[][] temp = {
			{100, 250, 100},
			{200, 200, 10},
			{200, 100, 100},
			{100, 100, 10},
			{150, 150, 100},
			{100, 200, 10},
			{150, 150, 100},
			{200, 200, 10},
			{150, 150, 100},
			{200, 100, 10},
			{150, 150, 100},
			{100, 100, 10},
		};
	
	public static Face f1 = new Face(new Point(temp[0][0], temp[0][1], temp[0][2], Color.black), new Point(temp[1][0], temp[1][1], temp[1][2], Color.black), new Point(temp[2][0], temp[2][1], temp[2][2], Color.black));
	public static Face f2 = new Face(new Point(temp[2][0], temp[2][1], temp[2][2], Color.red), new Point(temp[3][0], temp[3][1], temp[3][2], Color.red), new Point(temp[4][0], temp[4][1], temp[4][2], Color.red));
	
	public static void main(String[] args) {
		
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true"); //not necessary, just prevents a bug with Comparator that sends an error message, however this error message is not fatal
		
		
		
		initializeWindow();
		
		
		
		
		
		
		// ArrayList<Point> tempPoints = new ArrayList<Point>();
		
		// for (int i = 0; i < temp.length; i++) {
			// for (double[] point : generateLineArray(temp[i][0], temp[i][1], temp[i][2], temp[(i + 1) % temp.length][0], temp[(i + 1) % temp.length][1], temp[(i + 1) % temp.length][2])) {
				// tempPoints.add(new Point(point[0], point[1], point[2], camX, camY, camZ, Color.black));
			// }
		// }
		
		// objects.add(new BasicObject(tempPoints));
		objects.add(new BasicObject(f1.points()));
		objects.add(new BasicObject(f2.points()));
		
		JPanel p = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2D = (Graphics2D) g;
					// Face f1 = new Face(new Point(temp[0][0], temp[0][1], temp[0][2], Color.black), new Point(temp[1][0], temp[1][1], temp[1][2], Color.black), new Point(temp[2][0], temp[2][1], temp[2][2], Color.black));
					// Face f2 = new Face(new Point(temp[2][0], temp[2][1], temp[2][2], Color.red), new Point(temp[3][0], temp[3][1], temp[3][2], Color.red), new Point(temp[4][0], temp[4][1], temp[4][2], Color.red));
					objects.remove(0);
					objects.remove(0);
					objects.add(new BasicObject(f1.points()));
					objects.add(new BasicObject(f2.points()));
					
					for (int i = 0; i < points.size(); i++) {
						points.remove(0);
					}
					for (BasicObject object : objects) {
						for (Point p : object.points) {
							points.add(p);
						}
					}
					
					points = organize(points);
					for (int i = points.size() - 1; i >= 0; i--) {
						g2D.setColor(points.get(i).color);
						
						int[] point = points.get(i).xyInt();
						
						// double scale = Math.exp(-point[2] * scaleSensitivity) + 0.5;
						double scale = 1;
						
						// if (point[2] - ((scale * size) / 2) >= camZ) {
							g2D.drawOval((int) (point[0] - (scale * size) / 2), (int) (point[1] - (scale * size) / 2), (int) (scale * size), (int) (scale * size));
							g2D.fillOval((int) (point[0] - (scale * size) / 2), (int) (point[1] - (scale * size) / 2), (int) (scale * size), (int) (scale * size));
						// }
						
					}
					
					
					
					
					
					// g2D.drawOval((int) (camX - 2.5), (int) (camY - 2.5), 5, 5);
			}
		};
		
		window.add(p);
		
		// System.out.println(objects.get(0).points.size());
		
		Timer t = new Timer();
		
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				millis++;
				// try {
					// for (Point p : objects.get(1).points) {
						
						if (millis > 1000 && millis < 3000) {
							f2.a.x += 0.01;
							f2.a.y += 0.01;
						}
						
						if (millis > 3000) {
							// p.z += Math.random() * (millis / 800) - (millis / 1600);
							f2.a.z += Math.sin(millis/300) / 2;
						}
					// }
				// } catch (Exception e) {
					
				// }
				
			}
		}, 0, 1);
		
	}
	
	public static void initializeWindow() {
		window = new JFrame() {
			@Override
			public void paint(Graphics g) {
				super.paintComponents(g);
			}
		};
		window.setTitle("Window");
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(500, 500));
		window.setLocationRelativeTo(null);
		
		Timer fps = new Timer();
		
		fps.schedule(new TimerTask() {
			@Override
			public void run() {
				// calculate();
				window.repaint();
			}
		}, 0, 16);
	}
	
	public static void initializePanel(JPanel panel) {
		panel.setVisible(true);
		panel.setOpaque(false);
		panel.setBounds(new Rectangle(1920, 1080));
		
	}
	
	public static double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
	}
	
	public static double[][] generateLineArray(double x1, double y1, double z1, double x2, double y2, double z2) {
		double numPoints = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
		double[][] points = new double[(int) numPoints][3];
		for (int i = 0; i < (int) numPoints; i++) {
			points[i][0] = x1 + i * (x2 - x1) / numPoints;
			points[i][1] = y1 + i * (y2 - y1) / numPoints;
			points[i][2] = z1 + i * (z2 - z1) / numPoints;
		}
		
		return points;
	}
	
	public static int[][] generateLineArrayInt(double x1, double y1, double z1, double x2, double y2, double z2) {
		double numPoints = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		int[][] points = new int[(int) numPoints][3];
		for (int i = 0; i < (int) numPoints; i++) {
			points[i][0] = (int) (x1 + i * (x2 - x1) / numPoints);
			points[i][1] = (int) (y1 + i * (y2 - y1) / numPoints);
			points[i][2] = (int) (z1 + i * (z2 - z1) / numPoints);
		}
		
		return points;
	}
	
	// public static double[][] generatePolygonArray2D(double[][] points) {
		// double[][] result = null;
		
		// for (int i = 0; i < points.length; i += 2) {
			// int[] point = calculatePoint(points[i][0], points[i][1], points[i][2]);
		// }
		
		// return result;
	// }
	
	// public static int[][] generateTriangleArray(int[] p1, int[] p2, int[] p3) {
		// ArrayList<int[]> points = new ArrayList<int[]>();
		
		
		
		// int[] center = {(p1[0] + p2[0] + p3[0]) / 3, (p1[1] + p2[1] + p3[1]) / 3};
		
		// int numPoints = 0;
		// if (Math.sqrt(Math.pow(p1[0] - center[0], 2) + Math.pow(p1[1] - center[1], 2)) > numPoints) {
			// numPoints = (int) Math.sqrt(Math.pow(p1[0] - center[0], 2) + Math.pow(p1[1] - center[1], 2));
		// }
		
		// if (Math.sqrt(Math.pow(p2[0] - center[0], 2) + Math.pow(p2[1] - center[1], 2)) > numPoints) {
			// numPoints = (int) Math.sqrt(Math.pow(p1[0] - center[0], 2) + Math.pow(p1[1] - center[1], 2));
		// }
		
		// if (Math.sqrt(Math.pow(p3[0] - center[0], 2) + Math.pow(p3[1] - center[1], 2)) > numPoints) {
			// numPoints = (int) Math.sqrt(Math.pow(p1[0] - center[0], 2) + Math.pow(p1[1] - center[1], 2));
		// }
		
		// for (int i = 0; i < numPoints; i++) {
			// int[] p4 = {(p1[0] + i * (center[0] - p1[0]) / numPoints), (p1[1] + i * (center[1] - p1[1]) / numPoints), p1[2]};
			// int[] p5 = {(p2[0] + i * (center[0] - p2[0]) / numPoints), (p2[1] + i * (center[1] - p2[1]) / numPoints), p2[2]};
			// int[] p6 = {(p3[0] + i * (center[0] - p3[0]) / numPoints), (p3[1] + i * (center[1] - p3[1]) / numPoints), p3[2]};
			
			
			// int[][] line1 = generateLineArrayInt(p4[0], p4[1], p4[2], p5[0], p5[1], p5[2]);
			// int[][] line2 = generateLineArrayInt(p5[0], p5[1], p5[2], p6[0], p6[1], p6[2]);
			// int[][] line3 = generateLineArrayInt(p6[0], p6[1], p6[2], p4[0], p4[1], p4[2]);
			
			// for (int j = 0; j < line1.length; j++) {
				// points.add(line1[j]);
			// }
			
			// for (int j = 0; j < line2.length; j++) {
				// points.add(line2[j]);
			// }
			
			// for (int j = 0; j < line3.length; j++) {
				// points.add(line3[j]);
			// }
		// }
		
		
		// int[][] result = new int[points.size()][3];
		
		// for (int i = 0; i < points.size(); i++) {
			// result[i] = points.get(i);
		// }
		
		
		
		// return result;
	// }
	
	public static ArrayList<Point> organize(ArrayList<Point> points) {
		ArrayList<Point> result = new ArrayList<Point>();
		for (Point point : points) {
			result.add(point);
		}
		Collections.sort(result, new ArrayComparator());
		
		return result;
	}
	
	
}

class ArrayComparator implements Comparator<Point> {
	// Sorting in ascending order of z-value
	public int compare(Point a, Point b) {
		// Comparing z-values
		if (a.z < b.z) return -1; // The first point has a smaller z-value
		if (a.z > b.z) return 1;  // The first point has a larger z-value
		return 0; // Both points have the same z-value
	}
}

class BasicObject {
	public ArrayList<Point> points;
	
	
	public BasicObject() {
		this.points = new ArrayList<Point>();
	}
	public BasicObject(ArrayList<Point> points) {
		this.points = points;
	}
	
	public void add(Point p) {
		this.points.add(p);
	}
	
	public void remove(int index) {
		this.points.remove(index);
	}
}

