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

		public RedBlackNode<Integer> createNodeWithNull(int numLeft, int numRight, int color){
				RedBlackNode<Integer> node = new RedBlackNode<>(null);
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

		@Test
		public void testInsert(){
				redBlackTree.insert(10);
				redBlackTree.insert(15);
				redBlackTree.insert(13);
				redBlackTree.insert(14);

				RedBlackNode<Integer> root = redBlackTree.root;
				Assert.assertEquals(1, root.numLeft);
				Assert.assertEquals(2, root.numRight);
				Assert.assertEquals(13, root.key.intValue());
				Assert.assertEquals(0, root.color);

				RedBlackNode<Integer> node1 = root.left;
				Assert.assertEquals(0, node1.numLeft);
				Assert.assertEquals(0, node1.numRight);
				Assert.assertEquals(10, node1.key.intValue());
				Assert.assertEquals(0, node1.color);

				RedBlackNode<Integer> node2 = root.right;
				Assert.assertEquals(1, node2.numLeft);
				Assert.assertEquals(0, node2.numRight);
				Assert.assertEquals(15, node2.key.intValue());
				Assert.assertEquals(0, node2.color);

				RedBlackNode<Integer> nodeLeft2 = node2.left;
				Assert.assertEquals(0, nodeLeft2.numLeft);
				Assert.assertEquals(0, nodeLeft2.numRight);
				Assert.assertEquals(14, nodeLeft2.key.intValue());
				Assert.assertEquals(1, nodeLeft2.color);

		}

		@Test
		public void testTreeMinimum(){
				redBlackTree.insert(10);
				redBlackTree.insert(15);
				redBlackTree.insert(13);
				redBlackTree.insert(14);
				RedBlackNode<Integer> node = redBlackTree.root.right;
				RedBlackNode<Integer> nodeMin = node.left;
				Assert.assertEquals(nodeMin, redBlackTree.treeMinimum(node));
		}

		@Test
		public void testTreeSuccessor(){
				redBlackTree.insert(10);
				redBlackTree.insert(15);
				redBlackTree.insert(13);
				redBlackTree.insert(14);

				RedBlackNode<Integer> root = redBlackTree.root;
				RedBlackNode<Integer> node1 = redBlackTree.treeSuccessor(root);
				Assert.assertEquals(14, node1.key.intValue());

				RedBlackNode<Integer> node2 = redBlackTree.treeSuccessor(node1);
				Assert.assertEquals(15, node2.key.intValue());
		}

		@Test
		public void testFixNodeData(){
				RedBlackNode<Integer> node1 = createNode(13, 1, 2, 0);
				RedBlackNode<Integer> node2 = createNode(15, 1, 0, 0);
				RedBlackNode<Integer> node3 = createNode(14, 0, 0, 1);
				RedBlackNode<Integer> x = createNodeWithNull(0,0,0);
				RedBlackNode<Integer> y = createNode(10, 0,0,0);
				y.parent = node1;
				y.right = redBlackTree.nil;
				y.left = redBlackTree.nil;

				x.parent = node1;
				x.left = redBlackTree.nil;
				x.right = redBlackTree.nil;

				node1.parent = redBlackTree.nil;
				node1.left = redBlackTree.nil;
				node1.right = node2;

				node2.parent = node1;
				node2.left = node3;
				node2.right = redBlackTree.nil;

				node3.parent = node2;
				node3.left = redBlackTree.nil;
				node3.right = redBlackTree.nil;
				redBlackTree.root = node1;
				redBlackTree.fixNodeData(x,y);

				RedBlackNode<Integer> root  = redBlackTree.root;
				Assert.assertEquals(0, root.numLeft);
				Assert.assertEquals(2, root.numRight);
		}

		@Test
		public void testRemoveFixup(){
				RedBlackNode<Integer> node1 = createNode(13, 1, 2, 0);
				RedBlackNode<Integer> node2 = createNode(15, 1, 0, 0);
				RedBlackNode<Integer> node3 = createNode(14, 0, 0, 1);
				RedBlackNode<Integer> x = createNodeWithNull(0,0,0);
				RedBlackNode<Integer> y = createNode(10, 0,0,0);
				y.parent = node1;
				y.right = redBlackTree.nil;
				y.left = redBlackTree.nil;

				x.parent = node1;
				x.left = redBlackTree.nil;
				x.right = redBlackTree.nil;

				node1.parent = redBlackTree.nil;
				node1.left = redBlackTree.nil;
				node1.right = node2;

				node2.parent = node1;
				node2.left = node3;
				node2.right = redBlackTree.nil;

				node3.parent = node2;
				node3.left = redBlackTree.nil;
				node3.right = redBlackTree.nil;
				redBlackTree.root = node1;
				redBlackTree.fixNodeData(x,y);

				redBlackTree.removeFixup(x);
				RedBlackNode<Integer> root  = redBlackTree.root;

				Assert.assertEquals(13, root.key.intValue());
				Assert.assertEquals(0, root.color);
				Assert.assertEquals(15, root.right.key.intValue());
				Assert.assertEquals(0, root.right.color);
				Assert.assertEquals(14, root.right.left.key.intValue());
				Assert.assertEquals(1, root.right.left.color);
		}
}
