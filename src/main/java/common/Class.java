package common;

/**
 * Created by ghazal on 2/10/2017 AD.
 */
public class Class {
    private char name;
    private int price;
    private char available; // A: more than 8 chairs available
                            // C: full
                            // 0 < number <= 8
    public Class(char _name,char _available,int _price){
        name=_name;
        price=_price;
        available=_available;
    }
}
