import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();
        ClimberDao climberDao = new ClimberDao(manager);
        GroupDao groupDao = new GroupDao(manager);
        MountainDao mountainDao= new MountainDao(manager);

        Climber vasia = new Climber("Вася", "где то где то", 46);
        Climber petr = new Climber("Петр", "где то там", 34);
        Climber anya = new Climber("Аня", "там то", 26);
        Climber sonya = new Climber("Соня", "а тут не скажу", 43);
        Climber lena = new Climber("Лена", "туточки", 19);
        Climber lena1 = new Climber("Лена", "а не тааам", 24);
         Mountain kavkaz = new Mountain("Эльбрус", "Россия", 5200 );
        Mountain ural = new Mountain("ГораУрал", "Россия", 3600 );

        LocalDate start1 = LocalDate.of(2021, Month.JANUARY, 20);
        LocalDate end1 = LocalDate.of(2021, Month.JANUARY, 26);
        LocalDate start2 = LocalDate.of(2021, Month.APRIL, 13);
        LocalDate end2 = LocalDate.of(2021, Month.APRIL, 21);

        GroupForUp group = new GroupForUp(kavkaz, true, start1, end1);
        GroupForUp group2 = new GroupForUp(ural, false, start2, end2);
        GroupForUp group3 = new GroupForUp(ural, true, start1, end1);
        System.out.println(group.toString());
        group.setClimberList(lena);

        manager.getTransaction().begin();;
        mountainDao.add(kavkaz);
        mountainDao.add(ural);
        groupDao.add(group);
        groupDao.add(group2);
        groupDao.add(group3);
        climberDao.add(lena1);
        climberDao.add(vasia);
        climberDao.add(petr);
        climberDao.add(anya);
        climberDao.add(sonya);
        climberDao.add(lena);


        manager.getTransaction().commit();


       System.out.println(climberDao.getAge(24, 45));
        System.out.println(groupDao.getGroupTrue(true));
        System.out.println(mountainDao.getMountain("Россия"));
        System.out.println(groupDao.getAll());
      System.out.println(groupDao.getMountainGroup("Эльбрус"));




    }


}
