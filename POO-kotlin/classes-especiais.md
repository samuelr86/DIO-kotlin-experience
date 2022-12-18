# Data Classes 
#### (DataClasses.kt)

São classe que foram projetadas para armazenar valores, implementam uma séria de funções que são muito úteis para fazer comparações de igualdade de objetos e manipular informações.

Normalmente você vai armazenar o resultado de uma API em uma estrutura de dados e geralmente uma estrutura de dados é uma *Data Classe*.

1. Define a classe com o modificador *data*.
2. Sobrescreveu o método equals apenas para comparar os id's.            
3. Imprime o usuário de Id = 1.
4. Compara, em termos de estrutura(==), os valores entre o *user* e o *secondUser*. 
5. Imprime o hashCode dos elementos criados.

A função Copy() cria novas instâncias, mas com os mesmo valores estruturais da primeira.

6. Cria uma cópia do user.
7. Cria uma cópia da instância user, é uma cópia referencial.
8. Cria uma cópia específicando o nome MAX como um argumento de construção do objeto *user*.
9. Cria uma cópia específicando o Id como argumento de construção do objeto *user*.


# Enum Classes
#### (EnumClasses.kt)

Esse tipo de classe representa um número finito de possibilidade dentro de uma estrutura como direções, estados, modos e assim por diante.

1. Define uma enumeração com 3 estados usando o modificador *enum*.
2. Cria uma constante state que acessa o enum através do nome da classe.
3. Com enums, o compilador pode inferir se uma *when-expression* é exaustiva para que você não precise do *else*.

Enums podem conter propriedades e métodos como outras classes, separados da lista de constantes de enum por um ponto-e-virgula.

- 1a. Define uma classe Enum como uma propriedade e um método.
- 2a. Cada constante de enumeração dever passar um argumento para o parâmetro do construtor.
- 3a. Os membros da classe Enum são separados das definições constantes por um ponto-e-virgula.
- 4a. O padrão *toString* retorna o nome da constante, aqui "RED".
- 5a. Chama um método em uma constante de enumeração.
- 6a. Chama um método por meio do nome da classe enum.
- 7a. Os valores RGB de "RED" e "YELLOW" compartilham os mesmos bits(0xFF) para que imprima true.


# Sealed Classes
#### (SealedClasses.kt)

Permite restringir o uso de herança. Depois de declarar uma classe selada, ela só pode ser subclasse de dentro do mesmo pacote em que a classe selada foi declarada. Não pode ser subclassificado fora do pacote onde a classe lacrada é declarada.

1. Define uma classe selada.
2. Define subclasses. Observe que todas as subclasses devem estar no mesmo pacote.
3. Usa uma instância da classe selada como um argumento em uma expressão *when*.
4. Um smartcast é executado, lançando **Mammal** para **Human**.
5. Um smartcast é executado, lançando **Mammal** para **Cat**.
6. O *else* não é necessário aqui, pois todas as subclasses possíveis da classe selada são cobertas. Com uma superclasse não selada *else* seria necessário.

# Object Keyword

Classes e objetos em Kotlin funcionam da mesma forma que na maioria das linguagens orientadas a objetos: uma classe é um projeto e um objeto é uma instância de uma classe. Normalmente você define uma classe e cria várias instâncias dessa classe:

```kotlin
import java.util.Random

class LuckDispatcher {                    //1 
    fun getNumber() {                     //2 
        var objRandom = Random()
        println(objRandom.nextInt(90))
    }
}

fun main() {
    val d1 = LuckDispatcher()             //3
    val d2 = LuckDispatcher()
    
    d1.getNumber()                        //4 
    d2.getNumber()
}
```

1. Define um projeto   
2. Define um método
3. Cria instâncias
4. Chama o método nas instâncias

Em Kotlin você também tem a palavra-chave *object*. É usado para obeter um tipo de dado com uma única implementação, como se fosse um **Singleton**.
Para conseguir isso em Kotlin, você precisa declarar um *object* sem classe, sem construtor, apenas uma instância preguiçosa. Por que preguiçoso? Porque ele será criado uma vez quando o objeto for acessado. Caso contrário nem será criado.

### Object Expression

Você cria um único objeto, declara seus membros e o acessa em uma função. Objetos como esse geralmente são criados em Java como instâncias de classes anônimas.

```kotlin
fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1

    val dayRates = object {                                                     //2
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }

    val total = dayRates.standard + dayRates.festivity + dayRates.special       //3

    print("Total price: $$total")                                               //4

}

fun main() {
    rentPrice(10, 2, 1)                                                         //5
}
```

1. Cria uma função com parâmetros.
2. Cria um objeto para usar ao calcular o valor do resultado.
3. Acessa as propriedades do objeto.
4. Imprime o resultado.
5. Chama a função. É quando o objeto é realmente criado.

### Object Declaration

Você deve usá-lo para acessar diretamente seus membros:

```kotlin

    fun takeParams(username: String, password: String) {        //2 
        println("input Auth parameters = $username:$password")
    }
}

fun main(){
    DoAuth.takeParams("foo", "qwerty")                          //3
}
```

1. Cria uma declaração do objeto
2. Define o método do objeto
3. Chama o método. É quando o método é realmente criado

### Objetos Complementares

Uma declaração de objeto dentro de uma classe define outro caso útil: o *objeto companheiro*. Sintaticamente é semelhante aos métodos estáticos em Java: você chama os membros do objeto usando seu nome de classe como um qualificador. Se você planejar usar um objeto complementar em Kotlin, considere usar uma função de nível de pacote.


```kotlin
class BigBen {                                  //1 
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}

fun main() {
    BigBen.getBongs(12)                         //4
}
```

1. Define uma classe
2. Define um *companion Object*. Se nome pode ser omitido
3. Define um método de objeto complementar
4. Chama o método de objeto complementar por meio do nome da classe


