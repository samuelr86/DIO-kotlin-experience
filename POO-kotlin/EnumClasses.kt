enum class State{
    IDLE, RUNNING, FINISHED             //1
}

enum class Color(val rgb: Int){     // 1a
    RED(0xFF0000),                          //2a
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00);

    fun containsRed() = (this.rgb and 0xFF0000 != 0)    //3a
}

fun main(){
    val state = State.RUNNING       //2
    val message = when(state){         //3
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
        
    }

    println(message) //It's running



    val red = Color.RED
    println(red)                        //4a    RED
    println(red.containsRed())          //5a    true
    println(Color.BLUE.containsRed())   //6a    false
    println(Color.YELLOW.containsRed()) //7a    true
}