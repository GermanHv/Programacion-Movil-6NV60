package clases

import java.util.*
import kotlin.concurrent.schedule

class Mario(var vidas: Int = 3) {
    init {
        println("It's me! Mario!")
    }

    private var state = "small" // Mario es pequeño al iniciar el juego
        set(value) {
            val before = field
            field = value
            println("Tu estado ahora es $field")
            if (value == "Star") {
                Timer("SettingUp", false).schedule(10000) {
                    field = before
                    println("Tu estado ahora es $field")
                }
            }
        }

    private var lives = 3 // Uno comienza el juego con tres vidas
        set(value){
            if(field == 1){
                field = 0
                gameOver()
            }
            else if (field == 0){
                println("Necesitas volver a jugar")
            }
            else{
                field = value
            }
        }

    val isAlive: Boolean
        get() {
            return lives >= 1
        }

    fun collision(collisionObj: String) {
        when (collisionObj) {
            "Goomba" -> lives--
            "Super Mushroom" -> state = "Super Mario"
            "Fire flower" -> state = "Fire Mario"
            "Star" -> state = "Star"
        }
    }

    fun getLives(): String {
        return "$lives vidas"
    }
}
    private fun gameOver(){
        println("JUEGO TERMINADO")
    }
