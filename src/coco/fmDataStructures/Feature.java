package coco.fmDataStructures;

public class Feature {

	private String name;
	private String id;
	private boolean mandatory;
	private boolean instantiated;
	
	public Feature( String name, String id, boolean mandatory ) {
		this.name = name;
		this.id = id;
		this.mandatory = mandatory;
		this.instantiated = false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean getMandatory() {
		return mandatory;
	}
	
	public boolean getInstantiated() {
		return instantiated;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public void setId( String id ) {
		this.id = id;
	}
	
	public void setMandatory( boolean mandatory ) {
		this.mandatory = mandatory;
	}
	
	public void setInstantiated( boolean instantiated ) {
		this.instantiated = instantiated;
	}
}
