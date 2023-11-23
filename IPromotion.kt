package clases

interface IPromotion {
        val discount: Int
        val typeDiscount: Int

        fun getDiscountPrice(amount: Int): Int{
            return if (typeDiscount == 0){
                (amount * (100-discount))/100
            } else{
                amount - discount
            }
        }
}
