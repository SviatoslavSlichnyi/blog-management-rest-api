CREATE TABLE application_user
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    bio      TEXT
);

CREATE TABLE category
(
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE article
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    content       TEXT         NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    author_id     BIGINT,
    category_name VARCHAR(255),
    FOREIGN KEY (author_id) REFERENCES application_user (id),
    FOREIGN KEY (category_name) REFERENCES category (name)
);

CREATE TABLE comment
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    content    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author_id  BIGINT,
    article_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES application_user (id),
    FOREIGN KEY (article_id) REFERENCES article (id)
);

CREATE TABLE tag
(
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE article_tag
(
    article_id BIGINT,
    tag_name   VARCHAR(255),
    PRIMARY KEY (article_id, tag_name),
    FOREIGN KEY (article_id) REFERENCES article (id),
    FOREIGN KEY (tag_name) REFERENCES tag (name)
);