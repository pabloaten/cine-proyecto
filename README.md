<em> # Proyecto Cine </em>

En este proyecto se intenta simular un cine en la vida real.Hay 3 puestos de ventas de entrada en los que se hace cola, una vez es tu turno el cliente especifica la sala a la que quiere ir.


### Tipo de estructura 
Para este proyecto hemos usado la linked blocking queue como estructura de datos principal

## Métodos Principales

| Método               | Descripción                                                                                                  |
|----------------------|--------------------------------------------------------------------------------------------------------------|
| `put(E elemento)`    | Inserta el elemento especificado al final de la cola, esperando si es necesario hasta que haya espacio disponible. |
| `take()`             | Recupera y elimina el primer elemento de la cola, esperando si es necesario hasta que haya un elemento disponible. |
| `size()`             | Retorna el número de elementos presentes en la cola en un momento dado.                                       |

### Clases del proyecto 
  
#### Cliente
La clase cliente es la entidad que comprará los asientos dentro de la cola.
* Atributos : String SalaPreferida , int Id. <br>
* Constructor : <br>![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/ca77f5f6-987e-4de8-8ead-d8e91d4119cc)    <br>
* Métodos : <br>
   ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/f661cc3f-131c-4add-9f65-d7d172631d16)
    <br>

#### EntradaCine 
La clase EntradaCine es la puerta del cine donde pasarán los clientes.
* Atributos : String pelicula,String sala,String hora,String butaca,Cliente cliente <br>
* Constructor : ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/7a6a8212-9b96-4135-b7f0-a3e2ee022438)<br>

* Métodos :    <br> ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/de1ecf41-4965-4c66-98fd-d5510b420f83)
<br>

#### PuestoVentasEntrada 
La clase PuestoVentasEntrada es la clase que permite la venta de las entradas dentro del cine,implementa runnable,sobreescritura del metodo run,comprobación de butacas disponibles y asignación de butacas
* Atributos :  <br> ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/6fc0bd5d-60ab-4a62-9e03-670a1828fa15)
* Constructor: <br>
  ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/764b8c1e-fdcc-4304-9d9f-1f9525bf531b)

* Métodos :<br>
  asignarButacas(EntradaCine entradaCine)<br>
  ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/228c2a6f-388d-4765-8b2e-ed351e0b4c16)
  <br>
  
  butacaOcupada(String butaca)<br>
  ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/717b62ca-c4a3-421d-b887-10f4eb7f6363)
  <br>
  run : <br>
  ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/f69339a9-c102-4339-8b57-8e75eacaa73c)
   <br>

#### Main 
Es la clase que inicia e instancia el proyecto,creamos unas salas junto con las peliculas que se van a emitir para posteriormente abrir el cine


   
## Autores
| Pablo Atenciano| Raúl Calero |  Pablo Corral| Sebastián Olea |
| :---: | :---: | :---: | :---: | 

