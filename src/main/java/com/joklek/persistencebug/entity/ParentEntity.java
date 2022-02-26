package com.joklek.persistencebug.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "parent")
public class ParentEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChildEntity> children = new HashSet<>();

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<ChildEntity> getChildren() {
        return this.children;
    }

    public void setChildren(Set<ChildEntity> children) {
        this.children = children;
    }

    public void addChildren(ChildEntity child) {
        this.children.add(child);
        child.setParent(this);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var that = (ParentEntity) o;
        return this.id != null
                && Objects.equals(this.id, that.id);
    }
}
