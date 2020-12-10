package fr.johannvonissou.alcooloops.filemanager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Product {

	private final Map<Long, Double> priceEvolution, notationEvolution;
	private Map<String, String> optional;
	private long added, lastEdit;
	private int uniqueID, number;
	private boolean available;
	
	private double price, degrees, volume, notation;
	private String name, shop;
	
	public Product(String name, String shop, double price, double notation, double degrees,
			double volume, int number, boolean available, int uniqueID) {
		
		this.name = name.toLowerCase();
		this.shop = shop.toLowerCase();
		this.degrees = degrees;
		this.volume = volume;
		this.number = number;
		this.available = available;
		
		this.setAdded(System.currentTimeMillis());
		this.setLastEdit(this.added);
		this.priceEvolution = new TreeMap<>();
		this.notationEvolution = new TreeMap<>();
		this.optional = new HashMap<>();
		this.updatePrice(price);
		this.updateNotation(notation);
		this.setUniqueID(uniqueID);
	}
	
	public Product(String name, String shop, double price, double notation, double degrees,
			double volume, int number, boolean available) {
		
		this(name, shop, price, notation, degrees, volume, number, available, -1);
	}
	
	public Product() {
		this.name = "Nom non défini";
		this.shop = "Magasin non défini";
		this.degrees = -1;
		this.price = -1;
		this.volume = -1;
		this.number = -1;
		this.available = false;
		
		this.setAdded(System.currentTimeMillis());
		this.setLastEdit(this.added);
		this.setUniqueID(-1);
		
		this.notationEvolution = new TreeMap<>();
		this.priceEvolution = new TreeMap<>();
		this.optional = new HashMap<>();
	}
	
	public long getAdded() {
		return this.added;
	}
	
	public void setAdded(long added) {
		this.added = added;
	}

	public long getLastEdit() {
		return this.lastEdit;
	}
	
	public void setLastEdit(long lastEdit) {
		this.lastEdit = lastEdit;
		this.edit();
	}
	
	public double getDegrees() {
		return this.degrees;
	}

	public void setDegrees(double degrees) {
		this.degrees = degrees;
		this.edit();
	}
	
	public double getVolume() {
		return this.volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
		this.edit();
	}

	public double getPrice() {
		return this.price;
	}

	public void updatePrice(double price) {
		this.price = price;
		this.edit();
		this.priceEvolution.put(this.lastEdit, this.price);
	}
	
	public void updateNotation(double notation) {
		this.notation = notation;
		this.edit();
		this.notationEvolution.put(this.lastEdit, this.notation);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name.toLowerCase();
		this.edit();
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop.toLowerCase();
		this.edit();
	}
	
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
		this.edit();
	}

	public double getNotation() {
		return this.notation;
	}

	public Map<Long, Double> getPriceEvolution() {
		return this.priceEvolution;
	}
	
	public Map<Long, Double> getNotationEvolution() {
		return this.notationEvolution;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
		this.edit();
	}

	public int getUniqueID() {
		return this.uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public Map<String, String> getOptional() {
		return this.optional;
	}

	public void setOptional(Map<String, String> optional) {
		this.optional = optional;
		this.edit();
	}
	
	public void addOptionalInformations(String key, String value) {
		this.optional.put(key, value);
		this.edit();
	}

	public void edit() {
		this.lastEdit = System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		Map<Date, Double> tmap = new HashMap<>();
		Map<Date, Double> nmap = new HashMap<>();
		
		for(long l : this.priceEvolution.keySet()) {
			tmap.put(new Date(l), this.priceEvolution.get(l));
		}
		
		for(long l : this.notationEvolution.keySet()) {
			nmap.put(new Date(l), this.notationEvolution.get(l));
		}
		
		return "Produit : id=" + this.uniqueID + ", name: " + this.name + ", shop: " + this.shop + ", price: " +
				this.price + "€, degrees: " + this.degrees + ", notation: " + this.notation + ", volume: " + 
				this.volume + ", number: " + this.number + ", added: " + this.added + ", available: " + 
				this.available + ", lastEdit: " + this.lastEdit + ", priceEvolution: " + tmap + 
				", notationEvolution: " + nmap + ", optional: " + this.optional;
	}
}