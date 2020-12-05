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
}