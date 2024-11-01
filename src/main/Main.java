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
	public static Object3D cubea;
	public static Object3D cubeb;
	
	public static Point[][] points2D = new Point[1920][1080];
	public static Panel panel;
	
	public static double millis = 0;
	public static double avg = 0;
	
	public static void main(String[] args) {
		// System.setProperty("sun.java2d.opengl", "true"); //supposed to enable hardware acceleration
		
		
		
		frame = new Frame();
		/* Panel */ panel = new Panel();
		frame.add(panel);
		
		

		
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
		
		cube = new Object3D(tris);
		
		
		
		
		// Tri tri1a = new Tri(a, b, c);
		// Tri tri2a = new Tri(a, c, d);
		// Tri tri3a = new Tri(a1, b1, c1);
		// Tri tri4a = new Tri(a1, c1, d1);
		// Tri tri5a = new Tri(a1, a, b1);
		// Tri tri6a = new Tri(b1, b, a);
		// Tri tri7a = new Tri(b1, b, c);
		// Tri tri8a = new Tri(b1, c1, c);
		// Tri tri9a = new Tri(d, c, c1);
		// Tri tri10a = new Tri(d, d1, c1);
		// Tri tri11a = new Tri(a1, a, d1);
		// Tri tri12a = new Tri(d1, d, a);
		
		// ArrayList<Tri> trisa = new ArrayList<Tri>();
		// trisa.add(tri1a);
		// trisa.add(tri2a);
		// trisa.add(tri3a);
		// trisa.add(tri4a);
		// trisa.add(tri5a);
		// trisa.add(tri6a);
		// trisa.add(tri7a);
		// trisa.add(tri8a);
		// trisa.add(tri9a);
		// trisa.add(tri10a);
		// trisa.add(tri11a);
		// trisa.add(tri12a);
		
		// cubea = new Object3D(trisa);
		
		
		
		// Tri tri1b = new Tri(a, b, c);
		// Tri tri2b = new Tri(a, c, d);
		// Tri tri3b = new Tri(a1, b1, c1);
		// Tri tri4b = new Tri(a1, c1, d1);
		// Tri tri5b = new Tri(a1, a, b1);
		// Tri tri6b = new Tri(b1, b, a);
		// Tri tri7b = new Tri(b1, b, c);
		// Tri tri8b = new Tri(b1, c1, c);
		// Tri tri9b = new Tri(d, c, c1);
		// Tri tri10b = new Tri(d, d1, c1);
		// Tri tri11b = new Tri(a1, a, d1);
		// Tri tri12b = new Tri(d1, d, a);
		
		// ArrayList<Tri> trisb = new ArrayList<Tri>();
		// trisb.add(tri1b);
		// trisb.add(tri2b);
		// trisb.add(tri3b);
		// trisb.add(tri4b);
		// trisb.add(tri5b);
		// trisb.add(tri6b);
		// trisb.add(tri7b);
		// trisb.add(tri8b);
		// trisb.add(tri9b);
		// trisb.add(tri10b);
		// trisb.add(tri11b);
		// trisb.add(tri12b);
		
		// cubeb = new Object3D(trisb);
		
		
		
		
		
		// panel.objects.add(cube);
		
		// panel.objects.add(cubea);
		
		// panel.objects.add(cubeb);
		
		
		cube.move(100, 100, 0);
		
		
		
		// Tri tri1 = new Tri(a, b, c);
		// ArrayList<Tri> temp = new ArrayList<Tri>();
		// temp.add(tri1);
		// Object3D triangle = new Object3D(temp);
		// panel.objects.add(triangle);
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				cube.move(Math.cos(millis / 100) * 1, Math.sin(millis / 100) * 1, Math.sin(millis / 100) * 1);
				// cubea.move(Math.cos(millis / 250) * 1, Math.sin(millis / 170) * 1, 0);
				// cubeb.move(0, 0, Math.sin(millis / 250) * 1);
				
				// tri1.a.z += 0.3;
				millis++;
				
			}
		}, 0, 1);
		
		Timer t2 = new Timer();
		t2.schedule(new TimerTask() {
			@Override
			public void run() {
				
				if (!panel.running) {
					if (cube.update()) {
						// if (panel.running) System.out.println(panel.running);
						// if (cubea.update()) {
							// if (cubeb.update()) {
								// if (!panel.running) {
									if (!panel.running) {
										panel.repaint();
									}
								// }
							// }
						// }
					}
				}
				
				
			}
		}, 0, 1);
	}
	
	
	// public static void updateFrame() {
		
	// }
	
	
	public static ArrayList<Line> tri(Point a, Point b, Point c) {
		ArrayList<Line> result = new ArrayList<Line>();
		
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		Point c2D = c.xy();
		
		
		ArrayList<Point> r = new ArrayList<Point>();
		ArrayList<Point> rz = new ArrayList<Point>();
		r.add(a2D);
		rz.add(a);
		
		
		if (b2D.x < a2D.x) {
			r.add(0, b2D);
			rz.add(0, b);
		} else {
			r.add(b2D);
			rz.add(b);
		}
		if (c2D.x < r.get(0).x) {
			r.add(0, c2D);
			rz.add(0, c);
		} else {
			if (c2D.x < r.get(1).x) {
				r.add(1, c2D);
				rz.add(1, c);
			} else {
				r.add(c2D);
				rz.add(c);
			}
		}
		
		// ArrayList<Point> fill = lineArray2D(r.get(0), r.get(1));
		Point x = r.get(0); //furthest point
		Point y = r.get(1); //closer point 1
		Point z = r.get(2); //closer point 2
		
		//Points above in 3D
		Point xz = rz.get(0);
		Point yz = rz.get(1);
		Point zz = rz.get(2);
		
		
		for (int i = (int) (x.x + 1); i <= (int) z.x; i++) {
			double p1 = (double) (i - x.x) / (z.x - x.x);
			double p2;
			if (i <= y.x) p2 = (double) (i - x.x) / (y.x - x.x);
			else p2 = (double) (i - y.x) / (z.x - y.x);
			
			//Had to add this because p2 was getting negative when i was equal to or very close to x.x (since y.x - x.x is evaluated as 0 (or a decimal) and causes p2 to be very positive or very negative
			//There is still a single point being generated that shouldn't be, this can be fixed by adding one to the start value of i, but this also removes
			//a point that should be in the triangle
			if (p2 < 0) p2 = 0;
			
			Point add1 = new Point(i, x.y + (z.y - x.y) * (p1), 0, a.color).toXYZ(xz.z + (zz.z - xz.z) * (p1));
			
			// add1.addToPoints();
			
			Point add2;
			if (i <= y.x) {
				add2 = new Point(i, x.y + (y.y - x.y) * (p2), 0, a.color).toXYZ(xz.z + (yz.z - xz.z) * (p2));
			} else {
				add2 = new Point(i, y.y + (z.y - y.y) * (p2), 0, a.color).toXYZ(yz.z + (zz.z - yz.z) * (p2));
			}
			// add2.addToPoints();
			
			
			result.add(verticalLineArray(add1, add2));
		}
		
				
		//Adding black edges
		Point e1 = new Point(a.x - 0.5, a.y - 0.5, a.z, Color.black);
		Point e2 = new Point(b.x - 0.5, b.y - 0.5, b.z, Color.black);
		Point e3 = new Point(c.x - 0.5, c.y - 0.5, c.z, Color.black);
		
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
						add.addToPoints();
					}
				}
				result.points.add(add);
			}
		}
		
		return result;
	}
	
	//Vertical line array, used for generating triangles
	public static Line verticalLineArray(Point a, Point b) {
		Dimension size = frame.getContentPane().getSize();
		int width = size.width;
		int height = size.height;
		
		Line result = new Line();
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		
		int numPoints = (int) (Math.abs(b2D.y - a2D.y) + 1);


		if (b2D.y < a2D.y) {
			for (int i = (int) b2D.y; i <= (int) a2D.y; i++) {
				//The percent, as a decimal, to lerp from the first point to the last point
				double p = (double) (i - b2D.y) / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
				
				double z = b.z + (a.z - b.z) * p;
				if (!(z < Point.camZ)) {
					Point add = new Point(b2D.x, i, 0, b.color).toXYZ(z);
					Point addxy = add.xy();
					
					if (!(addxy.x < 0 || addxy.x > width)) {
						if (!(addxy.y < 0 || addxy.y > height)) {
							add.addToPoints();
						}
					}
					result.points.add(add);
				}
			}
		}
		
		
		if (a2D.y <= b2D.y) {
			for (int i = (int) a2D.y; i <= (int) b2D.y; i++) {
				//The percent, as a decimal, to lerp from the first point to the last point
				double p = (double) (i - a2D.y) / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
				
				double z = a.z + (b.z - a.z) * p;
				if (!(z < Point.camZ)) {
					Point add = new Point(a2D.x, i, 0, a.color).toXYZ(z);
					Point addxy = add.xy();
					
					if (!(addxy.x < 0 || addxy.x > width)) {
						if (!(addxy.y < 0 || addxy.y > height)) {
							add.addToPoints();
						}
					}
					result.points.add(add);
				}
			}
		}
		
		
		return result;
	}
	
	// public static ArrayList<Point> trueLineArray(Point a, Point b) {
		// ArrayList<Point> result = new ArrayList<Point>();
		
		// int numPoints = (int) (a.dist(b) + 1); //trying to use a2D and b2D in order to not find more points than can be drawn, however I am unsure if it is working 100% properly
		
		// for (int i = 0; i <= numPoints; i++) {
			// double p = (double) i / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
			
			// result.add(new Point(a.x + (b.x - a.x) * p, a.y + (b.y - a.y) * p, a.z + (b.z - a.z) * p, a.color));
		// }
		
		// return result;
	// }
	
	
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
		// addToPoints();
	}
	
	public Point(double x, double y, double z, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = color;
		// addToPoints();
	}
	
	public double dist(Point p) {
		return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) + Math.pow(p.z - z, 2));
	}
	
	public void addToPoints() {
		Point temp = this.xy();
		//adding 0.5 to round to the nearest number, which should account for floating point precision errors
		temp.x += 0.5;
		temp.y += 0.5;
		int row = (int) temp.x;
		int column = (int) temp.y;
		// System.out.println(temp);
		if (temp.x < 1920 && temp.y < 1080 && temp.x >= 0 && temp.y >= 0) {
			try {
				if (Main.points2D[row][column].z >= this.z) {
					// if (Main.points2D[(int) temp.x][(int) temp.y].z == this.z) {
						//Will implement later, I intend to average the colors of two points that land on the same pixel and have the same z-value
						// Main.points2D[(int) temp.x][(int) temp.y] = new Point(temp.x, temp.y, this.z, this.color);
					// } else {
						Main.points2D[row][column] = new Point(temp.x, temp.y, this.z, this.color);
					// }
				}
			} catch (NullPointerException e) {
				Main.points2D[row][column] = new Point(temp.x, temp.y, this.z, this.color);
			}
		}
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
		// super.paintComponents(g);
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
		super.paintComponent(g);
		
		
		BufferedImage bImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		
		
		Graphics2D g2D = (Graphics2D) g;
		
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				if (Main.points2D[i][j] == null) {
					bImage.setRGB(i, j, Color.white.getRGB());
				}
			}
		}
		
		for (int i = 0; i < bImage.getWidth(); i++) {
			for (int j = 0; j < bImage.getHeight(); j++) {
				Point p = Main.points2D[i][j];
				if (p != null) {
					bImage.setRGB((int) p.x, (int) p.y, p.color.getRGB());
				}					
			}
		}
		
		
		g.drawImage(bImage, 0, 0, this);
		g.dispose();
		
		
		Main.points2D = new Point[1920][1080];
		running = false;
		// Main.updateFrame();
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
	public boolean updating = false;
	
	public Object3D() {
		tris = new ArrayList<Tri>();
	}
	
	public Object3D(ArrayList<Tri> tris) {
		this.tris = tris;
	}
	
	
	public boolean update() {
		updating = true;
		for (int i = 0; i < tris.size(); i++) {
			tris.get(i).update();
		}
		updating = false;
		return true;
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
}



// class Object3D {
	// public ArrayList<Tri> tris;
	// public ExecutorService exec;
	// public int numThreads = 1;
	// public boolean updating = false;
	
	// public Object3D() {
		// tris = new ArrayList<Tri>();
		// exec = Executors.newFixedThreadPool(1);
	// }
	
	// public Object3D(int numParts) {
		// tris = new ArrayList<Tri>();
		// exec = Executors.newFixedThreadPool(numParts);
		// numThreads = numParts;
	// }
	
	// public Object3D(ArrayList<Tri> tris) {
		// this.tris = tris;
		// exec = Executors.newFixedThreadPool(tris.size());
		// numThreads = tris.size();
	// }
	
	// public Object3D(ArrayList<Tri> tris, int numParts) {
		// this.tris = tris;
		// exec = Executors.newFixedThreadPool(numParts);
		// numThreads = numParts;
	// }
	
	

	// public void updateFunc(int index) {	
		// tris.get(index).update();
	// }
	
	// public boolean update() {
		// updating = true;
		// ArrayList<RenderRunnable> runnables = new ArrayList<RenderRunnable>();
		
		// for (int i = 0; i < numThreads; i++) {
			// final int[] count = {i};
			// runnables.add(new RenderRunnable() {
				// @Override
				// public void run() {
					// updateFunc(count[0]);
				// }
			// });
		// }
		
		// ArrayList<FutureTask<Boolean>> tasks = new ArrayList<FutureTask<Boolean>>();
		// for (int i = 0; i < runnables.size(); i++) {
			// tasks.add(new FutureTask<Boolean>(runnables.get(i), true));
		// }
		
		// for (int i = 0; i < tasks.size(); i++) {
			// exec.submit(tasks.get(i));
		// }
		
		
		// try {
			
			// for (int i = 0; i < tasks.size(); i++) {
				// tasks.get(i).get();
			// }
			
			// updating = false;
			// return true;
		// } catch (Exception e) {
			// updating = false;
			// e.printStackTrace();
			// return false;
		// }
	// }
	
	
	// public void move(double x, double y, double z) {
		// for (Tri t : tris) {
				// t.a.x += x;
				// t.a.y += y;
				// t.a.z += z;
				
				// t.b.x += x;
				// t.b.y += y;
				// t.b.z += z;
				
				// t.c.x += x;
				// t.c.y += y;
				// t.c.z += z;
		// }
		
	// }
// }

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