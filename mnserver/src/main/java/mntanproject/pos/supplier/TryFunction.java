package mntanproject.pos.supplier;

import java.lang.reflect.Method;
import java.util.HashMap;


import com.google.gson.Gson;


public class TryFunction extends GenericApi<Supplier>{

	public static void main(String[] args) {
		Class<?> classRef; 
		try {
			//classRef = Class.forName("mntanproject.pos.supplier.SupplierResponse");
			SupplierResponse supp = new SupplierResponse();
			//Object instance = classRef.newInstance();
			for (Method clazzMethod : supp.getClass().getMethods()) {
				System.out.println(clazzMethod);
			}
		
			supp.test("");
			System.out.println("-----");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	


}
