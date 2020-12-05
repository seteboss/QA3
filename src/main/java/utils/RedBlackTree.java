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
}