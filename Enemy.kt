package clases

open class Enemy(val name: String, val strength: Int) {

    protected var direction: String = "LEFT"

    init {
        println("Iniciando superclase de $name")
    }

    protected fun changeDirection() {
        direction = if (direction == "RIGHT") "LEFT" else "RIGHT"
        println("$name va en dirección $direction")
    }

    protected fun die() {
        println("$name ha muerto")
    }

    open fun collision(collider: String) {
        when (collider) {
            "Weapon" -> die()
            "Enemy" -> changeDirection()
        }
    }
}

class Goomba : Enemy("Goomba", 1) {
    //(name: String, strength: int):
    //Enemy(name,strength){
    init {
        println("Iniciando subclase de $name")
    }
}

class Koopa : Enemy("Koopa", 2) {
    override fun collision(collider: String) {
        //super.collision(collider)
        println("Usando la colision de la clase Enemy")
        when (collider) {
            "Weapon" -> {
                var state = "Shell"
                println("El estado ahora es: $state")
            }
            "Enemy" -> changeDirection()
        }
        //println("Usando la colision de la clase Enemy")
    }
}
