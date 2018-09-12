package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a file path: ");
		String strPath = sc.nextLine();
		
		List<Product> list = new ArrayList<>();
		
		File path = new File(strPath);
		String sourcefolder = path.getParent();
		
		new File(sourcefolder + "\\out").mkdir();
		
		String targetPath = sourcefolder + "\\out\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(strPath))){
			
			String line = br.readLine();
			
			while(line != null) {
				String[] vect = line.split(",");
				String name = vect[0];
				double price = Double.parseDouble(vect[1]);
				int quantity = Integer.parseInt(vect[2]);
				list.add(new Product(name, price, quantity));
				line = br.readLine();
			}
			
			for(Product pro : list) {
				System.out.println(pro);
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath))){
				
				for(Product pro : list) {
					bw.write(pro.getName() + "," + pro.sum());
					bw.newLine();
				}
				
				System.out.println(targetPath);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}		

		sc.close();
	}

}
