DROP DATABASE IF EXISTS showmewhere;
CREATE DATABASE showmewhere;
USE showmewhere;
CREATE TABLE showmewhere_subscription (
  applicationId VARCHAR(20) NOT NULL ,
  frequency VARCHAR(20) ,
  status VARCHAR(20),
  subscriberId VARCHAR(20),
  version VARCHAR(10),
  timeStamp VARCHAR(30),
  CONSTRAINT PRIMARY KEY(subscriberId)
);

CREATE TABLE showmewhere_username (
  subscriberId VARCHAR(20),
  pin INT AUTO_INCREMENT,
  username VARCHAR(50) DEFAULT NULL ,
  CONSTRAINT PRIMARY KEY(subscriberId),
  CONSTRAINT FOREIGN KEY (subscriberId) REFERENCES showmewhere_subscription(subscriberId)
    ON DELETE CASCADE ON UPDATE CASCADE
) AUTO_INCREMENT = 10001;


