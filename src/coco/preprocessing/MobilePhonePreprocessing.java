package coco.preprocessing;

import java.util.List;

import coco.dataStructures.Node;
import coco.dataStructures.Tree;
import coco.fmDataStructures.AtomicSet;
import coco.fmDataStructures.Feature;

public class MobilePhonePreprocessing {

	private Tree<Feature> fm;
	private FMPreprocessing processor;
	
	public MobilePhonePreprocessing() {
		loadTree();
		processor = new FMPreprocessing(fm);
	}
	
	private void loadTree() {
		fm = new Tree<Feature>();
		Node<Feature> root = new Node<Feature>(new Feature("MobilePhone", "MobilePhone", true));
		Node<Feature> n1_1 = new Node<Feature>(new Feature("UtilityFunction", "UtilityFunction", true));
		Node<Feature> n1_2 = new Node<Feature>(new Feature("Settings", "Settings", true));
		Node<Feature> n1_3 = new Node<Feature>(new Feature("Connectivity", "Connectivity", true));
		Node<Feature> n2_1 = new Node<Feature>(new Feature("Calls", "Calls", true));
		Node<Feature> n2_2 = new Node<Feature>(new Feature("Messaging", "Messaging", true));
		Node<Feature> n2_3 = new Node<Feature>(new Feature("Games", "Games", false));
		Node<Feature> n2_4 = new Node<Feature>(new Feature("Alarm", "Alarm", true));
		Node<Feature> n2_5 = new Node<Feature>(new Feature("JavaSupport", "JavaSupport", false));
		Node<Feature> n2_6 = new Node<Feature>(new Feature("OS", "OS", true));
		Node<Feature> n2_7 = new Node<Feature>(new Feature("Bluetooth", "Bluetooth", false));
		Node<Feature> n2_8 = new Node<Feature>(new Feature("Wifi", "Wifi", false));
		Node<Feature> n3_1 = new Node<Feature>(new Feature("Voice", "Voice", false));
		Node<Feature> n3_2 = new Node<Feature>(new Feature("Data", "Data", false));
		Node<Feature> n3_3 = new Node<Feature>(new Feature("SMS", "SMS", false));
		Node<Feature> n3_4 = new Node<Feature>(new Feature("MMS", "MMS", false));
		Node<Feature> n3_5 = new Node<Feature>(new Feature("Symbian", "Symbian", false));
		Node<Feature> n3_6 = new Node<Feature>(new Feature("WinCE", "WinCE", false));
	
		n2_1.addChild(n3_1);
		n2_1.addChild(n3_2);
		
		n2_2.addChild(n3_3);
		n2_2.addChild(n3_4);
		
		n2_6.addChild(n3_5);
		n2_6.addChild(n3_6);
		
		n1_1.addChild(n2_1);
		n1_1.addChild(n2_2);
		n1_1.addChild(n2_3);
		n1_1.addChild(n2_4);
		
		n1_2.addChild(n2_5);
		n1_2.addChild(n2_6);
		
		n1_3.addChild(n2_7);
		n1_3.addChild(n2_8);
		
		root.addChild(n1_1);
		root.addChild(n1_2);
		root.addChild(n1_3);
		
		fm.setRoot(root);
	}
	
	public void getCoreFeatures() {
		AtomicSet<Node<Feature>> as = processor.getCoreFeatures();
		List<Node<Feature>> coreFeatures = as.getFeatures();
		
		System.out.println("---------CORE FEATURES---------");
		
		if(coreFeatures != null) {
			for(int i = 0; i < coreFeatures.size(); i++) {
				System.out.println(coreFeatures.get(i).getObject().getName());
			}
		}
		
		System.out.println();
	}
	
	public void getAtomicSets() {
		List<AtomicSet<Node<Feature>>> atomicSets = processor.getAtomicSets();
		
		System.out.println("---------ATOMIC SETS---------");
		
		if(atomicSets != null) {
			for(int i = 0; i < atomicSets.size(); i++) {
				AtomicSet<Node<Feature>> as = atomicSets.get(i);
				List<Node<Feature>> features = as.getFeatures();
				System.out.println("AS-" + i);
				
				for(int j = 0; j < features.size(); j++) {
					System.out.println(features.get(j).getObject().getName());
				}
			}
		}
		
		System.out.println();
	}
	
	public void getTreeByWeight() {
		Tree<Feature> treeWeight = processor.getTreeWeight();
		List<Node<Feature>> nodes = treeWeight.getNodesPreorder();
		
		System.out.println("---------TREE BY WEIGHT---------");
		
		for(int i = 0; i < nodes.size(); i++) {
			Feature child = nodes.get(i).getObject();
			Node<Feature> parent = nodes.get(i).getParent();
			
			if(parent != null) {
				System.out.println("Parent: " + parent.getObject().getName() 
						+ " - Child: " + child.getName());
			}
			else {
				System.out.println("Parent: NONE" + " - Child: " + child.getName());
			}
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		MobilePhonePreprocessing mp = new MobilePhonePreprocessing();
		mp.getCoreFeatures();
		mp.getAtomicSets();
		mp.getTreeByWeight();
	}
	
}
