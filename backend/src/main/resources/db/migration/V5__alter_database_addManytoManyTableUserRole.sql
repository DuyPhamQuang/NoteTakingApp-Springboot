CREATE TABLE IF NOT EXISTS user_roles (
  id BIGSERIAL PRIMARY KEY,
  user_id bigint REFERENCES users(id),
  role_id int REFERENCES roles(id)
);