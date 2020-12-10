package fr.johannvonissou.alcooloops.filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

public class ProductManager {

	private static final String FILENAME = "products.biere";
	private static final double MAX_NOT = 5;
	
	private double rcoef, tcoef, vcoef;
	
	private Map<Integer, Product> products;
	private File directory, pictures;
	private long fileCreation;
	
	public ProductManager(File directory, double rcoef, double tcoef) {
		this.pictures = new File(this.directory, "captures");

		if(!this.pictures.exists()) {
			this.pictures.mkdir();
		}

		this.products = new TreeMap<>();
		this.directory = directory;
		this.rcoef = rcoef;
		this.tcoef = tcoef;
		this.importFile();
	}
	
	public void importFile() {
		File file = new File(this.directory, FILENAME);
		
		if(!file.exists()) return;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String json = br.readLine();
			ProductList pl = new Gson().fromJson(json, ProductList.class);
			br.close();
			this.setFileCreation(pl.getFileCreation());
			
			for(Product p : pl.getProducts()) {
				this.products.put(p.getUniqueID(), p);
			}
			this.calculateVCoef();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void export() {
		this.calculateVCoef();
		
		File file = new File(this.directory, FILENAME);
		
		try {
			ProductList pl = new ProductList();
			if(file.exists()) {
				if(this.fileCreation == 0) {
					throw new IOException("Erreur de validation du fichier existant ! (importez le fichier en premier)");
				}else {
					pl.setFileCreation(this.fileCreation);
				}
			}else {
				file.createNewFile();
				pl.setFileCreationToNow();
				this.setFileCreation(pl.getFileCreation());
			}
			
			pl.setProducts(new ArrayList<Product>(this.products.values()));
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(new Gson().toJson(pl));
			bw.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void addProducts(Product... products) {
		for(Product p : products) {
			if(p.getUniqueID() == -1) {
				p.setUniqueID(this.generateUniqueID());
			}
			
			this.products.put(p.getUniqueID(), p);
		}
		this.export();
	}
	
	public void addProduct(String name, String shop, double price, double notation,
			double degrees, double volume, int number, boolean available) {
		
		this.addProducts(new Product(name, shop, price, notation, degrees, volume, number, available));
	}
	
	private int generateUniqueID() {
		if(this.products.isEmpty()) return 1;
		return this.products.get(this.products.size()).getUniqueID() + 1;
	}
	
	public Product getProductFromID(int uniqueID) {
		return this.products.get(uniqueID);
	}
	
	@Deprecated
	public void setExportUnsafe() {
		this.setFileCreation(-1);
	}
	
	public Map<String, List<Product>> getProductsByShops() {
		Map<String, List<Product>> map = new HashMap<>();
		
		for(Product p : this.products.values()) {
			if(map.containsKey(p.getShop())) {
				map.get(p.getShop()).add(p);
			}else {
				List<Product> l = new ArrayList<>();
				l.add(p);
				map.put(p.getShop(), l);
			}
		}
		
		Map<String, List<Product>> finalmap = new HashMap<>();
		
		for(String shop : map.keySet()) {
			finalmap.put(shop, this.sortProducts(map.get(shop)));
		}
		
		return finalmap;
	}
	
	public List<Product> sortProducts(Collection<Product> products) {
		List<Product> l = new ArrayList<>(products);
		
		l.sort(new Comparator<Product>() {

			@Override
			public int compare(Product p0, Product p1) {
				return getScore(p1) - getScore(p0);
			}
		});
		
		return l;
	}
	
	public List<Product> sortAllProducts() {
		return this.sortProducts(this.products.values());
	}
	
	public int getScore(Product p) {
		if(!p.isAvailable()) return 0;
		
		double sr = this.vcoef*this.getEthRat(p);
		double sn = (p.getNotation()*this.tcoef)/MAX_NOT;
		int t = (int) Math.round(sr + sn);
		//System.out.println("score " + p.getName() + " : r=" + sr + " | ethrat= " + this.getEthRat(p) + ", g=" + sn + ", t: " + t);
		return t;
	}
	
	public Product getBestProduct() {
		List<Product> l = this.sortAllProducts();
		if(l.size() == 0) return null;
		return l.get(0);
	}
	
	public boolean pictureExists(Product product) {
		return this.getPictureFileFromProduct(product).exists();
	}
	
	public byte[] getPictureDataFromProduct(Product product) {
		File file = this.getPictureFileFromProduct(product);
		
		try {
			if(!file.exists()) {
				throw new FileNotFoundException("L'image associciée au produit \"" + product.getName() + "\" n'a pas été trouvée !");
			}
			
			FileInputStream fis = new FileInputStream(file);
			byte[] b = new byte[(int) file.length()];
			fis.read(b);
			fis.close();
			return b;
		}catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void setPictureDataFromProduct(Product product, byte[] data) {
		File file = this.getPictureFileFromProduct(product);
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private double getEthRat(Product p) {
		return (p.getNumber()*p.getVolume()*p.getDegrees())/(100*p.getPrice());
	}
	
	private void calculateVCoef() {
		double best = 0;
		for(Product p : this.products.values()) {
			double ethrat = this.getEthRat(p);
			if(best <= ethrat) best = ethrat;
		}
		this.vcoef = this.rcoef/best;
		//System.out.println("SCOEF=" + this.vcoef + ", best:" + best + ", rcoef=" + this.rcoef);
	}
	
	private File getPictureFileFromProduct(Product product) {
		return new File(this.pictures, product.getUniqueID()+"-picture.biere");
	}
	
	public Map<Integer, Product> getProducts() {
		return this.products;
	}

	public File getDirectory() {
		return this.directory;
	}

	public File getPictureDirectory() {
		return this.pictures;
	}
	
	public long getFileCreation() {
		return this.fileCreation;
	}

	public void setFileCreation(long fileCreation) {
		this.fileCreation = fileCreation;
	}

	public double getRCoef() {
		return this.rcoef;
	}

	public void setRCoef(double rcoef) {
		this.rcoef = rcoef;
	}

	public double getTCoef() {
		return this.tcoef;
	}

	public void setTCoef(double tcoef) {
		this.tcoef = tcoef;
	}
}