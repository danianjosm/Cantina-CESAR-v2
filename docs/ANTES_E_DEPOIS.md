# Antes (go-horse) e depois (PoC final)

## Lado a lado: regra de tempo
**Antes**, dentro do `Main`, misturada com leitura de teclado e tela:
```java
int op = Integer.parseInt(sc.nextLine().trim());   // texto = exceção, app fecha
...
if (pedido.tempo() + escolhido.tempo() + 3 <= minutos) {
    pedido.itens.add(escolhido);                    // lista pública
```
**Depois**:
```java
// PoliticaDeTempo
return estimador.minutosAtePronto(pedido.itensCom(novo)) + MINUTOS_PARA_COMER;
// FluxoDePedido
int opcao = console.lerInteiro("Escolha um item (0 para finalizar): ", 0, cardapio.itens().size());
pedido.adicionar(item);                              // valida estado do pedido
```

## O que mudou
| Princípio | Antes | Depois |
|---|---|---|
| Responsabilidade Única | `Main` com tela, regra de tempo, cardápio, pagamento e fluxo | cada uma dessas tarefas tem sua classe |
| Ocultamento de informação | `Pedido.itens` e `Pedido.status` públicos; status era `String` | privados, com métodos e `enum StatusPedido` |
| Aberto/Fechado | novo item ou pagamento exigia mexer no `Main` | novo item/pagamento = classe + 1 linha no catálogo; nova fila ou estimativa sem alterar o fluxo |
| Robustez | texto ou opção fora da lista derrubava o app | `Console.lerInteiro` pergunta de novo |
| Preço | `R$ 6.0` | `R$ 6,00`, `R$ 13,50` |

## Números 
Método: complexidade ciclomática = 1 + `if`, `for`, `while`, `case`, `catch`, `&&`, `||`, `?`; CBO = quantas outras classes do projeto a classe cita.

| Métrica | Antes | Depois |
|---|---|---|
| Complexidade ciclomática do `Main` | **11** | **2** |
| CBO do `Main` | 10 | 13 |
| Classes | 11 | 35 |

Panorama geral: a complexidade do `Main` dininuiu e nenhuma classe passa de 5. O CBO do `Main` **subiu**, e isso é esperado: ele virou a raiz de composição, o único lugar que conhece as classes concretas para ligá-las. O acoplamento ficou concentrado ali, e o resto (`FluxoDePedido`, `PoliticaDeTempo`) depende de interfaces. O preço foi ter mais classes, cada uma pequena.

