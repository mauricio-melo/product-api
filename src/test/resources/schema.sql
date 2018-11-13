
CREATE TABLE company (
  idt_company       BIGINT AUTO_INCREMENT PRIMARY KEY,
  cnpj_company      BIGINT NOT NULL,
  flg_enabled       BIT NOT NULL,
  dat_creation      DATETIME NOT NULL,
  dat_update        DATETIME DEFAULT NULL,
  user_creation     VARCHAR(255) DEFAULT NULL,
  UNIQUE (cnpj_company)
);

CREATE TABLE product (
  idt_product       BIGINT AUTO_INCREMENT PRIMARY KEY,
  nam_product       VARCHAR(255) NOT NULL,
  desc_product      VARCHAR(255) NOT NULL,
  flg_enabled       BIT NOT NULL,
  dat_creation      DATETIME NOT NULL,
  dat_update        DATETIME DEFAULT NULL,
  user_creation     VARCHAR(255) DEFAULT NULL,
);


CREATE TABLE company_product (
  idt_company       BIGINT NOT NULL,
  idt_product       BIGINT NOT NULL,
  FOREIGN KEY (idt_company) REFERENCES company (idt_company),
  FOREIGN KEY (idt_product) REFERENCES product (idt_product)
);

CREATE TABLE bunch (
  idt_bunch         BIGINT AUTO_INCREMENT PRIMARY KEY,
  nam_bunch         VARCHAR(255) NOT NULL,
  desc_bunch        VARCHAR(255) NOT NULL,
  flg_enabled       BIT NOT NULL,
  dat_creation      DATETIME NOT NULL,
  dat_update        DATETIME DEFAULT NULL,
  user_creation     VARCHAR(255) DEFAULT NULL,
  idt_product       BIGINT NOT NULL,
  FOREIGN KEY (idt_product) REFERENCES product (idt_product)
);

CREATE TABLE feature (
  idt_feature       BIGINT AUTO_INCREMENT PRIMARY KEY,
  nam_feature       VARCHAR(255) NOT NULL,
  desc_feature      VARCHAR(255) NOT NULL,
  flg_enabled       BIT NOT NULL,
  dat_creation      DATETIME NOT NULL,
  dat_update        DATETIME DEFAULT NULL,
  user_creation     VARCHAR(255) DEFAULT NULL,
  idt_bunch       BIGINT NOT NULL,
  FOREIGN KEY (idt_bunch) REFERENCES bunch (idt_bunch)
);