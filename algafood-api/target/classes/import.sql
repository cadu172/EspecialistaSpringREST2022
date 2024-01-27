INSERT INTO permissao (id, descricao, nome) VALUES(1, 'Usuario com permissao de administrador', 'ADMIN');
INSERT INTO permissao (id, descricao, nome) VALUES(2, 'Permite incluir um pedido', 'OPERACAO');
INSERT INTO permissao (id, descricao, nome) VALUES(3, 'Permite o estorno de uma venda', 'SUPERVISAO');

INSERT INTO algafood.estado (id, nome) VALUES(1, 'SP - São Paulo');
INSERT INTO algafood.estado (id, nome) VALUES(2, 'RJ - Rio de Janeiro');
INSERT INTO algafood.estado (id, nome) VALUES(3, 'CE - Ceará');
INSERT INTO algafood.estado (id, nome) VALUES(4, 'RS - Rio Grande do Sul');
INSERT INTO algafood.estado (id, nome) VALUES(5, 'MG - Minas Gerais');

INSERT INTO cidade (id, nome,estado_id) VALUES(1, 'São Paulo',1);
INSERT INTO cidade (id, nome,estado_id) VALUES(2, 'Angra dos Reis',2);
INSERT INTO cidade (id, nome,estado_id) VALUES(3, 'Fortaleza',3);
INSERT INTO cidade (id, nome,estado_id) VALUES(4, 'Alvorada',4);
INSERT INTO cidade (id, nome,estado_id) VALUES(5, 'Belo Horizonte',5);
INSERT INTO cidade (id, nome,estado_id) VALUES(6, 'Cotia',1);

INSERT INTO algafood.forma_pagamento (id, descricao) VALUES(1, 'DINHEIRO');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES(2, 'PIX');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES(3, 'CARTAO DE CREDITO');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES(4, 'PAYPAL');

INSERT INTO cozinha (id,nome) VALUES (1,'Americana');
INSERT INTO cozinha (id,nome) VALUES (2,'Portuguesa');
INSERT INTO cozinha (id,nome) VALUES (3,'Tailandesa');
INSERT INTO cozinha (id,nome) VALUES (4,'Brasileira');

INSERT INTO algafood.restaurante (data_cadastro, data_atualizacao,id, taxa_frete, nome, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero) VALUES(utc_timestamp,utc_timestamp,1, 25.00, 'Subway',1, 6, 'Chacara Ondas Verdes','06715725','Casa 30','Estrada do Capuava','2451');
INSERT INTO algafood.restaurante (data_cadastro, data_atualizacao,id, taxa_frete, nome, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero) VALUES(utc_timestamp,utc_timestamp,2, 25.00, 'MacDonalds',1, 6, 'Chacara Ondas Verdes','06715725','Casa 30','Estrada do Capuava','2451');
INSERT INTO algafood.restaurante (data_cadastro, data_atualizacao,id, taxa_frete, nome, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero) VALUES(utc_timestamp,utc_timestamp,3, 25.00, 'Comida da Fazenda',1, 6, 'Chacara Ondas Verdes','06715725','Casa 30','Estrada do Capuava','2451');
INSERT INTO algafood.restaurante (data_cadastro, data_atualizacao,id, taxa_frete, nome, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero) VALUES(utc_timestamp,utc_timestamp,4, 25.00, 'Bom Gosto',1, 6, 'Chacara Ondas Verdes','06715725','Casa 30','Estrada do Capuava','2451');

INSERT INTO algafood.restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) VALUES (1, 1);
INSERT INTO algafood.restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) VALUES (2, 1);
INSERT INTO algafood.restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) VALUES (3, 1);
INSERT INTO algafood.restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) VALUES (1, 2);
INSERT INTO algafood.restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) VALUES (2, 2);
INSERT INTO algafood.restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) VALUES (1, 3);
INSERT INTO algafood.restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) VALUES (1, 4);