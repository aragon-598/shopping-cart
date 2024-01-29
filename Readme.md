# ShoppingCart
## ShoppingCart Spring boot project

Proyecto de java spring boot para el manejo de ordenes en un carrito de compras.
## Instrucciones

#### Para la ejecución del proyecto realizar los siguientes pasos:

#### 1. Ejecutamos el siguiente comando si estás en un ambiente unix:

```sh
sudo docker compose up -d
```
###### El comando anterior servira para levantar la DB del proyecto


#### 2. Para levantar el servicio ejecutamos el siguiente comando:

```sh 
mvn spring-boot:run
```
###### O bien:
```sh 
mvn clean install
java -jar target/shopping-cart-0.0.1-SNAPSHOT.jar
```
###### La tablas se crearan al momento de ejecutar el proyecto
## Ve a postman y consulta las url

```sh 
http://localhost:8000/auth  --> registro y autenticacion
http://localhost:8000/api/v1/orders ---> orden
http://localhost:8080/api/v1/order-detail ---> detalle de orde
http://localhost:8080/api/v1/products ---> productos
http://localhost:8080/api/v1/payments ---> pagos
```



