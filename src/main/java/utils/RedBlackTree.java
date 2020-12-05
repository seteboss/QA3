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
}