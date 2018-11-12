CREATE TABLE company (
  idt_company bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID da empresa cadastrada',
  cnpj_company bigint(20) NOT NULL COMMENT 'CNPJ da empresa',
  flg_enabled tinyint(1) NOT NULL COMMENT 'Situacao do produto ativa ou inativa',
  dat_creation datetime NOT NULL COMMENT 'Data da criação da empresa',
  dat_update datetime DEFAULT NULL COMMENT 'Data de atualização da empresa',
  user_creation varchar(255) DEFAULT NULL COMMENT 'Usuario de criação da empresa',
  PRIMARY KEY (idt_company),
  UNIQUE KEY cnpj_idx (cnpj_company)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela de empresa contratante do produto';

CREATE TABLE product (
  idt_product bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID do produto',
  nam_product varchar(255) NOT NULL COMMENT 'Nome do produto',
  desc_product varchar(255) NOT NULL COMMENT 'Descricao do produto',
  flg_enabled tinyint(1) NOT NULL COMMENT 'Situacao do produto ativa ou inativa',
  dat_creation datetime NOT NULL COMMENT 'Data da criação do produto',
  dat_update datetime DEFAULT NULL COMMENT 'Data de atualização produto',
  user_creation varchar(255) DEFAULT NULL COMMENT 'Usuario de criação do produto',
  PRIMARY KEY (idt_product)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela de produto';

CREATE TABLE company_product (
  idt_company bigint(20) NOT NULL COMMENT 'ID da empresa',
  idt_product bigint(20) NOT NULL COMMENT 'Id do produto',
  KEY company_id_idx (idt_company),
  KEY product_idx01_idx (idt_product),
  CONSTRAINT company_id02 FOREIGN KEY (idt_company) REFERENCES company (idt_company) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT product_idx01 FOREIGN KEY (idt_product) REFERENCES product (idt_product) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela de ligação entre companhia e produto';

CREATE TABLE bunch (
  idt_bunch bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID do grupo do grupo',
  nam_bunch varchar(255) NOT NULL COMMENT 'Nome do grupo do sistema',
  desc_bunch varchar(255) NOT NULL COMMENT 'Descricao do grupo do sistema',
  flg_enabled tinyint(1) NOT NULL COMMENT 'Situacao do grupo ativa ou inativa',
  dat_creation datetime NOT NULL COMMENT 'Data da criação do grupo',
  dat_update datetime DEFAULT NULL COMMENT 'Data de atualização do grupo',
  user_creation varchar(255) DEFAULT NULL COMMENT 'Usuario de criação do grupo',
  idt_product bigint(20) NOT NULL COMMENT 'Id do produto',
  PRIMARY KEY (idt_bunch),
  KEY product_idex01_idx (idt_product),
  CONSTRAINT product_idex01 FOREIGN KEY (idt_product) REFERENCES product (idt_product) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela de grupo do produto';

CREATE TABLE feature (
  idt_feature bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID da empresa cadastrada',
  nam_feature varchar(255) NOT NULL COMMENT 'Nome da funcionalidade do sistema',
  desc_feature varchar(255) NOT NULL COMMENT 'Descricao da funcinalidade do sistema',
  flg_enabled tinyint(1) NOT NULL COMMENT 'Situacao da funcionalidade ativa ou inativa',
  dat_creation datetime NOT NULL COMMENT 'Data da criação da funcinalidade',
  dat_update datetime DEFAULT NULL COMMENT 'Data de atualização da funcionalidade',
  user_creation varchar(255) DEFAULT NULL COMMENT 'Usuario de criação da funcionalidade',
  idt_bunch bigint(20) NOT NULL COMMENT 'Id do grupo de funcoes',
  PRIMARY KEY (idt_feature),
  KEY bunch_idex01_idx (idt_bunch),
  CONSTRAINT bunch_idex01 FOREIGN KEY (idt_bunch) REFERENCES bunch (idt_bunch) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela funcionalidade do produto';



insert into company (cnpj_company, flg_enabled, dat_creation, user_creation)
values ('81270418000111', 1, '2018-11-08 15:20:00', '12345');

insert into company (cnpj_company, flg_enabled, dat_creation, user_creation)
values ('81270418000112', 1, '2018-11-08 15:20:00', '12345');

insert into company (cnpj_company, flg_enabled, dat_creation, user_creation)
values ('81270418000113', 1, '2018-11-08 15:20:00', '12345');

insert into product (nam_product, desc_product, flg_enabled, dat_creation, user_creation)
values ('Advanced 1','Advanced 1', 1, '2018-11-08 15:20:00',  '12345');

insert into product (nam_product, desc_product, flg_enabled, dat_creation, user_creation)
values ('Premium 1','Premium 1', 1,  '2018-11-08 15:20:00', '12345');

insert into product (nam_product, desc_product, flg_enabled, dat_creation, user_creation)
values ('Basic 1','Basic 1', 1, '2018-11-08 15:20:00', '12345');

insert into bunch (nam_bunch, desc_bunch, flg_enabled, dat_creation, user_creation, idt_product)
values ('Automacao','Automação', 1, '2018-11-08 15:20:00', '12345', 1);

insert into bunch (nam_bunch, desc_bunch, flg_enabled, dat_creation, user_creation, idt_product)
values ('Automacao 1','Automação 1', 1, '2018-11-08 15:20:00', '12345', 1);

insert into bunch (nam_bunch, desc_bunch, flg_enabled, dat_creation, user_creation, idt_product)
values ('Automacao 2','Automação 2', 1, '2018-11-08 15:20:00', '12345', 1);

insert into feature (nam_feature, desc_feature, flg_enabled, dat_creation, user_creation, idt_bunch)
values ('Enviar SMS','Enviar SMS', 1, '2018-11-08 15:20:00', '12345', 1);

insert into feature (nam_feature, desc_feature, flg_enabled, dat_creation, user_creation, idt_bunch)
values ('Enviar SMS 1','Enviar SMS 1', 1, '2018-11-08 15:20:00', '12345', 1);

insert into feature (nam_feature, desc_feature, flg_enabled, dat_creation, user_creation, idt_bunch)
values ('Enviar SMS 2','Enviar SMS 2', 1, '2018-11-08 15:20:00', '12345', 1);

insert into company_product (idt_company, idt_product) values (1, 1);
insert into company_product (idt_company, idt_product) values (2, 3);


