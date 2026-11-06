# Estrutura 

## Árvore de arquivos
```
Cantina-CESAR/
├── docs/            ESTRUTURA.md · ANTES_E_DEPOIS.md 
├── README.md
└── src/            
    ├── Main.java                 monta as peças e inicia o menu
    ├── MenuPrincipal.java        lista os Modos e executa o escolhido
    ├── Modo.java                 interface: titulo(), executar()
    ├── FluxoDePedido.java        Modo: pedido, pagamento e status
    ├── ModoSimulador.java        Modo: fila única x filas separadas
    ├── Console.java              leitura validada do teclado
    ├── EntradaEncerradaException.java
    ├── Tela.java                 formatação de cardápio, resumo e status
    ├── Dinheiro.java             "R$ 13,50"
    ├── Item.java · TipoItem.java · Coxinha · Refrigerante · Tapioca · Cuscuz
    ├── Cardapio.java             catálogo de itens
    ├── Pagamento.java · Pix · CartaoCredito · ValeRefeicao
    ├── MeiosDePagamento.java     catálogo de pagamentos
    ├── Pedido.java · StatusPedido.java
    ├── Fila.java · FilaRapida · FilaPreparo · FilaUnica
    ├── EstimadorDeTempo.java · EstimadorPorFilas · EstimadorFilaUnica
    ├── PoliticaDeTempo.java      regra do "dá tempo?"
    └── Aluno · GeradorDeAlunos · Simulador · ResultadoSimulacao
```

## Quem faz o quê
| Classe | Responsabilidade |
|---|---|
| `Main` | raiz de composição: escolhe as implementações e liga tudo |
| `Console` | única que lê o teclado; repete a pergunta se a entrada for inválida |
| `Tela` | só apresentação (usa `Dinheiro` e `TipoItem.rotulo()`) |
| `Pedido` | itens e status privados; `adicionar`, `itens()` (cópia imutável), `itensCom`, `total`, `pagarCom`, `avancar` |
| `StatusPedido` | enum CRIADO → PAGO → EM_PREPARO → PRONTO; `proximo()` impede passos inválidos |
| `PoliticaDeTempo` | `tempo do estimador + 3 min para comer <= minutos que faltam` |
| `EstimadorPorFilas` | tempo do pedido = tempo da **fila mais lenta** |
| `EstimadorFilaUnica` | soma tudo (a cantina de hoje) |
| `FluxoDePedido` | orquestra o diálogo do pedido, sem regra de tempo nem formatação |
| `Simulador` | para cada aluno, escolhe a fila, calcula início e fim; desiste se não der tempo de comer |

## Fluxo do pedido
```
1. Console.lerInteiro: minutos (1 a 15)
2. loop: Tela.cardapio -> escolha -> PoliticaDeTempo.minutosNecessarios <= minutos? adiciona : recusa
3. Tela.resumo
4. loop de pagamento: escolha (0 cancela) -> pedido.pagarCom(meio); recusado = tenta outro
5. pedido.avancar(): PAGO -> EM PREPARO -> PRONTO
```

## Prova de Aberto/Fechado
**Novo meio de pagamento (ex.: pagar em espécie):** criar `Especie.java` implementando `Pagamento` (o nome `Dinheiro` já é da classe de formatação) e acrescentar `new Especie()` em `MeiosDePagamento.padrao()`. `Pedido`, `FluxoDePedido`, `Pix` e as demais não mudam.

**Nova fila (ex.: FilaBebidas):** criar a classe implementando `Fila` e incluí-la na lista do `Main`. `PoliticaDeTempo`, `FluxoDePedido` e `Simulador` não mudam.

**Novo cálculo de tempo:** criar uma classe `EstimadorDeTempo` e trocar uma linha no `Main`. Foi assim que `EstimadorPorFilas` entrou: o fluxo do pedido não foi alterado.
