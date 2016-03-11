# autentiaBackend

COMANDOS MAVEN - SPRING BOOT

	- Para arrancar la aplicación -> mvn spring-boot:run -Drun.arguments="dev"
	  Paso el parámetro dev para cargar el datasource para mysql y para crear y cargar datos en la base de datos.
	- Para pasar test de integración -> mvn integration-test

TESTS DE INTEGRACIÓN

	- Son test de integración para probar los servicios rest, daos y mappers en cada llamada.
	- En el arranque de los tests se carga una base de datos en memoria; H2.
	
PROBLEMAS

	- He tenido problemas con la carga del contexto de spring. Tengo un contexo para la aplicación, con creación 
	  y carga de base de datos en MySql, y tenia otro para los test, con creacion y carga de base de datos en
	  Memoria H2, pero al arrancar los test me cogía las dos contextos, a pesar de poner 'exclude' de 
	  varias maneras, con lo cual se daban multitud de problemas. Para solucionarlo he tenido que dejar solo el 
	  contexto de aplicacion pero pasando un argumento  en el arranque para diferenciar si es ejecucion normal
	  (-Drun.arguments="dev") o test, y de esa manera creo un datasource con su base de datos correspondiente. 
	  Esto parece que es un problema de SpringBoot.
	

TODOs:

	1. Debería implemetar un validador de formato de parámetros para los servicios rest. No sé si JAX-RS tiene algún
	   mecanismo por anotación.  
	
ARQUITECTURA

	1. Los servicios rest los considero servicios de tipo entity, por tanto, no he creido conveniente crear una 
	   capa de servicios o managers entre los servicios rest y los dao. Con lo cual los servicios rest utilizan
	   directamente los Dao.
	
	2. Los Dao hacen uso de los Mappers de MyBatis.
	
	
	
