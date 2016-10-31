package coco.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

	private Node<T> root;
	
	public Tree() {
		this.root = null;
	}
	
	public Node<T> getRoot() {
		return root;
	}
	
	public void setRoot( Node<T> root ) {
		this.root = root;
	}
	
	public int getWeight() {
		return root.getWeight();
	}
	
	public List<Node<T>> getNodesPreorder() {
		List<Node<T>> nodes = new ArrayList<Node<T>>();
		root.getNodesPreorder(nodes);
		return nodes;
	}
}
