--Cadastro de clientes
insert into cliente (nome, email) values ('Fabio Kenzo', 'fabio@email.com');
insert into cliente (nome, email) values ('Alex Silva', 'alex.silva@email.com');
insert into cliente (nome, email) values ('Beatriz Souza', 'b.souza@provedor.net');
insert into cliente (nome, email) values ('Carlos Oliveira', 'carlos.oliveira@techmail.com');
insert into cliente (nome, email) values ('Daniela Lima', 'dani.lima@webmail.com');


--Cadastro de produtos
insert into produto (nome, descricao, preco, estoque) values ('Fone KZ EDX PRO', 'Fone in-ear profissional', 159.9, 10);
insert into produto (nome, descricao, preco, estoque) values ('Teclado Mecânico RGB', 'Teclado 60% switch red', 299.9, 50);
insert into produto (nome, descricao, preco, estoque) values ('Mouse Óptico Precision', 'Sensor de 12.000 DPI, cabo trançado e 6 botões laterais.', 210.00, 50);
insert into produto (nome, descricao, preco, estoque) values ('Monitor UltraWide 29', 'Resolução Full HD+, taxa de atualização de 75Hz e HDR.', 1100.00, 10);
insert into produto (nome, descricao, preco, estoque) values ('Webcam Full HD 1080p', 'Microfone integrado com redução de ruído e foco automático.', 280.00, 15);


--Cadastro de Avaliacoes (Demonstrando relacionamentos entre tabelas)
--Nota: O produto_id1 refere-se ao fone, o 2 ao teclado e etc...
insert into avaliacao (comentario, nota, produto_id) values ('Qualidade de som impressionante pelo valor.', 5, 1);
insert into avaliacao (comentario, nota, produto_id) values ('Muito confortável para longas horas de uso.', 4, 1);
insert into avaliacao (comentario, nota, produto_id) values ('O teclado é pesado e passa muita robustez.', 5, 2);
insert into avaliacao (comentario, nota, produto_id) values ('O software do mouse é um pouco difícil de configurar.', 3, 3);