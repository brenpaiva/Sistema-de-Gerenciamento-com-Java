import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe principal para executar o teste prático.
 */
public class Principal {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    
    public static void main(String[] args) {
        try {
            Principal principal = new Principal();
            principal.executarTeste();
        } catch (Exception e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void executarTeste() {
        System.out.println("=== TESTE PRÁTICO INIFLEX ===\n");
        
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = inserirFuncionarios();
        System.out.println("3.1 - Funcionários inseridos com sucesso!\n");
        
        // 3.2 - Remover o funcionário "João"
        removerFuncionario(funcionarios, "João");
        System.out.println("3.2 - Funcionário 'João' removido da lista!\n");
        
        // 3.3 - Imprimir todos os funcionários
        imprimirTodosFuncionarios(funcionarios);
        
        // 3.4 - Aplicar aumento de 10%
        aplicarAumento(funcionarios, 10.0);
        System.out.println("3.4 - Aumento de 10% aplicado a todos os funcionários!\n");
        
        // 3.5 e 3.6 - Agrupar por função e imprimir
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao(funcionarios);
        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);
        
        // 3.8 - Funcionários que fazem aniversário em outubro e dezembro
        imprimirAniversariantes(funcionarios, Arrays.asList(10, 12));
        
        // 3.9 - Funcionário com maior idade
        imprimirFuncionarioMaisVelho(funcionarios);
        
        // 3.10 - Lista por ordem alfabética
        imprimirFuncionariosOrdemAlfabetica(funcionarios);
        
        // 3.11 - Total dos salários
        imprimirTotalSalarios(funcionarios);
        
        // 3.12 - Salários mínimos por funcionário
        imprimirSalariosMinimos(funcionarios);
    }
    
    private List<Funcionario> inserirFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        return funcionarios;
    }
    
    private void removerFuncionario(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }
    
    private void imprimirTodosFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("3.3 - TODOS OS FUNCIONÁRIOS:");
        System.out.println("─".repeat(80));
        funcionarios.forEach(System.out::println);
        System.out.println();
    }
    
    private void aplicarAumento(List<Funcionario> funcionarios, double percentual) {
        funcionarios.forEach(f -> f.aplicarAumento(percentual));
    }
    
    private Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }
    
    private void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("3.5/3.6 - FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO:");
        System.out.println("─".repeat(80));
        
        funcionariosPorFuncao.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.println("FUNÇÃO: " + entry.getKey());
                    entry.getValue().forEach(f -> System.out.println("  • " + f));
                    System.out.println();
                });
    }
    
    private void imprimirAniversariantes(List<Funcionario> funcionarios, List<Integer> meses) {
        System.out.println("3.8 - FUNCIONÁRIOS QUE FAZEM ANIVERSÁRIO EM OUTUBRO E DEZEMBRO:");
        System.out.println("─".repeat(80));
        
        funcionarios.stream()
                .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
                .forEach(f -> System.out.println(f.getNome() + " - " + 
                    f.getDataNascimento().format(Funcionario.getDateFormatter())));
        System.out.println();
    }
    
    private void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        System.out.println("3.9 - FUNCIONÁRIO COM MAIOR IDADE:");
        System.out.println("─".repeat(80));
        
        funcionarios.stream()
                .max(Comparator.comparing(Pessoa::getIdade))
                .ifPresent(f -> System.out.println("Nome: " + f.getNome() + " | Idade: " + f.getIdade() + " anos"));
        System.out.println();
    }
    
    private void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        System.out.println("3.10 - FUNCIONÁRIOS EM ORDEM ALFABÉTICA:");
        System.out.println("─".repeat(80));
        
        funcionarios.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println();
    }
    
    private void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        System.out.println("3.11 - TOTAL DOS SALÁRIOS:");
        System.out.println("─".repeat(80));
        
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        System.out.println("Total: " + Funcionario.formatarNumero(total));
        System.out.println();
    }
    
    private void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        System.out.println("3.12 - SALÁRIOS EM QUANTIDADE DE SALÁRIOS MÍNIMOS:");
        System.out.println("─".repeat(80));
        
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.calcularSalariosMinimos(SALARIO_MINIMO);
            System.out.println(f.getNome() + ": " + Funcionario.formatarNumero(salariosMinimos) + " salários mínimos");
        });
        System.out.println();
    }
}
