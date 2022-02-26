package com.joklek.persistencebug;

import com.joklek.persistencebug.entity.ParentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParentRepository extends CrudRepository<ParentEntity, UUID> {

    @Override
    @EntityGraph(attributePaths = {"children"})
    Optional<ParentEntity> findById(UUID uuid);
}
