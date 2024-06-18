INSERT INTO role (code, name)
VALUES ('WRITER', 'Writer'),
       ('READER', 'Reader');

INSERT INTO application_user (username, email, password, role_code, bio)
VALUES ('author', 'author@mail.com', '$2a$10$y.rxCLAAPmGl0bMHQceMeuhCfCe3guESvvVIJeVlkdkw7GOIWYJB6', 'WRITER', 'The Writer');

INSERT INTO category (name)
VALUES ('Technology'),
       ('Health'),
       ('Lifestyle');
