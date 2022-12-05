
<h1 align="center"><b>KOTLIN</b></h1>

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

<h2></h2>