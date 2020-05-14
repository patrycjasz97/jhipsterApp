package com.pdio.jhipsterapp.web.rest;

import com.pdio.jhipsterapp.JhipsterApp;
import com.pdio.jhipsterapp.domain.MyEntity;
import com.pdio.jhipsterapp.repository.MyEntityRepository;
import com.pdio.jhipsterapp.service.MyEntityService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.pdio.jhipsterapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MyEntityResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MyEntityResourceIT {

    private static final Integer DEFAULT_JH_POLE_ID = 1;
    private static final Integer UPDATED_JH_POLE_ID = 2;

    private static final String DEFAULT_JH_POLE_NAZWA = "AAAAAAAAAA";
    private static final String UPDATED_JH_POLE_NAZWA = "BBBBBBBBBB";

    private static final Float DEFAULT_JH_JAKAS_LICZBA = 1F;
    private static final Float UPDATED_JH_JAKAS_LICZBA = 2F;

    private static final LocalDate DEFAULT_JH_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JH_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_JH_BOOLEAN = false;
    private static final Boolean UPDATED_JH_BOOLEAN = true;

    private static final Long DEFAULT_JH_LONG = 1L;
    private static final Long UPDATED_JH_LONG = 2L;

    private static final BigDecimal DEFAULT_JH_BIG_DECIMAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_JH_BIG_DECIMAL = new BigDecimal(2);

    private static final Double DEFAULT_JH_DOUBLE = 1D;
    private static final Double UPDATED_JH_DOUBLE = 2D;

    private static final ZonedDateTime DEFAULT_JH_ZONED_DT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_JH_ZONED_DT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Instant DEFAULT_JH_INSTANT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JH_INSTANT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private MyEntityRepository myEntityRepository;

    @Autowired
    private MyEntityService myEntityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMyEntityMockMvc;

    private MyEntity myEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MyEntity createEntity(EntityManager em) {
        MyEntity myEntity = new MyEntity()
            .jhPoleID(DEFAULT_JH_POLE_ID)
            .jhPoleNazwa(DEFAULT_JH_POLE_NAZWA)
            .jhJakasLiczba(DEFAULT_JH_JAKAS_LICZBA)
            .jhData(DEFAULT_JH_DATA)
            .jhBoolean(DEFAULT_JH_BOOLEAN)
            .jhLong(DEFAULT_JH_LONG)
            .jhBigDecimal(DEFAULT_JH_BIG_DECIMAL)
            .jhDouble(DEFAULT_JH_DOUBLE)
            .jhZonedDT(DEFAULT_JH_ZONED_DT)
            .jhInstant(DEFAULT_JH_INSTANT);
        return myEntity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MyEntity createUpdatedEntity(EntityManager em) {
        MyEntity myEntity = new MyEntity()
            .jhPoleID(UPDATED_JH_POLE_ID)
            .jhPoleNazwa(UPDATED_JH_POLE_NAZWA)
            .jhJakasLiczba(UPDATED_JH_JAKAS_LICZBA)
            .jhData(UPDATED_JH_DATA)
            .jhBoolean(UPDATED_JH_BOOLEAN)
            .jhLong(UPDATED_JH_LONG)
            .jhBigDecimal(UPDATED_JH_BIG_DECIMAL)
            .jhDouble(UPDATED_JH_DOUBLE)
            .jhZonedDT(UPDATED_JH_ZONED_DT)
            .jhInstant(UPDATED_JH_INSTANT);
        return myEntity;
    }

    @BeforeEach
    public void initTest() {
        myEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createMyEntity() throws Exception {
        int databaseSizeBeforeCreate = myEntityRepository.findAll().size();

        // Create the MyEntity
        restMyEntityMockMvc.perform(post("/api/my-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(myEntity)))
            .andExpect(status().isCreated());

        // Validate the MyEntity in the database
        List<MyEntity> myEntityList = myEntityRepository.findAll();
        assertThat(myEntityList).hasSize(databaseSizeBeforeCreate + 1);
        MyEntity testMyEntity = myEntityList.get(myEntityList.size() - 1);
        assertThat(testMyEntity.getJhPoleID()).isEqualTo(DEFAULT_JH_POLE_ID);
        assertThat(testMyEntity.getJhPoleNazwa()).isEqualTo(DEFAULT_JH_POLE_NAZWA);
        assertThat(testMyEntity.getJhJakasLiczba()).isEqualTo(DEFAULT_JH_JAKAS_LICZBA);
        assertThat(testMyEntity.getJhData()).isEqualTo(DEFAULT_JH_DATA);
        assertThat(testMyEntity.isJhBoolean()).isEqualTo(DEFAULT_JH_BOOLEAN);
        assertThat(testMyEntity.getJhLong()).isEqualTo(DEFAULT_JH_LONG);
        assertThat(testMyEntity.getJhBigDecimal()).isEqualTo(DEFAULT_JH_BIG_DECIMAL);
        assertThat(testMyEntity.getJhDouble()).isEqualTo(DEFAULT_JH_DOUBLE);
        assertThat(testMyEntity.getJhZonedDT()).isEqualTo(DEFAULT_JH_ZONED_DT);
        assertThat(testMyEntity.getJhInstant()).isEqualTo(DEFAULT_JH_INSTANT);
    }

    @Test
    @Transactional
    public void createMyEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = myEntityRepository.findAll().size();

        // Create the MyEntity with an existing ID
        myEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMyEntityMockMvc.perform(post("/api/my-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(myEntity)))
            .andExpect(status().isBadRequest());

        // Validate the MyEntity in the database
        List<MyEntity> myEntityList = myEntityRepository.findAll();
        assertThat(myEntityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMyEntities() throws Exception {
        // Initialize the database
        myEntityRepository.saveAndFlush(myEntity);

        // Get all the myEntityList
        restMyEntityMockMvc.perform(get("/api/my-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(myEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].jhPoleID").value(hasItem(DEFAULT_JH_POLE_ID)))
            .andExpect(jsonPath("$.[*].jhPoleNazwa").value(hasItem(DEFAULT_JH_POLE_NAZWA)))
            .andExpect(jsonPath("$.[*].jhJakasLiczba").value(hasItem(DEFAULT_JH_JAKAS_LICZBA.doubleValue())))
            .andExpect(jsonPath("$.[*].jhData").value(hasItem(DEFAULT_JH_DATA.toString())))
            .andExpect(jsonPath("$.[*].jhBoolean").value(hasItem(DEFAULT_JH_BOOLEAN.booleanValue())))
            .andExpect(jsonPath("$.[*].jhLong").value(hasItem(DEFAULT_JH_LONG.intValue())))
            .andExpect(jsonPath("$.[*].jhBigDecimal").value(hasItem(DEFAULT_JH_BIG_DECIMAL.intValue())))
            .andExpect(jsonPath("$.[*].jhDouble").value(hasItem(DEFAULT_JH_DOUBLE.doubleValue())))
            .andExpect(jsonPath("$.[*].jhZonedDT").value(hasItem(sameInstant(DEFAULT_JH_ZONED_DT))))
            .andExpect(jsonPath("$.[*].jhInstant").value(hasItem(DEFAULT_JH_INSTANT.toString())));
    }
    
    @Test
    @Transactional
    public void getMyEntity() throws Exception {
        // Initialize the database
        myEntityRepository.saveAndFlush(myEntity);

        // Get the myEntity
        restMyEntityMockMvc.perform(get("/api/my-entities/{id}", myEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(myEntity.getId().intValue()))
            .andExpect(jsonPath("$.jhPoleID").value(DEFAULT_JH_POLE_ID))
            .andExpect(jsonPath("$.jhPoleNazwa").value(DEFAULT_JH_POLE_NAZWA))
            .andExpect(jsonPath("$.jhJakasLiczba").value(DEFAULT_JH_JAKAS_LICZBA.doubleValue()))
            .andExpect(jsonPath("$.jhData").value(DEFAULT_JH_DATA.toString()))
            .andExpect(jsonPath("$.jhBoolean").value(DEFAULT_JH_BOOLEAN.booleanValue()))
            .andExpect(jsonPath("$.jhLong").value(DEFAULT_JH_LONG.intValue()))
            .andExpect(jsonPath("$.jhBigDecimal").value(DEFAULT_JH_BIG_DECIMAL.intValue()))
            .andExpect(jsonPath("$.jhDouble").value(DEFAULT_JH_DOUBLE.doubleValue()))
            .andExpect(jsonPath("$.jhZonedDT").value(sameInstant(DEFAULT_JH_ZONED_DT)))
            .andExpect(jsonPath("$.jhInstant").value(DEFAULT_JH_INSTANT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMyEntity() throws Exception {
        // Get the myEntity
        restMyEntityMockMvc.perform(get("/api/my-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMyEntity() throws Exception {
        // Initialize the database
        myEntityService.save(myEntity);

        int databaseSizeBeforeUpdate = myEntityRepository.findAll().size();

        // Update the myEntity
        MyEntity updatedMyEntity = myEntityRepository.findById(myEntity.getId()).get();
        // Disconnect from session so that the updates on updatedMyEntity are not directly saved in db
        em.detach(updatedMyEntity);
        updatedMyEntity
            .jhPoleID(UPDATED_JH_POLE_ID)
            .jhPoleNazwa(UPDATED_JH_POLE_NAZWA)
            .jhJakasLiczba(UPDATED_JH_JAKAS_LICZBA)
            .jhData(UPDATED_JH_DATA)
            .jhBoolean(UPDATED_JH_BOOLEAN)
            .jhLong(UPDATED_JH_LONG)
            .jhBigDecimal(UPDATED_JH_BIG_DECIMAL)
            .jhDouble(UPDATED_JH_DOUBLE)
            .jhZonedDT(UPDATED_JH_ZONED_DT)
            .jhInstant(UPDATED_JH_INSTANT);

        restMyEntityMockMvc.perform(put("/api/my-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMyEntity)))
            .andExpect(status().isOk());

        // Validate the MyEntity in the database
        List<MyEntity> myEntityList = myEntityRepository.findAll();
        assertThat(myEntityList).hasSize(databaseSizeBeforeUpdate);
        MyEntity testMyEntity = myEntityList.get(myEntityList.size() - 1);
        assertThat(testMyEntity.getJhPoleID()).isEqualTo(UPDATED_JH_POLE_ID);
        assertThat(testMyEntity.getJhPoleNazwa()).isEqualTo(UPDATED_JH_POLE_NAZWA);
        assertThat(testMyEntity.getJhJakasLiczba()).isEqualTo(UPDATED_JH_JAKAS_LICZBA);
        assertThat(testMyEntity.getJhData()).isEqualTo(UPDATED_JH_DATA);
        assertThat(testMyEntity.isJhBoolean()).isEqualTo(UPDATED_JH_BOOLEAN);
        assertThat(testMyEntity.getJhLong()).isEqualTo(UPDATED_JH_LONG);
        assertThat(testMyEntity.getJhBigDecimal()).isEqualTo(UPDATED_JH_BIG_DECIMAL);
        assertThat(testMyEntity.getJhDouble()).isEqualTo(UPDATED_JH_DOUBLE);
        assertThat(testMyEntity.getJhZonedDT()).isEqualTo(UPDATED_JH_ZONED_DT);
        assertThat(testMyEntity.getJhInstant()).isEqualTo(UPDATED_JH_INSTANT);
    }

    @Test
    @Transactional
    public void updateNonExistingMyEntity() throws Exception {
        int databaseSizeBeforeUpdate = myEntityRepository.findAll().size();

        // Create the MyEntity

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMyEntityMockMvc.perform(put("/api/my-entities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(myEntity)))
            .andExpect(status().isBadRequest());

        // Validate the MyEntity in the database
        List<MyEntity> myEntityList = myEntityRepository.findAll();
        assertThat(myEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMyEntity() throws Exception {
        // Initialize the database
        myEntityService.save(myEntity);

        int databaseSizeBeforeDelete = myEntityRepository.findAll().size();

        // Delete the myEntity
        restMyEntityMockMvc.perform(delete("/api/my-entities/{id}", myEntity.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MyEntity> myEntityList = myEntityRepository.findAll();
        assertThat(myEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
