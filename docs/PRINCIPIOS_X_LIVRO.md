# Princípios de projeto: livro × Cantina CESAR

Relação entre os conceitos do livro *Engenharia de Software Moderna* (Marco Tulio Valente) e o código do app. A maior parte está no **Capítulo 5 (Princípios de Projeto)**, com ligações aos capítulos 6 (Padrões), 8 (Testes) e 9 (Refactoring).

## Mapa rápido

| Princípio | Onde no livro | Onde no código |
|---|---|---|
| Responsabilidade Única | Cap. 5, SOLID | `Main`, `Console`, `Tela`, `FluxoDePedido`, `PoliticaDeTempo`, `Cardapio` |
| Aberto/Fechado | Cap. 5, SOLID | `EstimadorPorFilas` e `ModoSimulador` entraram como classes novas |
| Inversão de Dependências | Cap. 5, SOLID | `PoliticaDeTempo` depende de `EstimadorDeTempo`; só o `Main` conhece classes concretas |
| Segregação de Interfaces | Cap. 5, SOLID | `Item`, `Pagamento`, `Fila`, `EstimadorDeTempo`, `Modo` (1 a 4 métodos) |
| Ocultamento de informação | Cap. 5, Ocultamento de Informação | `Pedido` com itens e status privados |
| Coesão | Cap. 5, Coesão | `StatusPedido` e `Dinheiro` |
| Acoplamento | Cap. 5, Acoplamento | `FluxoDePedido` não conhece `Coxinha`, `Pix` nem `FilaRapida` |
| Lei de Demeter | Cap. 5, SOLID e outros princípios | `pedido.pagarCom(...)` (parcial, ver 8) |
| Substituição de Liskov | Cap. 5, SOLID | filas e estimadores intercambiáveis |
| Composição em vez de herança | Cap. 5, SOLID e outros princípios | `PoliticaDeTempo` tem um estimador; `EstimadorPorFilas` tem filas |
| Integridade conceitual | Cap. 5, Integridade Conceitual | itens, pagamentos e filas seguem o mesmo padrão |
| Métricas | Cap. 5, Métricas | complexidade ciclomática do `Main`: 11 → 2 |

## Princípio por princípio

### 1. Responsabilidade Única
**No livro:** uma classe deve ter um único motivo para mudar. É uma consequência direta da busca por coesão.
**No código:** o `Main` só monta o sistema, o `Console` só lê, a `Tela` só mostra, o `FluxoDePedido` só orquestra, a `PoliticaDeTempo` guarda a regra do "dá tempo?" e o `Cardapio` guarda os itens.
**Teste prático:** se o formato da tela mudar, a regra de tempo muda junto? Não, porque estão em classes diferentes.

### 2. Aberto/Fechado
**No livro:** classes abertas para extensão e fechadas para modificação, em geral com interfaces e padrões como Strategy.
**No código:** `Fila`, `EstimadorDeTempo`, `Item`, `Pagamento` e `Modo` são pontos de extensão. A evidência é histórica: o requisito das duas filas veio da pesquisa com a cantina, e `EstimadorPorFilas` entrou como classe nova, sem mexer no `FluxoDePedido`.

### 3. Inversão de Dependências
**No livro:** clientes dependem de abstrações, não de implementações concretas.
**No código:** `PoliticaDeTempo` recebe um `EstimadorDeTempo` no construtor. O `Main` é o único lugar que cria as classes concretas e liga as peças, por isso trocar a fila ou o estimador exige mudar só ele.

### 4. Segregação de Interfaces
**No livro:** interfaces pequenas e específicas, para que ninguém dependa de métodos que não usa.
**No código:** `Item`, `Pagamento`, `Fila`, `EstimadorDeTempo` e `Modo` têm de 1 a 4 métodos. O que importa não é o número, e sim que cada cliente use o que a interface oferece: o `Simulador`, por exemplo, só precisa de `aceita()` e `atendentes()` da `Fila`.

### 5. Ocultamento de informação
**No livro:** detalhes internos ficam escondidos, e só o necessário é exposto.
**No código:** `Pedido` mantém `itens` e `status` privados. `itens()` devolve uma cópia imutável, e o status só muda por `pagarCom()` e `avancar()`. Isso impede, por exemplo, marcar como pronto um pedido que não foi pago. `CartaoCredito.limite` e `ValeRefeicao.saldo` também são privados.

### 6. Coesão
**No livro:** os membros de uma classe coesa trabalham juntos para um mesmo propósito.
**No código:** `StatusPedido` concentra as transições de estado (`proximo()`), e `Dinheiro` concentra a formatação de valores (`R$ 13,50`).

### 7. Acoplamento
**No livro:** acoplamento é o grau de dependência entre classes. Quanto menor e mais apoiado em interfaces estáveis, mais fácil evoluir o sistema.
**No código:** `FluxoDePedido` não conhece `Coxinha`, `Pix` nem `FilaRapida`. O acoplamento ficou concentrado no `Main`, e por isso o CBO dele subiu (ver `ANTES_E_DEPOIS.md`).

### 8. Lei de Demeter
**No livro:** um método conversa só com o próprio objeto, seus atributos, seus parâmetros e os objetos que cria, sem encadear chamadas pela estrutura de outros objetos.
**No código:** `pedido.pagarCom(pagamento)` e `pedido.itensCom(item)` deixam o comportamento dentro do `Pedido`. **Ponto a melhorar:** a `Tela` ainda faz `pedido.status().rotulo()` e `item.tipo().rotulo()`. Um método como `pedido.rotuloDoStatus()` resolveria.

### 9. Substituição de Liskov
**No livro:** uma implementação deve poder entrar no lugar do tipo base sem quebrar o programa.
**No código:** `FilaUnica`, `FilaRapida` e `FilaPreparo` entram uma no lugar da outra no `Simulador`, e `EstimadorPorFilas` e `EstimadorFilaUnica` entram um no lugar do outro na `PoliticaDeTempo`. É o que torna o Aberto/Fechado seguro.

### 10. Composição em vez de herança
**No livro:** preferir composição, porque herança cria um acoplamento forte entre superclasse e subclasse.
**No código:** `PoliticaDeTempo` tem um estimador, e `EstimadorPorFilas` tem filas. Nenhuma classe de domínio herda de outra; elas só implementam interfaces.

### 11. Integridade conceitual
**No livro:** o sistema deve parecer desenhado por uma mente só, com soluções parecidas para problemas parecidos.
**No código:** itens, pagamentos e filas seguem o mesmo padrão (interface + implementações), e os catálogos `Cardapio` e `MeiosDePagamento` funcionam do mesmo jeito.

### 12. Métricas
**No livro:** métricas ajudam a avaliar propriedades como tamanho, coesão, acoplamento e complexidade.
**No código:** a complexidade ciclomática do `Main` caiu de 11 para 2. Os números e o método de contagem estão em `docs/ANTES_E_DEPOIS.md`.

## Como os princípios se reforçam
- **Ocultamento → Demeter:** se a estrutura interna não é exposta, não há o que encadear.
- **Coesão → Responsabilidade Única → Segregação de Interfaces:** classes e interfaces focadas em um propósito.
- **Inversão de Dependências → baixo acoplamento:** depender de interfaces reduz o que cada classe precisa conhecer.
- **Liskov → Aberto/Fechado:** a extensão só é segura se a nova classe respeita o contrato.

## Ligações com outros capítulos
- **Cap. 6, Padrões de Projeto:** a troca de estimadores e filas é o padrão **Strategy**. `Cardapio.padrao()` e `MeiosDePagamento.padrao()` lembram um **método fábrica**.
- **Cap. 8, Testes:** as dependências por interface facilitam testes com objetos falsos (ex.: testar `PoliticaDeTempo` com um estimador que devolve um tempo fixo). O projeto ainda não tem testes automatizados.
- **Cap. 9, Refactoring:** o "antes e depois" do `Main` é a refatoração de uma classe que concentrava responsabilidades demais.

## Pontos de atenção
- **Abstração na medida:** para 4 itens de cardápio, o número de interfaces é maior que o mínimo necessário. A escolha é didática: mostrar os princípios do cap. 5 funcionando.
- **Uso de IA:** a IA ajudou a escrever o código, e a equipe revisou e sabe justificar cada princípio com um trecho concreto.

## Referência
VALENTE, Marco Tulio. *Engenharia de Software Moderna: Princípios e Práticas para Desenvolvimento de Software com Produtividade*. Independente, 2020. Versão online: engsoftmoderna.info.
