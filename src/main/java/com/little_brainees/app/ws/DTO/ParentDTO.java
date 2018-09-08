/**
 * 
 */
package com.little_brainees.app.ws.DTO;

import java.util.List;

/**
 * @author ashish
 *
 */


public class ParentDTO implements BaseDTO {

	String parentID;
	String parentName;
	String parentAddress;
	String parentNumber;
	String parentEmail;
	List<ChildDTO> children;
	
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
	 * @return the children
	 */
	public List<ChildDTO> getChildren() {
		return children;
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
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ChildDTO> children) {
		this.children = children;
	}
	
	@Override
	public String toString() {
		return "Parent - [ParentID : "+this.parentID+",parentName : "+this.parentName+"]";
	}
}
