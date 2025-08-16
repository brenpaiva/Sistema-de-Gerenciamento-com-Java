import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * Classe que representa um funcionário, estendendo a classe Pessoa.
 */
public class Funcionario extends Pessoa implements Comparable<Funcionario> {
    private BigDecimal salario;
    private String funcao;
    
    // Constantes para formatação
    private static final Locale LOCALE_BR = Locale.forLanguageTag("pt-BR");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS;
    private static final DecimalFormat NUMBER_FORMATTER;
    
    static {
        DECIMAL_FORMAT_SYMBOLS = new DecimalFormatSymbols(LOCALE_BR);
        DECIMAL_FORMAT_SYMBOLS.setDecimalSeparator(',');
        DECIMAL_FORMAT_SYMBOLS.setGroupingSeparator('.');
        NUMBER_FORMATTER = new DecimalFormat("#,##0.00", DECIMAL_FORMAT_SYMBOLS);
    }
    
    /**
     * Construtor da classe Funcionario.
     * 
     * @param nome Nome do funcionário
     * @param dataNascimento Data de nascimento
     * @param salario Salário (deve ser positivo)
     * @param funcao Função do funcionário (não pode ser null ou vazio)
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salário deve ser positivo");
        }
        if (funcao == null || funcao.trim().isEmpty()) {
            throw new IllegalArgumentException("Função não pode ser null ou vazia");
        }
        
        this.salario = salario;
        this.funcao = funcao.trim();
    }
    
    public BigDecimal getSalario() {
        return salario;
    }
    
    public String getFuncao() {
        return funcao;
    }
    
    public void setSalario(BigDecimal salario) {
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salário deve ser positivo");
        }
        this.salario = salario;
    }
    
    public void setFuncao(String funcao) {
        if (funcao == null || funcao.trim().isEmpty()) {
            throw new IllegalArgumentException("Função não pode ser null ou vazia");
        }
        this.funcao = funcao.trim();
    }
    
    /**
     * Aplica um aumento percentual ao salário.
     * 
     * @param percentual Percentual de aumento (ex: 10 para 10%)
     */
    public void aplicarAumento(double percentual) {
        if (percentual < 0) {
            throw new IllegalArgumentException("Percentual não pode ser negativo");
        }
        
        BigDecimal fatorAumento = BigDecimal.valueOf(1 + (percentual / 100));
        this.salario = this.salario.multiply(fatorAumento).setScale(2, RoundingMode.HALF_UP);
    }
    
    /**
     * Calcula quantos salários mínimos o funcionário ganha.
     * 
     * @param salarioMinimo Valor do salário mínimo
     * @return Quantidade de salários mínimos
     */
    public BigDecimal calcularSalariosMinimos(BigDecimal salarioMinimo) {
        if (salarioMinimo == null || salarioMinimo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salário mínimo deve ser positivo");
        }
        
        return salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    }
    
    /**
     * Verifica se o funcionário faz aniversário no mês especificado.
     * 
     * @param mes Mês (1-12)
     * @return true se faz aniversário no mês
     */
    public boolean fazAniversarioNoMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês deve estar entre 1 e 12");
        }
        return getDataNascimento().getMonthValue() == mes;
    }
    
    @Override
    public int compareTo(Funcionario outro) {
        return this.getNome().compareToIgnoreCase(outro.getNome());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        
        Funcionario that = (Funcionario) obj;
        return Objects.equals(salario, that.salario) && 
               Objects.equals(funcao, that.funcao);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salario, funcao);
    }
    
    @Override
    public String toString() {
        String dataFormatada = getDataNascimento().format(DATE_FORMATTER);
        String salarioFormatado = NUMBER_FORMATTER.format(salario);
        
        return String.format("Nome: %s | Nascimento: %s | Salário: %s | Função: %s", 
                           getNome(), dataFormatada, salarioFormatado, funcao);
    }
    
    // Métodos estáticos para formatação
    public static String formatarNumero(BigDecimal numero) {
        return NUMBER_FORMATTER.format(numero);
    }
    
    public static DateTimeFormatter getDateFormatter() {
        return DATE_FORMATTER;
    }
}
