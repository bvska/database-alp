import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroupForUp extends BaseIdentity {



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Mountain mountain;


    @ManyToMany
    @JoinTable(name = "st_climber", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "clim_id"))
    private List<Climber> climberList = new ArrayList<>();

    @Column(nullable = false)
    private boolean access;

    @Column(nullable = false)
    private LocalDate dateUp;

    @Column(nullable = false)
    private LocalDate dateLength;

    public GroupForUp() {
    }

    public GroupForUp(Mountain mountain, boolean access, LocalDate dateUp, LocalDate dateLength) {
        this.mountain = mountain;
        setAccess(access);
        setDateUp(dateUp);
        setDateLength(dateLength);

    }

    public Mountain getMountain() {
        return mountain;
    }

    public List<Climber> getClimberList() {
        return climberList;
    }

    public void setClimberList(Climber name) {
        if (getAccess()) {
            Test test = new Test();
            if (getDateUp().isAfter(test.getValid(name))) {
                climberList.add(name);
            }
            else {
            System.out.println("в это время вы еще в походе.");}
        }
    }

    public boolean getAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public LocalDate getDateUp() {
        return dateUp;
    }

    public void setDateUp(LocalDate dateUp) {
        if (dateUp.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("не может начаться раньше, чем сейчас");

        this.dateUp = dateUp;
    }

    public LocalDate getDateLength() {
        return dateLength;
    }

    public void setDateLength(LocalDate dateLength) {
        if (dateLength.isBefore(LocalDate.now().plusDays(5))) throw new IllegalArgumentException("не менее 5ти дней");
        this.dateLength = dateLength;
    }

    @Override
    public String toString() {
        return "GroupForUp{" + mountain +
                ", climberList=" + climberList +
                ", access=" + access +
                ", dateUp=" + dateUp +
                ", dateLength=" + dateLength +
                '}';
    }
}
