package com.pdio.jhipsterapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A MyEntity.
 */
@Entity
@Table(name = "my_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jh_pole_id")
    private Integer jhPoleID;

    @Column(name = "jh_pole_nazwa")
    private String jhPoleNazwa;

    @Column(name = "jh_jakas_liczba")
    private Float jhJakasLiczba;

    @Column(name = "jh_data")
    private LocalDate jhData;

    @Column(name = "jh_boolean")
    private Boolean jhBoolean;

    @Column(name = "jh_long")
    private Long jhLong;

    @Column(name = "jh_big_decimal", precision = 21, scale = 2)
    private BigDecimal jhBigDecimal;

    @Column(name = "jh_double")
    private Double jhDouble;

    @Column(name = "jh_zoned_dt")
    private ZonedDateTime jhZonedDT;

    @Column(name = "jh_instant")
    private Instant jhInstant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJhPoleID() {
        return jhPoleID;
    }

    public MyEntity jhPoleID(Integer jhPoleID) {
        this.jhPoleID = jhPoleID;
        return this;
    }

    public void setJhPoleID(Integer jhPoleID) {
        this.jhPoleID = jhPoleID;
    }

    public String getJhPoleNazwa() {
        return jhPoleNazwa;
    }

    public MyEntity jhPoleNazwa(String jhPoleNazwa) {
        this.jhPoleNazwa = jhPoleNazwa;
        return this;
    }

    public void setJhPoleNazwa(String jhPoleNazwa) {
        this.jhPoleNazwa = jhPoleNazwa;
    }

    public Float getJhJakasLiczba() {
        return jhJakasLiczba;
    }

    public MyEntity jhJakasLiczba(Float jhJakasLiczba) {
        this.jhJakasLiczba = jhJakasLiczba;
        return this;
    }

    public void setJhJakasLiczba(Float jhJakasLiczba) {
        this.jhJakasLiczba = jhJakasLiczba;
    }

    public LocalDate getJhData() {
        return jhData;
    }

    public MyEntity jhData(LocalDate jhData) {
        this.jhData = jhData;
        return this;
    }

    public void setJhData(LocalDate jhData) {
        this.jhData = jhData;
    }

    public Boolean isJhBoolean() {
        return jhBoolean;
    }

    public MyEntity jhBoolean(Boolean jhBoolean) {
        this.jhBoolean = jhBoolean;
        return this;
    }

    public void setJhBoolean(Boolean jhBoolean) {
        this.jhBoolean = jhBoolean;
    }

    public Long getJhLong() {
        return jhLong;
    }

    public MyEntity jhLong(Long jhLong) {
        this.jhLong = jhLong;
        return this;
    }

    public void setJhLong(Long jhLong) {
        this.jhLong = jhLong;
    }

    public BigDecimal getJhBigDecimal() {
        return jhBigDecimal;
    }

    public MyEntity jhBigDecimal(BigDecimal jhBigDecimal) {
        this.jhBigDecimal = jhBigDecimal;
        return this;
    }

    public void setJhBigDecimal(BigDecimal jhBigDecimal) {
        this.jhBigDecimal = jhBigDecimal;
    }

    public Double getJhDouble() {
        return jhDouble;
    }

    public MyEntity jhDouble(Double jhDouble) {
        this.jhDouble = jhDouble;
        return this;
    }

    public void setJhDouble(Double jhDouble) {
        this.jhDouble = jhDouble;
    }

    public ZonedDateTime getJhZonedDT() {
        return jhZonedDT;
    }

    public MyEntity jhZonedDT(ZonedDateTime jhZonedDT) {
        this.jhZonedDT = jhZonedDT;
        return this;
    }

    public void setJhZonedDT(ZonedDateTime jhZonedDT) {
        this.jhZonedDT = jhZonedDT;
    }

    public Instant getJhInstant() {
        return jhInstant;
    }

    public MyEntity jhInstant(Instant jhInstant) {
        this.jhInstant = jhInstant;
        return this;
    }

    public void setJhInstant(Instant jhInstant) {
        this.jhInstant = jhInstant;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyEntity)) {
            return false;
        }
        return id != null && id.equals(((MyEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MyEntity{" +
            "id=" + getId() +
            ", jhPoleID=" + getJhPoleID() +
            ", jhPoleNazwa='" + getJhPoleNazwa() + "'" +
            ", jhJakasLiczba=" + getJhJakasLiczba() +
            ", jhData='" + getJhData() + "'" +
            ", jhBoolean='" + isJhBoolean() + "'" +
            ", jhLong=" + getJhLong() +
            ", jhBigDecimal=" + getJhBigDecimal() +
            ", jhDouble=" + getJhDouble() +
            ", jhZonedDT='" + getJhZonedDT() + "'" +
            ", jhInstant='" + getJhInstant() + "'" +
            "}";
    }
}
