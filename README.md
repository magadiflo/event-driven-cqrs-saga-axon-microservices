# [Event-Driven Microservices, CQRS, SAGA, Axon, Spring Boot](https://indra.udemy.com/course/spring-boot-microservices-cqrs-saga-axon-framework/)

- Curso tomado de `Udemy` del instructor `Sergey Kargopolov`.
- Aprenda a crear microservicios distribuidos basados en eventos `(Event-driven)`, `CQRS`, `Event Sourcing`, `SAGA` y
  `transactions`.

---

## Descripción general de la arquitectura de microservicios

Supongamos que has empezado a construir tu proyecto como una pequeña aplicación monolítica. Su única aplicación
monolítica se empaqueta como archivo `WAR`, se despliega en un contenedor de `Tomcat` y funciona con una única base de
datos de escala mínima.

![01.png](assets/section-01/01.png)

Ahora, con el tiempo, la funcionalidad de la aplicación creció y se hizo muy grande. Su equipo decidió dividir esta gran
aplicación monolítica en microservicios más pequeños.

![02.png](assets/section-01/02.png)

Cuando su aplicación se divide en varios microservicios diferentes, cada microservicio tendrá su propia base de datos,
a esto se le denomina `patrón de diseño de base de datos por servicio`. Ya no existe una única base de datos que
contenga toda la información sobre productos, usuarios y pedidos en una sola base de datos.

La información del producto estará en la base de datos de productos, la información del usuario estará en la base de
datos de usuarios, y la información del pedido estará almacenada en la base de datos de pedidos.

En caso sea necesario, los microservicios pueden ampliarse. Por ejemplo, si aumenta la carga de un microservicio, puede
ejecutar varias instancias del mismo en el mismo servidor y este será otro patrón de diseño de microservicios que se
llama `múltiples instancias de servicio por host`.

En la imagen anterior podemos ver varios patrones de diseño, como el patrón de diseño de base de datos por servicio, el
patrón de diseño de descubrimiento de servicios, el API Gateway y la configuración centralizada, y hay muchos más con
los que aún podemos encontrarnos, como por ejemplo, rastreo distribuido, circuit breaker, token de acceso, event
sourcing, CQRS, SAGA y muchos más.

![03.png](assets/section-01/03.png)