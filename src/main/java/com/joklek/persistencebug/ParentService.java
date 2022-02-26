package com.joklek.persistencebug;

import com.joklek.persistencebug.entity.ChildEntity;
import com.joklek.persistencebug.entity.ParentEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ParentService {

    private final ParentRepository parentRepo;

    public ParentService(ParentRepository parentRepo) {
        this.parentRepo = parentRepo;
    }

    public ParentEntity getParent(UUID parentId) {
        return this.parentRepo.findById(parentId)
                .orElseThrow();
    }

    @Transactional
    public ParentEntity resetChildren(UUID parentId) {
        var parent = getParent(parentId);

        parent.getChildren().clear();
        addChildren(parent, 2);

        return this.parentRepo.save(parent);
    }

    private void addChildren(ParentEntity parent, int childCount) {
        for (var i = 0; i < childCount; i++) {
            createChild(parent);
        }
    }

    private ChildEntity createChild(ParentEntity parent) {
        var child = new ChildEntity();
        child.setParent(parent);
        parent.addChildren(child);
        return child;
    }
}
