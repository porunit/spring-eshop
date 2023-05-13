ALTER TABLE purchases DROP COLUMN purchase_date;

ALTER TABLE purchases RENAME TO buckets;

ALTER TABLE purchased_items DROP COLUMN product_count;

ALTER TABLE purchased_items RENAME COLUMN purchase_id TO bucket_id;

