import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Puestos de venta de entradas
        LinkedBlockingQueue<EntradaCine> colaClientesPuesto1 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<EntradaCine> colaClientesPuesto2 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<EntradaCine> colaClientesPuesto3 = new LinkedBlockingQueue<>();

        // Puestos de venta de entradas compartiendo las butacas disponibles
        String[] butacasDisponibles = new String[30];
        for (int i = 0; i < butacasDisponibles.length; i++) {
            butacasDisponibles[i] = "Butaca" + (i + 1);
        }

        // Crear instancias de los puestos de venta
        PuestoVentaEntradas puesto1 = new PuestoVentaEntradas(colaClientesPuesto1, butacasDisponibles);
        PuestoVentaEntradas puesto2 = new PuestoVentaEntradas(colaClientesPuesto2, butacasDisponibles);
        PuestoVentaEntradas puesto3 = new PuestoVentaEntradas(colaClientesPuesto3, butacasDisponibles);

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

        // Asignar las butacas a los clientes de cada puesto de venta
        asignarButacas(colaClientesPuesto1);
        asignarButacas(colaClientesPuesto2);
        asignarButacas(colaClientesPuesto3);
    }

    // Método para asignar butacas a los clientes
    private static void asignarButacas(LinkedBlockingQueue<EntradaCine> colaClientes) {
        while (!colaClientes.isEmpty()) {
            try {
                EntradaCine entradaActual = colaClientes.take();  // Utilizamos take para desencolar en LinkedBlockingQueue
                System.out.println("Cliente asignado: " + entradaActual.asiento);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

