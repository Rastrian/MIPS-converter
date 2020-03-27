import invoke.MIPSDecoder;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			String input = "C:/Users/luise/IdeaProjects/MIPS-converter/src/input.txt";
			String output = "";

			MIPSDecoder.run(input, output);
		} catch (Exception e) {
				throw e;
		}

	}

}
