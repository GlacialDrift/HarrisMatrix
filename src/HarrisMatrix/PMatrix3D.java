package HarrisMatrix;

public class PMatrix3D implements PMatrix{
	
	public double d00, d01, d02, d03;
	public double d10, d11, d12, d13;
	public double d20, d21, d22, d23;
	public double d30, d31, d32, d33;
	
	public PMatrix3D(){
		reset();
	}
	
	public PMatrix3D(double n00, double n01, double n02, double n10, double n11, double n12){
		set(n00, n01, n02, 0, n10, n11, n12, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	public PMatrix3D(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33){
		set(d00, d01, d02, d03, d10, d11, d12, d13, d20, d21, d22, d23, d30, d31, d32, d33);
	}
	
	public PMatrix3D(PMatrix m){
		set(m);
	}
	
	@Override
	public void apply(double m00, double m01, double m02, double m10, double m11, double m12){
		apply(m00, m01, 0, m02, m10, m11, 0, m12, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void apply(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33){
		double a00 = d00 * m00 + d01 * m10 + d02 * m20 + d03 * m30;
		double a10 = d10 * m00 + d11 * m10 + d12 * m20 + d13 * m30;
		double a20 = d20 * m00 + d21 * m10 + d22 * m20 + d23 * m30;
		double a30 = d30 * m00 + d31 * m10 + d32 * m20 + d33 * m30;
		
		double a01 = d00 * m01 + d01 * m11 + d02 * m21 + d03 * m31;
		double a11 = d10 * m01 + d11 * m11 + d12 * m21 + d13 * m31;
		double a21 = d20 * m01 + d21 * m11 + d22 * m21 + d23 * m31;
		double a31 = d30 * m01 + d31 * m11 + d32 * m21 + d33 * m31;
		
		double a02 = d00 * m02 + d01 * m12 + d02 * m22 + d03 * m32;
		double a12 = d10 * m02 + d11 * m12 + d12 * m22 + d13 * m32;
		double a22 = d20 * m02 + d21 * m12 + d22 * m22 + d23 * m32;
		double a23 = d30 * m02 + d31 * m12 + d32 * m22 + d33 * m32;
		
		double a03 = d00 * m03 + d01 * m13 + d02 * m23 + d03 * m33;
		double a13 = d10 * m03 + d11 * m13 + d12 * m23 + d13 * m33;
		double a32 = d20 * m03 + d21 * m13 + d22 * m23 + d23 * m33;
		double a33 = d30 * m03 + d31 * m13 + d32 * m23 + d33 * m33;
		
		d00 = a00;
		d01 = a01;
		d02 = a02;
		d03 = a03;
		
		d10 = a10;
		d11 = a11;
		d12 = a12;
		d13 = a13;
		
		d20 = a20;
		d21 = a21;
		d22 = a22;
		d23 = a23;
		
		d30 = a30;
		d31 = a31;
		d32 = a32;
		d33 = a33;
	}
	
	@Override
	public void apply(PMatrix m){
		if(m instanceof PMatrix2D) {
			apply((PMatrix2D) m);
		} else {
			apply((PMatrix3D) m);
		}
	}
	
	@Override
	public void apply(PMatrix2D m){
		apply(m.d00, m.d01, 0, m.d02, m.d10, m.d11, 0, m.d12, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void apply(PMatrix3D m){
		apply(m.d00, m.d01, m.d02, m.d03, m.d10, m.d11, m.d12, m.d13, m.d20, m.d21, m.d22, m.d23, m.d30, m.d31, m.d32, m.d33);
	}
	
	@Override
	public double determinant(){
		return d00 * det3x3(d11, d12, d13, d21, d22, d23, d31, d32, d33) + d01 * det3x3(d10, d12, d13, d20, d22, d23, d30, d32, d33) + d02 * det3x3(d10, d11, d13, d20, d21, d23, d30, d31, d33) + d03 * det3x3(d10, d11, d12, d20, d21, d22, d30, d31, d32);
	}
	
	@Override
	public PMatrix3D get(){
		return new PMatrix3D(this);
	}
	
	@Override
	public double[] get(double[] target){
		if(target == null || target.length != 16) {
			target = new double[16];
		}
		target[0] = d00;
		target[1] = d01;
		target[2] = d02;
		target[3] = d03;
		target[4] = d10;
		target[5] = d11;
		target[6] = d12;
		target[7] = d13;
		target[8] = d20;
		target[9] = d21;
		target[10] = d22;
		target[11] = d23;
		target[12] = d30;
		target[13] = d31;
		target[14] = d32;
		target[15] = d33;
		
		return target;
	}
	
	@Override
	public boolean invert(){
		double det = determinant();
		if(det == 0) {
			return false;
		} else {
			double a00 = det3x3(d11, d12, d13, d21, d22, d23, d31, d32, d33);
			double a01 = -det3x3(d10, d12, d13, d20, d22, d23, d30, d32, d33);
			double a02 = det3x3(d10, d11, d13, d20, d21, d23, d30, d31, d33);
			double a03 = -det3x3(d10, d11, d12, d20, d21, d22, d30, d31, d32);
			
			double a10 = -det3x3(d01, d02, d03, d21, d22, d23, d31, d32, d33);
			double a11 = det3x3(d00, d02, d03, d20, d22, d23, d30, d32, d33);
			double a12 = -det3x3(d00, d01, d03, d20, d21, d23, d30, d31, d33);
			double a13 = det3x3(d00, d01, d02, d20, d21, d22, d30, d31, d32);
			
			double a20 = det3x3(d01, d02, d03, d11, d12, d13, d31, d32, d33);
			double a21 = -det3x3(d00, d02, d03, d10, d12, d13, d30, d32, d33);
			double a22 = det3x3(d00, d01, d03, d10, d11, d13, d30, d31, d33);
			double a23 = -det3x3(d00, d01, d02, d10, d11, d12, d30, d31, d32);
			
			double a30 = -det3x3(d01, d02, d03, d11, d12, d13, d21, d22, d23);
			double a31 = det3x3(d00, d02, d03, d10, d12, d13, d20, d22, d23);
			double a32 = -det3x3(d00, d01, d03, d10, d11, d13, d20, d21, d23);
			double a33 = det3x3(d00, d01, d02, d10, d11, d12, d20, d21, d22);
			
			d01 = a10 / det;
			d02 = a20 / det;
			d03 = a30 / det;
			
			d10 = a01 / det;
			d12 = a21 / det;
			d13 = a31 / det;
			
			d20 = a02 / det;
			d21 = a12 / det;
			d23 = a32 / det;
			
			d30 = a03 / det;
			d31 = a13 / det;
			d32 = a23 / det;
			
			d00 = a00 / det;
			d11 = a11 / det;
			d22 = a22 / det;
			d33 = a33 / det;
			
			return true;
		}
	}
	
	@Override
	public double[] mult(double[] v, double[] t){
		if(t == null || (t.length != 3 && t.length != 4)) {
			t = new double[3];
		}
		if(v == t) {
			throw new RuntimeException("the source and target vectors for multiply cannot be the same");
		}
		if(t.length == 3) {
			t[0] = v[0] * d00 + v[1] * d01 + v[2] * d02 + d03;
			t[1] = v[0] * d10 + v[1] * d11 + v[2] * d12 + d13;
			t[2] = v[0] * d20 + v[1] * d21 + v[2] * d22 + d23;
		} else if(t.length == 4) {
			t[0] = v[0] * d00 + v[1] * d01 + v[2] * d02 + v[3] * d03;
			t[1] = v[0] * d10 + v[1] * d11 + v[2] * d12 + v[3] * d13;
			t[2] = v[0] * d20 + v[1] * d21 + v[2] * d22 + v[3] * d23;
			t[3] = v[0] * d30 + v[1] * d31 + v[2] * d32 + v[3] * d33;
		}
		
		return t;
	}
	
	@Override
	public PVector mult(PVector s, PVector t){
		if(t == null) {
			t = new PVector();
		}
		t.x = d00 * s.x + d01 * s.y + d02 * s.z + d03;
		t.y = d10 * s.x + d11 * s.y + d12 * s.z + d13;
		t.z = d20 * s.x + d21 * s.y + d22 * s.z + d23;
		
		return t;
	}
	
	@Override
	public void preApply(double m00, double m01, double m02, double m10, double m11, double m12){
		preApply(m00, m01, 0, m02, m10, m11, 0, m12, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void preApply(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33){
		double a00 = m00 * d00 + m01 * d10 + m02 * d20 + m03 * d30;
		double a10 = m10 * d00 + m11 * d10 + m12 * d20 + m13 * d30;
		double a20 = m20 * d00 + m21 * d10 + m22 * d20 + m23 * d30;
		double a30 = m30 * d00 + m31 * d10 + m32 * d20 + m33 * d30;
		
		double a01 = m00 * d01 + m01 * d11 + m02 * d21 + m03 * d31;
		double a11 = m10 * d01 + m11 * d11 + m12 * d21 + m13 * d31;
		double a21 = m20 * d01 + m21 * d11 + m22 * d21 + m23 * d31;
		double a31 = m30 * d01 + m31 * d11 + m32 * d21 + m33 * d31;
		
		double a02 = m00 * d02 + m01 * d12 + m02 * d22 + m03 * d32;
		double a12 = m10 * d02 + m11 * d12 + m12 * d22 + m13 * d32;
		double a22 = m20 * d02 + m21 * d12 + m22 * d22 + m23 * d32;
		double a23 = m30 * d02 + m31 * d12 + m32 * d22 + m33 * d32;
		
		double a03 = m00 * d03 + m01 * d13 + m02 * d23 + m03 * d33;
		double a13 = m10 * d03 + m11 * d13 + m12 * d23 + m13 * d33;
		double a32 = m20 * d03 + m21 * d13 + m22 * d23 + m23 * d33;
		double a33 = m30 * d03 + m31 * d13 + m32 * d23 + m33 * d33;
		
		d00 = a00;
		d01 = a01;
		d02 = a02;
		d03 = a03;
		
		d10 = a10;
		d11 = a11;
		d12 = a12;
		d13 = a13;
		
		d20 = a20;
		d21 = a21;
		d22 = a22;
		d23 = a23;
		
		d30 = a30;
		d31 = a31;
		d32 = a32;
		d33 = a33;
	}
	
	@Override
	public void preApply(PMatrix m){
		if(m instanceof PMatrix2D) {
			preApply((PMatrix2D) m);
		} else {
			preApply((PMatrix3D) m);
		}
	}
	
	@Override
	public void preApply(PMatrix2D m){
		preApply(m.d00, m.d01, 0, m.d02, m.d10, m.d11, 0, m.d12, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void preApply(PMatrix3D m){
		preApply(m.d00, m.d01, m.d02, m.d03, m.d10, m.d11, m.d12, m.d13, m.d20, m.d21, m.d22, m.d23, m.d30, m.d31, m.d32, m.d33);
	}
	
	@Override
	public void reset(){
		set(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void rotate(double angle){
		rotateZ(angle);
	}
	
	@Override
	public void rotate(double angle, double v1, double v2, double v3){
		double mag = Math.sqrt(v1 * v1 + v2 * v2 + v3 * v3);
		if(mag == 0) {
			return;
		}
		
		if(Math.abs(mag) != 1) {
			v1 /= mag;
			v2 /= mag;
			v3 /= mag;
		}
		
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		double t = 1.0d - c;
		
		apply((t * v1 * v1) + c, (t * v1 * v2) - (s * v2), (t * v1 * v3) + (s * v2), 0, (t * v1 * v2) + (s * v3), (t * v2 * v2) + c, (t * v2 * v3) - (s * v1), 0, (t * v1 * v3) - (s * v2), (t * v2 * v3) + (s * v1), (t * v3 * v3) + c, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void rotateX(double angle){
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		apply(1, 0, 0, 0, 0, c, -s, 0, 0, s, c, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void rotateY(double angle){
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		apply(c, 0, s, 0, 0, 1, 0, 0, -s, 0, c, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void rotateZ(double angle){
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		apply(c, -s, 0, 0, s, c, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void scale(double s){
		scale(s, s, s);
	}
	
	@Override
	public void scale(double sx, double sy){
		scale(sx, sy, 1);
	}
	
	@Override
	public void scale(double x, double y, double z){
		d00 *= x;
		d01 *= y;
		d02 *= z;
		
		d10 *= x;
		d11 *= y;
		d12 *= z;
		
		d20 *= x;
		d21 *= y;
		d22 *= z;
		
		d30 *= x;
		d31 *= y;
		d32 *= z;
	}
	
	@Override
	public void set(double[] src){
		if(src.length == 6) {
			set(src[0], src[1], src[2], src[3], src[4], src[5]);
		} else if(src.length == 16) {
			set(src[0], src[1], src[2], src[3], src[4], src[5], src[6], src[7], src[8], src[9], src[10], src[11], src[12], src[13], src[14], src[15]);
		} else {
			throw new IllegalArgumentException("incompatible source array for PMatrix3D");
		}
	}
	
	@Override
	public void set(double n00, double n01, double n02, double n10, double n11, double n12){
		set(n00, n01, 0, n02, n10, n11, 0, n12, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void set(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33){
		this.d00 = d00;
		this.d01 = d01;
		this.d02 = d02;
		this.d03 = d03;
		
		this.d10 = d10;
		this.d11 = d11;
		this.d12 = d12;
		this.d13 = d13;
		
		this.d20 = d20;
		this.d21 = d21;
		this.d22 = d22;
		this.d23 = d23;
		
		this.d30 = d30;
		this.d31 = d31;
		this.d32 = d32;
		this.d33 = d33;
	}
	
	@Override
	public void set(PMatrix matrix){
		if(matrix instanceof PMatrix2D) {
			PMatrix2D src = (PMatrix2D) matrix;
			set(src.d00, src.d01, 0, src.d02, src.d10, src.d11, 0, src.d12, 0, 0, 1, 0, 0, 0, 0, 1);
		} else {
			PMatrix3D src = ((PMatrix3D) matrix);
			set(src.d00, src.d01, src.d02, src.d03, src.d10, src.d11, src.d12, src.d13, src.d20, src.d21, src.d22, src.d23, src.d30, src.d31, src.d32, src.d33);
		}
	}
	
	@Override
	public void shearX(double angle){
		double t = Math.tan(angle);
		apply(1, t, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void shearY(double angle){
		double t = Math.tan(angle);
		apply(1, 0, 0, 0, t, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	}
	
	@Override
	public void translate(double tx, double ty){
		translate(tx, ty, 0);
	}
	
	@Override
	public void translate(double tx, double ty, double tz){
		d03 += d00 * tx + d01 * ty + d02 * tz;
		d13 += d10 * tx + d11 * ty + d12 * tz;
		d23 += d20 * tx + d21 * ty + d22 * tz;
		d33 += d30 * tz + d31 * ty + d32 * tz;
	}
	
	@Override
	public void transpose(){
		double t1 = d01;
		double t2 = d10;
		d10 = t1;
		d01 = t2;
		
		t1 = d02;
		t2 = d20;
		d20 = t1;
		d02 = t2;
		
		t1 = d03;
		t2 = d30;
		d30 = t1;
		d03 = t2;
		
		t1 = d12;
		t2 = d21;
		d21 = t1;
		d12 = t2;
		
		t1 = d13;
		t2 = d31;
		d31 = t1;
		d13 = t2;
		
		t1 = d32;
		t2 = d23;
		d23 = t1;
		d32 = t2;
	}
	
	public void print(){
		System.out.println(d00 + " " + d01 + " " + d02 + " " + d03);
		System.out.println(d10 + " " + d11 + " " + d12 + " " + d13);
		System.out.println(d20 + " " + d21 + " " + d22 + " " + d23);
		System.out.println(d30 + " " + d31 + " " + d32 + " " + d33);
	}
	
	public double multX(double x, double y){
		return multX(x, y, 0, 1);
	}
	
	public double multY(double x, double y){
		return multY(x, y, 0, 1);
	}
	
	public double multX(double x, double y, double z){
		return multX(x, y, z, 1);
	}
	
	public double multY(double x, double y, double z){
		return multY(x, y, z, 1);
	}
	
	public double multZ(double x, double y, double z){
		return multZ(x, y, z, 1);
	}
	
	public double multW(double x, double y, double z){
		return multW(x, y, z, 1);
	}
	
	public double multX(double x, double y, double z, double w){
		return d00 * x + d01 * y + d02 * z + d03 * w;
	}
	
	public double multY(double x, double y, double z, double w){
		return d10 * x + d11 * y + d12 * z + d13 * w;
	}
	
	public double multZ(double x, double y, double z, double w){
		return d20 * x + d21 * y + d22 * z + d23 * w;
	}
	
	public double multW(double x, double y, double z, double w){
		return d30 * x + d31 * y + d32 * z + d33 * w;
	}
	
	private double det3x3(double a, double b, double c, double i, double j, double k, double x, double y, double z){
		return a * j * z - a * k * y + b * i * z - b * k * x + c * i * y - c * j * z;
	}
}
