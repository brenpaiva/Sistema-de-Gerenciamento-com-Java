import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Classe que representa uma pessoa com nome e data de nascimento.
 */
public class Pessoa {
    private final String nome;
    private final LocalDate dataNascimento;
    
    /**
     * Construtor da classe Pessoa.
     * 
     * @param nome Nome da pessoa (não pode ser null ou vazio)
     * @param dataNascimento Data de nascimento (não pode ser null)
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public Pessoa(String nome, LocalDate dataNascimento) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser null ou vazio");
        }
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser null");
        }
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
        }
        
        this.nome = nome.trim();
        this.dataNascimento = dataNascimento;
    }
    
    public String getNome() {
        return nome;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    /**
     * Calcula a idade da pessoa em anos.
     * 
     * @return idade em anos
     */
    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Pessoa pessoa = (Pessoa) obj;
        return Objects.equals(nome, pessoa.nome) && 
               Objects.equals(dataNascimento, pessoa.dataNascimento);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nome, dataNascimento);
    }
    
    @Override
    public String toString() {
        return String.format("Nome: %s | Nascimento: %s", 
                           nome, 
                           dataNascimento.format(Funcionario.getDateFormatter()));
    }
}
