package com.pdio.jhipsterapp.service.impl;

import com.pdio.jhipsterapp.service.MyEntityService;
import com.pdio.jhipsterapp.domain.MyEntity;
import com.pdio.jhipsterapp.repository.MyEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link MyEntity}.
 */
@Service
@Transactional
public class MyEntityServiceImpl implements MyEntityService {

    private final Logger log = LoggerFactory.getLogger(MyEntityServiceImpl.class);

    private final MyEntityRepository myEntityRepository;

    public MyEntityServiceImpl(MyEntityRepository myEntityRepository) {
        this.myEntityRepository = myEntityRepository;
    }

    /**
     * Save a myEntity.
     *
     * @param myEntity the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MyEntity save(MyEntity myEntity) {
        log.debug("Request to save MyEntity : {}", myEntity);
        return myEntityRepository.save(myEntity);
    }

    /**
     * Get all the myEntities.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MyEntity> findAll() {
        log.debug("Request to get all MyEntities");
        return myEntityRepository.findAll();
    }

    /**
     * Get one myEntity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MyEntity> findOne(Long id) {
        log.debug("Request to get MyEntity : {}", id);
        return myEntityRepository.findById(id);
    }

    /**
     * Delete the myEntity by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyEntity : {}", id);
        myEntityRepository.deleteById(id);
    }
}
