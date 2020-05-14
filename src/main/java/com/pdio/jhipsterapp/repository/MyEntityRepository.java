package com.pdio.jhipsterapp.repository;

import com.pdio.jhipsterapp.domain.MyEntity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MyEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
}
