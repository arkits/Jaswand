package io.github.arkits.jaswand.elements;

public class CollectionElement {

	private String elementName;
	private String elementIcon;
	private String elementIconColor;

	public CollectionElement(){
	}

	public CollectionElement(String elementName, String elementIcon, String elementIconColor){
		this.elementName = elementName;
		this.elementIcon = elementIcon;
		this.elementIconColor = elementIconColor;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementIcon() {
		return elementIcon;
	}

	public void setElementIcon(String elementIcon) {
		this.elementIcon = elementIcon;
	}

	public String getElementIconColor() {
		return elementIconColor;
	}

	public void setElementIconColor(String elementIconColor) {
		this.elementIconColor = elementIconColor;
	}

}
