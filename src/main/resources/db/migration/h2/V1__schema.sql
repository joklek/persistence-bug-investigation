CREATE TABLE parent
(
    id         UUID          NOT NULL,
    CONSTRAINT PK__parent PRIMARY KEY (id)
);

CREATE TABLE child
(
    id             UUID        NOT NULL,
    parent_id UUID        NOT NULL,
    CONSTRAINT PK__child PRIMARY KEY (id),
    CONSTRAINT FK__child_parent FOREIGN KEY (parent_id) REFERENCES parent (id)
);

INSERT INTO parent (id)
VALUES ('8651ae1c-38ea-40a4-9d4e-68ac2049039d');

INSERT INTO child(id, parent_id)
VALUES ('565de4cb-dc24-491e-a5e0-e065d082cb22', '8651ae1c-38ea-40a4-9d4e-68ac2049039d'),
       ('d9902cdc-8000-4c4a-b4c1-5023c0b2e58c', '8651ae1c-38ea-40a4-9d4e-68ac2049039d');
