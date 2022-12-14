# Collections

## Listas(List)
É uma estrutura de dados que cria uma lista ordenada de elementos. Ela pode ser mutável ou imutável, contendo métodos específicos para isso: listOf() sempre vai criar uma lista imutável, enquanto o mutableListOf() vai sempre criar uma lista mutável.

```kotlin
val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3) //1

val sudoers : List<Int> = systemUsers //2

fun addSystemUser(newUser: Int){ //3
    systemUser.add(newUser)
}

fun getSysSudoers(): List<Int>{ 4
    return sudoers
}

fun main() {
    addSystemUser(4) //5
    println("Total sudoers; ${getSysSudoers().size}") //6
    getSysSudoers().forEach{ //7
        i -> println("Some useful info on user $i")
    }
    getSysSudoers().add(5) //8
}
```

1. Cria uma lista mutável em uma instância imutável(tipo val)
2. Cria uma exibição somente de leitura da lista mutável, também em uma instância imutável(tipo val)
3. A função que adiciona um novo item na lista mutável
4. A função retorna a lista imutável
5. Adiciona à lista mutável um novo elemento. As exibições também são atualizadas por referem-se ao mesmo objeto.
6. Recupera o tamanho total da lista, somente leitura.
7. Itera a lista e imprime os seus elementos.
8. A tentativa de adicionar um novo elemento em uma lista de somente na leitura causa um erro de compilação.

## Conjuntos(Set)

É uma coleção não ordenada que não suporta duplicatas. Para criar conjuntos, existem funções setOf() e mutableSetOf().

```kotlin
val openIssues:MutableSet<String> = mutableSetOf("uniqueDescr1", "uniqueDescr2","uniqueDescr3")     //1

fun addIssues(uniqueDesc:String):Boolean{
    return openIssues.add(uniqueDesc)   //2
}

fun getStatusLog(isAdded: Boolean):String{
    return if(isAdded)"registered correctly" else "marked as duplicated and rejected."  //3
}

fun main(){
    val aNewIssue:String = "uniqueDescr4"
    var anIssueAlreadyIn:String = "uniqueDescr2"

    println("Issue $aNewIssue ${getStatusLog(addIssues(aNewIssue))}")   //4
    println("Issue $anIssueAlreadyIn ${getStatusLog(addIssues(anIssueAlreadyIn))}")     //5
}
```
1. Uma variável imutável recebendo uma referência mutável com 3 elementos
2. Criada função que recebe uma descrição e adiciona a lista mutável
3. Função que retorna uma string informando o status de entrada do elemento.
4. Verifica se foi adicionado com sucesso um elemento novo.
5. Verifica se foi adicionado com sucesso um elemento duplicado ou se rejeita por ser um elemento duplicado.

```bash
Issue uniqueDescr4 registered correctly
Issue uniqueDescr2 marked as duplicated and rejected
```

## Mapas(Map)

É uma coleção de pares chave/valor, onde cada chave é única e é usada para recuperar o valor correspondente. Para criar mapas, existem funções mapOf() e mutableMapOf(). Uma exibição somente de leitura de um mapa mutável pode ser obtida convertendo-a *Map*.

```kotlin
const val POINT_X_PASS: Int = 15

val EZPassAccounts: MutableMap<Int,Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)   //1

val EZPassReport> Map<Int, Int> = EZPassAccounts  //2

fun updatePointsCredit(accountId:Int){
    if(EZPassAccounts.containsKey(accountId)){  //3
        println("Updating $accountId...")
        EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + POINT_X_PASS   //4
    } else {
        println("Error: Trying to update a non-existing account (id: $accountId)")
    }
}

fun accountsReport(){
    println("EZ-Pass report:")
    EZPassReport.forEach{   //5
        k, v -> println("ID $k: credit $v")
    }
}

fun main() { //6
    accountsReport() // exibe relatorio e verifica se todas as contas tem 100 pontos
    updatePointsCredit(1) // conta 1 soma 15 total 115
    updatePointsCredit(1) // conta 1 soma 15 total 130
    updatePointsCredit(5) // conta 5 não existe, mostra erro 
    accountsReport()    // exibe relatório

}

```
1. Criada variável, um mapa mutável com 3 elementos e seus respectivos valores. 
2. Cria uma cópia somente de leitura desses elementos
3. Função que verifica se o elemento chamada no parâmetro da função tem algum valor, se tiver então ele atualiza somando ao valor constante POINT_X_PASS
4. Caso nao exista no mapa ele devolve um erro
5. Imprime os pares chave/valor 
6. Teste das funções

```bash
EZ-Pass report:
ID 1: credit 100
ID 2: credit 100
ID 3: credit 100
Updating 1...
Updating 1...
Error: Trying to update a non-existing account 5
EZ-Pass report:
ID 1: credit 130
ID 2: credit 100
ID 3: credit 100

```
## Funções Úteis

### filter()

Permite filtrar coleções. Leva um predicado de filtro como um parâmetro lambda. O predicado é aplicado a cada elemento. Os elementos que compõem o predicado são retornados na coleção de resultados.

```kotlin
val numbers = listOf(1,-2,3,-4,5,-6)    //1

val positives = numbers.filter{x -> x > 0}  //2

val negatives = numbers.filter{it < 0}  //3
```

1. Define a coleção de números
2. Obtém os números positivos
3. Usa o it, notação mais curta para obter números negativos

### map()

A função map permite modificar todos os elementos em uma coleção.

```kotlin
val numbers = listOf(1,-2,3,-4,5,-6)    //1

val doubled = numbers.map{x -> x * 2}  //2

val tripled = numbers.map{it * 3}  //3
```

1. Define a coleção de números
2. Transforma os números dobrados
3. Transforma os números triplicados

### any(), all() e none()

Essas funções verificam a  existência de elementos de coleção que correspondem a um determinado predicado.

- **any** retorna *true* se a coleção contiver PELO MENOS UM elemento que correponda ao predicado.
- **all** retorna *true* se TODOS os elementos da coleção corresponderem ao predicado fornecido.
- **none** retorna *true* se NÃO HOUVER elementos que correspondam ao predicado fornecido.

### flatMap()
Transforma cada elemento de uma coleção em um objeto iterável e cria uma única lista dos resultados da transformação.

```kotlin
fun main() {

    val fruitBag = listOf("apple", "oranges","banana","grapes") //1
    val clothesBag = listOf("shirts","pants","jeans") //2 
    val cart = listOf(fruitBag, clothesBag) //3
    val mapBag = cart.map{it} //4
    val flatMapBag = cart.flatMap{it} //5


    println("Your bags are: $mapBag")
    println("The things you bought are: $flatMapBag")
}

```

1. Define uma coleção de frutas
2. Define uma coleção de roupas
3. Adiciona as coleções de frutas e roupas a um lista
4. Constrói uma lista *map* , que é uma lista de duas listas
5. Constrói uma lista *flatMap*, que é uma única lista composta por elementos de ambas as listas