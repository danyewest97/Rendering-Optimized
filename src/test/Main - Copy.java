package test;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;

public class Main {
	public static double camX = 250;
	public static double camY = 250;
	public static double camZ = 0;
	public static double zSensitivity = 0.01;
	public static double frames = 0;
	public static double fps = 60;
	public static double millis = 0;
	
	public static void main(String[] args) {
		Frame frame = new Frame();
		Panel panel = new Panel();
		frame.add(panel);
		
		Point a = new Point(130, 130, 100);
		Point b = new Point(250, 100, 50);
		Point c = new Point(130, 220, 100);
		
		// Object3D line1 = new Object3D(lineArray(a, b));
		// panel.objects.add(line1);
		
		// for (Point p : lineArray(a, b)) {
			// System.out.println(p.xyInt());
		// }
		
		Point d = new Point(100, 100, 0, Color.red);
		Point e = new Point(200, 100, 0, Color.red);
		Point f = new Point(100, 200, 0, Color.red);
		
		ArrayList<Point> tri1 = triArray(a, b, c);
		ArrayList<Point> tri2 = triArray(d, e, f);
		
		Object3D obj1 = new Object3D(tri1);
		Object3D obj2 = new Object3D(tri2);
		
		panel.objects.add(obj1);
		panel.objects.add(obj2);
		
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				panel.repaint();
				// a.x += 0.1;
				// a.y += 0.1;
				
				a.z += -0.5;
				b.z += -0.5;
				c.z += -0.5;
			
				// panel.objects.get(0).points = lineArray(a, b);
				
				panel.objects.get(0).points = triArray(a, b, c);
				panel.objects.get(1).points = triArray(d, e, f);
				
			}
		}, 0, (int) (1000/fps));
	}
	
	public static ArrayList<Point> lineArray(Point a, Point b) {
		ArrayList<Point> result = new ArrayList<Point>();
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		// Point a2D = a;
		// Point b2D = b;
		
		int numPoints = (int) (a2D.dist(b2D) + 1); //trying to use a2D and b2D in order to not find more points than can be drawn, however I am unsure if it is working 100% properly
		// int numPoints = (int) (a.dist(b) + 1);
		
		for (int i = 0; i < numPoints; i++) {
			//The percent, as a decimal, to lerp from the first point to the last point
			double p = (double) i / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
			
			result.add(new Point(a.x + (b.x - a.x) * p, a.y + (b.y - a.y) * p, a.z + (b.z - a.z) * p, a.color));
		}
		
		result.add(b);
		
		return result;
	}
	
	public static ArrayList<Point> lineArray(Point a, Point b, int numPoints) {
		ArrayList<Point> result = new ArrayList<Point>();
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		// Point a2D = a;
		// Point b2D = b;
		
		// int numPoints = (int) (a2D.dist(b2D) + 1); //trying to use a2D and b2D in order to not find more points than can be drawn, however I am unsure if it is working 100% properly
		// int numPoints = (int) (a.dist(b) + 1);
		
		for (int i = 0; i < numPoints; i++) {
			//The percent, as a decimal, to lerp from the first point to the last point
			double p = (double) i / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
			
			result.add(new Point(a.x + (b.x - a.x) * p, a.y + (b.y - a.y) * p, a.z + (b.z - a.z) * p, a.color));
		}
		
		result.add(b);
		
		return result;
	}
	
	
	public static ArrayList<Point> triArray(Point a, Point b, Point c) {
		ArrayList<Point> result = new ArrayList<Point>();
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		Point c2D = c.xy();
		
		double numLines = 0;
		Point hyp = a;
		
		
		
		if (a2D.dist(b2D) > numLines) {
			numLines = a2D.dist(b2D);
			hyp = a;
		}
		
		if (b2D.dist(c2D) > numLines) {
			numLines = b2D.dist(c2D);
			hyp = b;
		}
		
		if (c2D.dist(a2D) > numLines) {
			numLines = c2D.dist(a2D);
			hyp = c;
		}
		
		
		ArrayList<Point> opp = new ArrayList<Point>();
		if (hyp == a) opp = lineArray(b, c);
		if (hyp == b) opp = lineArray(c, a);
		if (hyp == c) opp = lineArray(a, b);
		
		for (Point p : opp) {
			ArrayList<Point> fillLine = lineArray(hyp, p);
			for (Point fillPoint : fillLine) {
				result.add(fillPoint);
			}
		}
		
		
		return result;
	}
	
	
	//Alternate way to find an array for points on a triangle, slightly slower (I think)
	
	// public static ArrayList<Point> triArray(Point a, Point b, Point c) {
		// ArrayList<Point> result = new ArrayList<Point>();
		
		// Point center = new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3, (a.z + b.z + c.z) / 3);
		
		// ArrayList<Point> dists = new ArrayList<Point>();
		
		
		// dists.add(0, a);
		
		// if (b.dist(center) > dists.get(0).dist(center)) {
			// dists.add(0, b);
		// } else {
			// dists.add(b);
		// }
		
		// if (c.dist(center) > dists.get(0).dist(center)) {
			// dists.add(0, c);
		// } else {
			// dists.add(c);
		// }
		
		
		// ArrayList<Point> hyp = lineArray(dists.get(0), dists.get(1));
		// hyp.add(dists.get(2));
		
		// ArrayList<Point> adj = lineArray(dists.get(2), dists.get(1), hyp.size());
		// adj.add(dists.get(0));
		
		// for (int i = 0; i < hyp.size(); i++) {
			
			// ArrayList<Point> fillLine = lineArray(hyp.get(i), adj.get(i));
			// for (Point fillPoint : fillLine) {
				// result.add(fillPoint);
			// }
		// }
		
		
		// return result;
	// }
	
}

class Point {
	public double x;
	public double y;
	public double z;
	public Color color;
	
	public static double camX = 250;
	public static double camY = 250;
	public static double camZ = 0;
	public static double zSensitivity = 0.01;
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = Color.black;
	}
	
	public Point(double x, double y, double z, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = color;
	}
	
	public double dist(Point p) {
		return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) + Math.pow(p.z - z, 2));
	}
	
	public Point xy() {
		double rx = camX;
		double ry = camY;
		
		
		double p = Math.exp(-z * zSensitivity);
		
		rx += (x - camX) * p;
		ry += (y - camY) * p;
		
		Point result = new Point(rx, ry, 0, color);
		
		return result;
	}
	
	public Point xyInt() {
		double rx = camX;
		double ry = camY;
		
		
		double p = Math.exp(-z * zSensitivity);
		
		rx += (x - camX) * p;
		ry += (y - camY) * p;
		
		Point result = new Point((int) (rx + 0.5), (int) (ry + 0.5), 0);
		
		return result;
	}
	
	public String toString() {
		return "" + x + ", " + y + ", " + z;
	}
}


class PointComparator implements Comparator<Point> {
    public int compare(Point a, Point b) {
		if (a.z < b.z) return 1;
		if (a.z > b.z) return -1;
        return 0;
    }
}







class Frame extends JFrame {
	public Frame() {
		initializeWindow();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
	}
	
	public void initializeWindow() {
		this.setTitle("Window");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		
	}
}



class Panel extends JPanel {
	public ArrayList<Object3D> objects;
	
	public Panel() {
		objects = new ArrayList<Object3D>();
		initializePanel();
	}
	
	public Panel(ArrayList<Object3D> objects) {
		this.objects = objects;
		initializePanel();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		ArrayList<Point> allPoints = new ArrayList<Point>();
		
		for (Object3D obj : objects) {
			for (Point p : obj.points) {
				allPoints.add(p);
			}
		}
		
		//Sorting points by z-value in descending order so objects further away are drawn first, then drawn over by closer objects
		Collections.sort(allPoints, new PointComparator());
		
		for (Point point : allPoints) {
			Point p = point.xy();
			g2D.setColor(point.color);
			g2D.drawOval((int) (p.x + 0.5), (int) (p.y + 0.5), 1, 1); //Adding 0.5 to round the values to the nearest whole number, in order to prevent holes due to integer conversion
		}
		
		g2D.setColor(Color.black);
		g2D.drawOval((int) (Main.camX - 2.5), (int) (Main.camY - 2.5), 5, 5);
		
		Main.frames++;
	}
	
	
	public void initializePanel() {
		this.setVisible(true);
		this.setOpaque(false);
		this.setBounds(new Rectangle(1920, 1080));
	}
}



class Object3D {
	public ArrayList<Point> points;
	
	public Object3D() {
		points = new ArrayList<Point>();
	}
	
	public Object3D(ArrayList<Point> points) {
		this.points = points;
	}
}