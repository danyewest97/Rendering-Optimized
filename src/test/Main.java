
package test;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;

import java.time.Duration;
import java.time.Instant;

public class Main {
	public static double camX = 250;
	public static double camY = 250;
	public static double camZ = 0;
	public static double zSensitivity = 0.005;
	public static double frames = 0;
	public static double fps = 60;
	public static double millis = 0;
	public static Frame frame;
	
	public static Object3D cube;
	
	public static void main(String[] args) {
		frame = new Frame();
		Panel panel = new Panel();
		frame.add(panel);
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true"); //not necessary, just prevents a bug with Comparator that sends an error message, however this error message is not fatal
		
		// try {
			// while (true) {
				// Thread temp = new Thread() {
						// public void run() {
						// int x = 0;
						// while (true) {
							// x++;
						// }
					// }
				// };
				// temp.start();
			// }
		// } catch (Exception e) {
			
		// }
		
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
		
		
		// Point d = new Point(100, 100, 0, Color.red);
		// Point e = new Point(200, 100, 0, Color.red);
		// Point f = new Point(100, 200, 0, Color.red);
		
		
		ArrayList<Line> tri1 = tri(a, b, c);
		ArrayList<Line> tri2 = tri(a, c, d);
		ArrayList<Line> tri3 = tri(a1, b1, c1);
		ArrayList<Line> tri4 = tri(a1, c1, d1);
		ArrayList<Line> tri5 = tri(a1, a, b1);
		ArrayList<Line> tri6 = tri(b1, b, a);
		ArrayList<Line> tri7 = tri(b1, b, c);
		ArrayList<Line> tri8 = tri(b1, c1, c);
		ArrayList<Line> tri9 = tri(d, c, c1);
		ArrayList<Line> tri10 = tri(d, d1, c1);
		ArrayList<Line> tri11 = tri(a1, a, d1);
		ArrayList<Line> tri12 = tri(d1, d, a);
		
		Object3D cube = new Object3D();
		cube.lines.addAll(tri1);
		cube.lines.addAll(tri2);
		cube.lines.addAll(tri3);
		cube.lines.addAll(tri4);
		cube.lines.addAll(tri5);
		cube.lines.addAll(tri6);
		cube.lines.addAll(tri7);
		cube.lines.addAll(tri8);
		cube.lines.addAll(tri9);
		cube.lines.addAll(tri10);
		cube.lines.addAll(tri11);
		cube.lines.addAll(tri12);
		
		panel.objects.add(cube);
		
		// ArrayList<Line> tri1 = tri(a, b, c);
		// ArrayList<Line> tri2 = tri(d, e, f);
		// ArrayList<Line> tri3 = tri(d, e, f);
		
		
		// Object3D obj1 = new Object3D(tri1);
		// Object3D obj2 = new Object3D(tri2);
		// Object3D obj3 = new Object3D(tri3);
		
		// panel.objects.add(obj1);
		// panel.objects.add(obj2);
		// panel.objects.add(obj3);
		
		
		for (Point p : cpoints) {
			p.x += 100;
		}
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				// panel.repaint();
				// a.x += 0.1;
				// a.y += 0.1;
				
				// a.z += 0.1;
				// b.z += 0.1;
				// panel.objects.get(0).points = lineArray(a, b);
				
				// a.z += -0.3;
				// b.z += -0.3;
				// c.z += -0.3;
				// a1.z += -0.3;
				
				
				for (Point p : cpoints) {
					// p.x += Math.cos(millis / 100);
					// p.y += Math.sin(millis / 100);
					p.z += 0.3;
				}
			
				ArrayList<Line> tri1 = tri(a, b, c);
				ArrayList<Line> tri2 = tri(a, c, d);
				ArrayList<Line> tri3 = tri(a1, b1, c1);
				ArrayList<Line> tri4 = tri(a1, c1, d1);
				ArrayList<Line> tri5 = tri(a1, a, b1);
				ArrayList<Line> tri6 = tri(b1, b, a);
				ArrayList<Line> tri7 = tri(b1, b, c);
				ArrayList<Line> tri8 = tri(b1, c1, c);
				ArrayList<Line> tri9 = tri(d, c, c1);
				ArrayList<Line> tri10 = tri(d, d1, c1);
				ArrayList<Line> tri11 = tri(a1, a, d1);
				ArrayList<Line> tri12 = tri(d1, d, a);
				
				cube.lines = new ArrayList<Line>();
				
				cube.lines.addAll(tri1);
				cube.lines.addAll(tri2);
				cube.lines.addAll(tri3);
				cube.lines.addAll(tri4);
				cube.lines.addAll(tri5);
				cube.lines.addAll(tri6);
				cube.lines.addAll(tri7);
				cube.lines.addAll(tri8);
				cube.lines.addAll(tri9);
				cube.lines.addAll(tri10);
				cube.lines.addAll(tri11);
				cube.lines.addAll(tri12);
				
				
				
				// panel.objects.get(0).lines = tri(a, b, c);
				// panel.objects.get(1).lines = tri(a, c, a1);
				// panel.objects.get(2).lines = tri(d, e, f);
				
				// System.out.println(panel.objects.get(0).points.size());
				
				panel.repaint();
				millis++;
				
			}
		}, 0, 10);
		
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
		
		//------------------------------------------------------
		
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
		
		//Point above in 3D
		Point fz = r.get(0);
		Point c1z = r.get(1);
		Point c2z = r.get(2);
		
		// for (int i = 0; i < fill.size(); i++) {
			
			// result.addAll(lineArray2D(f, fill.get(i)));
		// }
		
		
		//------------------------------------------------------
		
		
		// int numLines = 0;
		// if (a.dist(center) > numLines) numLines = (int) (a.dist(center) + 1);
		// if (b.dist(center) > numLines) numLines = (int) (b.dist(center) + 1);
		// if (c.dist(center) > numLines) numLines = (int) (c.dist(center) + 1);
		
		int temp = (int) (c1.dist(c2) + 0.5);
		for (int i = 0; i <= temp; i++) {
			double p = (double) i / temp;
			Point x = new Point(f.x + (c1.x - f.x) * (p), f.y + (c1.y - f.y) * (p), 0, f.color).toXYZ(fz.z + (c1z.z - fz.z) * (p));
			Point y = new Point(c2.x + (c1.x - c2.x) * (p), c2.y + (c1.y - c2.y) * (p), 0, c2.color).toXYZ(c2z.z + (c1z.z - c2z.z) * (p));
			
			result.add(lineArray(x, y));
		}
		
		
		
		// result.add(center);
		
		//Adding black edges
		Point e1 = new Point(a.x, a.y, a.z, Color.black);
		Point e2 = new Point(b.x, b.y, b.z, Color.black);
		Point e3 = new Point(c.x, c.y, c.z, Color.black);
		
		result.add(lineArray(e1, e2));
		result.add(lineArray(e2, e3));
		result.add(lineArray(e3, e1));
		
		// System.out.println(area);
		// System.out.println(area - result.size());
		
		return result;
	}
	
	public static Line lineArray(Point a, Point b) {
		Dimension size = frame.getContentPane().getSize();
		int width = size.width;
		int height = size.height;
		
		Line result = new Line();
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		// Point a2D = a;
		// Point b2D = b;
		
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
		// numPoints *= 2;
		// int numPoints = (int) (a.dist(b) + 1);
		
		for (int i = 0; i <= numPoints; i++) {
			//The percent, as a decimal, to lerp from the first point to the last point
			double p = (double) i / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
			
			result.add(new Point(a.x + (b.x - a.x) * p, a.y + (b.y - a.y) * p, a.z + (b.z - a.z) * p, a.color));
		}
		
		return result;
	}
	
	
	//Not updated, currently deprecated
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
	
	
}




class Point {
	public double x;
	public double y;
	public double z;
	public Color color;
	
	public static double camX = 250;
	public static double camY = 250;
	public static double camZ = 0;
	public static double zSensitivity = 0.003;
	
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
		
		for (Object3D obj : objects) {
			for (Line l : obj.lines) {
				for (Point p : l.points) {
					allPoints.add(p);
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
		
		Main.frames++;
	}
	
	
	public void initializePanel() {
		this.setVisible(true);
		this.setOpaque(false);
		this.setBounds(new Rectangle(1920, 1080));
	}
}



class Object3D {
	public ArrayList<Line> lines;
	
	public Object3D() {
		lines = new ArrayList<Line>();
	}
	
	public Object3D(ArrayList<Line> lines) {
		this.lines = lines;
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