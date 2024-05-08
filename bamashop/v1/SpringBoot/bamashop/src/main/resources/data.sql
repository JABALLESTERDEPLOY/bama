INSERT INTO `roles`(`id_roles`, `role_name`) VALUES (1,'ROLE_USER');
INSERT INTO `roles`(`id_roles`, `role_name`) VALUES (2,'ROLE_CLIENT');
INSERT INTO `roles`(`id_roles`, `role_name`) VALUES (3,'ROLE_PROV');
INSERT INTO `roles`(`id_roles`, `role_name`) VALUES (4,'ROLE_ADMIN');

INSERT INTO `users`(`id_users`, `email`, `password`, `username`) VALUES (1,'joseantonio@gmail.com','$2a$10$Jet.7/WUeiF6tAlKOVZtmeWXrohdLpiX7JbUOA2/2Ury/ucCnQlDq','joseantonio');
INSERT INTO `users`(`id_users`, `email`, `password`, `username`) VALUES (2,'manuel@gmail.com','$2a$10$Jet.7/WUeiF6tAlKOVZtmeWXrohdLpiX7JbUOA2/2Ury/ucCnQlDq','manuel');

INSERT INTO `users_roles`(`id_users`, `id_roles`) VALUES (1,1);
INSERT INTO `users_roles`(`id_users`, `id_roles`) VALUES (1,2);
INSERT INTO `users_roles`(`id_users`, `id_roles`) VALUES (2,1);
INSERT INTO `users_roles`(`id_users`, `id_roles`) VALUES (2,2);