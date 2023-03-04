
-- CRM.company definition

CREATE TABLE IF NOT EXISTS `company` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- CRM.status definition

CREATE TABLE IF NOT EXISTS `status` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- CRM.contact definition

CREATE TABLE IF NOT EXISTS `contact` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `company_id` varchar(255) NOT NULL,
  `status_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpgbqt6dnai52x55o1qvsx1dfn` (`company_id`),
  KEY `FKtp0gbknv4j92yko7ucc3tpp2y` (`status_id`),
  CONSTRAINT `FKpgbqt6dnai52x55o1qvsx1dfn` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKtp0gbknv4j92yko7ucc3tpp2y` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;