use bugtracking;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `full_name` varchar(120) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `priority` int(11) NOT NULL DEFAULT '10',
  `title` varchar(250) NOT NULL,
  `description` text,
  `responsible_id` int(11) DEFAULT NULL,
  `status` enum('NEW','CLOSED','WONTFIX','ACCEPTED') NOT NULL DEFAULT 'NEW',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `reso_fk_idx` (`responsible_id`),
  CONSTRAINT `reso_fk` FOREIGN KEY (`responsible_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `body` text NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `author_id` int(11) NOT NULL,
  `bug_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `author_fk_idx` (`author_id`),
  KEY `bug_fk_idx` (`bug_id`),
  CONSTRAINT `bug_fk` FOREIGN KEY (`bug_id`) REFERENCES `bug` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `author_fk` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user` (`id`,`username`,`full_name`,`email`) VALUES (1,'admin',NULL,'admin@localhost');
INSERT INTO `user` (`id`,`username`,`full_name`,`email`) VALUES (2,'John','John Doe','john@example.com');
INSERT INTO `user` (`id`,`username`,`full_name`,`email`) VALUES (3,'vasya007', 'Vasiliy Pupkin', 'vasiliy@example.com');
