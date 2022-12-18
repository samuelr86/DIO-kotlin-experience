## Herança Simples

Para que uma classe possa ser considerada uma "classe pai", dever ser declarada como *open class*, e suas funções internas também devem ser declaradas *open fun*. 

```kotlin
open class Dog{
    open fun sayHello(){
        println("wow wow!")
    }
}
```

Quando uma classe filha herdar usamos o "dois-pontos" para indicar a herança e as funções sobrescritas(override)...

```kotlin
class Yorkshire : Dog(){
    override fun sayHello(){
        println("wif wif!")
    }
}
```

Na chamada da classe na função main, nomeamos uma variável do tipo da classe pai que recebe a classe filha(instanciação). Essa variável chama a função sobrescrita exibindo a mensagem da classe filha.

```kotlin
fun main() {
    val dog: Dog = Yorkshire()
    dog.sayHello() // "wif wif!"
}
```

## Herança com construtor parametrizado

No próximo exemplo temos uma classe Tigre que recebe uma String no construtor indicando sua origem. Diferente do exemplo anterior a função "Olá" não pode ser sobrescrita, pois não é uma *open fun*.

```kotlin
open class Tiger(val origin: String){
    fun sayHello(){
        println("A tiger from $origin says: grrhhh!")
    }
}

class SiberianTiger : Tiger("Siberia")          //1

fun main() {

    val tiger: Tiger = SiberianTiber()       //2
    tiger.sayHello() // A tiger from Siberia says: grrhhh!


}

```
1. A classe SiberianTiger é um classe do tipo Tiger e que recebe a origem no construtor.
2. Quando chamamos a variável tiger na função main, indicamos a classe pai e o tipo da referência, no caso a classe filha "SiberianTiger"

## Herança passando argumentos do construtor para a Superclasse

```kootlin
open class Lion(val name: String, val origin: String){
    fun sayHello(){
        println("$name, the lion from $origin says: graoh!")
    }
}

class Asiatic(name: String): Lion(name = name, origin = "India")    //1

fun main(){
    val lion: Lion = Asiatic("Rufo")    //2
    lion.sayHello()
}
```

1. *name* na classe Asiatic não é nem val nem var: é um argumento do construtor, cujo valor é passado para a propriedade name da superclasse Lion.
2. Cria uma instância Asiatic como nome Rufo. A chamada chama o construtor de Lion com os argumentos *Rufo* e *India*.


