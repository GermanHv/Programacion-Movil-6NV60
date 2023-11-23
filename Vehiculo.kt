package clases

class Vehiculo {

    var color =""
    var marca=""
    var modelo=""
    var placa=""
    var gasolina=0.0f
    var encendido =false

    fun encender(){
        encendido = true
    }
    fun apagar(){
        encendido = false
    }
    fun recargar(litros: Float){
        gasolina +=litros
    }


}
