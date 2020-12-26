import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Test {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
    EntityManager manager = factory.createEntityManager();
    ClimberDao climberDao = new ClimberDao(manager);
    GroupDao groupDao = new GroupDao(manager);


    public LocalDate getValid(Climber climber){
       int id;
        id = climberDao.getNam(climber);
        groupDao.getMountainGroup1(id);
       return groupDao.getMountainGroup1(id);

    }



}
