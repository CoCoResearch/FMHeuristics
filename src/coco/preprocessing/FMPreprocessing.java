package coco.preprocessing;

import java.util.ArrayList;
import java.util.List;

import coco.dataStructures.Node;
import coco.dataStructures.Tree;
import coco.fmDataStructures.*;

public class FMPreprocessing {
	
	private Tree<Feature> tree;
	private Tree<Feature> treeWeight;
	private List<AtomicSet<Node<Feature>>> atomicSets;
	private AtomicSet<Node<Feature>> coreFeatures;
	
	public FMPreprocessing(Tree<Feature> tree) {
		this.tree = tree;
		this.treeWeight = null;
	}
	
	public Tree<Feature> getTree() {
		return tree;
	}
	
	public Tree<Feature> getTreeWeight() {
		if(treeWeight == null) {
			sortTreeByWeight();
		}
		
		return treeWeight;
	}
	
	public List<AtomicSet<Node<Feature>>> getAtomicSets() {
		if(atomicSets == null) {
			findAtomicSets();
		}
		
		return atomicSets;
	}
	
	public AtomicSet<Node<Feature>> getCoreFeatures() {
		if(coreFeatures == null) {
			findAtomicSets();
		}
		
		return coreFeatures;
	}
	
	public void findAtomicSets() {
		int set = 0;
		Node<Feature> root = tree.getRoot();
		coreFeatures = new AtomicSet<Node<Feature>>("AS-0", set);
		coreFeatures.addFeature(root);
		
		if(atomicSets == null) {
			atomicSets = new ArrayList<AtomicSet<Node<Feature>>>();
		}
		
		atomicSets.add(coreFeatures);
		computeAtomicSets(root, coreFeatures, set);
	}
	
	private void computeAtomicSets( Node<Feature> feature, AtomicSet<Node<Feature>> currentAtomicSet, int set ) {
		List<Node<Feature>> children = feature.getChildren();
		
		if(children != null) {
			for(int i = 0; i < children.size(); i++) {
				Node<Feature> child = children.get(i);
				Feature childFeature = child.getObject();
				
				if(childFeature.getMandatory()) {
					currentAtomicSet.addFeature(child);
					computeAtomicSets(child, currentAtomicSet, set);
				}
				
				else {
					set++;
					AtomicSet<Node<Feature>> newAtomicSet = new AtomicSet<Node<Feature>>("AS-" + set, set);
					newAtomicSet.addFeature(child);
					atomicSets.add(newAtomicSet);
					computeAtomicSets(child, newAtomicSet, set);
				}
			}
		}
	}
	
	public void findCoreFeatures() {
		int set = 0;
		Node<Feature> root = tree.getRoot();
		coreFeatures = new AtomicSet<Node<Feature>>("AS-0", set);
		coreFeatures.addFeature(root);
		computeCoreFeatures(root);
	}
	
	private void computeCoreFeatures( Node<Feature> feature) {
		List<Node<Feature>> children = feature.getChildren();
		
		for(int i = 0; i < children.size(); i++) {
			Node<Feature> child = children.get(i);
			Feature childFeature = child.getObject();
			
			if(childFeature.getMandatory()) {
				coreFeatures.addFeature(child);
				computeCoreFeatures(child);
			}
		}
	}
	
	public void sortTreeByWeight() {
		treeWeight = tree;
		computeTreeByWeight(treeWeight.getRoot());
	}
	
	private void computeTreeByWeight(Node<Feature> feature) {
		List<Node<Feature>> children = feature.getChildren();
		
		if(children != null && children.size() > 0) {
			
			for(int i = 0; i < children.size(); i++) {
				int minWeight = children.get(i).getWeight();
				int minPosition = i;
				
				for(int j = i + 1; j < children.size(); j++) {
					Node<Feature> currentFeature = children.get(j);
					
					if(currentFeature.getWeight() < minWeight) {
						minWeight = currentFeature.getWeight();
						minPosition = j;
					}
				}	
				
				if(minPosition != i) {
					Node<Feature> tempFeature = children.get(i);
					children.set(i, children.get(minPosition));
					children.set(minPosition, tempFeature);
				}
				
				computeTreeByWeight(children.get(i));
			}
		}
	}
	
	/**
	 * 1. Ordenar variables por dominio (pequeño - grande)
	 * 2. Retornar core features OK
	 * 3. Retornar atomic sets OK
	 * 4. Ordenar subárboles por peso OK
	 * 5. Ordenar subárboles en clústers
	 * 6. Ordenar variables por profundidad OK
	 */
}
