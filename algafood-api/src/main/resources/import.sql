INSERT INTO permissao (id, descricao, nome) VALUES(1, 'Usuario com permissao de administrador', 'ADMIN');
INSERT INTO permissao (id, descricao, nome) VALUES(2, 'Permite incluir um pedido', 'OPERACAO');
INSERT INTO permissao (id, descricao, nome) VALUES(3, 'Permite o estorno de uma venda', 'SUPERVISAO');

INSERT INTO algafood.estado (id, nome) VALUES(1, 'SP - São Paulo');
INSERT INTO algafood.estado (id, nome) VALUES(2, 'RJ - Rio de Janeiro');
INSERT INTO algafood.estado (id, nome) VALUES(3, 'CE - Ceará');
INSERT INTO algafood.estado (id, nome) VALUES(4, 'RS - Rio Grande do Sul');
INSERT INTO algafood.estado (id, nome) VALUES(5, 'MG - Minas Gerais');

INSERT INTO cidade (estado_id, nome) VALUES(1, 'São Paulo');
INSERT INTO cidade (estado_id, nome) VALUES(2, 'Angra dos Reis');
INSERT INTO cidade (estado_id, nome) VALUES(3, 'Fortaleza');
INSERT INTO cidade (estado_id, nome) VALUES(4, 'Alvorada');
INSERT INTO cidade (estado_id, nome) VALUES(5, 'Belo Horizonte');

INSERT INTO algafood.forma_pagamento (id, descricao) VALUES(1, 'DINHEIRO');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES(2, 'PIX');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES(3, 'CARTAO DE CREDITO');
INSERT INTO algafood.forma_pagamento (id, descricao) VALUES(4, 'PAYPAL');

INSERT INTO cozinha (id,nome) VALUES (1,'Americana');
INSERT INTO algafood.restaurante (id, taxa_frete, nome, cozinha_id, forma_pagamento_id) VALUES(1, 25.00, 'Subway',1, 3);

INSERT INTO cozinha (id,nome) VALUES (2,'Portuguesa');
INSERT INTO algafood.restaurante (id, taxa_frete, nome, cozinha_id, forma_pagamento_id) VALUES(2, 23.45, 'Alfama dos Marinheiros',2, 2);

INSERT INTO cozinha (id,nome) VALUES (3,'Tailandesa');
INSERT INTO algafood.restaurante (id, taxa_frete, nome, cozinha_id, forma_pagamento_id) VALUES(3, 40.00, 'Hub Food & Art Lounge',3, 1);

INSERT INTO cozinha (id,nome) VALUES (4,'Brasileira');
INSERT INTO algafood.restaurante (id, taxa_frete, nome, cozinha_id, forma_pagamento_id) VALUES(4, 25.00, 'Mocotó',4, 4);