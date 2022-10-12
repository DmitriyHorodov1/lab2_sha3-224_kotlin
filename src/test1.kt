import java.security.MessageDigest

fun main(args: Array<String>) {
    val text  = "worldwa"
    val bytes = text.toByteArray()
    val md = MessageDigest.getInstance("SHA3-224")
    val digest = md.digest(bytes)
    for (byte in digest) print("%02x".format(byte))
    println()
}