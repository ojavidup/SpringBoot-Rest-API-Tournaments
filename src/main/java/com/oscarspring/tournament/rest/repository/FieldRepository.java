package com.oscarspring.tournament.rest.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oscarspring.tournament.rest.entity.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Serializable> {

}
