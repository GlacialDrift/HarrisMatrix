package HarrisMatrix;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SMatrixTest{
	
	private SMatrix s;
	
	@BeforeEach
	void setUp(){
		s = new SMatrix(2, 1);
	}
	
	@AfterEach
	void tearDown(){
		s = null;
	}
	
	@Test
	@DisplayName("get the value of the 0,0 position of the matrix")
	void getValue(){
		assertEquals(1.0f, s.getValue(0, 0));
	}
	
	@Test
	@DisplayName("test that a matrix can be filled")
	void fill(){
		SMatrix t = new SMatrix(2, 2);
		s.fill(2);
		assertEquals(t, s);
	}
}