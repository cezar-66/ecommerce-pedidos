CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) NOT NULL
);

-- Inserindo dois usuários apenas para testes, as senhas foram criptografadas com BCrypt
INSERT INTO usuarios (nome, email, senha, perfil) VALUES
('João Silva', 'joao@email.com', '$2a$10$mPS.gxorsP.kUNLMVk.DnuyaiMHsuSDa4Kt6.GJbb7dP/IPa4G7Ee', 'ADMIN'),
('Maria Souza', 'maria@email.com', '$2a$10$mPS.gxorsP.kUN
