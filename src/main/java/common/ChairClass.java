package common;

/**
 * Created by ghazal on 2/10/2017 AD.
 */
public class ChairClass {
    private char name;
    private int price;
    private char available; // A: more than 8 chairs available
                            // C: full
                            // 0 < number <= 8
    public ChairClass(char _name,char _available){
        name=_name;
        available=_available;
    }
    public char get_num_of_availables() { return available; }
}
