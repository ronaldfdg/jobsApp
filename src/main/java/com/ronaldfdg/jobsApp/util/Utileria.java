package com.ronaldfdg.jobsApp.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	
	private static final int CANTIDAD_CARACTERES = 8;

	public static String saveImage(MultipartFile multipart, String route) {
		
		String imageName = removeSpaces(generateRandomCharacters() + multipart.getOriginalFilename());
		
		try {
			File file = new File(route+imageName);
			multipart.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imageName;
		
	}
	
	public static String generateRandomCharacters() {
		
		StringBuilder characters = new StringBuilder();
		char character = ' ';
		int opcion = 0;
		int count = 0;
		
		while(count < CANTIDAD_CARACTERES) {
			
			opcion = (int) (Math.random()*2);
			
			if(opcion == 1)
				character = (char)(int)(Math.random() * 26 + 65);
			else
				character = (char)(int)(Math.random() * 10 + 48);
				
			characters.append(character);
			count++;
		}
		
		return characters.toString();
	}
	
	public static String removeSpaces(String fileName) {
		return fileName.replace(' ', '-');
	}
	
}
