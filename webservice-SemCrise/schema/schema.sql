DROP TABLE IF EXISTS posto;


-- Tabela posto
CREATE TABLE posto (
    id int auto_increment,
    nome varchar(20),
    local varchar(30),
    produto varchar(30),
    preco FLOAT(10,2),
    PRIMARY KEY (id)
);

INSERT INTO posto VALUES (DEFAULT, 'Ipiranga', 'Campinas',    'Gasolina', 2.97);
INSERT INTO posto VALUES (DEFAULT, 'Ipiranga', 'Hortolândia', 'Gasolina', 2.98);
INSERT INTO posto VALUES (DEFAULT, 'Ipiranga', 'Sao Paulo',   'Alcool', 2.97);
INSERT INTO posto VALUES (DEFAULT, 'Ipiranga', 'Sao Paulo',   'Gasolina', 3.50);
