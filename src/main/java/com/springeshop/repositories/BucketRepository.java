package com.springeshop.repositories;

import com.springeshop.data.domain.Bucket;
import com.springeshop.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {


}
