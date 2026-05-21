import dao.ProdutoDAO;
import model.Produto;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ProdutoDAO dao = new ProdutoDAO();
        int opcao;

        do {
            System.out.println("\n==================================");
            System.out.println("   CONTROLE DE ESTOQUE - PADARIA");
            System.out.println("==================================");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Aumentar estoque");
            System.out.println("3 - Diminuir estoque");
            System.out.println("4 - Filtrar por categoria");
            System.out.println("5 - Ver produtos vencidos");
            System.out.println("6 - Ver produtos próximos a vencer (7 dias)");
            System.out.println("7 - Ver estoque abaixo do mínimo");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");
            opcao = teclado.nextInt();

            switch(opcao) {
                case 1:
                    listarProdutos(dao);
                    break;
                case 2:
                    aumentarEstoque(teclado, dao);
                    break;
                case 3:
                    diminuirEstoque(teclado, dao);
                    break;
                case 4:
                    filtrarPorCategoria(teclado, dao);
                    break;
                case 5:
                    verVencidos(dao);
                    break;
                case 6:
                    verProximos(dao);
                    break;
                case 7:
                    verEstoqueBaixo(dao);
                    break;
                case 0:
                    System.out.println("\nSistema encerrado!");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }

        } while(opcao != 0);

        teclado.close();
    }

    // Método para listar produtos
    public static void listarProdutos(ProdutoDAO dao) {
        System.out.println("\n========== PRODUTOS ==========");
        List<Produto> produtos = dao.listarTodos();

        if(produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for(Produto p : produtos) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Categoria: " + p.getNomeCategoria());
                System.out.println("Estoque: " + p.getQuantidadeAtual());
                System.out.println("Estoque Mínimo: " + p.getQuantidadeMinima());
                System.out.println("Validade: " + p.getDataValidade());
                System.out.println("-----------------------------");
            }
        }
    }

    // Método para aumentar estoque
    public static void aumentarEstoque(Scanner teclado, ProdutoDAO dao) {
        System.out.println("\n========== AUMENTAR ESTOQUE ==========");
        System.out.print("Digite o ID do produto: ");
        int id = teclado.nextInt();
        System.out.print("Digite a quantidade a adicionar: ");
        int qtd = teclado.nextInt();

        dao.aumentarEstoque(id, qtd);
    }

    // Método para diminuir estoque
    public static void diminuirEstoque(Scanner teclado, ProdutoDAO dao) {
        System.out.println("\n========== DIMINUIR ESTOQUE ==========");
        System.out.print("Digite o ID do produto: ");
        int id = teclado.nextInt();
        System.out.print("Digite a quantidade a remover: ");
        int qtd = teclado.nextInt();

        dao.diminuirEstoque(id, qtd);
    }

    // Método para filtrar por categoria
    public static void filtrarPorCategoria(Scanner teclado, ProdutoDAO dao) {
        System.out.println("\n========== FILTRAR POR CATEGORIA ==========");
        System.out.print("Digite a categoria (Pães, Bolos, Bebidas, Frios): ");
        teclado.nextLine(); // limpar buffer
        String categoria = teclado.nextLine();

        List<Produto> produtos = dao.filtrarPorCategoria(categoria);

        if(produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado na categoria " + categoria);
        } else {
            System.out.println("\nProdutos da categoria " + categoria + ":");
            for(Produto p : produtos) {
                System.out.println("- " + p.getNome() + " | Estoque: " + p.getQuantidadeAtual());
            }
        }
    }

    // Método para ver produtos vencidos
    public static void verVencidos(ProdutoDAO dao) {
        System.out.println("\n========== PRODUTOS VENCIDOS ==========");
        List<Produto> produtos = dao.produtosVencidos();

        if(produtos.isEmpty()) {
            System.out.println("Não há produtos vencidos!");
        } else {
            for(Produto p : produtos) {
                System.out.println("- " + p.getNome() + " | Venceu em: " + p.getDataValidade());
            }
        }
    }

    // Método para ver produtos próximos ao vencimento
    public static void verProximos(ProdutoDAO dao) {
        System.out.println("\n========== PRODUTOS PRÓXIMOS AO VENCIMENTO (7 dias) ==========");
        List<Produto> produtos = dao.produtosProximosVencimento();

        if(produtos.isEmpty()) {
            System.out.println("Não há produtos próximos ao vencimento!");
        } else {
            for(Produto p : produtos) {
                System.out.println("- " + p.getNome() + " | Vence em: " + p.getDataValidade());
            }
        }
    }

    // Método para ver estoque abaixo do mínimo
    public static void verEstoqueBaixo(ProdutoDAO dao) {
        System.out.println("\n========== ESTOQUE ABAIXO DO MÍNIMO ==========");
        List<Produto> produtos = dao.estoqueBaixo();

        if(produtos.isEmpty()) {
            System.out.println("Todos os produtos estão com estoque adequado!");
        } else {
            System.out.println("⚠️  ATENÇÃO! Produtos com estoque baixo:");
            for(Produto p : produtos) {
                System.out.println("- " + p.getNome() + " | Atual: " + p.getQuantidadeAtual() +
                        " | Mínimo: " + p.getQuantidadeMinima());
            }
        }
    }
}