package main;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.image.*;

import java.time.Duration;
import java.time.Instant;


/*Reminders/TODO:
-Fix panel.paintComponent() running during cube.update() (or other way around) to prevent flickering and errors
*/

//to find the time it takes for some code to run (in millis):

// long startTime = System.nanoTime();
// *code to test*
// long stopTime = System.nanoTime();
// System.out.println((stopTime - startTime) / 1000000);

public class Main {
	public static Frame frame;
	public static Camera mainCam = new Camera(250, 250, 0);
	public static double zSensitivity = 0.003;
	
	public static Object3D cube;
	
	
	public static Panel panel;
	
	public static double millis = 0;
	public static double avg = 0;
	
	public static void main(String[] args) {
		// System.setProperty("sun.java2d.opengl", "true"); //supposed to enable hardware acceleration
		
		
		
		frame = new Frame();
		/* Panel */ panel = new Panel();
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
		
		
		Tri tri1 = new Tri(a, b, c);
		Tri tri2 = new Tri(a, c, d);
		Tri tri3 = new Tri(a1, b1, c1);
		Tri tri4 = new Tri(a1, c1, d1);
		Tri tri5 = new Tri(a1, a, b1);
		Tri tri6 = new Tri(b1, b, a);
		Tri tri7 = new Tri(b1, b, c);
		Tri tri8 = new Tri(b1, c1, c);
		Tri tri9 = new Tri(d, c, c1);
		Tri tri10 = new Tri(d, d1, c1);
		Tri tri11 = new Tri(a1, a, d1);
		Tri tri12 = new Tri(d1, d, a);
		
		ArrayList<Tri> tris = new ArrayList<Tri>();
		tris.add(tri1);
		tris.add(tri2);
		tris.add(tri3);
		tris.add(tri4);
		tris.add(tri5);
		tris.add(tri6);
		tris.add(tri7);
		tris.add(tri8);
		tris.add(tri9);
		tris.add(tri10);
		tris.add(tri11);
		tris.add(tri12);
		
		Object3D cube = new Object3D(tris);
		
		
		
		
		Tri tri1a = new Tri(a, b, c);
		Tri tri2a = new Tri(a, c, d);
		Tri tri3a = new Tri(a1, b1, c1);
		Tri tri4a = new Tri(a1, c1, d1);
		Tri tri5a = new Tri(a1, a, b1);
		Tri tri6a = new Tri(b1, b, a);
		Tri tri7a = new Tri(b1, b, c);
		Tri tri8a = new Tri(b1, c1, c);
		Tri tri9a = new Tri(d, c, c1);
		Tri tri10a = new Tri(d, d1, c1);
		Tri tri11a = new Tri(a1, a, d1);
		Tri tri12a = new Tri(d1, d, a);
		
		ArrayList<Tri> trisa = new ArrayList<Tri>();
		trisa.add(tri1a);
		trisa.add(tri2a);
		trisa.add(tri3a);
		trisa.add(tri4a);
		trisa.add(tri5a);
		trisa.add(tri6a);
		trisa.add(tri7a);
		trisa.add(tri8a);
		trisa.add(tri9a);
		trisa.add(tri10a);
		trisa.add(tri11a);
		trisa.add(tri12a);
		
		Object3D cubea = new Object3D(trisa);
		
		
		
		Tri tri1b = new Tri(a, b, c);
		Tri tri2b = new Tri(a, c, d);
		Tri tri3b = new Tri(a1, b1, c1);
		Tri tri4b = new Tri(a1, c1, d1);
		Tri tri5b = new Tri(a1, a, b1);
		Tri tri6b = new Tri(b1, b, a);
		Tri tri7b = new Tri(b1, b, c);
		Tri tri8b = new Tri(b1, c1, c);
		Tri tri9b = new Tri(d, c, c1);
		Tri tri10b = new Tri(d, d1, c1);
		Tri tri11b = new Tri(a1, a, d1);
		Tri tri12b = new Tri(d1, d, a);
		
		ArrayList<Tri> trisb = new ArrayList<Tri>();
		trisb.add(tri1b);
		trisb.add(tri2b);
		trisb.add(tri3b);
		trisb.add(tri4b);
		trisb.add(tri5b);
		trisb.add(tri6b);
		trisb.add(tri7b);
		trisb.add(tri8b);
		trisb.add(tri9b);
		trisb.add(tri10b);
		trisb.add(tri11b);
		trisb.add(tri12b);
		
		Object3D cubeb = new Object3D(trisb);
		
		
		
		
		
		panel.objects.add(cube);
		
		panel.objects.add(cubea);
		
		panel.objects.add(cubeb);
		
		
		cube.move(100, 100, 0);
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				long startTime = System.nanoTime();
				
				cube.move(Math.cos(millis / 100) * 1, Math.sin(millis / 100) * 1, Math.sin(millis / 100) * 1);
				cubea.move(Math.cos(millis / 250) * 1, Math.sin(millis / 170) * 1, 0);
				cubeb.move(0, 0, Math.sin(millis / 250) * 1);
				
				
				cube.update();
				cubea.update();
				cubeb.update();
				
				if (!panel.running) {
					panel.repaint();
				}
				
				
				
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
			if (!(z < Point.camZ)) {
				Point add = new Point(a2D.x + (b2D.x - a2D.x) * p, a2D.y + (b2D.y - a2D.y) * p, 0, a.color).toXYZ(z);
				Point addxy = add.xy();
				
				if (!(addxy.x < 0 || addxy.x > width)) {
					if (!(addxy.y < 0 || addxy.y > height)) {
						result.points.add(add);
					}
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
	
	public Point() {
		this.x = 0;
		this.y = 0;
		this.z = Integer.MAX_VALUE;
		this.color = Color.white;
	}
	
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
	
	@Override
	public Point clone() {
		return new Point(x, y, z, color);
	}
	
	@Override
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
	}
}



class Panel extends JPanel {
	public ArrayList<Object3D> objects;
	public boolean running = false;
	
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
		running = true;
		
		
		
		BufferedImage bImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		
		//Creating a new points array representing the pixels on the screen
		Point[][] points2D = new Point[1920][1080];
		
		Graphics2D g2D = (Graphics2D) g;
		
		
		// long startTime = System.nanoTime();
		
		for (int i = 0; i < objects.size(); i++) {
			ArrayList<Tri> tris = objects.get(i).tris;
			for (int j = 0; j < tris.size(); j++) {
				Tri t = tris.get(j);
				ArrayList<Line> lines = t.lines;
				for (int k = 0; k < lines.size(); k++) {
					Line l = lines.get(k);
					for (int m = 0; m < l.points.size(); m++) {
						Point pz = l.points.get(m);
						Point pxy = pz.xy();
						//The default points in the points2D array are null objects, so checking the z value of one of them will return a NullPointerException
						//However, if the if statement below returns a NullPointerException, we want to add the current Point, because that means the current point is in
						//an available position on the screen
						try {
							if (points2D[(int) pxy.x][(int) pxy.y].z >= pz.z) {
								points2D[(int) pxy.x][(int) pxy.y] = new Point(pxy.x, pxy.y, pz.z, pz.color);
							}
						} catch (NullPointerException e) {
							points2D[(int) pxy.x][(int) pxy.y] = new Point(pxy.x, pxy.y, pz.z, pz.color);
						}
					}
				}
			}
		}
		
		
		
		
		
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				bImage.setRGB(i, j, Color.white.getRGB());
			}
		}
		
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				Point p = points2D[i][j];
				if (p != null) bImage.setRGB((int) p.x, (int) p.y, p.color.getRGB());
			}
		}
		
		// long stopTime = System.nanoTime();
		// System.out.println(1000 / ((stopTime - startTime) / 1000000));
		
		
		g.drawImage(bImage, 0, 0, null);
		g.dispose();
		
		
		running = false;
	}
	
	
	public void initializePanel() {
		this.setVisible(true);
		this.setOpaque(false);
		this.setBounds(new Rectangle(1920, 1080));
	}
}




class Tri {
	public Point a;
	public Point b;
	public Point c;
	public ArrayList<Line> lines;
	public Tri(Point a, Point b, Point c) {
		this.a = a.clone();
		this.b = b.clone();
		this.c = c.clone();
		lines = Main.tri(a, b, c);
	}
	
	public boolean update() {
		lines = Main.tri(a, b, c);
		return true;
	}
	
	@Override
	public Tri clone() {
		return new Tri(a.clone(), b.clone(), c.clone());
	}
	
}



class Object3D {
	public ArrayList<Tri> tris;
	public ExecutorService exec;
	public int numThreads = 1;
	public boolean updating = false;
	
	public Object3D() {
		tris = new ArrayList<Tri>();
		exec = Executors.newFixedThreadPool(1);
	}
	
	public Object3D(int numParts) {
		tris = new ArrayList<Tri>();
		exec = Executors.newFixedThreadPool(numParts);
		numThreads = numParts;
	}
	
	public Object3D(ArrayList<Tri> tris) {
		this.tris = tris;
		exec = Executors.newFixedThreadPool(tris.size());
		numThreads = tris.size();
	}
	
	public Object3D(ArrayList<Tri> tris, int numParts) {
		this.tris = tris;
		exec = Executors.newFixedThreadPool(numParts);
		numThreads = numParts;
	}
	
	

	public void updateFunc(int index) {	
		tris.get(index).update();
	}
	
	public boolean update() {
		updating = true;
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
				tasks.get(i).get();
			}
			
			updating = false;
			return true;
		} catch (Exception e) {
			updating = false;
			return false;
		}
	}
	
	
	public void move(double x, double y, double z) {
		for (Tri t : tris) {
				t.a.x += x;
				t.a.y += y;
				t.a.z += z;
				
				t.b.x += x;
				t.b.y += y;
				t.b.z += z;
				
				t.c.x += x;
				t.c.y += y;
				t.c.z += z;
		}
		
	}
	// public ArrayList<Tri> trisClone() {
		// ArrayList<Tri> newTris = new ArrayList<Tri>();
		// for (int i = 0; i < this.tris.size(); i++) {
			// Tri t = this.tris.get(i);
			// newTris.add(t.clone());
		// }
		
		// return newTris;
	// }
	
	// public boolean clear() {
		// this.lines.clear();
		// return true;
	// }
}

class Line {
	public ArrayList<Point> points;
	
	public Line() {
		points = new ArrayList<Point>();
	}
	
	public Line(ArrayList<Point> points) {
		this.points = points;
	}
	
	@Override
	public Line clone() {
		Line result = new Line();
		for (Point p : points) {
			result.points.add(p.clone());
		}
		return result;
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