import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

// Clase que representa un puesto de venta de entradas
class PuestoVentaEntradas implements Runnable {
    private String nombre;
    private LinkedBlockingQueue<EntradaCine> colaClientes;
    private String[] butacasDisponibles;
    private Semaphore semaforo;

    public PuestoVentaEntradas(String nombre, LinkedBlockingQueue<EntradaCine> colaClientes, String[] butacasDisponibles, Semaphore semaforo) {
        this.nombre = nombre;
        this.colaClientes = colaClientes;
        this.butacasDisponibles = butacasDisponibles;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        while (true) {
            // Cada puesto intenta obtener una entrada de la cola compartida
            try {
                semaforo.acquire(); // Adquirir el permiso del semáforo
                EntradaCine entradaCliente = colaClientes.take();  // Utilizamos take para desencolar en LinkedBlockingQueue
                if (entradaCliente == null) {
                    // Si la cola está vacía, el puesto termina
                    break;
                }

                // Crear instancia de Cliente con sala preferida
                Cliente cliente = new Cliente(entradaCliente.sala);

                // Verificar si la sala está llena para el cliente
                if (verificarSalaLlena(cliente)) {
                    System.out.println(nombre + ": Sala llena para " + entradaCliente.sala + ", Cliente asignado: " + entradaCliente.asiento);
                } else {
                    // Simular un proceso de venta que tarda un tiempo
                    realizarVenta(entradaCliente);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.release(); // Liberar el permiso del semáforo, incluso si ocurre una excepción
            }
        }
    }

    // Método para simular un proceso de venta
    private void realizarVenta(EntradaCine entradaCliente) {
        System.out.println(nombre + ": Cliente asignado: " + entradaCliente.asiento +
                " - Película: " + entradaCliente.pelicula + " - Sala: " + entradaCliente.sala);
        // Simular un proceso de venta que tarda un tiempo
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para verificar si la sala está llena para el cliente
    private boolean verificarSalaLlena(Cliente cliente) {
        // Simulación simple: verificamos si quedan butacas en la sala preferida del cliente
        for (String butaca : butacasDisponibles) {
            if (butaca.startsWith(cliente.salaPreferida) && !butaca.equals(cliente.salaPreferida)) {
                return false;  // La sala no está llena
            }
        }
        return true;  // La sala está llena
    }
}

