//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class Test {
//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
//    EntityManager manager = factory.createEntityManager();
//    ClimberDao climberDao = new ClimberDao(manager);
//    GroupDao groupDao = new GroupDao(manager);
//
//
//    public boolean getValid(Climber climber){
//       int id;
//        id = climberDao.getNam(climber);
//        if (groupDao.getMountainGroup1(id) != null) return false;
//        return true;
//    }
//
//
//
//}
