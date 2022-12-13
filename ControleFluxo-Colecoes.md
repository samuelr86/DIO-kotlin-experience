<h1>Controle de Fluxo</h1>

<h2>When</h2>
<p>O <em>when</em> é uma versão mais flexível e clara do <em>switch</em>.

```kotlin
when(obj){					//1
	1 -> println("One")			//2
	"Hello" -> println("greeting")		//3
	is Long -> println("Long") 		//4
	!is String -> println("Not a string") 	//5
	else -> println("Unknown") 		//6
}

```
</p>
<ol>
	<li>é uma declaração <em>when</em></li>
	<li>verifica se obj é igual a 1</li>
	<li>verifica se obj é igual a "Hello"</li>
	<li>Executa a verificação de tipo</li>
	<li>Executa a verificação de tipo inverso</li>
	<li>Instrução padrão, se nao for nenhuma das anteriores.</li>
</ol>

<p>Um exemplo de uma expressão usado o <em>when</em>:

```kotlin
class MyClass(){

}

fun whenAssign(obj: Any): Any{
	val result = when(obj){
		1 -> "one"
		"Hello" -> 1
		is Long -> false
		else -> 42
	}
	return result
}

fun main(){
	println(whenAssign(MyClass())) //42
	println(whenAssign("Hello")) //1
	println(whenAssign(1)) // "one"
}
```

</p>

<h2>Loops: FOR</h2>
<p>A apresentação do for em kotlin é similar ao foreach em Java...

```kotlin
fun main(){

	val cakes = listOf("carrot", "chocolate", "cheese") 

	for (cake in cakes){
		println("It's a $cake cake")
	}
}
```

... e será impresso toda a lista de elementos.

```bash
It's a carrot cake
It's a chocolate cake
It's a cheese cake
```
</p>

<h2>LOOPS: While e do-while</h2>

<p>A estrutura while e do-while também são bastante similares como em outras linguagens...

```kotlin
fun eatACake() = println("Eat a cake.")
fun bakeACake() = println("Bake a cake")

fun main(){
	var cakesEaten = 0
	var cakesBaked = 0

	while (cakesEaten < 5){
		eatACake()
		cakesEaten++
	}

	do{
		bakeACake()
		cakesBaked++
	}while(cakesBaked < cakesEaten)
}
```

</p>

<h2>LOOPS: Iterators</h2>
<p>Você pode definir seus próprios iterators em suas classes implementando o operador iterator como no exemplo abaixo...

```kotlin
class Animal(val name: String) 	//1

class Zoo (val animals: List<Animal>){ 	//2
	operator fun interator(): Iterator<Animal>{	//3
		return animals.iterator()
	}
}

fun main(){
	val animals = listOf(Animal("Zebra"), Animal("Lion"))

	val zoo = Zoo(animals)

	for(animal in zoo){
		println("Watch out, it's a ${animal.name}")
	}
}

```
</p>
<ol>
	<li> Temos uma classe <em>Animal</em> que recebe como parâmetro uma variável imutável, que é o nome do animal do tipo String</li>
	<li> Temos uma classe <em>Zoo</em> que recebe também como parâmetro uma variável imutável, que é uma lista de Animal. </li>
	<li> Dentro desta classe podemos definir um iterator, que retorna o iterator e atende os seguintes métodos: [next() - Animal] e [hasNext() - Boolean].</li>
</ol>
<p>O iterator pode ser declarado no tipo ou como uma função de extensão.</p>

<h2>RANGES: Loops com Int</h2>
<p>As ranges lembram as aplicadas na linguagem python...</p>

```kotlin
fun main(){
	for(i in 0..3){ 	//1 
		print(i)
	}

	for(i in 0 until 3){    //2 
        print(i)
    }

    for(i in 2..8 steps 2){ //3 
        print(i)
    }  

    for(i in 3 downTo 0){ //4 
        print(i)
    }
    
}
```

<ol>
	<li> Imprime os elementos do intervalo entre 0 e 3, inclusive.</li>
	<li> Imprime os elementos do intervalo entre 0 e menor que 3.</li>
	<li> Imprime os elementos do intervalo entre 2 e 8 com incremento de 2 em 2.</li>
	<li> Imprime os elementos do intervalo entre 3 e 0, decrementando.</li>
</ol>

<h2>RANGES: Ifs e Loops com Char</h2>
<p>Similiar aos ranges usados com inteiros, é possível usar como exemplo os elementos do tipo char...

```kotlin
fun main(){
	for(c in 'a'..'f'){ 	//1 
		print(c) 
	}

	for(c in 'z' downTo 's' step 2){    //2 
        print(c)
    }
}

```
</p>

<ol>
	<li> Neste exemplo há um loop que imprime os caracteres entre 'a' e 'f'</li>
	<li> Neste exemplo há um loop que imprime os caracteres em decremento de 'z' a 's' com intervalos de 2 em 2.</li>
</ol>
<p>Outra forma de uso desses intervalos seria como verificação de um número, como por exemplo:


```kotlin
fun main(){
	val x = 2 		//1

	if(x in 1..5){ 		//2 
		print("x is in range from 1 to 5")
	}

	if(x !in 6..10){ 	//3
		print("x is not in range from 6 to 10")
	}
}
```
</p>

<ol>
	<li> Considere que x vale 2...</li>
	<li> Verifique se x está no intervalo entre 1 e 5.</li>
	<li> Verifique se x NÃO está no intervalo entre 6 e 10</li>
</ol>

<h2>Verificação de Igualdade '==' e '==='</h2>
<p>Existem duas formas de verificar a igualdade de objetos uma seria uma comparação estrutural, feita com a sintaxe de dois iguais(==), e outra que seria uma comparação referencial, feita com a sintaxe com três iguais(===), algo como se dois ou mais elementos tem o mesmo endereço de memória.</p>

```kotlin
fun main(){
	val authors = setOf("Shakespeare", "Hemingway", "Twain")
	val writers = setOf("Twain", "Shakespeare", "Hemingway")

	println(authors == writers) 	//1
	println(authors === writers) 	//2
}
```
<ol>
	<li> Comparação Estrutural: imprime true, os elementos são iguais.</li>
	<li> Comparação Referencial: imprime false, pois apesar de serem as mesmas palavras, são de referências diferentes.</li>
</ol>

<h2>Expressão Condicional</h2>
<p>Em Kotlin não há o conceito de operador ternário com é conhecido em outras linguagens, porém é possivel aplicar um if como uma expressão...

```kotlin

fun main(){
	
	fun max(a: Int, b: Int) = if(a > b)a else b 	//1

	println(max(99, -42))
}

```
</p>

<ol>
	<li> De forma bem simples, temos uma função max que recebe dois inteiros como parâmetros e o retorno é um if como expressão condicional, que faz a comparação se 'a' é maior que 'b', e imprime o resultado 'a' ou 'b'.</li>
</ol>