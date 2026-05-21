#  Sistema de Controle de Estoque - Padaria

Sistema desenvolvido em Java para gerenciamento de estoque de uma padaria, permitindo controle de quantidades, validades e categorias de produtos.

##  Requisitos Funcionais

| ID   | Requisito Funcional | Descrição |
|------|---------------------|-------------|
| RF01 | Listar produtos | O sistema deve exibir todos os produtos cadastrados com suas respectivas informações (ID, nome, categoria, estoque atual, estoque mínimo e data de validade). |
| RF02 | Aumentar estoque | O usuário deve poder aumentar a quantidade de um produto no estoque informando o ID do produto e a quantidade a ser adicionada. |
| RF03 | Diminuir estoque | O usuário deve poder reduzir a quantidade de um produto no estoque informando o ID do produto e a quantidade a ser removida. |
| RF04 | Filtrar por categoria | O sistema deve permitir filtrar e exibir produtos pertencentes a uma categoria específica (ex: Pães, Bolos, Bebidas, Frios). |
| RF05 | Visualizar produtos vencidos | O sistema deve listar todos os produtos cuja data de validade seja anterior à data atual. |
| RF06 | Visualizar produtos próximos ao vencimento | O sistema deve listar produtos cuja data de validade esteja dentro dos próximos 7 dias. |
| RF07 | Verificar estoque baixo | O sistema deve identificar e listar produtos cuja quantidade atual seja inferior à quantidade mínima definida. |
| RF08 | Conexão com banco de dados | O sistema deve conectar-se a um banco de dados MySQL para persistência das informações. |
| RF09 | Exibir mensagens de feedback | O sistema deve exibir mensagens de confirmação ou erro para cada operação realizada pelo usuário. |

## ⚙️ Requisitos Não Funcionais

| ID    | Requisito Não Funcional | Descrição |
|-------|-------------------------|-------------|
| RNF01 | Linguagem de programação | O sistema foi desenvolvido em Java, utilizando JDBC para acesso ao banco de dados. |
| RNF02 | SGBD | Utiliza MySQL como sistema gerenciador de banco de dados. |
| RNF03 | Interface | A interface é textual (console/terminal), sem interface gráfica. |
| RNF04 | Tratamento de exceções | O sistema deve tratar exceções de conexão e SQL, exibindo mensagens amigáveis ao usuário. |
| RNF05 | Organização do código | O código está estruturado em camadas: `conexao` (conexão com BD), `dao` (acesso a dados) e `model` (entidades). |
| RNF06 | Manutenibilidade | O uso de classes DAO separadas facilita a manutenção e evolução do sistema. |
| RNF07 | Portabilidade | Por ser Java, o sistema pode ser executado em qualquer sistema operacional com JVM instalada. |
| RNF08 | Disponibilidade | O sistema depende do banco de dados MySQL estar em execução para funcionar corretamente. |

## Programas Utilizados

- Java 17
- SQLite

##  Como executar

1. Criar o banco de dados `padaria_estoque` no MySQL
2. Executar os scripts de criação das tabelas `categorias` e `produtos`
3. Configurar usuário e senha do banco na classe `Conexao.java`
4. Compilar e executar a classe `Main.java`
