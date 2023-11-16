import java.util.concurrent.LinkedBlockingQueue;

class PuestoVentaEntradas implements Runnable {
    private String nombre;
    private LinkedBlockingQueue<EntradaCine> colaClientes;
    private String[] butacasDisponibles;

    public PuestoVentaEntradas(String nombre, LinkedBlockingQueue<EntradaCine> colaClientes, String[] butacasDisponibles) {
        this.nombre = nombre;
        this.colaClientes = colaClientes;
        this.butacasDisponibles = butacasDisponibles;
    }

    @Override
    public void run() {
        while (true) {
            // Cada puesto intenta obtener una entrada de la cola compartida
            try {
                EntradaCine entradaCliente = colaClientes.take();  // Utilizamos take para desencolar en LinkedBlockingQueue
                if (entradaCliente == null) {
                    // Si la cola está vacía, el puesto termina
                    break;
                }

                // Simular un proceso de venta que tarda un tiempo
                realizarVenta(entradaCliente);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para simular un proceso de venta
    private void realizarVenta(EntradaCine entradaCliente) {
        System.out.println(nombre + ": Cliente asignado: " + entradaCliente.asiento+"-A LAS "+entradaCliente.hora+"-PELICULA : "+entradaCliente.pelicula);
        // Simular un proceso de venta que tarda un tiempo
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}