package coco.fmDataStructures;

import java.util.ArrayList;
import java.util.List;

public class AtomicSet<T> {
	
	private String name;
	private int set;
	private List<T> features;
	
	public AtomicSet( String name, int set ) {
		this.name = name;
		this.set = set;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSet() {
		return set;
	}
	
	public List<T> getFeatures() {
		return features;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public void setSet( int set ) {
		this.set = set;
	}
	
	public void addFeature( T feature ) {
		if(features == null) {
			features = new ArrayList<T>();
		}
		features.add(feature);
	}
}
