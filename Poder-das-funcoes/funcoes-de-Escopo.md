# Let

É uma funçao de escopo que pode exercer duas funções: uma função auxiliar para verificação de nulo ou função de escopo.
sempre a ultima instrução é a instução de retorno.

### Função de escopo

```kotlin
fun customPrint(s:String){
    println(s.uppercase())
}

fun main(){
    val empty = "test".let{     //1
        customPrint(it)         //2
        it.isEmpty()            //3
    }
    println("is empty: $empty")
}
```

1. Um texto ("test") que esta usando o let como função de escopo
2. dentro do let chama a função **customPrint** passando o "it" como parâmetro(por padrão) ou nome personalizavél  
3. retorno da variável *empty*


### Função auxiliar para verificação de nulo

Aqui temos uma função **printNonNull** que recebe uma string que pode ser nula...
```kotlin
fun main() {
    fun printNonNull(str: String?){
        println("Printing \"$str\":")

        str?.let{               //4
            println("\t")
            customPrint(it)
            println()
        }
    }
}
```

4. ...aqui a gente consegue fazer o *let* executar somente se a String não for nula.

```kotlin
printNonNull(null) // Printing "null":
printNonNull("my string") //Printing "my string": MY STRING
```
E como último exemplo caso queira encadear let's voce pode nomear o parâmetro "it"...


```kotlin
fun printIfBotNonNull(strOne: String?, strTwo: String?){
    strOne?.let{ firstString ->          //5
        strTwo?.let{ secondString -> 
            customPrint("$firstString : $secondString")
        }
    }

}
```
5. Na primeira estamos fazendo um let e chamamos de firstString, na segunda e chamamos de secondString, encadeamos dois let's que têm verificação de nulo e para testar chamamos o customPrint().

```kotlin
printIfBotNonNull("first", "second")     // FIRST : SECOND
```

# Run

É uma outra função de escopo da biblioteca padrão. Basicamente, ele faz o mesmo que o let, a diferença é que dentro do *run* do objeto é acessado por *this*. Isso é util quando você deseja chamar métodos do objeto em vez de passá-los como um argumento.

```kotlin
fun main() {
        
    fun getNullableLength(ns: String?){
        println("for \"$ns\":")
        ns?.run{                                        //1
            println("\t is empty? " + isEmpty())        //2
            length                                      //3
        }
    }
    getNullableLength(null)
    getNullableLength("")
    getNullableLength("some string with Kotlin")
   
}
```
1. Chama o bloco fornecido em uma variável anulável.
2. Dentro de *run*, os membros do objeto são acessados sem seu nome. Se tivesse usado o *let* teríamos que usar **this.isEmpty()** ou **it.isEmpty()**.
3. *run* retorna o length do dado String se não for nulo.

se for para retornar um *Int*, por exemplo, terìamos que colocar um return pra função run e um *Elvis Operator(?:)* no final da instrução...

```kotlin
fun main() {
        
    fun getNullableLength(ns: String?): Int{
        println("for \"$ns\":")
        return ns?.run{                                       
            println("\t is empty? " + isEmpty())        
            length                                      
        } ?: 0
    }
    getNullableLength(null)
    getNullableLength("")
    getNullableLength("some string with Kotlin")
   
}
```
```bash
for "null":

for "":

for "some string with Kotlin":
        is empty? false
        length = 23
```

# With

É uma função sem extensão que pode acessar membro de seu argumento de forma concisa: você pode omitir o nome da instância ao se referir a seus membros.

```kotlin
class Configuration(val host: String, val port: Int)

fun main(){
    val configuration = Configuration(host = "127.0.0.1", port = 9000)

    with(configuration){
        println("$host:$port") // 127.0.0.1:9000
    }

    //alternative
    configuration.run{
        println("$host:$port") // 127.0.0.1:9000
    }

    //instead of
    println("${configuration.host}:${configuration.port}")  // 127.0.0.1:9000
}
```

# Apply

Executa um bloco de código em um objto e retorna o próprio objeto. Dentro do bloco, o objeto é referenciado por *this*. Esta função é útil para inicializar objetos.

```kotlin
data class Person(var name: String, var age: Int, var about: String){
    constructor(): this("", 0, "")
}

fun main(){
    val jake = Person()                         //1
    val stringDescription = jake.apply{         //2
        name = "Jake"                           //3
        age = 30
        about = "Android Developer"
    }.toString()                                //4


    println(stringDescription)
}
```

1. Cria uma classe **Person()** e instância com valores de propriedade padrão
2. Aplica o bloco de código à instância
3. Dentro do *apply*, é equivalente a **jake.name = "Jake"**
4. O valor de retorno é a própria instância, então você pode encadear outras operações.


# Also

**Also** funciona como **apply**: executa um determinado bloco e retorna o objeto chamado. Dentro do bloco, o objeto é referenciado por it, então é mais fácil passá-lo como argumento. Essa função é útil para incorporar ações adicionais, como fazer login em cadeias de chamadas.

```kotlin
data class Person(var name: String, var age: Int, var about: String){
    constructor(): this("", 0, "")
}

fun writeCreationLog(p: Person){
    println("A new person ${p.name} was created.")
}

fun main() {
    
    val jake = Person("Jake", 30, "Android Developer")  //1
        .also{                                          //2
            writeCreationLong(it)                       //3
        }
}
```

1. Cria um objeto Person(), com valores de propriedade fornecidos
2. Aplica o bloco de código fornecido ao objeto. O valor de retorno é o próprio objeto
3. Chama a função de registro passadno o objeto como um argumento


