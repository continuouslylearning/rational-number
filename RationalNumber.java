public class RationalNumber implements Comparable<RationalNumber> {
	private final int numerator;
	private final int denominator;

	public RationalNumber(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("The denominator cannot be zero.");
		}
		
		int GCD = findGCD(numerator, denominator);

		this.numerator = numerator/GCD;
		this.denominator = denominator/GCD;
	}

	@Override
	public boolean equals(Object o) {
		RationalNumber other = (RationalNumber) o;

		return numerator*other.denominator == denominator*other.numerator;
	}

	@Override
	public int compareTo(RationalNumber other) {
		if (equals(other)) {
			return 0;
		} else if (numerator*other.denominator < other.numerator*denominator) {
			return -1;
		}
		return 1;
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

	public void printDecimal() {
		System.out.println((float) numerator / (float) denominator);
	}

	public void printFraction() {    
		System.out.println(toString());
	}

	public RationalNumber reciprocal() {
		return new RationalNumber(denominator, numerator);
	}

	public RationalNumber add(RationalNumber other) {
		int numerator = this.numerator*other.denominator + other.numerator*this.denominator;
		int denominator = this.denominator*other.denominator;

		return new RationalNumber(numerator, denominator);
	}

	public RationalNumber subtract(RationalNumber other) {
		return add(new RationalNumber(-other.numerator, other.denominator));
	}

	public RationalNumber multiply(RationalNumber other) {
		int numerator = this.numerator*other.numerator;
		int denominator = this.denominator*other.denominator;

		return new RationalNumber(numerator, denominator);
	}

	public RationalNumber divide(RationalNumber other) {
		return multiply(other.reciprocal());
	}

	private int findGCD(int a, int b) {
		// Euclid's method for computing the GCD
		// https://en.wikipedia.org/wiki/Greatest_common_divisor#Euclid's_algorithm
		if (b == 0) {
			return a;
		}

		return findGCD(b, a % b);
	}

	public static void main(String[] args) {
		RationalNumber num = new RationalNumber(2, 4);
		num.printDecimal();
		num.printFraction();
		RationalNumber sum = num.add(new RationalNumber(1, 3));
		sum.printDecimal();
		sum.printFraction();
		RationalNumber product = num.multiply(new RationalNumber(1, 5));
		product.printDecimal();
		product.printFraction();
		RationalNumber quotient = num.divide(new RationalNumber(1, 5));
		quotient.printDecimal();
		quotient.printFraction();
		// assert num.equals(num.equals(new RationalNumber(5, 10)));
		// assert num.compareTo(new RationalNumber(5, 9)) == 0;
		System.out.println(num.equals(new RationalNumber(5, 10)));
		System.out.println(num.compareTo(new RationalNumber(5, 9)));
	}
}