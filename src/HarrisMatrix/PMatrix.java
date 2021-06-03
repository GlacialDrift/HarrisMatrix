package HarrisMatrix;

public interface PMatrix{
	
	void apply(double d00, double d01, double d02, double d10, double d11, double d12);
	void apply(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33);
	void apply(PMatrix source);
	void apply(PMatrix2D source);
	void apply(PMatrix3D source);
	double determinant();
	PMatrix get();
	double[] get(double[] target);
	boolean invert();
	double[] mult(double[] source, double[] target);
	PVector mult(PVector source, PVector target);
	void preApply(double d00, double d01, double d02, double d10, double d11, double d12);
	void preApply(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33);
	void preApply(PMatrix left);
	void preApply(PMatrix2D left);
	void preApply(PMatrix3D left);
	void reset();
	void rotate(double angle);
	void rotate(double angle, double v0, double v1, double v2);
	void rotateX(double angle);
	void rotateY(double angle);
	void rotateZ(double angle);
	void scale(double s);
	void scale(double sx, double sy);
	void scale(double x, double y, double z);
	void set(double[] source);
	void set(double d00, double d01, double d02, double d10, double d11, double d12);
	void set(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33);
	void set(PMatrix source);
	void shearX(double angle);
	void shearY(double angle);
	void translate(double tx, double ty);
	void translate(double tx, double ty, double tz);
	void transpose();
	void print();
}
