REPLACE INTO `role` VALUES (1,'ADMIN');
REPLACE INTO `role` VALUES (2,'USER');
REPLACE INTO `user` VALUES ( 1, 1, 'default@user.in', 'Default', 'User', '$2a$04$GDCL3qOrXcOM6uaEoic5EO67MYXWjHRLOSp1s9gM9H6xv4Lbo3Kmu');
REPLACE INTO `user_role` VALUES (1, 2);
