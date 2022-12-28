# Infix Functions
São funções que podem ser convertidas em funções de membros ou extensões de um unico parâmetro que trazem algumas possibilidade de uso que tornam o código mais legível, idiomático.. 

```kotlin
fun main(){
    infix fun Int.times(str: String) = str.repeat(this)     //1
    println(2 times "Bye")                                  //2

    val pair = "Ferrari" to "Katrina"                           //3
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)    //4
    val myPair = "McLaren" onto "Lucas"
    println(myPair)


    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia                                        //5


}

class Person(val  name:String){
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other:Person){
        likedPeople.add(other)                                  //6
    }
}


```

1. Define uma função extensão Infix do tipo Int, como a definição de times, recebendo uma string como parâmetro
2. Chama a função Infix imprimindo 2x o texto "Bye"
3. Cria um par de elementos com definição *to* da biblioteca padrão
4. Também cria um par de elementos agora com a definição *onto*, recendo uma String com parâmetro, tem a mesma caracteristica de *to*
5. A notação Infix também funciona em funções membros que nós mesmos podemos criar(no item 6)
6. A classe Person tem uma infix function que recebe um outro Person como parâmetro, e o que ela faz é adicionar uma outra Person em uma lista mutável


# Operator Functions

Dando continuidade chegamos as funções de operadores, sao certas que podem ser atualizadas para operadores, permitindo que suas chamadas com o símbolo do operador correspondente.

```kotlin
fun main() {
    operator fun Int.times(str:String) = str.repeat(this)           //1
    println(2 * "Bye")                                              //2

    operator fun String.get(range: IntRange) = substring(range)     //3
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])                                             //4
}
```

1. Temos uma função como operator como modificador do tipo Int com extensão *times* e recebe uma string como parâmetro
2. Através do operador de multiplicação imprime quantas vezes o texto é repetido
3. Temos uma função operator do tipo string que receb uma range de inteiros como parâmetro 
4. e imprime, seguindo o range de 0 a 14, o numero de caracteres da String


# Higher Order Functions
São funções que recebem outras funções como parâmetro ou retornar essas outras funções como resposta da sua própria função.

## Taking Functions as Parameters

```kotlin
fun calculate (X:Int, y:Int, operation:(Int, Int)->Int): Int{       //1
    return operation(x, y)                              //2
}

fun sum(x: Int, y: Int) = x + y                         //3

fun main(){
    val sumResult = calculate(4,5,::sum)                //4
    val mulResult = calculate(4, 5){
        a, b -> a * b                                   //5
    }

    println("sumResult $sumResult, mulResult $mulResult")
}
```

1. Aqui temos uma função *calculate* que recebe x e y como parâmetros do tipo Inteiro e recebe também uma operação como outra função no parâmetro; essa função recebe dois Inteiros e retorna um Inteiro
2. O retorno do calculate será essa função *operation* que recebe x e y para executar a instrução
3. Temos uma função normal de soma
4. podemos usar a função calculate e torná-la mais genérica ainda, passando dois Inteiros e a função *sum* como parâmetro
5. O uso da função calculate passando uma função dinâmica, através de uma expressão lambda, para determinar a multiplicação dos dois valores o calculo será de multiplicação entre 4 e 5

## Returning Functions
Como criar uma função de alta ordem para retornar uma função dentro de outra função

```kotlin
fun operation():(Int)->Int{                     //1
    return ::square
}

fun square(x: Int) = x * x                      //2

fun main(){
    val func = operation()                      //3
    println(func(2))                            //4
}
```

1. Temos uma função *operation* que retorna a função square através do operador de chamada (::).
2. A função *square* recebe um Int como parâmetro e retorna a multiplicação desses valores.
3. Guarda em uma variável imutável a chamada da função operation(), transformando a variável em uma função.
4. Imprime a variável com um parâmetro.

# Lambda Functions

São uma maneira simples de criar funções ad-hoc. Lambdas pode ser denotado de forma muito concisa em muitos casos, graças a inferência de tipo e à variável implícita *it*.

```kotlin
fun main(){
    val upperCase1 : (String) -> String = {str: String -> str.uppercase()}  //1

    val upperCase2 : (String) -> String = { str -> str.uppercase()}         //2

    val upperCase3 = { str: String -> str.uppercase()}                      //3

    //val upperCase4 = { str -> str.uppercase()}                            //4

    val upperCase5 : (String) -> String = {it.uppercase()}                  //5

    val upperCase6 : (String) -> String = String::uppercase                 //6

}

```

1. Uma lambda em toda a sua glória, com tipos explícitos em todos os lugares. O lambda é a parte entre chaves, que é atribuída a uma variável do tipo *(String) -> String*.
2. Inferência de tipo dentro da lambda: o tipo do parâmetro lambda é inferido a partir do tipo da variável à qual está atribuido.  
3. Inferência de tipo fora da lambda: o tipo da variável é inferido a partir do tipo do parâmetro lambda e do valor de retorno.
4. Você não pode fazer dessa forma, o compilador não tem chance de inferir o tipo dessa maneira.
5. Para lambdas com um único parâmetro, você não precisa nomeá-lo explicitamente. Em vez disso, você pode usar a variável implícita. Isso é especialmente útil quando o tipo *it* pode ser inferido.
6. Se seu lambda consiste em uma única chamada de função, você pode usar ponteiros de função (::).

# Extension Functions e Properties

Kotlin permite adicionar novos membro a qualquer classe com mecanismo de extensões, ou seja, existem dois tipo de extensões: funções de extensão e propriedades de extensão. Eles se parecem muito com função e propriedades normais, mas com uma diferença importante: você precisa especificar o tipo que estende.

```kotlin
data class Item(val name: String, val price: Float)         //1

data class Order(val items: Collection<Item>)

fun Order.maxPricedItemValue(): Float = this.items.maxByOrNull { it.price} ?.price ?: 0F    //2

fun Order.maxPricedItemName() = this.items.maxByOrNull {it.price} ?.name ?: "NO_PRODUCTS"

val Order.commaDelimitedItemNames: String                               //3
    get() = items.map {it.name}.joinToString()

fun main(){

// criar uma variável com uma lista de itens com nomes e valores
    val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))


    println("Max priced item name: ${order.maxPricedItemName()}")               //4
    println("Max priced item value: ${order.maxPricedItemValue()}")
    println("Items: ${order.commaDelimitedItemNames}")                          //5
}
```
1. Define modelos simples de **data class**  de *Item* (com nome e preço) e *Order*. Order pode conter uma coleção de objetos Item.  
2. Criação de *extension functions* para o tipo Order. A primeira faz um filtro para buscar o item pelo preço e caso não tenha retorna 0. A segunda também faz um filtro para busca do item pelo nome e caso não ache retorna "NO_PRODUCTS".
3. Declaração de uma variável imutável que é uma propriedade dentro da *Order*, retorna um map dos itens passando o nome concatenado em forma de string separados por vírgula. é uma forma de encapsular com o operador get() e retornar algum valor baseado nessa propriedade que foi criada.
4. Chama as funções de extensaõ diretamente em uma instância de Order.
5. Acessa a propriedade de extensaõ em uma instância de Order.

```bash
Max priced item name: Wine
Max priced item value: 29.0
Items: Bread, Wine, Water

```
# Extension Function Generics
Além desse exemplo que foi explorado acima, há um exemplo ainda mais genérico. É até possivel executar extensões em referências nulas. Em uma função de extensão, você pode verificar o objeto **null** e usar o resultado em seu código.

```kotlin
fun <T> T.?nullSafeToString() = this?.toString() ?: "Ausência de valor" //1

fun main(){
    println(null.nullSafeToString())  // NULL
    println("Kotlin".nullSafeToString()) //Kotlin
}
```
# Suspend Functions
É uma função que será executada em um escopo de co-rotinas, que é uma abstração que facilita o consumo e manipulação de execuções assíncronas, por exemplo acessar uma API-HTTP, ou acessar um Banco de Dados e etc.

```kotlin


//  Exemplo básico de uma função suspensa com Coroutines.
 
// [Coroutines basics](https://kotlinlang.org/docs/coroutines-basics.html)


import kotlinx.coroutine.*

fun main(){
    doWorld()
}

suspend fun doWorld() = coroutineScope {
    launch{                     //1
        delay(1000L)
        println("World!")
    }

    println("Hello ")           //2
}
```

1. Nesta suspend function temos uma chamada de launch que mesmo que a função *doWorld* carregue o launch primeiro ela será executada depois do Hello, por causa do **delay()**
2. imprime Hello 


