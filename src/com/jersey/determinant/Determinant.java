package com.jersey.determinant;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Determinant {
	private int order;
	private double value;
	private double[][] array;
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double[][] getArray() {
		return array;
	}
	public void setArray(double[][] array) {
		this.array = array;
	}
}
