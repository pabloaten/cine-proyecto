// Clase que representa una entrada de cine
public class EntradaCine {
    private String pelicula;
    private String sala;
    private String hora;
    private String butaca;
    private Cliente cliente; // Agregamos una referencia al cliente asociado

    public EntradaCine(String pelicula, String sala, String hora, String butaca, Cliente cliente) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.hora = hora;
        this.butaca = butaca;
        this.cliente = cliente;
    }

    public String getPelicula() {
        return pelicula;
    }

    public String getSala() {
        return sala;
    }

    public String getHora() {
        return hora;
    }

    public String getButaca() {
        return butaca;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
