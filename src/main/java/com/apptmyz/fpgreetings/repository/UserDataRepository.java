/*
 * Created on 11 Oct 2022 ( Time 22:31:58 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.apptmyz.fpgreetings.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.apptmyz.fpgreetings.entity.UserData;

/**
 * Spring JPA Repository for UserData
 * 
 * @author Telosys Tools Generator
 *
 */
public interface UserDataRepository extends CrudRepository<UserData, Integer> {

	List<UserData> findByCreatedTimestampBetweenAndActiveFlag(Date fromDate, Date toDate, int activeFlag);
	
}