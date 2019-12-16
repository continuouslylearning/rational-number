public class RationalNumber implements Comparable<RationalNumber> {
	private final int numerator;
	private final int denominator;

	public RationalNumber(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("The denominator cannot be zero.");
		}
		
		int GCD = findGCD(numerator, denominator);

		numerator = numerator/GCD;
		denominator = denominator/GCD;

		if (denominator < 0) {
			denominator = -denominator;
			numerator = -numerator;
		}

		this.numerator = numerator;
		this.denominator = denominator;
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

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
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
		return add(other.negate());
	}

	public RationalNumber multiply(RationalNumber other) {
		int numerator = this.numerator*other.numerator;
		int denominator = this.denominator*other.denominator;

		return new RationalNumber(numerator, denominator);
	}

	public RationalNumber exponentiate(int n) {
		int numerator = (int) Math.pow(this.numerator, n);
		int denominator = (int) Math.pow(this.denominator, n);

		return new RationalNumber(numerator, denominator);
	}

	public RationalNumber divide(RationalNumber other) {
		return multiply(other.reciprocal());
	}

	private RationalNumber negate() {
		return new RationalNumber(-numerator, denominator);
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
		assert num.getNumerator() == 1 && num.getDenominator() == 2;
		
		RationalNumber num2 = new RationalNumber(1, -10);
		assert num2.getNumerator() == -1 && num2.getDenominator() == 10;

		RationalNumber num3 = new RationalNumber(-5, -20);
		assert num3.getNumerator() == 1 && num3.getDenominator() == 4;

		RationalNumber sum = num.add(new RationalNumber(1, 3));
		assert sum.getNumerator() == 5 && sum.getDenominator() == 6;

		RationalNumber difference = num.subtract(new RationalNumber(1, 3));
		assert difference.getNumerator() == 1 && difference.getDenominator() == 6;

		RationalNumber product = num.multiply(new RationalNumber(1, 5));
		assert product.getNumerator() == 1 && product.getDenominator() == 10;

		RationalNumber quotient = num.divide(new RationalNumber(1, 5));
		assert quotient.getNumerator() == 5 && quotient.getDenominator() == 2;

		RationalNumber power = num.exponentiate(5);
		assert power.getNumerator() == 1 && power.getDenominator() == 32;

		RationalNumber power2 = (new RationalNumber(-2, 3)).exponentiate(4);
		assert power2.getNumerator() == 16 && power2.getDenominator() == 81;

		assert num.equals(new RationalNumber(5, 10));
		assert !num.equals(new RationalNumber(5, 11));
		assert num.compareTo(new RationalNumber(5, 9)) == -1;
		assert num.compareTo(new RationalNumber(5, 10)) == 0;
		assert num.compareTo(new RationalNumber(5, 11)) == 1;
	}
}