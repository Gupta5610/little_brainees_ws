/**
 * 
 */
package com.little_brainees.app.ws.request;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ashish
 *
 */

@XmlRootElement
public class ParentRequestObject {
	String parentID;
	String parentName;
	String parentAddress;
	String parentNumber;
	String parentEmail;
	List<ChildRequestObject> children;
	/**
	 * @return the children
	 */
	public List<ChildRequestObject> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ChildRequestObject> children) {
		this.children = children;
	}
	/**
	 * @return the parentID
	 */
	public String getParentID() {
		return parentID;
	}
	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}
	/**
	 * @return the parentAddress
	 */
	public String getParentAddress() {
		return parentAddress;
	}
	/**
	 * @return the parentNumber
	 */
	public String getParentNumber() {
		return parentNumber;
	}
	/**
	 * @return the parentEmail
	 */
	public String getParentEmail() {
		return parentEmail;
	}
	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/**
	 * @param parentAddress the parentAddress to set
	 */
	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}
	/**
	 * @param parentNumber the parentNumber to set
	 */
	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}
	/**
	 * @param parentEmail the parentEmail to set
	 */
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	
}
