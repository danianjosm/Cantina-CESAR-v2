# Cantina CESAR


## O que é o app
No intervalo, a fila da cantina come boa parte do tempo. Pior: quem quer um item rápido, como a coxinha, espera na mesma fila de quem pediu um item demorado, como a tapioca. Muita gente desiste ou compra fora.

O **Cantina CESAR** é um app de console (PoC) que ajuda o aluno a **se planejar** e responde: **"ainda dá tempo de pegar isso?"**

**Modo pedido**
- Pergunta quantos minutos faltam no intervalo
- Mostra o cardápio com **preço, tempo de preparo e tipo** de cada item (rápido ou preparado na hora)
- Calcula quando o pedido fica pronto pelas **duas filas** (rápida e de preparo), que andam ao mesmo tempo: vale a **fila mais lenta**. Ex.: coxinha (2 min) + cuscuz (10 min) = pronto em ~10 min, e não 12
- **Avisa se ainda dá tempo** antes de adicionar cada item, e alerta quando vai sobrar pouco tempo para comer
- Recebe o **pagamento** por Pix, cartão ou vale-refeição
- Mostra o **status do pedido**: PAGO → EM PREPARO → PRONTO
- Não fecha com entrada inválida: texto ou opção fora da lista fazem o app perguntar de novo

**Modo simulador:** 30 alunos chegando ao longo de 15 minutos, comparando quantos conseguem comer com **fila única** e com **filas separadas** (ver [Simulador do intervalo](#simulador-do-intervalo)).

## Pesquisa com a cantina

O app foi construído a partir de 3 pesquisas: uma com alunos e duas com a cantina (Quiosque Imperial Brum).

### 1. Entrevistas com alunos (30/10)
6 entrevistas sobre a experiência no intervalo, com alunos da própria equipe (amostra pequena; ainda não ouvimos alunos de fora).

| O que ouvimos | Quantos |
|---|---|
| Fila grande ou espera longa | 4 de 6 |
| Desistiu ou mudou de plano por causa da fila | 3 de 6 |
| Foi tranquilo quando havia pouco movimento | 3 de 6 |
| Pagar foi a pior parte | 2 de 6 |
| Desistiu por não saber o preço | 1 de 6 |

**Como contornam:** ir à cantina antes de todo mundo, ir em outro horário, perguntar o preço a conhecidos, comprar fora ou deixar de comprar.
**Consequências:** comer em sala, onde é proibido, e a cantina perde vendas.

### 2. Cantina, 1ª conversa (30/10)
Objetivo: entender **onde a fila trava**.

| Pergunta | Resposta |
|---|---|
| Qual o pior horário? | Os intervalos da noite e da manhã |
| O que mais trava o atendimento? | O preparo de comidas demoradas, como tapioca e cuscuz |
| O que mais sai? | Coxinha |
| Quantas pessoas trabalham? | Geralmente 2 |
| A coxinha já fica pronta? | Sim, mas é esquentada antes de entregar |

### 3. Cantina, 2ª conversa (05/11)
Objetivo: trocar as estimativas da Entrega 1 por **dados reais**.

| Pergunta | Resposta |
|---|---|
| Quanto tempo leva cada item? | Tapioca: 5 min · Cuscuz: 7 a 10 min · Coxinha (esquentar): 2 min |
| Quantas pessoas atendem por intervalo? | "Muitas". Não souberam dar um número |
| Dá para adiantar algum preparo? | A tapioca perde a qualidade; o cuscuz pode ser adiantado |
| Uma pessoa poderia ficar só nos itens rápidos e a outra só no preparo? | **Já trabalham assim:** uma prepara e a outra atende |
| Quanto custa cada item? | Fotografamos as 3 páginas do cardápio (em `docs/cantina/`) |

### O que a pesquisa mudou no projeto
- **As duas filas já existem na cozinha, mas o aluno só vê uma.** O app torna visível a divisão que a cantina já faz.
- **Cuscuz com 10 min (pior caso):** é melhor o app avisar um tempo maior e o pedido sair antes do que prometer um tempo menor e o aluno ficar esperando.
- **Simulador com 30 alunos:** como a cantina não soube dar um número, usamos o valor sugerido pelo professor.

### Cardápio usado no app

| Item | Tipo | Tempo | Preço |
|---|---|---|---|
| Coxinha de frango | Rápido | 2 min | R$ 12,00 |
| Refrigerante lata | Rápido | 30 s | R$ 8,00 |
| Tapioca de queijo | Preparado na hora | 5 min | R$ 12,00 |
| Cuscuz com queijo | Preparado na hora | 10 min | R$ 14,00 |

> O cardápio real tem mais de 30 itens. Escolhemos 2 rápidos e 2 preparados na hora, que bastam para mostrar as duas filas. O tempo do refrigerante (30 s) é estimativa da equipe, porque não foi perguntado à cantina.

## Simulador do intervalo

30 alunos chegam ao longo de 15 minutos, cada um pede 1 item e precisa de 3 minutos para comer. Os dois cenários têm os **mesmos 2 atendentes**: na fila única, os dois fazem tudo; nas filas separadas, um cuida dos itens rápidos e o outro do preparo, como a cantina já trabalha.

| Cenário | Média de 1000 turmas (quantos comem) |
|---|---|
| Fila única | 7,8 de 30 |
| Filas separadas | 8,2 de 30 |

**O que descobrimos:** esperávamos que separar as filas fizesse muito mais gente comer, mas, com os dados reais, a diferença é pequena. Com 2 atendentes para 30 alunos, o limite é a **capacidade**, e o gargalo é o **cuscuz de 10 minutos**. O ganho das filas separadas é a **previsibilidade**: quem pede coxinha não fica preso atrás da tapioca, e o app consegue dizer se ainda dá tempo.

**Próximo passo sugerido pelos dados:** a cantina contou que o cuscuz pode ser adiantado. Simular esse cenário é uma classe nova, sem mexer no resto.

## Decisões da stack
| Decisão | Escolha | Motivo |
|---|---|---|
| Linguagem | Java 17 | OO deixa os princípios do cap. 5 visíveis no código |
| Interface | Console | PoC rápida: foco no projeto, não na tela |
| Build | `javac` puro | sem dependências, roda em qualquer máquina com JDK |
| Dados | Em memória | sem banco nem deploy (não são exigidos) |
| IA | Claude | gerou a base do código; a equipe revisa e explica cada parte |

## Papel dos integrantes

**Entrega 2**

| Integrante | Papel |
|---|---|
| Athos | Código: refatoração do `Main`, `Pedido` encapsulado, filas, estimador de tempo e simulador; docs `ESTRUTURA.md` e `ANTES_E_DEPOIS.md` |
| Dani | README, repositório (histórico da v1, `main` protegida, fluxo de pull requests) e dados reais da cantina no código |
| França | 2ª conversa com a cantina: tempos de preparo, preços e fotos do cardápio |
| Caio | Revisão de pull requests e relação dos princípios com o livro (`docs/PRINCIPIOS_X_LIVRO.md`) |

<details>
<summary>Papéis da Entrega 1</summary>

| Integrante | Papel |
|---|---|
| Athos | Código principal (`Main`, `Pedido`) e repositório no GitHub |
| Dani | Vídeo: gravação, edição e legenda |
| Arthur | Classes de itens (`Item`, `Coxinha`, `Refrigerante`, `Tapioca`, `Cuscuz`) |
| Almeida | Classes de pagamento (`Pagamento`, `Pix`, `CartaoCredito`, `ValeRefeicao`) |
| Pierre | README e documentação (`docs/ESTRUTURA.md`) |
| Caio | Testes do fluxo no terminal e conferência dos princípios no código |
| André | Roteiro do vídeo e pesquisa (entrevistas, estimativas de preço e tempo) |

</details>

Todos os integrantes sabem explicar o projeto, e a IA ajudou a escrever o código.

## Como rodar

**Pré-requisito:** JDK 17 ou mais novo (não precisa de Maven, Gradle nem bibliotecas).

| Sistema | Instalar o JDK |
|---|---|
| Windows | `winget install EclipseAdoptium.Temurin.17.JDK` ou instalador em adoptium.net |
| macOS | `brew install --cask temurin@17` |
| Linux (Ubuntu/Debian) | `sudo apt install openjdk-17-jdk` |

Confira com `java -version` e `javac -version` (os dois devem mostrar 17 ou mais).

**Baixar o projeto:** `git clone <url-do-repositório>` (ou *Code → Download ZIP* e extrair).

**Rodar** (no terminal, dentro da pasta que contém `src/`):
```bash
javac -encoding UTF-8 -d out src/*.java
java -Dfile.encoding=UTF-8 -cp out Main
```
- O primeiro comando compila para a pasta `out/`; o segundo executa a classe `Main`.
- Funciona em CMD, Git Bash, Terminal do macOS, terminais Linux e no terminal do VS Code.
- No PowerShell, troque `src/*.java` por `(Get-ChildItem src\*.java)`.

**Alternativas:** abrir a pasta no IntelliJ ou VS Code (extensão *Extension Pack for Java*) e clicar em Run no `Main.java`; ou usar o **GitHub Codespaces** (*Code → Codespaces*), que já vem com Java.

## Onde estão os princípios no código

> A relação de cada princípio com o livro *Engenharia de Software Moderna* (Marco Tulio Valente), capítulo por capítulo, está em [`docs/PRINCIPIOS_X_LIVRO.md`](docs/PRINCIPIOS_X_LIVRO.md).

| Princípio | Onde | Como aparece |
|---|---|---|
| Responsabilidade Única | `Main` (só monta), `Console` (só lê), `Tela` (só mostra), `FluxoDePedido` (só orquestra), `PoliticaDeTempo` (regra do "dá tempo?"), `Cardapio` (itens) | o `Main` deixou de ter tela, regra de tempo e cardápio |
| Aberto/Fechado | `Fila`, `EstimadorDeTempo`, `Item`, `Pagamento`, `Modo` | `EstimadorPorFilas` e `ModoSimulador` entraram como classes novas, sem alterar o fluxo do pedido |
| Inversão de Dependências | `PoliticaDeTempo` depende de `EstimadorDeTempo`; `FluxoDePedido` depende de interfaces e catálogos | só o `Main` conhece as classes concretas |
| Segregação de Interfaces | `Item`, `Pagamento`, `Fila`, `EstimadorDeTempo`, `Modo` | todas com 1 a 4 métodos |
| Ocultamento de informação | `Pedido` (itens e status privados), `CartaoCredito.limite`, `ValeRefeicao.saldo` | `pedido.itens()` devolve cópia imutável; status só muda por `pagarCom` e `avancar` |
| Coesão | `StatusPedido` (transições num lugar só), `Dinheiro` (formatação) | cada classe usa todos os seus atributos |
| Acoplamento | `FluxoDePedido` não conhece `Coxinha`, `Pix` nem `FilaRapida` | trocar a fila no `Main` não toca no fluxo |
| Lei de Demeter | `pedido.pagarCom(pagamento)`, `pedido.itensCom(item)` | parcial: o `Pedido` não expõe sua estrutura, mas a `Tela` ainda encadeia `pedido.status().rotulo()` e `item.tipo().rotulo()` |
| Substituição de Liskov | `FilaUnica`, `FilaRapida`, `FilaPreparo` e os dois estimadores | qualquer um entra no lugar do outro sem quebrar `PoliticaDeTempo` nem `Simulador` |
| Composição em vez de herança | `PoliticaDeTempo` tem um estimador; `EstimadorPorFilas` tem filas | nenhuma classe de domínio herda de outra (só implementam interfaces) |
| Integridade conceitual | itens, pagamentos e filas seguem o mesmo padrão | catálogos `Cardapio` e `MeiosDePagamento` são análogos |
| Métricas | `docs/ANTES_E_DEPOIS.md` | complexidade ciclomática do `Main`: 11 → 2 |

Mais detalhes: `docs/ESTRUTURA.md` (classes e fluxo), `docs/ANTES_E_DEPOIS.md` 

