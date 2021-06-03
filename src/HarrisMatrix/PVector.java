package HarrisMatrix;

import java.util.concurrent.ThreadLocalRandom;

public class PVector{
	
	public double x;
	public double y;
	public double z;
	
	public PVector(){
		x = 0;
		y = 0;
		z = 0;
	}
	
	public PVector(double a, double b){
		x = a;
		y = b;
		z = 0;
	}
	
	public PVector(double a, double b, double c){
		x = a;
		y = b;
		z = c;
	}
	
	public static PVector add(PVector a, PVector b){
		return new PVector(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public static PVector add(PVector a, PVector b, PVector target){
		if(target == null) {
			target = new PVector();
		}
		target.set(a.x + b.x, a.y + b.y, a.z + b.z);
		return target;
	}
	
	public static PVector sub(PVector a, PVector b){
		return new PVector(a.x - b.x, a.y - b.y, a.z - b.z);
	}
	
	public static PVector sub(PVector a, PVector b, PVector target){
		if(target == null) {
			target = new PVector();
		}
		target.set(a.x - b.x, a.y - b.y, a.z - b.z);
		return target;
	}
	
	public static PVector mult(PVector v, double m){
		v.mult(m);
		return v;
	}
	
	public static PVector mult(PVector v, double m, PVector target){
		if(target == null) {
			target = new PVector();
		}
		v.mult(m);
		target.set(v);
		return target;
	}
	
	public static PVector div(PVector v, double m){
		v.div(m);
		return v;
	}
	
	public static PVector div(PVector v, double m, PVector target){
		if(target == null) {
			target = new PVector();
		}
		v.div(m);
		target.set(v);
		return target;
	}
	
	public static double dist(PVector a, PVector b){
		return PVector.sub(a, b).mag();
	}
	
	public static double dot(PVector a, PVector b){
		return (a.x * b.x + a.y * b.y + a.z * b.z);
	}
	
	public static PVector cross(PVector a, PVector b){
		return a.cross(b);
	}
	
	public static PVector cross(PVector a, PVector b, PVector target){
		if(target == null) {
			target = new PVector();
		}
		target.set(a.cross(b));
		return target;
	}
	
	public static void normalize(PVector target){
		if(target == null) {
			target = new PVector(1, 1, 1);
			
		}
		target.normalize();
	}
	
	public static PVector limit(PVector target, double max){
		if(target == null) {
			target = new PVector();
		}
		target.limit(max);
		return target;
	}
	
	public static PVector setMag(PVector target, double l){
		if(target == null) {
			target = new PVector(1, 1, 1);
		}
		target.normalize();
		target.mult(l);
		return target;
	}
	
	public static PVector lerp(PVector a, PVector b, double t){
		a.mult(1 - t);
		b.mult(t);
		a.add(b);
		return a;
	}
	
	public static double angleBetween(PVector a, PVector b){
		return Math.acos(PVector.dot(a, b) / (a.mag() * b.mag()));
	}
	
	public static PVector random2D(){
		double a = ThreadLocalRandom.current().nextDouble(Math.PI * 2);
		return new PVector(Math.cos(a), Math.sin(a), 0);
	}
	
	public static void random2D(PVector target){
		if(target == null) {
			target = new PVector();
		}
		target.set(PVector.random2D());
	}
	
	public static PVector random3D(){
		double theta = ThreadLocalRandom.current().nextDouble(Math.PI * 2);
		double phi = ThreadLocalRandom.current().nextDouble(Math.PI);
		return new PVector(Math.sin(phi) * Math.cos(theta), Math.sin(phi) * Math.sin(theta), Math.cos(phi));
	}
	
	public static void random3D(PVector target){
		if(target == null) {
			target = new PVector();
		}
		target.set(PVector.random3D());
	}
	
	public static PVector fromAngle(double theta){
		return new PVector(Math.cos(theta), Math.sin(theta), 0);
	}
	
	public static void fromAngle(double theta, PVector target){
		if(target == null) {
			target = new PVector();
		}
		target.set(Math.cos(theta), Math.sin(theta), 0);
	}
	
	public static PVector fromAngle3D(double theta, double phi){
		return new PVector(Math.sin(phi) * Math.cos(theta), Math.sin(phi) * Math.sin(theta), Math.cos(phi));
	}
	
	public static void fromAngle3D(double theta, double phi, PVector target){
		if(target == null) {
			target = new PVector();
		}
		target.set(PVector.fromAngle3D(theta, phi));
	}
	
	public void set(double a, double b){
		x = a;
		y = b;
	}
	
	public void set(double a, double b, double c){
		x = a;
		y = b;
		z = c;
	}
	
	public void set(PVector v){
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public void set(double[] v){
		x = v[0];
		y = v[1];
		z = v[2];
	}
	
	public PVector copy(){
		return new PVector(x, y, z);
	}
	
	public void add(PVector v){
		x += v.x;
		y += v.y;
		z += v.z;
	}
	
	public void add(double a, double b){
		x += a;
		y += b;
	}
	
	public void add(double a, double b, double c){
		x += a;
		y += b;
		z += c;
	}
	
	public void sub(PVector v){
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}
	
	public void sub(double a, double b){
		x -= a;
		y -= b;
	}
	
	public void sub(double a, double b, double c){
		x -= a;
		y -= b;
		z -= c;
	}
	
	public void mult(double m){
		x *= m;
		y *= m;
		z *= m;
	}
	
	public void div(double m){
		x /= m;
		y /= m;
		z /= m;
	}
	
	public double mag(){
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public double magSq(){
		return (x * x + y * y + z * z);
	}
	
	public double dist(PVector v){
		return PVector.sub(this, v).mag();
	}
	
	public double dot(PVector v){
		return (x * v.x + y * v.y + z * v.z);
	}
	
	public double dot(double a, double b, double c){
		return (x * a + y * b + z * c);
	}
	
	public PVector cross(PVector v){
		double a = y * v.z - z * v.y;
		double b = z * v.x - x * v.z;
		double c = x * v.y - y * v.x;
		return new PVector(a, b, c);
	}
	
	public void normalize(){
		div(mag());
	}
	
	public void limit(double max){
		if(mag() > max) {
			setMag(max);
		}
	}
	
	public void setMag(double l){
		normalize();
		mult(l);
	}
	
	public double heading(){
		return Math.atan(y / x);
	}
	
	public void rotate(double theta){
		double a = Math.cos(theta) * x - Math.sin(theta) * y;
		double b = Math.sin(theta) * x + Math.cos(theta) * y;
		set(a, b);
	}
	
	public PVector lerp(PVector v, double a){
		PVector x = PVector.mult(this, (1 - a));
		v.mult(a);
		v.add(x);
		return v;
	}
	
	public PVector lerp(double a, double b, double c, double t){
		PVector x = new PVector(a, b, c);
		mult(1 - t);
		x.mult(t);
		x.add(this);
		return x;
	}
	
	public double[] array(){
		return new double[]{x, y, z};
	}
}
