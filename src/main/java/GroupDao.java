import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;



public class GroupDao implements Dao<GroupForUp>{

    private EntityManager manager;

    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(GroupForUp groupForUp) {
        manager.persist(groupForUp);
    }

    @Override
    public void update(GroupForUp groupForUp) {
        manager.merge(groupForUp);
    }

    public List<GroupForUp> getMountainGroup(String name){
        List<GroupForUp> groupList;
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<GroupForUp> criteriaQuery = builder.createQuery(GroupForUp.class);
        Root<GroupForUp> root = criteriaQuery.from(GroupForUp.class);
        Predicate condition = builder.equal(root.get("mountain").get("nameMountain"),name);
        criteriaQuery.select(root).where(condition);

        TypedQuery<GroupForUp> query = manager.createQuery(criteriaQuery);
        groupList = query.getResultList();
        return groupList;
    }

    public List<GroupForUp> getGroupTrue(Boolean t){
        List<GroupForUp> groupList;
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<GroupForUp> criteriaQuery = builder.createQuery(GroupForUp.class);
        Root<GroupForUp> root = criteriaQuery.from(GroupForUp.class);
        Predicate condition = builder.equal(root.get(GroupForUp_.access), t);
        criteriaQuery.select(root).where(condition);
        TypedQuery<GroupForUp> query = manager.createQuery(criteriaQuery);
        groupList = query.getResultList();
        return groupList;
    }

    public List<GroupForUp> getAll(){
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<GroupForUp> criteriaQuery =
                criteriaBuilder.createQuery(GroupForUp.class);
        Root<GroupForUp> root = criteriaQuery.from(GroupForUp.class);
        criteriaQuery.select(root);
        TypedQuery<GroupForUp> query1 = manager.createQuery(criteriaQuery);
        List<GroupForUp> groups = query1.getResultList();
        return  groups;
    }

    public LocalDate getMountainGroup1(int id){
        LocalDate date;
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<GroupForUp> criteriaQuery = builder.createQuery(GroupForUp.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);
        criteriaQuery.where(builder.equal(root.get(Climber_.id), id));
        Join<Climber, GroupForUp> join = root.join(Climber_.groupForUp, JoinType.LEFT);
        Query query = manager.createQuery(criteriaQuery.select(join).select(join.get("dateLength")));
        try {
            date = (LocalDate) query.getSingleResult();
            return date;
        } catch (NoResultException e){
            return LocalDate.now();
        }


    }


}
