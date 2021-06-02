package HarrisMatrix;

public interface HMatrix{
	
	float getValue(int c, int r);
	void setValue(int c, int r, float f);
	void setMatrix(float[][] m);
	float[][] getMatrix();
	void fillCol(int c, float f);
	void fillRow(int r, float f);
	void reset();
	void increment();
	void increment(float f);
	void add(HMatrix m);
	void add(SMatrix m);
	void add(NMatrix m);
	void mult(float f);
	void mult(HMatrix m);
	void mult(SMatrix m);
	void mult(NMatrix m);
	NMatrix mult(SMatrix m, boolean c);
	SMatrix mult(NMatrix m, boolean c);
	void sub(HMatrix m);
	void sub(SMatrix m);
	void sub(NMatrix m);
	void div(float f);
	HMatrix minor(int c, int r);
	HMatrix transpose();
	void print();
	
}
