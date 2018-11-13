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
