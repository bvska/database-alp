import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MountainDao implements Dao<Mountain>{

    private EntityManager manager;

    public MountainDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Mountain mountain) {
        manager.persist(mountain);
    }

    @Override
    public void update(Mountain mountain) {
        manager.merge(mountain);

    }

    public List<Mountain> getMountain(String name){
        List<Mountain> mountainList = new ArrayList<>();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> criteriaQuery = builder.createQuery(Mountain.class);
        Root<Mountain> root = criteriaQuery.from(Mountain.class);
        Predicate condition = builder.equal(root.get(Mountain_.countryMountain), name);
        criteriaQuery.select(root).where(condition);
        TypedQuery<Mountain> query = manager.createQuery(criteriaQuery);
        mountainList = query.getResultList();
        return mountainList;
    }
}
