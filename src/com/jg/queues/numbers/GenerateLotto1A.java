package com.jg.queues.numbers;

public class GenerateLotto1A {

	public static void main(String[] args) {
		int min = 0;
		int max = 32;
		System.out.println("Random value of type int between " + min + " to " + max + ":");
		
		    StringBuilder sb = new StringBuilder();
	        int count = 0;
			while (count < 6 ) {
				int b = (int) (Math.random() * (max - min + 1) + min);
				System.out.println(b);
			    sb.append(b);
			    sb.append(":");
			    count ++;
			}
			System.out.println( sb.toString() );
	   
	   }
	
    		
	}

