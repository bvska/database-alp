import javax.persistence.*;
import java.util.List;

@Entity
public class Climber extends BaseIdentity{


    @Column (nullable = false)
    private String name;
    @Column (nullable = false)
    private String address;
    @Column (nullable = false)
    private int age;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "climberList")
    private List<GroupForUp> groupForUp;

    public Climber() {
    }

    public Climber(String name, String address, int age) {
        setName(name);
        setAddress(address);
        setAge(age);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 3) throw new IllegalArgumentException("имя не менее 3х букв");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().length() < 5) throw new IllegalArgumentException("адрес не менее 5ти букв");
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18) throw new IllegalArgumentException("ерунда, есть альпинисты и моложе");
        this.age = age;
    }

    @Override
    public String toString() {
        return "Climber{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
