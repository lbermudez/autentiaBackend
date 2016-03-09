# autentiaBackend

COMANDOS MAVEN - SPRING BOOT
	- Para arrancar la aplicacion -> mvn spring-boot:run -Drun.arguments="dev"
	  Paso el parametro dev para cargar el datasource para mysql y para crear y cargar datos en la base de datos.
	- Para pasar test de integracion -> mvn integration-test

TESTS DE INTEGRACIÓN
	- Son test de integración para probar los servicios rest, daos y mappers en cada llamada.
	- En el arranque de los tests se carga una base de datos en memoria; H2.  
	- El test createCourseWithNotExistsTeacher falla porque al insertar un curso con un profesor inexistente
	  el servicio debería devolver un error 400 (BAD_REQUEST) pero no está ocurriendo porque la base de datos H2 no está lanzando 
	  una excepción por Foreing Key Constraint.

TODOs:
	1. Debería implemetar un validador de formato de parámetros para los servicios rest. No sé si JAX-RS tiene algún mecanismo
	   por anotación.  
	
ARQUITECTURA
	1. Los servicios rest los considero servicios de tipo entity, por tanto, no he creido conveniente crear una capa de servicios o managers 
	   entre los servicios rest y los dao. Con lo cual los servicios rest utilizan directamente los Dao.
	
	2. Los Dao hacen uso de los Mappers de MyBatis.
	
