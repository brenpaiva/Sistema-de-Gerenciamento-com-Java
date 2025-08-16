# Sistema de Gerenciamento de Funcionários

## 📋 Descrição
Sistema de gerenciamento de funcionários desenvolvido em Java. Implementa operações completas de CRUD, relatórios e cálculos salariais com formatação brasileira.

## 🎯 Funcionalidades Implementadas
- ✅ Cadastro e remoção de funcionários
- ✅ Aplicação de aumentos salariais (10%)
- ✅ Agrupamento por função
- ✅ Relatórios de aniversariantes
- ✅ Ordenação alfabética
- ✅ Cálculos de totais e salários mínimos
- ✅ Formatação brasileira (dd/MM/yyyy e valores monetários)

## 🏗️ Estrutura do Projeto
```
├── Pessoa.java          # Classe base (nome, data nascimento)
├── Funcionario.java     # Classe funcionário (salário, função)
├── Principal.java       # Execução dos requisitos do teste
└── README.md           # Documentação
```

## 🚀 Como Executar
```bash
# Compilar
javac Pessoa.java Funcionario.java Principal.java

# Executar
java Principal
```

## 💡 Tecnologias e Boas Práticas
- **Java 8+** com LocalDate, BigDecimal, Streams API
- **Validações** de entrada e tratamento de exceções
- **Clean Code** com JavaDoc e métodos bem definidos
- **Formatação brasileira** para datas e valores monetários
- **Herança e encapsulamento** seguindo princípios OOP

## 📊 Exemplo de Saída
```
=== TESTE PRÁTICO INIFLEX ===
3.3 - TODOS OS FUNCIONÁRIOS:
Nome: Maria | Nascimento: 18/10/2000 | Salário: 2.209,38 | Função: Operador
...
3.11 - TOTAL DOS SALÁRIOS: 54.471,68
3.12 - Maria: 1,82 salários mínimos
```

Desenvolvido seguindo as melhores práticas de programação Java para demonstrar conhecimento técnico e
