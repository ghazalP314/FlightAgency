package user;

/**
 * Created by ghazal on 2/11/2017 AD.
 */
public class Person {
    private String fname;
    private String sname;
    private String nID;
    private String age; // adult,child,infant

    public Person(String fn,String sn,String id,String _age) {
        fname=fn;
        sname=sn;
        nID=id;
        age=_age;
    }
    public String getAge() { return age; }
}
