
use sportsman_db;


LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'UNKNOWN','2016-12-13 14:08:29','2016-12-13 14:08:29','UNKNOWN','INSERT','ACTIVE',1481618309523,'de7f1aa4-1652-4c2f-9340-609f025c938c',0,'ROLE_SUPER_ADMIN');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'UNKNOWN','2016-12-13 14:11:26','2016-12-13 14:11:26','UNKNOWN','INSERT','ACTIVE',1481618486243,'b85850cc-1b06-4701-ad31-922a3d123693',0,'Test Address','0712186182',NULL,'janakawanigatunga82@gmail.com','Super user Testing','812265481v','');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `security_user` WRITE;
/*!40000 ALTER TABLE `security_user` DISABLE KEYS */;
INSERT INTO `security_user` VALUES (1,'UNKNOWN','2016-12-13 14:11:26','2016-12-13 14:11:26','UNKNOWN','INSERT','ACTIVE',1481618486237,'ea3b2a69-49f8-43fa-af7a-b54339fa8f26',0,1,'\0','\0','\0','$2a$10$MYTM3HaOFY.7lo90cccdvuLS3ZyjOszAM8U9Jdty9zwKfVZk4jxvq','sportsman_admin',1);
/*!40000 ALTER TABLE `security_user` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `security_user_user_role` WRITE;
/*!40000 ALTER TABLE `security_user_user_role` DISABLE KEYS */;
INSERT INTO `security_user_user_role` VALUES (1,1);
/*!40000 ALTER TABLE `security_user_user_role` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `sequence_number` WRITE;
/*!40000 ALTER TABLE `sequence_number` DISABLE KEYS */;
INSERT INTO `sequence_number` VALUES (1,'UNKNOWN','2016-12-13 14:05:03','2016-12-13 14:05:03','UNKNOWN','UPDATE','ACTIVE',1481618142275,'e7552429-a731-4f21-8689-8c7eeb135c13',0,0,'Player Number Sequence',5,'PL/YEAR/');
/*!40000 ALTER TABLE `sequence_number` ENABLE KEYS */;
UNLOCK TABLES;
