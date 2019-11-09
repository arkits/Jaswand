package io.github.arkits.jaswand.elements;

public class CollapsibleElement {

	private String elementName;
	private String elementBody;
	private String elementIcon;

	public CollapsibleElement(){
	}

	public CollapsibleElement(String elementName, String elementBody, String elementIcon){
		this.elementName = elementName;
		this.elementBody = elementBody;
		this.elementIcon = elementIcon;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementBody() {
		return elementBody;
	}

	public void setElementBody(String elementBody) {
		this.elementBody = elementBody;
	}

	public String getElementIcon() {
		return elementIcon;
	}

	public void setElementIcon(String elementIcon) {
		this.elementIcon = elementIcon;
	}
}
