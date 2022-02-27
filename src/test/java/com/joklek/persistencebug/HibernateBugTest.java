package com.joklek.persistencebug;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HibernateBugTest {

    @Autowired
    protected ParentService service;

    @Test
    void whenClearingChildrenAndAddingNew__shouldPersistAll() {
        var parentId = UUID.fromString("8651ae1c-38ea-40a4-9d4e-68ac2049039d");

        var response = this.service.resetChildren(parentId);
        var responseAfter = this.service.getParent(parentId);

        assertThat(response.getId()).isEqualTo(parentId);
        assertThat(responseAfter.getChildren())
                .hasSameSizeAs(response.getChildren());
    }
}
