package com.pdio.jhipsterapp.service;

import com.pdio.jhipsterapp.domain.MyEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link MyEntity}.
 */
public interface MyEntityService {

    /**
     * Save a myEntity.
     *
     * @param myEntity the entity to save.
     * @return the persisted entity.
     */
    MyEntity save(MyEntity myEntity);

    /**
     * Get all the myEntities.
     *
     * @return the list of entities.
     */
    List<MyEntity> findAll();

    /**
     * Get the "id" myEntity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MyEntity> findOne(Long id);

    /**
     * Delete the "id" myEntity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
