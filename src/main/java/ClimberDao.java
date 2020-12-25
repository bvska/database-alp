

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClimberDao implements Dao<Climber> {

    private EntityManager manager;

    public ClimberDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Climber climber) {
        manager.persist(climber);

    }

    @Override
    public void update(Climber climber) {
        manager.merge(climber);
    }

    public List<Climber> getAge(int i, int j){
        List<Climber> climberList = new ArrayList<>();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery = builder.createQuery(Climber.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);
        Predicate condition = builder.between(root.get(Climber_.age), i, j);
        criteriaQuery.select(root).where(condition);
        TypedQuery<Climber> query = manager.createQuery(criteriaQuery);
        climberList = query.getResultList();
        return climberList;
    }

    public int getNam(Climber climber){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery = builder.createQuery(Climber.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);
        Predicate condition = builder.equal(root.get(Climber_.name), climber.getName());
        Predicate condition1 = builder.equal(root.get(Climber_.address), climber.getAddress());
        Predicate and = builder.and(condition, condition1);
        criteriaQuery.select(root).where(and);
        TypedQuery<Climber> query = manager.createQuery(criteriaQuery);
        try {
            int i = query.getSingleResult().getId();
            return i;
        } catch (NoResultException e){
            return 0;
        }
    }
}
