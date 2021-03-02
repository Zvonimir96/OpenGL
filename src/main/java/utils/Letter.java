package utils;

import java.util.ArrayList;

import objects.LetterObject;

public class Letter {
		
	static int indices[] = {
		0, 1, 2,
		0, 2, 3,
	}; 
	
	private static ArrayList<Integer> X_LIST = new ArrayList<Integer>();
	private static ArrayList<Integer> Y_LIST = new ArrayList<Integer>();
	private static ArrayList<Integer> WIDTH_LIST = new ArrayList<Integer>();
	private static ArrayList<Integer> HEIGHT_LIST = new ArrayList<Integer>();
	private static ArrayList<Integer> X_OFFSET_LIST = new ArrayList<Integer>();
	private static ArrayList<Integer> Y_OFFSET_LIST = new ArrayList<Integer>();
	private static ArrayList<Integer> ID_LIST = new ArrayList<Integer>();
	private static ArrayList<Integer> X_ADVANCE_LIST = new ArrayList<Integer>();
	
	private static float PIXELS = 512;
	public static float UNIT = 100;
	
	private static String TEXTURE_NAME = "text";
	
 	public static LetterObject getLetter(char letter) {
		float vertices[] = new float [12];
		float texCoords[] = new float[8];

		for(int i=0; i<ID_LIST.size(); i++) 
			if(ID_LIST.get(i) == letter) {
				
				float x = X_LIST.get(i)/PIXELS, y = Y_LIST.get(i)/PIXELS, 
						width = WIDTH_LIST.get(i)/PIXELS, height = HEIGHT_LIST.get(i)/PIXELS,
						widthPosition = WIDTH_LIST.get(i)/UNIT, heightPosition = HEIGHT_LIST.get(i)/UNIT,
						offsetX =  X_OFFSET_LIST.get(i)/UNIT, offsetY = -Y_OFFSET_LIST.get(i)/UNIT;
				
				vertices[0] = offsetX+widthPosition;
				vertices[1] = offsetY;
				vertices[2] = 0;
				vertices[3] = offsetX+widthPosition;
				vertices[4] = offsetY-heightPosition;
				vertices[5] = 0;
				vertices[6] = offsetX;
				vertices[7] = offsetY-heightPosition;
				vertices[8] = 0;
				vertices[9] = offsetX;
				vertices[10] = offsetY;
				vertices[11] = 0;
				
				texCoords[0] = x+width;
				texCoords[1] = y;
				texCoords[2] = x+width;
				texCoords[3] = y+height;
				texCoords[4] = x;
				texCoords[5] = y+height;
				texCoords[6] = x;
				texCoords[7] = y;
				
				return new LetterObject(vertices, texCoords, indices, TEXTURE_NAME);
			}
		
		System.out.println("No desired letter");
		return null;
	}
 	
 	public static Integer getAdvance(char letter) {
		for(int i=0; i<ID_LIST.size(); i++) 
			if(ID_LIST.get(i) == letter) 
				return X_ADVANCE_LIST.get(i);
			
		System.out.println("Letter out of bounds");
		return X_ADVANCE_LIST.get(0);
	}
	
	public static void load() {
		String file = FileUtils.read("./Text/arial.fnt");
		int index = 0;
		String number = "";
		
		outerloop:
		while(true){
			for(int i=0; i<8; i++) {
				index = file.indexOf("=", index) + 1;
				if(index == 0) break outerloop;
				
				for(int j=0; j<3; j++) 
					number = number + (file.charAt(index + j));
				
				number = number.replaceAll("\\s+","");
				if(i==0) ID_LIST.add(Integer.parseInt(number));
				else if(i==1) X_LIST.add(Integer.parseInt(number));
				else if(i==2) Y_LIST.add(Integer.parseInt(number));
				else if(i==3) WIDTH_LIST.add(Integer.parseInt(number));
				else if(i==4) HEIGHT_LIST.add(Integer.parseInt(number));
				else if(i==5) X_OFFSET_LIST.add(Integer.parseInt(number));
				else if(i==6) Y_OFFSET_LIST.add(Integer.parseInt(number));
				else if(i==7) X_ADVANCE_LIST.add(Integer.parseInt(number));
				
				number = "";
			}
		}	
	}
	
}
