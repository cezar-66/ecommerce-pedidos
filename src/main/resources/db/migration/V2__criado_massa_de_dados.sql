-- Inserindo dois usuários apenas para testes, as senhas foram criptografadas com BCrypt
-- "password": "senha123"
INSERT INTO usuarios (nome, email, senha, perfil) VALUES
('Cezar Marçal', 'cezarmarcal@email.com', '$2a$10$mPS.gxorsP.kUNLMVk.DnuyaiMHsuSDa4Kt6.GJbb7dP/IPa4G7Ee', 'ADMIN'),
('Admin', 'admin.silva@email.com', '$2a$10$mPS.gxorsP.kUNLMVk.DnuyaiMHsuSDa4Kt6.GJbb7dP/IPa4G7Ee', 'ADMIN'),
('João Silva', 'joao.silva@email.com', '$2a$10$mPS.gxorsP.kUNLMVk.DnuyaiMHsuSDa4Kt6.GJbb7dP/IPa4G7Ee', 'ADMIN'),
('Ana Pereira', 'ana.pereira@email.com', '$2a$10$mPS.gxorsP.kUNLMVk.DnuyaiMHsuSDa4Kt6.GJbb7dP/IPa4G7Ee', 'USER'),
('Carlos Oliveira', 'carlos.oliveira@email.com', '$2a$10$mPS.gxorsP.kUNLMVk.DnuyaiMHsuSDa4Kt6.GJbb7dP/IPa4G7Ee', 'USER'),
('Fernanda Costa', 'fernanda.costa@email.com', '$2a$10$mPS.gxorsP.kUNLMVk.DnuyaiMHsuSDa4Kt6.GJbb7dP/IPa4G7Ee', 'USER'),
('Roberta Lima', 'roberta.lima@email.com', '$2a$10$mPS.gxorsP.kUNLMVk.DnuyaiMHsuSDa4Kt6.GJbb7dP/IPa4G7Ee', 'USER');


INSERT INTO produtos (nome, categoria, preco, estoque)
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
