CREATE TABLE User (
  id INT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE Post (
  id INT AUTO_INCREMENT PRIMARY KEY,
  content TEXT NOT NULL,
  views INT NOT NULL DEFAULT 0,
  title VARCHAR(255) NOT NULL,
  comment_count INT NOT NULL DEFAULT 0,
  author_id INT,
  likes INT NOT NULL DEFAULT 0,
  dislikes INT NOT NULL DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (author_id) REFERENCES User(id)
);

CREATE TABLE Comment (
  id INT AUTO_INCREMENT PRIMARY KEY,
  post_id INT,
  author_id INT,
  content TEXT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  likes INT NOT NULL DEFAULT 0,
  dislikes INT NOT NULL DEFAULT 0,
  reply_count INT NOT NULL DEFAULT 0,
  FOREIGN KEY (post_id) REFERENCES Post(id),
  FOREIGN KEY (author_id) REFERENCES User(id)
);

CREATE TABLE PostCommentRelation (
  post_id INT,
  comment_id INT,
  UNIQUE (post_id, comment_id),
  FOREIGN KEY (post_id) REFERENCES Post(id),
  FOREIGN KEY (comment_id) REFERENCES Comment(id)
);
