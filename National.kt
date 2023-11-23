package clases

open class National(override val city:String): Travel() {
    override val country = "Mexico"

    protected val fees = mapOf(
        "Monterrey" to 400,
        "Gudalajara" to 350,
        "CDMX" to 360,
        "San Cristobal de las casas" to 240,
        "San Miguel de Allende" to 320
    )

    override fun getPrice(numDays: Int):Int {
        val fee = fees[city]
        return if (fee == null) 0 else fee * numDays
    }

    override fun quotePrice(numDays: Int) {
        val price = getPrice(numDays)
        if (price == 0) {
            println("Los sentimos no hacemos viajes a esa Ciudad")
        } else {
            println("Tu viaje a $city cuesta \$$price")

        }
    }
}
