package bash_cartesian_product;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CartesianProductTest {

	private static final String TEST_STRING1 = "a{b,c}d{e,f,ghi";
	private static final String TEST_STRING2 = "a{b,c{d,e,f}g,h}ij{k,l}";
	
	@Test(expected = InvalidInputString.class)
	public void assertInvalidString1() throws Throwable{
		CartesianProduct  cartesianProduct= new CartesianProduct("a{b,c}d{e,f,ghi");
	}
	
	@Test(expected = InvalidInputString.class)
	public void assertInvalidString2() throws Throwable{
		CartesianProduct  cartesianProduct= new CartesianProduct("a{b,cd{e,f,ghi");
	}
	
	@Test(expected = InvalidInputString.class)
	public void assertInvalidString3() throws Throwable{
		CartesianProduct  cartesianProduct= new CartesianProduct("{a{b,cd{e,f,gh////i}}}}");
	}
	
	public void assertValidString1() throws Throwable{
		CartesianProduct  cartesianProduct= new CartesianProduct(TEST_STRING1);
	}
	public void assertValidString2() throws Throwable{
		CartesianProduct  cartesianProduct= new CartesianProduct(TEST_STRING2);
		assertEquals("abijk abijl acdgijk acdgijl acegijk acegijl acfgijk acfgijl ahijk ahijl", cartesianProduct.getCartesianProduct());
	}

}
