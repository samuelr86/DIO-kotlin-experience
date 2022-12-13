
<h1 align="center"><b>Kotlin</b></h1>

<h2>Hello World</h2>

```kotlin

//1. Definição do pacote(comum para organização do código)
package org.kotlinlang.play 


//2. Ponto de entrada do código Kotlin, definido pela função [main]
fun main()	{

	//3. Imprime na saída padrão de um texto
	println("Hello World")
}

```
<p><b>Package</b>: O código Kotlin é geralmente definidos por pacote e se você nao definir um pacote ele ficará num pacote padrão.</p>
<p><b>Função main</b>: é um função de entrada e desde a versão 1.3 não é necessário passar parâmetros. O tipo de retorno não é específicado, o que significa que a função não retorna nada.</p>
<p><b>Println</b>: é um comando de saída padrão, observe que o ponto-e-virgula(;) é opcional</p>

<h2>Funções</h2>

<h3>Valores de parâmetros padrão e argumentos nomeados</h3>
<p>1. Uma função simples que recebe um parâmetro do tipo <em>String</em> e retorna um <em>Unit</em>, ou seja, sem valor de retorno:</p>

```kotlin
fun printMessage (mensagem : String): Unit{
	println(mensagem)
}
```

<p>2. Uma função que leva um segundo parâmetro opcional com valor padrão [Info]. A saída recebe um conceito de interpolação de Strings. O tipo de retorno é omitido, o que significa que na verdade é Unit:</p>

```kotlin
fun printMessageWithPrefix(mensagem:String, prefixo:String = "Info"){
	println([$prefixo] $mensagem)
}
```
<p>3. Uma função que retorna um número inteiro:</p>

```kotlin
fun sum(x:Int, y:Int):Int{
	return x + y
}
```
<p>4. Uma função de expressão única que retorna um número inteiro:</p>

```kotlin
fun multiply(x:Int, y:Int) = x * y
```

<p>A saída das funções acima podem ser chamadas na função principal [main()]:</p>

```kotlin
fun main(){
	printMessage("Olá") // Olá
	printMessageWithPrefix("Olá","Log") // Olá [Log]
	printMessageWithPrefix("Olá") // Olá [Info]
	printMessageWithPrefix(prefixo = "Log", mensagem = "Olá") // Olá [Log]
	println(sum(1, 2)) //imprime o resultado 3
	println(multiply(2, 4)) //imprime o resultado 8
	
}
```
<h3>Parâmetros vararg</h3>

<p>1. <b>Varargs</b> permitem que você passe qualquer número de argumentos, separando-os com vírgulas.</p>

```kotlin
fun printAll(vararg mensagens:String){ //indica que há inúmeros parâmetros do tipo String que podem ser lançados

	for(m in mensagens) println(m) // imprime uma lista de parâmetros que serão passados
}

```
<p>2. É possível também nomear argumentos no parâmetro.</p>

```kotlin
fun printAllWithPrefix(vararg mensagens: String, prefix: String){
	for(m in mensagens) println([prefix] + m)
}
```
<p>3. Se tiver uma função que chamará outra função e que também for um vararg é necessário colocar uma notação [ * ]. Em tempo de execução um vararg é apenas um array, o operador permite chamada de outro vararg.</p>

```kotlin
fun log(vararg entrada :String){
	printAll(*entrada)
}
``` 
<p>As chamadas das funçoes acima podem ser chamadas na função principal [main()]:</p>

```kotlin
fun main(){
	printAll("Hello", "Olá", "Hola") 
	// Hello
	// Olá
	// Hola

	printAllWithPrefix("Hello", "Olá", "Hola", "Greeting")
	// [Greeting] Hello
	// [Greeting] Olá
	// [Greeting] Hola

	log("Hello", "Olá", "Hola")
	// Hello
	// Olá
	// Hola
}
```
<h2>Variáveis var e val</h2>

<p>1. Declara uma variável <b><em>mutável</em></b> e a inicializa:</p>

```kotlin
var a: String = "Inicial"
```
<p>2. Declara uma variável <b><em>imutável</em></b> e a inicializa:</p>

```kotlin
val b: Int = 1
```
<p>3. Declara uma variável <b><em>imutável</em></b> e a inicializa sem específicar o tipo. O compilador infere o tipo Int.</p>

```kotlin
val c = 3
```
<p>4. Podemos declarar uma variável sem inicializar, porém a tentativa de usá-la pode gerar um erro do compilador.

```kotlin
var e: Int

println(e)

// Err: "Variable 'e' must be initalized"
```
Você é livre para escolher quando inicializá-la, no entanto, ela dever ser inicializada antes da primeira leitura.</p>

<h2>Null Safety - Nulidade</h2>

<p>Em um esforço para livrar o mundo de "<em>NullPointerException</em>", os tipos de variáveis em Kotlin não permitem a atribuição de <em>null</em>. Se precisar de uma variável que possa ser nula, declare-a anulável adicionando "?" no final de seu tipo.</p>

```kotlin
var neverNull : String //Isso não pode ser nulo

var anulavel : String  ? // você pode manter um nulo aqui
anulavel = null
```
<p>Uma outra situação, você tem uma função que recebe um valor não-nulo...

```kotlin
fun strLength(notNull : String): Int{
	return notNull.length
}
```

... se chamar o neverNull, o código vai rodar normalmente. Porém se chamar o anulavel gerará um erro de compilação.

```kotlin
strLength(neverNull) //ok
strLength(anulavel) // Err: "Null can not be a value of a non-null typ String"
```
De outra forma, caso nossa função receba um parâmetro nulo podemos colocar no retorno uma situação se caso uma condição venha de uma variável nula retorne zero.

```kotlin
fun strLength(str : String ?): Int{
	return str?.length ?: 0 
}
```
</p>
<p>Um exemplo de como podemos trabalhar com nulos em Kotlin.</p>

```kotlin
fun describeString (maybeString: String?): String{
    if (maybeString != null && maybeString.length > 0) {        
        return "String of length ${maybeString.length}"
    } else {
        return "Empty or null string"                          
    }
}

fun main(){
	println(describeString(null)) // Empty or null string
	println(describeString("")) // Empty or null string
	println(describeString("Samuel")) // String of length 6
}
```

<h2>Classes</h2>
<p>A declaração da classe consiste no nome da classe, no cabeçalho da classe (especificando seus parâmetros de tipo, o construtor primário, etc.) e no corpo da classe entre chaves. Tanto o cabeçalho quanto o corpo são opcionais; se a classe não tiver corpo, as chaves podem ser omitidas.</p>

```kotlin
class Customer

class Contact(val id: Int, var email: String)


fun  main(){

	val customer = Customer() // como criar uma instância da classe Customer com construtor padrão

	val contact = Contact(1, "samuel@gmail.com") // criando um objeto com construtor personalizado

	println(contact.id) // imprime 1 e é imutável por ter sido usado o val
	println(contact.email) // imprime samuel@gmail.com
	contact.email = "joao@gmail.com" // por ter sido usado o var posso renomeá-lo

	println(contact.email) // imprime joao@gmail.com 
}
```
<h3>Generics - Classes Genéricas</h3>

<p>Generics são um mecanismo que se tornou padrão nas linguagens modernas. As classes e funções genéricas aumentam a capacidade de reutilização de código ao encapsular a lógica comum que é independente de um tipo genérico específico. </p>

```kotlin
class MutableStack<E>(vararg items:E){
	private val elements = items.toMutableList() 	//1

	fun push(element: E) = elements.add(element) 	//2

	fun peek():E = elements.last() 					//3

	fun pop():E = elements.removeAt(elements.size - 1)

	fun isEmpty() = elements.isEmpty()

	fun size() = elements.size

	override fun toString() = "MutableStack(${elements.joinToString()})"
}

```

<ol>
	<li>Declaração de uma classe genérica onde <E> é chamado de parâmetro de tipo genérico, se fosse usado um tipo específico, por exemplo "Int", teriamos <em>class MutableStack < Int></em>.</li>
	<li>Uma função <em>push</em> que recebe um elemento do tipo genérico E.
	</li>
	<li>Aqui você pega o ultimo elemento sem remover, usa o tipo E como retorno.</li>
</ol>

<h3>Funções genéricas</h3>

<p>Pode-se gerar também funções genéricas de um tipo específico. Uma função utilitária para criar pilhas mutáveis:</p>

```kotlin
fun <E> mutableStackOf(vararg elements:E) = MutableStack(*elements)	//1

fun main(){
	val stack = mutableStackOf(0.62, 3.14, 2.7) 	//2
	println(stack)
}
```
<ol>
	<li>Criação de uma função genérica que recebe como parâmetro a classe MutableStack </li>
	<li>Em uma variável imutável a função é chamada, instanciando uma lista com 3 valores.</li>
</ol>

