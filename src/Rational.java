/** A class to represent a rational number
    with a numerator and denominator

    @author P. Conrad for CS56 F16

    */

public class Rational {

    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
	if (a==0)
	    return b;
	else if (b==0)
	    return a;
	else
	    return gcd(b%a, a);
    }
    
    /**
    least common multiple of a and b
    @param a first number
    @param b second number
    @return lcm of a and b
    */
    public static int lcm(int a, int b){
        if (a==0 || b==0)
            return 0;
        return Math.abs(a*b) / gcd(a,b);
    }


    public Rational plus(Rational r){
        int cm= lcm(this.getDenominator(), r.getDenominator());
        int mulSelf = cm/ (this.getDenominator());
        int mulR = cm/(r.getDenominator());
        Rational tmp = new Rational(this.getNumerator()*mulSelf+r.getNumerator()*mulR, r.getDenominator()*mulR);
        return tmp;
    }

    public static Rational sum(Rational a, Rational b){
        int cm= lcm(a.getDenominator(), b.getDenominator());
        int mulA = cm/ (a.getDenominator());
        int mulB = cm/(b.getDenominator());
        Rational tmp = new Rational(a.getNumerator()*mulA+b.getNumerator()*mulB, a.getDenominator()*mulA);
        return tmp;
    }

    public Rational minus(Rational r){
        /*Rational tmp = new Rational(this.getNumerator()-r.getNumerator(), this.getDenominator()-r.getDenominator());
        return tmp;*/
        int cm= lcm(this.getDenominator(), r.getDenominator());
        int mulSelf = cm/ (this.getDenominator());
        int mulR = cm/(r.getDenominator());
        Rational tmp = new Rational(this.getNumerator()*mulSelf-r.getNumerator()*mulR, r.getDenominator()*mulR);
        return tmp;
    }

    public static Rational difference(Rational a, Rational b){
        /*Rational tmp = new Rational(a.getNumerator()-b.getNumerator(), this.getDenominator()+r.getDenominator());
        return tmp;*/
        int cm= lcm(a.getDenominator(), b.getDenominator());
        int mulA = cm/ (a.getDenominator());
        int mulB = cm/(b.getDenominator());
        Rational tmp = new Rational(a.getNumerator()*mulA-b.getNumerator()*mulB, a.getDenominator()*mulA);
        return tmp;
    }

    public Rational reciprocalOf(){
    if (this.num== 0) {
	    throw new ArithmeticException();
    }
    Rational tmp = new Rational(this.denom,this.num);
    return tmp;
    }


    public Rational dividedBy(Rational r){
        Rational tmp= r.reciprocalOf();
        return product(this,tmp);
    }

    public static Rational quotient(Rational a, Rational b){
        return product(a,b.reciprocalOf());
    }


    public Rational() {
	this.num = 1;
	this.denom = 1;
    }

    public Rational(int num, int denom) {
	if (denom== 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	if (num != 0) {
	    int gcd = Rational.gcd(num,denom);
	    this.num /= gcd;
	    this.denom /= gcd;
	}
    }

    /*
    public String toString() {
	if (denom == 1 || num == 0)
	    return "" + num;
	return num + "/" + denom;
    }
    */
    
    public String toString() {//fixed
	if (denom == 1 || num == 0){
	    return "" + num;
	}
	if(denom < 0){
		return (-num) + "/" + (-denom);
	}
	else{
	return num + "/" + denom;
	}
    }



    public int getNumerator() { return this.num; }
    public int getDenominator() { return this.denom; }

    public Rational times(Rational r) {
	return new Rational(this.num * r.num,
			    this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
	return new Rational(a.num * b.num,
			    a.denom * b.denom);
    }

    
    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
	Rational r = new Rational(5,7);
	System.out.println("r.getNumerator()=" + r.getNumerator());
	System.out.println("r.getDenominator()=" + r.getDenominator());
    }

    
}
