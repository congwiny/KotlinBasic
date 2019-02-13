package generics



fun main(args: Array<String>) {
    val value = listOf(1,2,3)
    /**
     * Cannot check for instance of erased type: List<String>
     *
     * if (value is List<String>){
     *
     * }
     */
    if (value is List<*>){ //星号投影
        println("value is list")
    }


}