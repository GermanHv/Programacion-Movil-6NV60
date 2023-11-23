package clases

class NationalLowSeason(city:String): National(city),IPromotion{
    override val discount = 10
    override val typeDiscount = 0

    override fun getPrice(numDays: Int): Int{
        val amount = super.getPrice(numDays)
        return if (amount == 0) 0 else getDiscountPrice(amount)
    }
}
