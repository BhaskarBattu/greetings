CREATE SCHEMA `greetings` ;

use `greetings` ;

drop table if exists `user_data`;
CREATE TABLE `user_data` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `email` varchar(150) DEFAULT NULL,
   `created_timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
   `active_flag` int(2) DEFAULT '0',
   `created_by` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `idx_user_data_id` (`id`),
   KEY `idx_user_data_email` (`email`),
   KEY `idx_user_registration_data_active_flag` (`active_flag`)
 ) ;
 
 
 drop table if exists `user_registration_data`;
 CREATE TABLE `user_registration_data` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `email` varchar(150) DEFAULT NULL,
   `name` varchar(150) DEFAULT NULL,
   `password` varchar(150) DEFAULT NULL,
   `dob` date DEFAULT NULL,
   `photo` varchar(45) DEFAULT NULL,
   `tag_name` varchar(145) DEFAULT NULL,
   `requested_timestamp` datetime DEFAULT NULL,
   `created_timestamp` datetime DEFAULT NULL,
   `active_flag` int(2) DEFAULT '0',
   `created_by` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `idx_user_registration_data_id` (`id`),
   KEY `idx_user_registration_data_email` (`email`),
   KEY `idx_user_registration_data_password` (`password`),
   KEY `idx_user_registration_data_tag_name` (`tag_name`),
   KEY `idx_user_registration_data_active_flag` (`active_flag`)
 ) ;
 
 
  drop table if exists `wishes_data`;
 CREATE TABLE `wishes_data` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `user_reg_id` int(11) DEFAULT NULL,
   `to_email` varchar(150) DEFAULT NULL,
   `bday_date` date DEFAULT NULL,
   `greeting` blob,
   `created_timestamp` datetime DEFAULT NULL,
   `active_flag` int(2) DEFAULT '0',
   PRIMARY KEY (`id`),
   KEY `idx_wishes_data_data_id` (`id`),
   KEY `idx_wishes_data_user_reg_id` (`user_reg_id`),
   KEY `idx_wishes_to_mail_id` (`to_email`),
   KEY `idx_wishes_data_active_flag` (`active_flag`,`created_timestamp`),
   KEY `idx_wishes_data_bday_date` (`bday_date`)
 ) ;