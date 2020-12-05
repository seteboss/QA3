package utils;

import lombok.Data;


@Data
public class RedBlackTree<T extends Comparable<T>> {

		public RedBlackNode<T> nil = new RedBlackNode<T>();

		public RedBlackNode<T> root = nil;

		public RedBlackTree() {
				root.left = nil;
				root.right = nil;
				root.parent = nil;
		}


		boolean isNil(RedBlackNode node) {
				return node == nil;
		}

		public void leftRotateFixup(RedBlackNode x) {
				if (isNil(x.left) && isNil(x.right.left)) {
						x.numLeft = 0;
						x.numRight = 0;
						x.right.numLeft = 1;
				} else {
						if (isNil(x.left) && !isNil(x.right.left)) {
								x.numLeft = 0;
								x.numRight = 1 + x.right.left.numLeft +
										x.right.left.numRight;
								x.right.numLeft = 2 + x.right.left.numLeft +
										x.right.left.numRight;
						} else {
								if (!isNil(x.left) && isNil(x.right.left)) {
										x.numRight = 0;
										x.right.numLeft = 2 + x.left.numLeft + x.left.numRight;

								} else {
										x.numRight = 1 + x.right.left.numLeft +
												x.right.left.numRight;
										x.right.numLeft = 3 + x.left.numLeft + x.left.numRight +
												x.right.left.numLeft + x.right.left.numRight;
								}
						}
				}

		}

		public void leftRotate(RedBlackNode<T> x) {

				System.out.println("leftRotate in Node with key: " + x.key);
				leftRotateFixup(x);

				RedBlackNode<T> y;
				y = x.right;
				x.right = y.left;

				if (!isNil(y.left)) {
						y.left.parent = x;
				}
				y.parent = x.parent;

				if (isNil(x.parent)) {
						root = y;
				} else {
						if (x.parent.left == x) {
								x.parent.left = y;
						} else {
								x.parent.right = y;
						}
				}

				y.left = x;
				x.parent = y;
		}

		public void rightRotateFixup(RedBlackNode y) {

				if (isNil(y.right) && isNil(y.left.right)) {
						y.numRight = 0;
						y.numLeft = 0;
						y.left.numRight = 1;
				} else {
						if (isNil(y.right) && !isNil(y.left.right)) {
								y.numRight = 0;
								y.numLeft = 1 + y.left.right.numRight +
										y.left.right.numLeft;
								y.left.numRight = 2 + y.left.right.numRight +
										y.left.right.numLeft;
						} else {
								if (!isNil(y.right) && isNil(y.left.right)) {
										y.numLeft = 0;
										y.left.numRight = 2 + y.right.numRight + y.right.numLeft;

								} else {
										y.numLeft = 1 + y.left.right.numRight +
												y.left.right.numLeft;
										y.left.numRight = 3 + y.right.numRight +
												y.right.numLeft +
												y.left.right.numRight + y.left.right.numLeft;
								}
						}
				}
		}

		public void rightRotate(RedBlackNode<T> y) {

				System.out.println("rightRotate in Node with key: " + y.key);

				rightRotateFixup(y);

				RedBlackNode<T> x = y.left;
				y.left = x.right;

				if (!isNil(x.right)) {
						x.right.parent = y;
				}
				x.parent = y.parent;
				if (isNil(y.parent)) {
						root = x;
				} else {
						if (y.parent.right == y) {
								y.parent.right = x;
						} else {
								y.parent.left = x;
						}
				}
				x.right = y;
				y.parent = x;
		}

		public void insert(T key) {
				insert(new RedBlackNode<T>(key));
		}

		private void insert(RedBlackNode<T> z) {

				RedBlackNode<T> y = nil;
				RedBlackNode<T> x = root;

				while (!isNil(x)) {
						y = x;
						if (z.key.compareTo(x.key) < 0) {
								x.numLeft++;
								x = x.left;
						} else {
								x.numRight++;
								x = x.right;
						}
				}
				z.parent = y;
				if (isNil(y)) {
						root = z;
				} else {
						if (z.key.compareTo(y.key) < 0) {
								y.left = z;
						} else {
								y.right = z;
						}
				}
				z.left = nil;
				z.right = nil;
				z.color = RedBlackNode.RED;
				insertFixup(z);
		}

		public void insertFixup(RedBlackNode<T> z) {
				RedBlackNode<T> y = nil;
				while (z.parent.color == RedBlackNode.RED) {
						if (z.parent == z.parent.parent.left) {
								y = z.parent.parent.right;
								if (y.color == RedBlackNode.RED) {
										z.parent.color = RedBlackNode.BLACK;
										y.color = RedBlackNode.BLACK;
										z.parent.parent.color = RedBlackNode.RED;
										z = z.parent.parent;
								} else {
										if (z == z.parent.right) {
												z = z.parent;
												leftRotate(z);
										} else {
												z.parent.color = RedBlackNode.BLACK;
												z.parent.parent.color = RedBlackNode.RED;
												rightRotate(z.parent.parent);
										}
								}
						} else {
								y = z.parent.parent.left;
								if (y.color == RedBlackNode.RED) {
										z.parent.color = RedBlackNode.BLACK;
										y.color = RedBlackNode.BLACK;
										z.parent.parent.color = RedBlackNode.RED;
										z = z.parent.parent;
								} else {
										if (z == z.parent.left) {
												z = z.parent;
												rightRotate(z);
										} else {
												z.parent.color = RedBlackNode.BLACK;
												z.parent.parent.color = RedBlackNode.RED;
												leftRotate(z.parent.parent);
										}
								}
						}
				}
				root.color = RedBlackNode.BLACK;
		}

		public RedBlackNode<T> treeMinimum(RedBlackNode<T> node){
				while (!isNil(node.left))
						node = node.left;
				return node;
		}

		public RedBlackNode<T> treeSuccessor(RedBlackNode<T> x) {
				if (!isNil(x.left)) {
						return treeMinimum(x.right);
				}
				RedBlackNode<T> y = x.parent;

				while (!isNil(y) && x == y.right) {
						x = y;
						y = y.parent;
				}
				return y;
		}
}