package com.joklek.persistencebug;

import com.joklek.persistencebug.entity.ChildEntity;
import com.joklek.persistencebug.entity.ParentEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ParentService {

    private final EntityManager em;

    public ParentService(EntityManager em) {
        this.em = em;
    }

    public ParentEntity getParent(UUID parentId) {
        return em.createQuery(
                        "SELECT p " +
                                "from ParentEntity p " +
                                "JOIN FETCH p.children " +
                                "WHERE p.id = :parentId", ParentEntity.class)
                .setParameter("parentId", parentId)
                .getSingleResult();
    }

    @Transactional
    public ParentEntity resetChildren(UUID parentId) {
        var parent = getParent(parentId);

        parent.getChildren().clear();
        addChildren(parent, 2);

        em.persist(parent);
        return parent;
    }

    private void addChildren(ParentEntity parent, int childCount) {
        for (var i = 0; i < childCount; i++) {
            parent.addChildren(new ChildEntity());
        }
    }
}
