package Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UtilityClass {

	public static void printList(Map<String, String> printList) {

		for (Map.Entry<String, String> entry : printList.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			System.out.println(key + " " + value);
		}
	}
	
	public static void printList(List<String> printList) {
	
		for(String element : printList) 
			System.out.println(element);
	}
}
