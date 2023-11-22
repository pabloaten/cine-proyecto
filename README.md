<em> # Proyecto Cine </em>

En este proyecto se intenta simular un cine en la vida real.Hay 3 puestos de ventas de entrada en los que se hace cola, una vez es tu turno el cliente especifica la sala a la que quiere ir.


### Tipo de estructura 
Para este proyecto hemos usado la linked blocking queue como estructura de datos principal

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
* Atributos : <br>
  ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/6fc0bd5d-60ab-4a62-9e03-670a1828fa15)
<br>
* Constructor : <br>
 ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/8c411d47-255d-4b74-95ef-f3f8523daf9b)
<br>
* Métodos :<br>
  asignarButacas(EntradaCine entradaCine)<br>
  butacaOcupada(String butaca)<br>
  run : <br>
  ![image](https://github.com/pabloaten/cine-proyecto/assets/127136385/f69339a9-c102-4339-8b57-8e75eacaa73c)
   <br>
## Autores
| Pablo Atenciano| Raúl Calero |  Pablo Corral| Sebastián Olea |
| :---: | :---: | :---: | :---: | 

