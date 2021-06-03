package HarrisMatrix;

public class NMatrix implements HMatrix{
	
	private int cols;
	private int rows;
	private float[][] matrix;
	
	public NMatrix(){
		new NMatrix(0, 0, 0F);
	}
	
	public NMatrix(int c, int r){
		new NMatrix(c, r, 0.0F);
	}
	
	public NMatrix(float f){
		new NMatrix(0, 0, f);
	}
	
	public NMatrix(int c, int r, float f){
		cols = c;
		rows = r;
		matrix = new float[c][r];
		fill(f);
	}
	
	public void fill(float f){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				matrix[i][j] = f;
			}
		}
	}
	
	public NMatrix copy(){
		NMatrix c = new NMatrix(cols, rows);
		c.setMatrix(matrix);
		return c;
	}
	
	public int getCols(){
		return cols;
	}
	
	public void setCols(int c){
		cols = c;
	}
	
	public int getRows(){
		return rows;
	}
	
	public void setRows(int r){
		rows = r;
	}
	
	public void rebuild(){
		rebuild(2, 2, 0F);
	}
	
	public void rebuild(int c, int r){
		rebuild(c, r, 0F);
	}
	
	public void rebuild(float f){
		rebuild(2, 2, f);
	}
	
	public void rebuild(int c, int r, float f){
		setRows(r);
		setCols(c);
		matrix = new float[cols][rows];
		fill(f);
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
	public float[][] getMatrix(){
		return matrix;
	}
	
	@Override
	public void setMatrix(float[][] m){
		matrix = m;
	}
	
	@Override
	public void fillCol(int c, float f){
		for(int i = 0; i < rows; i++) {
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
		reset(0F);
	}
	
	public void reset(float f){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				matrix[i][j] = f;
			}
		}
	}
	
	@Override
	public void increment(){
		increment(1.0F);
	}
	
	@Override
	public void increment(float f){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				matrix[i][j] = f;
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
		if(cols != m.getCols() || rows != m.getRows()) {
			System.out.println("Matrix Dimensions do not match, adding available elements of the argument to the current matrix");
		}
		int x = Math.min(cols, m.getCols());
		int y = Math.min(rows, m.getRows());
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				matrix[i][j] = m.getValue(i, j);
			}
		}
	}
	
	@Override
	public void add(NMatrix m){
		if(cols != m.getCols() || rows != m.getRows()) {
			System.out.println("Matrix Dimensions do not match, adding available elements of the argument to the current matrix");
		}
		int x = Math.min(cols, m.getCols());
		int y = Math.min(rows, m.getRows());
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				matrix[i][j] = m.getValue(i, j);
			}
		}
	}
	
	@Override
	public void mult(float f){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				matrix[i][j] *= f;
			}
		}
	}
	
	@Override
	public void mult(HMatrix m){
		if(m instanceof NMatrix) {
			NMatrix temp = (NMatrix) m;
			if(cols == temp.getRows()) {
				if(rows == temp.getCols()) {
					mult(temp, true); // this would create a square matrix
				} else {
					mult(temp); // this would not be a square matrix
				}
			} else {
				System.out.println("Incompatible Matrix Dimensions in mult(HMatrix)");
			}
		} else {
			SMatrix temp = (SMatrix) m;
			if(cols == temp.getRows()) {
				mult(temp); // cannot have a square matrix resulting from N*S matrices
			} else {
				System.out.println("Incompatible Matrix Dimensions in mult(HMatrix)");
			}
		}
	}
	
	@Override
	public void mult(SMatrix m){
		float f;
		HMatrix temp = this.copy();
		for(int i = 0; i < m.getCols(); i++) {
			for(int j = 0; j < rows; j++) {
				f = 0;
				for(int k = 0; k < m.getRows(); k++) {
					f += matrix[k][j] * m.getValue(i, k);
				}
				temp.setValue(i, j, f);
			}
		}
		setMatrix(temp.getMatrix());
	}
	
	@Override
	public void mult(NMatrix m){
		NMatrix n = new NMatrix(m.getCols(), rows);
		float temp;
		for(int i = 0; i < m.getCols(); i++) {
			for(int j = 0; j < rows; j++) {
				temp = 0;
				for(int k = 0; k < m.getRows(); k++) {
					temp += getValue(k, j) * m.getValue(i, k);
				}
				n.setValue(i, j, temp);
			}
		}
		rebuild(m.getCols(), rows);
		setMatrix(n.getMatrix());
	}
	
	@Override
	public NMatrix mult(SMatrix m, boolean c){
		// cannot multiply an NMatrix by an SMatrix and get
		return null;
	}
	
	@Override
	public SMatrix mult(NMatrix m, boolean c){
		SMatrix n = new SMatrix();
		n.rebuild(rows);
		float temp;
		for(int i = 0; i < m.getCols(); i++) {
			for(int j = 0; j < rows; j++) {
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
		HMatrix m = new NMatrix(cols - 1, rows - 1);
		int xCount = 0;
		int yCount = 0;
		for(int i = 0; i < cols; i++) {
			if(i != c) {
				for(int j = 0; j < rows; j++) {
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
		HMatrix t = new NMatrix(rows, cols);
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				t.setValue(j, i, matrix[i][j]);
			}
		}
		return t;
	}
	
	@Override
	public void print(){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
}