package utils;


import org.junit.Assert;
import org.junit.Test;

public class TestRedBlackTree {

		RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();

		public RedBlackNode<Integer> createNode(int key, int numLeft, int numRight, int color){
				RedBlackNode<Integer> node = new RedBlackNode<>(key);
				node.setNumLeft(numLeft);
				node.setNumRight(numRight);
				node.setColor(color);
				return node;
		}

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

		@Test
		public void testConstructorTree(){
				RedBlackTree<Integer> tree = new RedBlackTree<>();
				Assert.assertEquals(tree.nil, tree.root);
				Assert.assertEquals(tree.nil, tree.root.left);
				Assert.assertEquals(tree.nil, tree.root.right);
				Assert.assertEquals(tree.nil, tree.root.parent);
		}

		@Test
		public void testIsNil(){
				RedBlackNode<Integer> node = redBlackTree.root;
				RedBlackNode<Integer> nodeWithKey = new RedBlackNode<>(1);
				Assert.assertTrue(redBlackTree.isNil(node));
				Assert.assertFalse(redBlackTree.isNil(nodeWithKey));
		}

		@Test
		public void testLeftRotateFixup(){
				RedBlackNode<Integer> root = createNode(10, 0, 2, 1);
				RedBlackNode<Integer> node1 = createNode(13, 0, 1, 0);
				node1.parent = root;
				node1.left = redBlackTree.nil;
				root.right = node1;
				root.parent = redBlackTree.nil;
				root.left = redBlackTree.nil;

				RedBlackNode<Integer> node2 = createNode(15, 0, 0, 1);
				node2.parent = node1;
				node2.left = redBlackTree.nil;
				node2.right = redBlackTree.nil;
				node1.right = node2;

				redBlackTree.root = root;
				redBlackTree.leftRotateFixup(root);

				Assert.assertEquals(0, root.numLeft);
				Assert.assertEquals(0, root.numRight);
				Assert.assertEquals(1, node1.numLeft);
				Assert.assertEquals(1, node1.numRight);
				Assert.assertEquals(0, node2.numLeft);
				Assert.assertEquals(0, node2.numRight);
		}

		@Test
		public void testRightRotateFixup(){
				RedBlackNode<Integer> root = createNode(10, 2, 0, 1);
				RedBlackNode<Integer> node1 = createNode(8, 1, 0, 0);
				node1.parent = root;
				node1.right = redBlackTree.nil;
				root.left = node1;
				root.parent = redBlackTree.nil;
				root.right = redBlackTree.nil;

				RedBlackNode<Integer> node2 = createNode(7, 0, 0, 1);
				node2.parent = node1;
				node2.left = redBlackTree.nil;
				node2.right = redBlackTree.nil;
				node1.left = node2;

				redBlackTree.root = root;
				redBlackTree.rightRotateFixup(root);

				Assert.assertEquals(0, root.numLeft);
				Assert.assertEquals(0, root.numRight);
				Assert.assertEquals(1, node1.numLeft);
				Assert.assertEquals(1, node1.numRight);
				Assert.assertEquals(0, node2.numLeft);
				Assert.assertEquals(0, node2.numRight);
		}

		@Test
		public void testLeftRotate(){
				RedBlackNode<Integer> root = createNode(10, 0, 2, 1);
				RedBlackNode<Integer> node1 = createNode(13, 0, 1, 0);
				node1.parent = root;
				node1.left = redBlackTree.nil;
				root.right = node1;
				root.parent = redBlackTree.nil;
				root.left = redBlackTree.nil;

				RedBlackNode<Integer> node2 = createNode(15, 0, 0, 1);
				node2.parent = node1;
				node2.left = redBlackTree.nil;
				node2.right = redBlackTree.nil;
				node1.right = node2;

				redBlackTree.root = root;
				redBlackTree.leftRotate(root);

				Assert.assertEquals(redBlackTree.nil, node1.parent);
				Assert.assertEquals(root, node1.left);
				Assert.assertEquals(node2, node1.right);

				Assert.assertEquals(node1, root.parent);
				Assert.assertEquals(redBlackTree.nil, root.left);
				Assert.assertEquals(redBlackTree.nil, root.right);

				Assert.assertEquals(node1, node2.parent);
				Assert.assertEquals(redBlackTree.nil, node2.left);
				Assert.assertEquals(redBlackTree.nil, node2.right);

		}

		@Test
		public void testRightRotate(){
				RedBlackNode<Integer> root = createNode(10, 2, 0, 1);
				RedBlackNode<Integer> node1 = createNode(8, 1, 0, 0);
				node1.parent = root;
				node1.right = redBlackTree.nil;
				root.left = node1;
				root.parent = redBlackTree.nil;
				root.right = redBlackTree.nil;

				RedBlackNode<Integer> node2 = createNode(7, 0, 0, 1);
				node2.parent = node1;
				node2.left = redBlackTree.nil;
				node2.right = redBlackTree.nil;
				node1.left = node2;

				redBlackTree.root = root;
				redBlackTree.rightRotate(root);

				Assert.assertEquals(redBlackTree.nil, node1.parent);
				Assert.assertEquals(node2, node1.left);
				Assert.assertEquals(root, node1.right);

				Assert.assertEquals(node1, root.parent);
				Assert.assertEquals(redBlackTree.nil, root.left);
				Assert.assertEquals(redBlackTree.nil, root.right);

				Assert.assertEquals(node1, node2.parent);
				Assert.assertEquals(redBlackTree.nil, node2.left);
				Assert.assertEquals(redBlackTree.nil, node2.right);
		}

		@Test
		public void testInsertFixup(){
				RedBlackNode<Integer> root = createNode(10, 0, 2, 0);
				RedBlackNode<Integer> node1 = createNode(13, 0, 1, 1);
				RedBlackNode<Integer> node2 = createNode(15, 1, 0, 1);
				root.right = node2;
				root.parent = redBlackTree.nil;
				root.left = redBlackTree.nil;

				node2.parent = root;
				node2.left = node1;
				node2.right = redBlackTree.nil;

				node1.parent = node2;
				node1.left = redBlackTree.nil;
				node1.right = redBlackTree.nil;

				redBlackTree.root = root;
				redBlackTree.insertFixup(node1);

				Assert.assertEquals(redBlackTree.nil, node1.parent);
				Assert.assertEquals(root, node1.left);
				Assert.assertEquals(node2, node1.right);

				Assert.assertEquals(node1, root.parent);
				Assert.assertEquals(redBlackTree.nil, root.left);
				Assert.assertEquals(redBlackTree.nil, root.right);

				Assert.assertEquals(node1, node2.parent);
				Assert.assertEquals(redBlackTree.nil, node2.left);
				Assert.assertEquals(redBlackTree.nil, node2.right);

				Assert.assertEquals(0, root.numLeft);
				Assert.assertEquals(0, root.numRight);
				Assert.assertEquals(1, node1.numLeft);
				Assert.assertEquals(1, node1.numRight);
				Assert.assertEquals(0, node2.numLeft);
				Assert.assertEquals(0, node2.numRight);
		}
}
