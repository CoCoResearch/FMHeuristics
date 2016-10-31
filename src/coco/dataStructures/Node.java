package coco.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private T object;
	private Node<T> parent;
	private List<Node<T>> children;
	
	public Node( T object ) {
		this.object = object;
		this.parent = null;
		this.children = null;
	}
	
	public T getObject() {
		return object;
	}
	
	public Node<T> getParent() {
		return parent;
	}
	
	public List<Node<T>> getChildren() {
		return children;
	}
	
	public void setObject( T object ) {
		this.object = object;
	}
	
	public void setParent( Node<T> parent) {
		this.parent = parent;
	}
	
	public void addChild( Node<T> child ) {
		if(children == null) {
			children = new ArrayList<Node<T>>();
		}
		
		child.setParent(this);
		children.add(child);
	}
	
	public boolean isLeaf() {
		boolean isLeaf = false;
		
		if(children == null) {
			isLeaf = true;
		}
		
		return isLeaf;
	}
	
	public int getDepth() {
		int depth = 0;
		Node<T> currentParent = parent;
		
		while(currentParent != null) {
			depth++;
			currentParent = currentParent.getParent();
		}
		
		return depth;
	}
	
	public int getWeight() {
		int weight = 0;
		
		if(children != null) {
			weight = children.size();
			
			for(int i = 0; i < children.size(); i++) {
				weight += children.get(i).getWeight();
			}
		}
		return weight;
	}
	
	public void getNodesPreorder(List<Node<T>> nodes) {
		if(nodes == null) {
			nodes = new ArrayList<Node<T>>();
		}
		nodes.add(this);
		
		if(children != null) {
			for(int i = 0; i < children.size(); i++) {
				Node<T> child = children.get(i);
				child.getNodesPreorder(nodes);
			}
		}
	}
}
