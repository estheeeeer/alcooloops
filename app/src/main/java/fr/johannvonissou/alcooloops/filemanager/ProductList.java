package fr.johannvonissou.alcooloops.filemanager;

import java.util.List;

public class ProductList {
	
	private List<Product> products;
	private long fileCreation;
	
	public ProductList(List<Product> products) {
		this.setProducts(products);
	}

	public ProductList() {}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public long getFileCreation() {
		return fileCreation;
	}

	public void setFileCreation(long fileCreation) {
		this.fileCreation = fileCreation;
	}
	
	public void setFileCreationToNow() {
		this.setFileCreation(System.currentTimeMillis());
	}
}