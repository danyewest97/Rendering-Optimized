package main;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;
import java.util.concurrent.*;

import java.time.Duration;
import java.time.Instant;

public class Main {
	public static Frame frame;
	public static Camera mainCam = new Camera(250, 250, 0);
	public static double zSensitivity = 0.003;
	
	public static Object3D cube;
	
	
	public static double millis = 0;
	
	public static void main(String[] args) {
		frame = new Frame();
		Panel panel = new Panel();
		frame.add(panel);
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true"); //not necessary, just prevents a bug with Comparator that sends an error message, however this error message is not fatal

		
		Point a = new Point(100, 100, 0, Color.red);
		Point b = new Point(200, 100, 0, Color.blue);
		Point c = new Point(200, 200, 0, Color.green);
		Point d = new Point(100, 200, 0, Color.yellow);
		
		Point a1 = new Point(100, 100, 100, Color.red);
		Point b1 = new Point(200, 100, 100, Color.blue);
		Point c1 = new Point(200, 200, 100, Color.green);
		Point d1 = new Point(100, 200, 100, Color.yellow);
		
		
		ArrayList<Point> cpoints = new ArrayList<Point>();
		cpoints.add(a);
		cpoints.add(b);
		cpoints.add(c);
		cpoints.add(d);
		cpoints.add(a1);
		cpoints.add(b1);
		cpoints.add(c1);
		cpoints.add(d1);
		
		
		Object3D cube = new Object3D(new ArrayList<Line>(), cpoints, 12) {
			@Override
			public void updateFunc(int index) {
				ArrayList<Line> tri1 = new ArrayList<Line>();
				ArrayList<Line> tri2 = new ArrayList<Line>();
				ArrayList<Line> tri3 = new ArrayList<Line>();
				ArrayList<Line> tri4 = new ArrayList<Line>();
				ArrayList<Line> tri5 = new ArrayList<Line>();
				ArrayList<Line> tri6 = new ArrayList<Line>();
				ArrayList<Line> tri7 = new ArrayList<Line>();
				ArrayList<Line> tri8 = new ArrayList<Line>();
				ArrayList<Line> tri9 = new ArrayList<Line>();
				ArrayList<Line> tri10 = new ArrayList<Line>();
				ArrayList<Line> tri11 = new ArrayList<Line>();
				ArrayList<Line> tri12 = new ArrayList<Line>();
				
				if (index == 0) tri1 = tri(a, b, c);
				if (index == 1) tri2 = tri(a, c, d);
				if (index == 2) tri3 = tri(a1, b1, c1);
				if (index == 3) tri4 = tri(a1, c1, d1);
				if (index == 4) tri5 = tri(a1, a, b1);
				if (index == 5) tri6 = tri(b1, b, a);
				if (index == 6) tri7 = tri(b1, b, c);
				if (index == 7) tri8 = tri(b1, c1, c);
				if (index == 8) tri9 = tri(d, c, c1);
				if (index == 9) tri10 = tri(d, d1, c1);
				if (index == 10) tri11 = tri(a1, a, d1);
				if (index == 11) tri12 = tri(d1, d, a);
				
				
				
				
				if (index == 0)  this.lines.addAll(tri1);
				if (index == 1)  this.lines.addAll(tri2);
				if (index == 2)  this.lines.addAll(tri3);
				if (index == 3)  this.lines.addAll(tri4);
				if (index == 4)  this.lines.addAll(tri5);
				if (index == 5)  this.lines.addAll(tri6);
				if (index == 6)  this.lines.addAll(tri7);
				if (index == 7)  this.lines.addAll(tri8);
				if (index == 8)  this.lines.addAll(tri9);
				if (index == 9)  this.lines.addAll(tri10);
				if (index == 10) this.lines.addAll(tri11);
				if (index == 11) this.lines.addAll(tri12);
				
				
			}
		};
		
		
		
		
		
		
		
		panel.objects.add(cube);
		
		
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
			
				// ArrayList<Line> tri1 = tri(a, b, c);
				// ArrayList<Line> tri2 = tri(a, c, d);
				// ArrayList<Line> tri3 = tri(a1, b1, c1);
				// ArrayList<Line> tri4 = tri(a1, c1, d1);
				// ArrayList<Line> tri5 = tri(a1, a, b1);
				// ArrayList<Line> tri6 = tri(b1, b, a);
				// ArrayList<Line> tri7 = tri(b1, b, c);
				// ArrayList<Line> tri8 = tri(b1, c1, c);
				// ArrayList<Line> tri9 = tri(d, c, c1);
				// ArrayList<Line> tri10 = tri(d, d1, c1);
				// ArrayList<Line> tri11 = tri(a1, a, d1);
				// ArrayList<Line> tri12 = tri(d1, d, a);
				
				// cube.lines = new ArrayList<Line>();
				
				// cube.lines.addAll(tri1);
				// cube.lines.addAll(tri2);
				// cube.lines.addAll(tri3);
				// cube.lines.addAll(tri4);
				// cube.lines.addAll(tri5);
				// cube.lines.addAll(tri6);
				// cube.lines.addAll(tri7);
				// cube.lines.addAll(tri8);
				// cube.lines.addAll(tri9);
				// cube.lines.addAll(tri10);
				// cube.lines.addAll(tri11);
				// cube.lines.addAll(tri12);
				
				for (int i = 0; i < cube.points.size(); i++) {
					cube.points.get(i).x += Math.cos(millis / 100);
					cube.points.get(i).y += Math.sin(millis / 100);
					// cube.points.get(i).z += 1;
				}
				
				
				if (cube.clear()) if (cube.update()) panel.repaint();


				
				
				millis++;
			}
		}, 0, 1);
		
		
	}
	
	
	
	public static ArrayList<Line> tri(Point a, Point b, Point c) {
		ArrayList<Line> result = new ArrayList<Line>();


		Point center = new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3, (a.z + b.z + c.z) / 3, Color.red);
		
		Line line1 = lineArray(a, b);
		Line line2 = lineArray(b, c);
		Line line3 = lineArray(c, a);
		
		
		
		double area = a.dist(b);
		
		
		Point areaPoint = new Point(a.x, a.y, a.z, c.color);
		for (Point p : line1.points) {
			if (c.dist(p) < c.dist(areaPoint)) areaPoint = p;
		}
		
		area *= c.dist(areaPoint);
		area /= 2;
		
		
		ArrayList<Point> r = new ArrayList<Point>();
		r.add(a);
		
		int numLines = (int) (a.dist(center) + 1);
		
		if (b.dist(center) > numLines) {
			numLines = (int) (b.dist(center) + 1);
			r.add(0, b);
		} else {
			r.add(b);
		}
		if (c.dist(center) > numLines) {
			numLines = (int) (c.dist(center) + 1);
			r.add(0, c);
		} else {
			if (c.dist(center) < r.get(1).dist(center)) {
				r.add(c);
			} else {
				r.add(1, c);
			}
		}
		
		// ArrayList<Point> fill = lineArray2D(r.get(0), r.get(1));
		Point f = r.get(0).xy(); //furthest point
		Point c1 = r.get(1).xy(); //closer point 1
		Point c2 = r.get(2).xy(); //closer point 2
		
		//Points above in 3D
		Point fz = r.get(0);
		Point c1z = r.get(1);
		Point c2z = r.get(2);

		
		int temp = (int) (c1.dist(c2) + 0.5);
		for (int i = 0; i <= temp; i++) {
			double p = (double) i / temp;
			Point x = new Point(f.x + (c1.x - f.x) * (p), f.y + (c1.y - f.y) * (p), 0, f.color).toXYZ(fz.z + (c1z.z - fz.z) * (p));
			Point y = new Point(c2.x + (c1.x - c2.x) * (p), c2.y + (c1.y - c2.y) * (p), 0, c2.color).toXYZ(c2z.z + (c1z.z - c2z.z) * (p));
			
			result.add(lineArray(x, y));
		}
		
				
		//Adding black edges
		Point e1 = new Point(a.x, a.y, a.z, Color.black);
		Point e2 = new Point(b.x, b.y, b.z, Color.black);
		Point e3 = new Point(c.x, c.y, c.z, Color.black);
		
		result.add(lineArray(e1, e2));
		result.add(lineArray(e2, e3));
		result.add(lineArray(e3, e1));
		
		return result;
	}
	
	public static Line lineArray(Point a, Point b) {
		Dimension size = frame.getContentPane().getSize();
		int width = size.width;
		int height = size.height;
		
		Line result = new Line();
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		
		int numPoints = (int) (a2D.dist(b2D) + 1); //trying to use a2D and b2D in order to not find more points than can be drawn, however I am unsure if it is working 100% properly
		
		numPoints *= 2; //for some reason, just using the distance as the line density no workie (it causes unfilled points in each line), so I multiplied it by 2 and it looks fine :D 
		//(however this makes a lot of unnecessary points, since as far as I know numPoints should only need to equal the distance between a2D and b2D to fully fill the line)
		

		
		for (int i = 0; i <= numPoints; i++) {
			//The percent, as a decimal, to lerp from the first point to the last point
			double p = (double) i / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
			
			double z = a.z + (b.z - a.z) * p;
			Point add = new Point(a2D.x + (b2D.x - a2D.x) * p, a2D.y + (b2D.y - a2D.y) * p, 0, a.color).toXYZ(z);
			Point addxy = add.xy();
			
			if (!(addxy.x < 0 || addxy.x > width)) {
				if (!(addxy.y < 0 || addxy.y > height)) {
					result.points.add(add);
				}
			}
		}
		
		return result;
	}
	
	public static ArrayList<Point> trueLineArray(Point a, Point b) {
		ArrayList<Point> result = new ArrayList<Point>();
		
		int numPoints = (int) (a.dist(b) + 1); //trying to use a2D and b2D in order to not find more points than can be drawn, however I am unsure if it is working 100% properly
		
		for (int i = 0; i <= numPoints; i++) {
			//The percent, as a decimal, to lerp from the first point to the last point
			double p = (double) i / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
			
			result.add(new Point(a.x + (b.x - a.x) * p, a.y + (b.y - a.y) * p, a.z + (b.z - a.z) * p, a.color));
		}
		
		return result;
	}
	
	
}




class Point {
	public double x;
	public double y;
	public double z;
	public Color color;
	
	public static double camX = Main.mainCam.x;
	public static double camY = Main.mainCam.y;
	public static double camZ = Main.mainCam.z;
	public static double zSensitivity = Main.zSensitivity;
	
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
		
		
		double p = Math.exp(-(z * zSensitivity));
		if (z == 0) p = 1;
		
		rx += (x - camX) * p;
		ry += (y - camY) * p;
		
		Point result = new Point(rx, ry, 0, color);
		
		return result;
	}
	
	// public Point xyInt() {
		// double rx = camX;
		// double ry = camY;
		
		
		// double p = Math.exp(-z * zSensitivity);
		
		// rx += (x - camX) * p;
		// ry += (y - camY) * p;
		
		// Point result = new Point((int) (rx + 0.5), (int) (ry + 0.5), 0);
		
		// return result;
	// }
	
	public Point toXYZ(double z) {
		double p = Math.exp(-(z * zSensitivity));
		
		double rx = x;
		double ry = y;
		
		rx += p * camX;
		ry += p * camY;

		
		rx -= (camX);
		ry -= (camY);
		
		rx /= p;
		ry /= p;
		
		
		
		Point result = new Point(rx, ry, z, color);
		
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
	int frames = 0;
	int millis = 0;
	public Frame() {
		pack();
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
		// Timer fps1 = new Timer();
		// fps1.schedule(new TimerTask() {
			// @Override
			// public void run() {
				// repaint();
				
				
			// }
		// }, 0, 16);
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
		
		for (int i = 0; i < objects.size(); i++) {
			
			for (int j = 0; j < objects.get(i).lines.size(); j++) {
				for (int k = 0; k < objects.get(i).lines.get(j).points.size(); k++) {
					allPoints.add(objects.get(i).lines.get(j).points.get(k));
				}
			}
		}
		
		//Sorting points by z-value in descending order so objects further away are drawn first, then drawn over by closer objects
		Collections.sort(allPoints, new PointComparator());
		
		for (Point point : allPoints) {
			Point p = new Point(point.x, point.y, point.z - Point.camZ, point.color).xy();
			g2D.setColor(point.color);
			if (!((point.z - Point.camZ) < Point.camZ)) {
				g2D.drawOval((int) (p.x), (int) (p.y), 1, 1); //Adding 0.5 to round the values to the nearest whole number, in order to prevent holes due to integer conversion
			}
		}
		
		
		// g2D.setColor(Color.black);
		// g2D.drawOval((int) (Main.camX - 2.5), (int) (Main.camY - 2.5), 5, 5);
		
	}
	
	
	public void initializePanel() {
		this.setVisible(true);
		this.setOpaque(false);
		this.setBounds(new Rectangle(1920, 1080));
	}
}



class Object3D {
	public ArrayList<Line> lines;
	public ArrayList<Point> points;
	public ExecutorService exec;
	public int numThreads = 1;
	
	public Object3D() {
		lines = new ArrayList<Line>();
		points = new ArrayList<Point>();
		exec = Executors.newFixedThreadPool(1);
	}
	
	public Object3D(int numParts) {
		lines = new ArrayList<Line>();
		points = new ArrayList<Point>();
		exec = Executors.newFixedThreadPool(numParts);
		numThreads = numParts;
	}
	
	public Object3D(ArrayList<Line> lines) {
		this.lines = lines;
		points = new ArrayList<Point>();
		exec = Executors.newFixedThreadPool(1);
	}
	
	public Object3D(ArrayList<Line> lines, int numParts) {
		this.lines = lines;
		points = new ArrayList<Point>();
		exec = Executors.newFixedThreadPool(numParts);
		numThreads = numParts;
	}
	
	public Object3D(ArrayList<Line> lines, ArrayList<Point> points, int numParts) {
		this.lines = lines;
		this.points = points;
		exec = Executors.newFixedThreadPool(numParts);
		numThreads = numParts;
	}
	
	//meant to be overridden
	public void updateFunc(int index) {
		
	}
	
	public boolean update() {
		ArrayList<RenderRunnable> runnables = new ArrayList<RenderRunnable>();
		
		
		for (int i = 0; i < numThreads; i++) {
			final int[] count = {i};
			runnables.add(new RenderRunnable() {
				@Override
				public void run() {
					updateFunc(count[0]);
				}
			});
		}
		
		ArrayList<FutureTask<Boolean>> tasks = new ArrayList<FutureTask<Boolean>>();
		for (int i = 0; i < runnables.size(); i++) {
			tasks.add(new FutureTask<Boolean>(runnables.get(i), true));
		}
		
		for (int i = 0; i < tasks.size(); i++) {
			exec.submit(tasks.get(i));
		}
		
		try {
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).get());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean clear() {
		this.lines.clear();
		return true;
	}
}

class Line {
	public ArrayList<Point> points;
	
	public Line() {
		points = new ArrayList<Point>();
	}
	
	public Line(ArrayList<Point> points) {
		this.points = points;
	}
}

class Camera {
	double x;
	double y;
	double z;
	public Camera() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	public Camera(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

class Render implements Runnable {
	public ArrayList<Line> lines = new ArrayList<Line>();
	public ArrayList<Point> points = new ArrayList<Point>();
	@Override
	public void run() {
		
	}
}

class RenderRunnable implements Runnable {
	@Override
	public void run() {
		
	}
}