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
