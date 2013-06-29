public class GenreRecognizer {
	public static void main(String[] args) {
		int[] blues = {20, 30, 10};
		int[] jazz = {10, 20, 30};
		int[] test = {8, 6, 24};
		System.out.println(compare(test, test));
		System.out.println(compare(jazz, blues));
		System.out.println(compare(jazz, test));
		System.out.println(compare(blues, test));
	}
	//compares ordered lists of features
	//must be the same dimension
	public static double compare(int[] x, int[] y){
		double val = 0;
		if(x.length == y.length) {
			for (int w = 0; w < x.length; w++) val += Math.pow((x[w] - y[w]), 2);
			return Math.sqrt(val);
		} else return -1.0;
	}
}
