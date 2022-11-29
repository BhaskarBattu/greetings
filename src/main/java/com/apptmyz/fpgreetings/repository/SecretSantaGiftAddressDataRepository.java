/*
 * Created on 2022-11-27 ( Time 20:38:26 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

package com.apptmyz.fpgreetings.repository;

import com.apptmyz.fpgreetings.entity.SecretSantaGiftAddressData ;

import org.springframework.data.repository.CrudRepository;



/**
 * Spring JPA Repository for SecretSantaGiftAddressData
 * 
 * @author Telosys Tools Generator
 *
 */
public interface SecretSantaGiftAddressDataRepository extends CrudRepository<SecretSantaGiftAddressData, Integer> {

	SecretSantaGiftAddressData findByEmailAndGiftYear(String email, String year);
}