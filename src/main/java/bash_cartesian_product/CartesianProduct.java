package bash_cartesian_product;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CartesianProduct {
	public String inputString;
	int i = 0;
	Logger logger = Logger.getLogger(CartesianProduct.class.getName());

	public CartesianProduct(String inputString) throws InvalidInputString {
		// logs the message and throws an exception , need to rerun the project
		if(inputString ==null){
			logger.severe("empty string");
			System.exit(6);
		}
		if (!validString(inputString)) {
			logger.severe("wrong input string, please rerun the project");
			throw new InvalidInputString("invalid input" + inputString);
		}
		this.inputString = "{" + inputString + "}";
	}

	// method to check for valid parenthesis
	public boolean validString(String input) {
		int i = 0;
		for (char c : input.toCharArray()) {
			if (c != '}' && c != '{' && c != ',' && !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')
					&& !(c >= '0' && c <= '9'))
				return false;
			if (c == '{')
				i++;
			if (c == '}')
				i--;
		}
		logger.info("string is valid");
		return i == 0;
	}

	// returns the output of the bash cartesian product 
	public String getCartesianProduct() {
		StringBuilder sb = new StringBuilder();
		for (String i : getStrings()) {
			sb.append(i).append(" ");
		}
		logger.info("successfully determined the bash cartesian product");
		return sb.toString().trim();//removes extra space at the end
	}

	// Separates the strings based on braces
	public ArrayList<String> getStrings() {
		ArrayList<String> output = new ArrayList<>();
		ArrayList<String> temp;
		if (inputString.charAt(i) == '{') {
			i++;
			while (inputString.charAt(i - 1) != '}') {
				temp = getStrings();
				output.addAll(temp);
				i++;
			}
			if (i == inputString.length()) {
				return output;
			}
			output = getElements(output);
		} else {
			String literal = "";
			while (i < inputString.length() && ((inputString.charAt(i) >= 'a' && inputString.charAt(i) <= 'z')
					|| (inputString.charAt(i) >= 'A' && inputString.charAt(i) <= 'Z')
					|| (inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9'))) {
				literal += inputString.charAt(i);
				i++;
			}
			output.add(literal);
			output = getElements(output);
		}

		return output;
	}

	// Separates the elements inside the braces
	private ArrayList<String> getElements(ArrayList<String> output) {
		ArrayList<String> temp;
		while (i < inputString.length() && inputString.charAt(i) != ',' && inputString.charAt(i) != '}') {
			temp = getStrings();
			output = getProduct(output, temp);
		}
		return output;
	}

	// returns the cartesian product of the strings
	private ArrayList<String> getProduct(ArrayList<String> outputList, ArrayList<String> tempList) {
		ArrayList<String> output = new ArrayList<>();
		for (String a : outputList) {
			for (String b : tempList) {
				output.add(a + b);
			}
		}
		logger.info("successfully calculated a cartesian product");
		return output;
	}

	public static void main(String args[]) {

		if (args.length > 0) {
			CartesianProduct product;
			try {
				product = new CartesianProduct(args[0]);
				product.logger.info("the cartesian product of the input string has been calculated successfully");
				System.out.println(product.getCartesianProduct());
			} catch (InvalidInputString e) {
				e.printStackTrace();
			}
		}
	}
}
