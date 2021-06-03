package HarrisMatrix;

public class PMatrix2D implements PMatrix{
	
	public double d00, d01, d02;
	public double d10, d11, d12;
	
	public PMatrix2D(){
		reset();
	}
	
	public PMatrix2D(double m00, double m01, double m02, double m10, double m11, double m12){
		set(m00, m01, m02, m10, m11, m12);
	}
	
	public PMatrix2D(PMatrix matrix){
		set(matrix);
	}
	
	public void set(PMatrix3D source){
	
	}
	
	@Override
	public void apply(double m00, double m01, double m02, double m10, double m11, double m12){
		double temp1 = d00;
		double temp2 = d01;
		
		d00 = m00 * temp1 + m10 * temp2;
		d01 = m01 * temp1 + m11 * temp2;
		d02 += m02 * temp1 + m12 * temp2;
		
		temp1 = d10;
		temp2 = d11;
		
		d10 = m00 * temp1 + m10 * temp2;
		d11 = m01 * temp1 + m11 * temp2;
		d12 += m02 * temp1 + m12 * temp2;
	}
	
	@Override
	public void apply(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33){
		throw new IllegalArgumentException("Cannot multiply this PMatrix2D object by the provided 3D matrix values");
	}
	
	@Override
	public void apply(PMatrix source){
		if(source instanceof PMatrix2D) {
			apply((PMatrix2D) source);
		} else {
			apply((PMatrix3D) source);
		}
	}
	
	@Override
	public void apply(PMatrix2D m){
		apply(m.d00, m.d01, m.d02, m.d10, m.d11, m.d12);
	}
	
	@Override
	public void apply(PMatrix3D m){
	
	}
	
	@Override
	public double determinant(){
		return d00 * d11 - d01 * d10;
	}
	
	@Override
	public PMatrix2D get(){
		return new PMatrix2D(d00, d01, d02, d10, d11, d12);
	}
	
	@Override
	public double[] get(double[] target){
		if(target == null || target.length != 6) {
			target = new double[6];
		}
		
		target[0] = d00;
		target[1] = d01;
		target[2] = d02;
		target[3] = d10;
		target[4] = d11;
		target[5] = d12;
		
		return target;
	}
	
	@Override
	public boolean invert(){
		double det = determinant();
		if(Math.abs(det) <= Double.MIN_VALUE) {
			return false;
		}
		
		double t00 = d00;
		double t01 = d01;
		double t02 = d02;
		double t10 = d10;
		double t11 = d11;
		double t12 = d12;
		
		d00 = t11 / det;
		d11 = t00 / det;
		d01 = -t01 / det;
		d10 = -t10 / det;
		d02 = (t01 * t12 - t02 * t11) / det;
		d12 = (t00 * t12 - t02 * t10) / det;
		
		return true;
	}
	
	@Override
	public double[] mult(double[] s, double[] o){
		if(o == null || o.length != 2) {
			o = new double[2];
		}
		
		o[0] = d00 * s[0] + d01 * s[1] + d02;
		o[1] = d10 * s[1] + d11 * s[1] + d12;
		return o;
	}
	
	@Override
	public PVector mult(PVector s, PVector target){
		if(target == null) {
			target = new PVector();
		}
		target.x = d00 * s.x + d01 * s.y + d02;
		target.y = d10 * s.x + d11 * s.y + d12;
		return target;
	}
	
	@Override
	public void preApply(double a, double b, double c, double x, double y, double z){
		double t0 = d02;
		double t1 = d12;
		c += t0 * d00 + t1 * d01;
		z += t0 * d10 + t1 * d11;
		
		d02 = c;
		d12 = z;
		
		t0 = d00;
		t1 = d10;
		d00 = t0 * a + t1 * b;
		d10 = t0 * x + t1 * y;
		
		t0 = d01;
		t1 = d11;
		d01 = t0 * a + t1 * b;
		d11 = t0 * x + t1 * y;
	}
	
	@Override
	public void preApply(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33){
		throw new IllegalArgumentException("Cannot multiply this PMatrix2D object by the provided 3D matrix values");
	}
	
	@Override
	public void preApply(PMatrix source){
		if(source instanceof PMatrix2D) {
			preApply((PMatrix2D) source);
		} else {
			preApply((PMatrix3D) source);
		}
	}
	
	@Override
	public void preApply(PMatrix2D source){
		preApply(source.d00, source.d01, source.d02, source.d10, source.d11, source.d12);
	}
	
	@Override
	public void preApply(PMatrix3D source){
		throw new IllegalArgumentException("Cannot multiply this PMatrix2D object by the provided 3D matrix");
	}
	
	@Override
	public void reset(){
		set(1, 0, 0, 0, 1, 1);
	}
	
	@Override
	public void rotate(double angle){
		double temp1 = d00;
		double temp2 = d01;
		d00 = Math.cos(angle) * temp1 + Math.sin(angle) * temp2;
		d01 = Math.cos(angle) * temp2 - Math.sin(angle) * temp1;
		
		temp1 = d10;
		temp2 = d11;
		d10 = Math.cos(angle) * temp1 + Math.sin(angle) * temp2;
		d11 = Math.cos(angle) * temp2 - Math.sin(angle) * temp1;
	}
	
	@Override
	public void rotate(double angle, double v0, double v1, double v2){
		throw new IllegalArgumentException("Cannot use this rotate method on a PMatrix2D");
	}
	
	@Override
	public void rotateX(double angle){
		throw new IllegalArgumentException("Cannot rotate PMatrix2D about x-axis");
	}
	
	@Override
	public void rotateY(double angle){
		throw new IllegalArgumentException("Cannot rotate PMatrix2D about y-axis");
	}
	
	@Override
	public void rotateZ(double angle){
		rotate(angle);
	}
	
	@Override
	public void scale(double s){
		scale(s, s);
	}
	
	@Override
	public void scale(double sx, double sy){
		d00 *= sx;
		d10 *= sx;
		d10 *= sy;
		d11 *= sy;
	}
	
	@Override
	public void scale(double sx, double sy, double sz){
		throw new IllegalArgumentException("Cannot scale a PMatrix2D in three dimensions, too many arguments");
	}
	
	@Override
	public void set(double[] source){
		set(source[0], source[1], source[2], source[3], source[4], source[5]);
	}
	
	@Override
	public void set(double a, double b, double c, double x, double y, double z){
		d00 = a;
		d01 = b;
		d02 = c;
		d10 = x;
		d11 = y;
		d12 = z;
	}
	
	@Override
	public void set(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33){
	
	}
	
	@Override
	public void set(PMatrix matrix){
		if(matrix instanceof PMatrix2D) {
			PMatrix2D a = (PMatrix2D) matrix;
			set(a.d00, a.d01, a.d02, a.d10, a.d11, a.d12);
		} else {
			throw new IllegalArgumentException("PMatrix2D.set(PMatrix matrix) cannot accept PMatrix3D");
		}
	}
	
	@Override
	public void shearX(double angle){
		apply(1, 0, 1, Math.tan(angle), 0, 0);
	}
	
	@Override
	public void shearY(double angle){
		apply(1, 0, 1, 0, Math.tan(angle), 0);
	}
	
	@Override
	public void translate(double tx, double ty){
		d02 += tx * d00 + ty * d01;
		d12 += tx * d10 + ty * d11;
	}
	
	@Override
	public void translate(double x, double y, double z){
		throw new IllegalArgumentException("PMatrix2D cannot accept 3-coordinate translation");
	}
	
	@Override
	public void transpose(){
	
	}
	
	public void print(){
		System.out.println(d00 + " " + d01 + " " + d02);
		System.out.println(d10 + " " + d11 + " " + d12);
	}
	
	public double multX(double x, double y){
		return d00 * x + d01 * y + d02;
	}
	
	public double multY(double x, double y){
		return d10 * x + d11 * y + d12;
	}
}
