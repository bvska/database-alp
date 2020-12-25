import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mountain extends  BaseIdentity{

    @Column(nullable = false)
    private String nameMountain;
    @Column (nullable = false)
    private String countryMountain;
    @Column (nullable = false)
    private int heightMountain;
    @OneToMany (mappedBy = "mountain")
    List<GroupForUp> groupForUpList = new ArrayList<>();

    public Mountain(String nameMountain, String countryMountain, int heightMountain) {
       setCountryMountain(countryMountain);
       setNameMountain(nameMountain);
       setHeightMountain(heightMountain);
    }

    public String getNameMountain() {
        return nameMountain;
    }

    public void setNameMountain(String nameMountain) {
        if (nameMountain == null || nameMountain.trim().length() < 4) throw new IllegalArgumentException("адрес не менее 4х символов");
        this.nameMountain = nameMountain;
    }

    public String getCountryMountain() {
        return countryMountain;
    }

    public void setCountryMountain(String countryMountain) {
        if (countryMountain == null || countryMountain.trim().length() < 4) throw new IllegalArgumentException("адрес не менее 4х символов");
        this.countryMountain = countryMountain;
    }

    public int getHeightMountain() {
        return heightMountain;
    }

    public void setHeightMountain(int heightMountain) {
        if (heightMountain < 100) throw new IllegalArgumentException("гора должа быть, а не кочка");
        this.heightMountain = heightMountain;
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "nameMountain='" + nameMountain + '\'' +
                ", countryMountain='" + countryMountain + '\'' +
                ", heightMountain=" + heightMountain +
                '}';
    }
}
