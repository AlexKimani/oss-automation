drop database if exists oss_automation;

create database if not exists oss_automation;

use oss_automation;

DROP TABLE IF EXISTS films;

CREATE TABLE films (
                       `FILMID` bigint(20) NOT NULL AUTO_INCREMENT,
                       `TYPEID` bigint(20) NOT NULL,
                       `FILM_NAME` varchar(255) NOT NULL,
                       `DESCRIPTION` varchar(1024) DEFAULT NULL,
                       `ACTIVE` int(11) NOT NULL DEFAULT '0',
                       `CREATEDBY` varchar(255) NOT NULL,
                       `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       `UPDATEDBY` varchar(255) DEFAULT NULL,
                       `DATE_UPDATED` timestamp NULL DEFAULT NULL,
                       `DELETED` int(11) DEFAULT '0',
                       `DELETEDBY` varchar(255) DEFAULT '0',
                       `DATE_DELETED` timestamp NULL DEFAULT NULL,
                       PRIMARY KEY (`FILMID`),
                       KEY `TYPEID` (`TYPEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `films` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;

CREATE TABLE `reviews` (
                           `REVIEWID` bigint(20) NOT NULL AUTO_INCREMENT,
                           `REVIEWER` varchar(255) NOT NULL,
                           `WATCHED` int(11) DEFAULT '0',
                           `FILMID` bigint(20) NOT NULL,
                           `RATING` varchar(255) NOT NULL,
                           `REVIEW` varchar(1024) DEFAULT NULL,
                           `ACTIVE` int(11) NOT NULL DEFAULT '0',
                           `CREATEDBY` varchar(255) NOT NULL,
                           `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `UPDATEDBY` varchar(255) DEFAULT NULL,
                           `DATE_UPDATED` timestamp NULL DEFAULT NULL,
                           `DELETED` int(11) DEFAULT '0',
                           `DELETEDBY` varchar(255) DEFAULT '0',
                           `DATE_DELETED` timestamp NULL DEFAULT NULL,
                           PRIMARY KEY (`REVIEWID`),
                           KEY `REVIEWER` (`REVIEWER`),
                           KEY `FILMID` (`FILMID`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `USER_CODE` varchar(255) NOT NULL,
                         `SURNAME` varchar(255) NOT NULL,
                         `OTHERNAMES` varchar(255) NOT NULL,
                         `EMAIL` varchar(255) NOT NULL,
                         `MSISDN` varchar(255) NOT NULL,
                         `USERNAME` varchar(255) NOT NULL,
                         `PASSWORD` varchar(1024) NOT NULL,
                         `ACTIVE` int(11) NOT NULL DEFAULT '0',
                         `CREATEDBY` varchar(255) NOT NULL,
                         `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `UPDATEDBY` varchar(255) DEFAULT NULL,
                         `DATE_UPDATED` timestamp NULL DEFAULT NULL,
                         `DELETED` int(11) DEFAULT '0',
                         `DELETEDBY` varchar(255) DEFAULT '0',
                         `DATE_DELETED` timestamp NULL DEFAULT NULL,
                         PRIMARY KEY (`USER_CODE`),
                         UNIQUE KEY `USER_CODE` (`USER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
                               `USERS_USER_CODE` varchar(255) NOT NULL,
                               `ROLES` varchar(255) NOT NULL,
                               KEY `USER_CODE` (`USERS_USER_CODE`),
                               CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`USERS_USER_CODE`) REFERENCES `users` (`USER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`USER_CODE`, `SURNAME`, `OTHERNAMES`, `EMAIL`, `MSISDN`, `USERNAME`, `PASSWORD`, `ACTIVE`, `CREATEDBY`, `DATE_CREATED`, `UPDATEDBY`, `DATE_UPDATED`, `DELETED`, `DELETEDBY`, `DATE_DELETED`)
VALUES ('safaricom','safaricom','oss automation','joealex.kimani@gmail.com','254723683365','safaricom','safaricom',1,'safaricom','2019-05-10 09:50:19',NULL,NULL,0,'0',NULL);


LOCK TABLES `users_roles` WRITE;
INSERT INTO `users_roles` (`USERS_USER_CODE`, `ROLES`) VALUES ('safaricom','ROLE_ADMIN'),('safaricom','ROLE_USER');
UNLOCK TABLES;



DROP TABLE IF EXISTS `video_type`;

CREATE TABLE `video_type` (
                              `TYPEID` bigint(20) NOT NULL AUTO_INCREMENT,
                              `TYPENAME` varchar(255) NOT NULL,
                              `ACTIVE` int(11) NOT NULL DEFAULT '0',
                              `CREATEDBY` varchar(255) NOT NULL,
                              `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `UPDATEDBY` varchar(255) DEFAULT NULL,
                              `DATE_UPDATED` timestamp NULL DEFAULT NULL,
                              `DELETED` int(11) DEFAULT '0',
                              `DELETEDBY` varchar(255) DEFAULT '0',
                              `DATE_DELETED` timestamp NULL DEFAULT NULL,
                              PRIMARY KEY (`TYPEID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `video_type` WRITE;
INSERT INTO `video_type` (`TYPEID`, `TYPENAME`, `ACTIVE`, `CREATEDBY`, `DATE_CREATED`, `UPDATEDBY`, `DATE_UPDATED`, `DELETED`, `DELETEDBY`, `DATE_DELETED`) VALUES (1,'SERIES',1,'safaricom','2019-05-10 09:50:19',NULL,NULL,0,'0',NULL),(2,'MOVIE',1,'safaricom','2019-05-10 09:50:19',NULL,NULL,0,'0',NULL);
UNLOCK TABLES;

ALTER TABLE films ADD  FOREIGN KEY (`TYPEID`) REFERENCES `video_type` (`TYPEID`);

ALTER TABLE reviews ADD  FOREIGN KEY (`REVIEWER`) REFERENCES `users` (`USER_CODE`);
ALTER TABLE reviews ADD FOREIGN KEY (`FILMID`) REFERENCES `films` (`FILMID`);
