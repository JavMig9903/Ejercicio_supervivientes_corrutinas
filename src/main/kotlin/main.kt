import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

var cubosActuales=0
var lenaActual = 0
var ramasActuales = 0
var comidaActual = 0

const val CUBOS_NECESARIOS = 4
const val LENA_NECESARIA = 2
const val RAMAS_NECESARIAS = 1
const val COMIDA_NECESARIA = 1

val mutex = Mutex()

suspend fun main() {
    comenzar()
    Thread.sleep(80000)
}

suspend fun comenzar() {
    runBlocking {
        amigoA()
        amigoC()
        amigoB()
    }
}

fun amigoA(){
    GlobalScope.async{
        repeat(4){
            println("El amigo Amigo A va a por un cubo de agua")
            delay(4000)
            println("El amigo Amigo A ha vuelto con el cubo de agua")
            hamaca("El amigo Amigo A",1000)
            cubosActuales++
        }
        if (cubosActuales == CUBOS_NECESARIOS && lenaActual == LENA_NECESARIA && ramasActuales == RAMAS_NECESARIAS && comidaActual == COMIDA_NECESARIA)
            println("Barca construida y aprovisionada con exito")
    }
}

suspend fun amigoB(){
    repeat(2){
        println("El amigo Amigo B va a por lena")
        hacha("El amigo Amigo B",5000)
        lenaActual++
        hamaca("El amigo Amigo B",3000)
    }
}

fun amigoC(){
    GlobalScope.async{
        delay(1000)
        println("El amigo Amigo C va a por ramas")
        println("El amigo Amigo C vuelve con ramas")
        delay(3000)
        ramasActuales++
        println("El amigo Amigo C va a Cazar")
        hacha("El amigo Amigo C",4000)
        comidaActual++
    }
}
suspend fun hacha(nombre:String, tiempo:Long){
    runBlocking {
        mutex.withLock {
            println("$nombre, coge el hacha")
            delay(tiempo)
            println("$nombre deja el hacha")
            println("$nombre vuelve con la lena")
        }
    }
}
suspend fun hamaca(nombre: String, tiempo : Long){
    runBlocking {
        mutex.withLock {
            println("$nombre, quiere descansar")
            println("$nombre, se tumba en la hamaca")
            delay(tiempo)
            println("$nombre,se levanta de la hamaca")
            println("$nombre,deja de descansar")
        }
    }
}