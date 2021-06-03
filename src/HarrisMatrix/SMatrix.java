package HarrisMatrix;

import java.util.concurrent.ThreadLocalRandom;

public class SMatrix implements HMatrix{
	
	private int cols;
	private float[][] matrix;
	
	public SMatrix(){
		new SMatrix(3, 0F);
	}
	
	public SMatrix(int c){
		new SMatrix(c, 0F);
	}
	
	public SMatrix(float f){
		new SMatrix(3, f);
	}
	
	public SMatrix(int c, float f){
		cols = c;
		matrix = new float[c][c];
		fill(f);
	}
	
	public void fill(float f){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < cols; j++) {
				matrix[i][j] = f;
			}
		}
	}
	
	public int getCols(){
		return cols;
	}
	
	public int getRows(){
		return cols;
	}
	
	public void setDim(int c){
		cols = c;
	}
	
	public void rebuild(){
		rebuild(2, 0F);
	}
	
	public void rebuild(int c){
		rebuild(c, 0F);
	}
	
	public void rebuild(float f){
		rebuild(2, f);
	}
	
	public void rebuild(int c, float f){
		setDim(c);
		matrix = new float[c][c];
		fill(f);
	}
	
	public void identity(){
		diagonalize(1F);
	}
	
	public void diagonalize(float f){
		fill(0F);
		for(int i = 0; i < cols; i++) {
			matrix[i][i] = f;
		}
	}
	
	@Override
	public float getValue(int c, int r){
		return matrix[c][r];
	}
	
	@Override
	public void setValue(int c, int r, float f){
		matrix[c][r] = f;
	}
	
	@Override
	public void setMatrix(float[][] m){
		matrix = m;
	}
	
	@Override
	public float[][] getMatrix(){
		return matrix;
	}
	
	@Override
	public void fillCol(int c, float f){
		for(int i = 0; i < cols; i++) {
			matrix[c][i] = f;
		}
	}
	
	@Override
	public void fillRow(int r, float f){
		for(int i = 0; i < cols; i++) {
			matrix[i][r] = f;
		}
	}
	
	@Override
	public void reset(){
		identity();
	}
	
	@Override
	public void increment(){
		increment(1F);
	}
	
	@Override
	public void increment(float f){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < cols; j++) {
				matrix[i][j] += f;
			}
		}
	}
	
	@Override
	public void add(HMatrix m){
		if(m instanceof SMatrix) {
			add((SMatrix) m);
		}
		if(m instanceof NMatrix) {
			add((NMatrix) m);
		}
	}
	
	@Override
	public void add(SMatrix m){
		if(cols != m.getCols()) {
			System.out.println("Matrix Dimensions do not match, adding available elements of the argument to the current matrix");
		}
		int x = Math.min(cols, m.getCols());
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				matrix[i][j] += m.getValue(i, j);
			}
		}
	}
	
	@Override
	public void add(NMatrix m){
		if(cols != m.getCols() || cols != m.getRows()) {
			System.out.println("Matrix Dimensions do not match, adding available elements of the argument to the current matrix");
		}
		int x = Math.min(cols, m.getCols());
		int y = Math.min(cols, m.getRows());
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				matrix[i][j] += m.getValue(i, j);
			}
		}
	}
	
	@Override
	public void mult(float f){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < cols; j++) {
				matrix[i][j] *= f;
			}
		}
	}
	
	@Override
	public void mult(HMatrix m){
		if(m instanceof SMatrix) {
			SMatrix t = (SMatrix) m;
			if(cols == t.getCols()) {
				mult(t);
			} else {
				System.out.println("Incompatible matrix dimensions for multiplication");
			}
		} else {
			NMatrix t = (NMatrix) m;
			if(cols == t.getRows()) {
				mult(t, 1);
			} else {
				System.out.println("Incompatible matrix dimensions for multiplication");
			}
		}
	}
	
	@Override
	public void mult(SMatrix m){
		SMatrix n = new SMatrix(cols);
		float temp;
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < cols; j++) {
				temp = 0;
				for(int k = 0; k < m.getRows(); k++) {
					temp += getValue(k, j) * m.getValue(i, k);
				}
				n.setValue(i, j, temp);
			}
		}
		setMatrix(n.getMatrix());
	}
	
	@Override
	public void mult(NMatrix m){
		// unused in this class
	}
	
	@Override
	public NMatrix mult(SMatrix m, boolean c){
		// unused in this class
		return null;
	}
	
	@Override
	public SMatrix mult(NMatrix m, boolean c){
		// unused in this class
		return null;
	}
	
	public NMatrix mult(NMatrix m, int c){
		NMatrix n = new NMatrix(m.getCols(), cols);
		float temp;
		for(int i = 0; i < m.getCols(); i++) {
			for(int j = 0; j < cols; j++) {
				temp = 0;
				for(int k = 0; k < m.getRows(); k++) {
					temp += getValue(k, j) * m.getValue(i, k);
				}
				n.setValue(i, j, temp);
			}
		}
		return n;
	}
	
	@Override
	public void sub(HMatrix m){
		m.mult(-1F);
		add(m);
	}
	
	@Override
	public void sub(SMatrix m){
		m.mult(-1F);
		add(m);
	}
	
	@Override
	public void sub(NMatrix m){
		m.mult(-1F);
		add(m);
	}
	
	@Override
	public void div(float f){
		mult(1 / f);
	}
	
	@Override
	public HMatrix minor(int c, int r){
		HMatrix m = new NMatrix(cols - 1);
		int xCount = 0;
		int yCount = 0;
		for(int i = 0; i < cols; i++) {
			if(i != c) {
				for(int j = 0; j < cols; j++) {
					if(j != r) {
						m.setValue(xCount, yCount, matrix[i][j]);
						yCount++;
					}
				}
				xCount++;
			}
		}
		return m;
	}
	
	@Override
	public HMatrix transpose(){
		HMatrix n = new SMatrix(cols);
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < cols; j++) {
				n.setValue(i, j, matrix[i][j]);
			}
		}
		return n;
	}
	
	@Override
	public void print(){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	//build a permutation matrix of size n. Pass in an array of ints that describes the permutation matrix
	// for example [0,3,1,4,2] represents:
	// 1  0  0  0  0
	// 0  0  0  1  0
	// 0  0  1  0  0
	// 0  0  0  0  1
	// 0  1  0  0  0
	public static SMatrix permutation(int n, int[] swaps){
		SMatrix perm = new SMatrix(n, 0F);
		for(int i = 0; i < swaps.length; i++) {
			perm.setValue(swaps[i], i, 1F);
		}
		return perm;
	}
	
	public static SMatrix rotation2D(){
		double angle = ThreadLocalRandom.current().nextDouble(0, 2 * Math.PI);
		SMatrix m = new SMatrix(2);
		float sin = (float) Math.sin(angle);
		float cos = (float) Math.cos(angle);
		m.setValue(0, 0, cos);
		m.setValue(0, 1, sin);
		m.setValue(1, 0, -1 * sin);
		m.setValue(1, 1, cos);
		return m;
	}
	
	public static SMatrix rotation2D(double angle){
		SMatrix m = new SMatrix(2);
		float sin = (float) Math.sin(angle);
		float cos = (float) Math.cos(angle);
		m.setValue(0, 0, cos);
		m.setValue(0, 1, sin);
		m.setValue(1, 0, -1 * sin);
		m.setValue(1, 1, cos);
		return m;
	}
	
	public static SMatrix rotation3D(){
		SMatrix m = new SMatrix(3);
		double a = ThreadLocalRandom.current().nextDouble(0, 2 * Math.PI);
		double b = ThreadLocalRandom.current().nextDouble(0, 2 * Math.PI);
		double c = ThreadLocalRandom.current().nextDouble(0, 2 * Math.PI);
		
		float sina = (float) Math.sin(a);
		float sinb = (float) Math.sin(b);
		float sinc = (float) Math.sin(c);
		float cosa = (float) Math.cos(a);
		float cosb = (float) Math.cos(b);
		float cosc = (float) Math.cos(c);
		
		m.setValue(0, 0, cosa * cosb);
		m.setValue(0, 1, sina * cosb);
		m.setValue(0, 2, -1 * sinb);
		m.setValue(1, 0, cosa * sinb * sinc - sina * cosc);
		m.setValue(1, 1, sina * sinb * sinc + cosa * cosc);
		m.setValue(1, 2, cosb * sinc);
		m.setValue(2, 0, cosa * sinb * cosc + sina * sinc);
		m.setValue(2, 1, sina * sinb * cosc - cosa * sinc);
		m.setValue(2, 2, cosb * cosc);
		
		return m;
	}
	
	public static SMatrix rotation3D(double a, double b, double c){
		SMatrix m = new SMatrix(3);
		
		float sina = (float) Math.sin(a);
		float sinb = (float) Math.sin(b);
		float sinc = (float) Math.sin(c);
		float cosa = (float) Math.cos(a);
		float cosb = (float) Math.cos(b);
		float cosc = (float) Math.cos(c);
		
		m.setValue(0, 0, cosa * cosb);
		m.setValue(0, 1, sina * cosb);
		m.setValue(0, 2, -1 * sinb);
		m.setValue(1, 0, cosa * sinb * sinc - sina * cosc);
		m.setValue(1, 1, sina * sinb * sinc + cosa * cosc);
		m.setValue(1, 2, cosb * sinc);
		m.setValue(2, 0, cosa * sinb * cosc + sina * sinc);
		m.setValue(2, 1, sina * sinb * cosc - cosa * sinc);
		m.setValue(2, 2, cosb * cosc);
		
		return m;
	}
	
	public float trace(){
		float f = 0;
		for(int i = 0; i < cols; i++) {
			f += matrix[i][i];
		}
		return f;
	}
	
	public float det(){
		float det = 0;
		HMatrix temp;
		SMatrix t;
		if(cols == 1) {
			return matrix[0][0];
		} else if(cols == 2) {
			return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
		} else {
			for(int i = 0; i < cols; i++) {
				temp = minor(i, 0);
				t = (SMatrix) temp;
				det += matrix[i][0] * t.det() * Math.pow(-1, i);
			}
			return det;
		}
	}
	
	public SMatrix inverse(){
		float det = det();
		SMatrix s = new SMatrix(cols);
		HMatrix temp;
		SMatrix t;
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < cols; j++) {
				temp = minor(i, j);
				t = (SMatrix) temp;
				s.setValue(i, j, t.det() * (float) Math.pow(-1.0, i + j));
			}
		}
		s.transpose();
		if(det == 0) {
			return null;
		} else {
			s.mult(1 / det);
			return s;
		}
	}
}

