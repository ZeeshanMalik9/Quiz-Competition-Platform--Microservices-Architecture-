CREATE TABLE participants (
    id VARCHAR(36) PRIMARY KEY,
    contest_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    joined_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (contest_id) REFERENCES contests(id) ON DELETE CASCADE,


    UNIQUE (contest_id, user_id)
)