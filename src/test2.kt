import java.security.MessageDigest

fun stringHashToByteHash(hash: String): ByteArray {
    val ba = ByteArray(32)
    for (i in 0 until 56 step 2) ba[i / 2] = hash.substring(i, i + 2).toInt(16).toByte()
    return ba
}

fun ByteArray.matches(other: ByteArray): Boolean {
    for (i in 0 until 28 ) {
        if (this[i] != other[i]) return false
    }
    return true
}

fun main(args: Array<String>) {

    val begin = System.currentTimeMillis()

    val stringHashes = listOf(
            "eeceb44abc7b62807ba61b6ad2c9542116a11578d25628b468b37ae7",
            "eeceb44abc7b62807ba61b6ad2c9542116a11578d25628b468b37ae7"

    )
    val byteHashes = List(2) { stringHashToByteHash(stringHashes[it]) }
    val letters = List(26) { (97 + it).toByte() }

    println(" $stringHashes")
    println("Brute Force ...")
    letters.stream().parallel().forEach {
        val md = MessageDigest.getInstance("SHA3-224")
        val range = 97..122
        val pwd = ByteArray(7)
        pwd[0] = it
            for (i1 in range) {
                pwd[1] = i1.toByte()
                for (i2 in range) {
                    pwd[2] = i2.toByte()
                    for (i3 in range) {
                        pwd[3] = i3.toByte()
                        for (i4 in range) {
                            pwd[4] = i4.toByte()
                            for (i5 in range) {
                                pwd[5] = i5.toByte()
                                for (i6 in range) {
                                    pwd[6] = i6.toByte()
                                    val ba = md.digest(pwd)
                                    for (j in 0..1) {
                                        if (ba.matches(byteHashes[j])) {

                                            val text = pwd.toString(Charsets.US_ASCII)
                                            val bytes = text.toByteArray()
                                            val md = MessageDigest.getInstance("SHA3-224")
                                            val digest = md.digest(bytes)
                                            for (byte in digest) print("%02x".format(byte))
                                            println()

                                            break
                                        }
                                    }

                                }
                            }

                        }
                    }
                }
            }

    }

    val end = System.currentTimeMillis()
    println("Секунд зайняло: ${end-begin}")
}