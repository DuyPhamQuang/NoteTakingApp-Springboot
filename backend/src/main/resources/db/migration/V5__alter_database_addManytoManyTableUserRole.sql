CREATE TABLE IF NOT EXISTS user_roles (
  id int NOT NULL PRIMARY KEY,
  user_id int REFERENCES users(id),
  role_id int REFERENCES roles(id)
);