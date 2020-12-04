package utils;


import org.junit.Assert;
import org.junit.Test;

public class TestRedBlackTree {

		@Test
		public void testConstructorNodeWithoutKey(){
				RedBlackNode<Integer> node = new RedBlackNode<>();
				Assert.assertEquals(0, node.color);
				Assert.assertEquals(0,node.numLeft);
				Assert.assertEquals(0,node.numRight);
				Assert.assertNull(node.parent);
				Assert.assertNull(node.left);
				Assert.assertNull(node.right);
		}
}
