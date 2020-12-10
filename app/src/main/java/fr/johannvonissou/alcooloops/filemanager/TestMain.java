package fr.johannvonissou.alcooloops.filemanager;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		ProductManager pm = new ProductManager(new File("test"), 90, 10);
		
		System.out.println("products added ...");
		
		/*String line = null;
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);
		
		while(true) {
			System.out.println("nom:");
			
			line = sc.nextLine();
			line = sc.nextLine();
			if(line.equalsIgnoreCase("stop")) break;
			
			Product p = new Product();
			p.setName(line);
			System.out.println("shop:");
			p.setShop(sc.nextLine());
			System.out.println("degrees:");
			p.setDegrees(sc.nextDouble());
			System.out.println("price:");
			p.updatePrice(sc.nextDouble());
			System.out.println("volume:");
			p.setVolume(sc.nextDouble());
			System.out.println("number:");
			p.setNumber(sc.nextInt());
			System.out.println("available:");
			p.setAvailable(sc.nextBoolean());
			System.out.println("notation:");
			p.updateNotation(sc.nextDouble());
			
			pm.addProducts(p);
		}
		sc.close();*/
		
		System.out.println(pm.getProductsByShops());
		System.out.println(pm.getBestProduct());
		
		List<Product> best = pm.sortAllProducts();
		
		for(int i = 1; i <= best.size(); i++) {
			System.out.println(i + " : " + best.get(i - 1).getName() + " -> " + pm.getScore(best.get(i - 1)));
		}
	}
	
}