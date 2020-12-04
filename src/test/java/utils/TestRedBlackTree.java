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

		@Test
		public void testConstructorNodeWithKey(){
				RedBlackNode<Integer> node = new RedBlackNode<>(3);
				Assert.assertEquals(0, node.color);
				Assert.assertEquals(0,node.numLeft);
				Assert.assertEquals(0,node.numRight);
				Assert.assertEquals(3, node.key.intValue());
				Assert.assertNull(node.parent);
				Assert.assertNull(node.left);
				Assert.assertNull(node.right);
		}
}
