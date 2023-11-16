import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Puesto de venta de entradas
        LinkedBlockingQueue<EntradaCine> colaClientes = new LinkedBlockingQueue<>();

        // Puestos de venta de entradas compartiendo las butacas disponibles
        String[] butacasDisponibles = new String[30];
        for (int i = 0; i < butacasDisponibles.length; i++) {
            butacasDisponibles[i] = "Butaca" + (i + 1);
        }

        // Inicializar la cola de clientes
        for (int i = 0; i < 30; i++) {
            String butacaSolicitada = butacasDisponibles[i];
            EntradaCine entradaCliente = new EntradaCine("Spider-Man: No Way Home", "18:00", butacaSolicitada);
            colaClientes.offer(entradaCliente);  // Utilizamos offer para encolar en LinkedBlockingQueue
        }

        // Crear instancias de los puestos de venta
        PuestoVentaEntradas puesto1 = new PuestoVentaEntradas("Puesto 1", colaClientes, butacasDisponibles);
        PuestoVentaEntradas puesto2 = new PuestoVentaEntradas("Puesto 2", colaClientes, butacasDisponibles);
        PuestoVentaEntradas puesto3 = new PuestoVentaEntradas("Puesto 3", colaClientes, butacasDisponibles);

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