package org.jcl.numbers;

public class JNumbers {
    public static int convertBinary2Number(int binary) {
	System.out.println(binary);
	int index = 0;
	int sum = 0;
	while (binary > 0) {
	    int digit = binary % 10;
	    int representationOfDigit = (int) (digit * Math.pow(2, index));
	    sum += representationOfDigit;
	    index++;
	    binary /= 10;
	}
	return sum;
    }

    public static int convertBinary2Number(String binary) {
	return convertBinary2Number(Integer.parseInt(binary));
    }
}
