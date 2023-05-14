ALTER TABLE users ADD COLUMN bucket_id INTEGER;
ALTER TABLE users ADD CONSTRAINT fk_bucket_id FOREIGN KEY (bucket_id) REFERENCES buckets(id);
