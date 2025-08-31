-- This table stores the record of an overall submission attempt by a user for a contest.
CREATE TABLE submissions (
    id VARCHAR(36) PRIMARY KEY,
    contest_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    score INT NOT NULL,
    submitted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- A user should ideally only submit once per contest.
    UNIQUE (contest_id, user_id)
);

-- This table stores each individual answer within a single submission.
CREATE TABLE submission_answers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    submission_id VARCHAR(36) NOT NULL,
    question_id INT NOT NULL,
    submitted_answer VARCHAR(255) NOT NULL,

    -- Foreign key to link back to the parent submission record.
    FOREIGN KEY (submission_id) REFERENCES submissions(id) ON DELETE CASCADE
);

-- Create indexes for faster lookups.
CREATE INDEX idx_submissions_contest_id ON submissions(contest_id);
CREATE INDEX idx_submissions_user_id ON submissions(user_id);