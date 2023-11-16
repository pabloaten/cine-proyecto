import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // Puestos de venta de entradas
        LinkedBlockingQueue<EntradaCine> colaClientesPuesto1 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<EntradaCine> colaClientesPuesto2 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<EntradaCine> colaClientesPuesto3 = new LinkedBlockingQueue<>();

        // Butacas disponibles para todas las salas
        String[] butacasDisponibles = new String[4 * 40];
        for (int i = 0; i < butacasDisponibles.length; i++) {
            butacasDisponibles[i] = "Sala" + ((i / 40) + 1) + "-Butaca" + (i % 40 + 1);
        }

        // Inicializar la cola de clientes con entradas para todas las salas y películas
        String[] peliculas = {"Spider-Man: No Way Home", "The Matrix Resurrections", "Dune", "Inception"};
        Random random = new Random();

        for (int i = 0; i < 200; i++) {
            int salaAleatoria = random.nextInt(4) + 1;
            String butacaSolicitada = butacasDisponibles[i % butacasDisponibles.length];
            String pelicula = peliculas[i % peliculas.length];

            EntradaCine entradaCliente = new EntradaCine(pelicula, "Sala" + salaAleatoria, "18:00", butacaSolicitada);
            colaClientesPuesto1.offer(entradaCliente);
            colaClientesPuesto2.offer(entradaCliente);
            colaClientesPuesto3.offer(entradaCliente);
        }

        // Crear un semáforo con un permiso para controlar el acceso a la compra de entradas
        Semaphore semaforo = new Semaphore(1);

        // Crear instancias de los puestos de venta con el semáforo
        PuestoVentaEntradas puesto1 = new PuestoVentaEntradas("Puesto 1", colaClientesPuesto1, butacasDisponibles, semaforo);
        PuestoVentaEntradas puesto2 = new PuestoVentaEntradas("Puesto 2", colaClientesPuesto2, butacasDisponibles, semaforo);
        PuestoVentaEntradas puesto3 = new PuestoVentaEntradas("Puesto 3", colaClientesPuesto3, butacasDisponibles, semaforo);

        // Crear ExecutorService para gestionar la ejecución de los hilos
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Iniciar los hilos
        executorService.submit(puesto1);
        executorService.submit(puesto2);
        executorService.submit(puesto3);

        // Apagar el ExecutorService cuando todos los hilos hayan terminado
        executorService.shutdown();

        // Esperar a que todos los hilos finalicen
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}