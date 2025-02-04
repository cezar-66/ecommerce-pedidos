CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL
);

INSERT INTO produto (nome, categoria, preco, estoque)
VALUES
('iPhone 15', 'Eletrônicos', 6999.00, 50),
('Samsung Galaxy S23', 'Eletrônicos', 4999.00, 100),
('Smart TV LG 55" 4K', 'Eletrodomésticos', 2999.00, 30),
('PlayStation 5', 'Jogos e Consoles', 4999.00, 20),
('MacBook Pro 16"', 'Eletrônicos', 12999.00, 15),
('Tênis Nike Air Max 270', 'Moda', 649.90, 200),
('Camiseta Adidas Originals', 'Moda', 129.90, 300),
('Cafeteira Nespresso Expert', 'Eletrodomésticos', 1799.00, 80),
('Cadeira Gamer DXRacer', 'Móveis e Decoração', 1299.00, 60),
('Echo Dot 5ª Geração', 'Eletrônicos', 349.90, 150);
