insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 1', 7.30, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 2', 4.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 3', 2.80, 2);

insert into permissao (nome, descricao) values ('permissao 1', 'Permissao teste');

insert into estado (nome) values ('Paraná');
insert into estado (nome) values ('São Paulo');

insert into cidade (nome, estado_id) values ('Siqueira Campos', 1);
insert into cidade (nome, estado_id) values ('Ourinhos', 2);

insert into forma_pagamento (descricao) values ('Cartao');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cheque');