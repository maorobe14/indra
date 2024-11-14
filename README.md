
# Proyecto Gestión de Carrito de Compras

## Descripción

El objetivo del proyecto es crear una API de carrito de compras en **Java Spring Boot**. La API permitirá agregar productos a su carrito, actualizar cantidades, eliminar productos y obtener productos asociados al carrito.

---

## Prerrequisitos despliegue local

Se debe tener instalados los siguientes programas en tu máquina local:

- **Java 17**
- **Maven**
- **PostgreSQL** 
- **Git** 

---

## 1. Clonar el Repositorio

ESta es la ruta del repositorio: https://github.com/maorobe14/indra

Primero, clona el repositorio del proyecto en tu máquina local ejecutando:

```bash
git clone https://github.com/maorobe14/indra.git
```
Una vez clonado, sobre la rama **master** encontrarás todo lo solicitado en la prueba técnica. El código del servicio se encuentra en la carpeta **carrito**. Para la ejecución y análisis del código, se recomienda el uso de un IDE de desarrollo.

## 2. Configuración de la Base de Datos (PostgreSQL)

La base de datos empleada en la aplicacion es **PostgreSQL**, realiza los siguientes pasos para configurarla:

### 2.1. Instalar PostgreSQL

Si aún no tienes PostgreSQL instalado, puedes seguir las instrucciones en la [página de descargas de PostgreSQL](https://www.postgresql.org/download/) según tu sistema operativo.

### 2.2. Crear la Base de Datos

Una vez que PostgreSQL esté instalado en local, se debe crear una base de datos con el nombre **gestion**.

### 2.3. Configurar las Credenciales en el Proyecto

Dentro de tu proyecto, localiza el archivo de configuración de la base de datos, generalmente application.properties o application.yml. Allí, configura las credenciales de la base de datos de la siguiente manera:

Si utilizas application.properties:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/gestion
spring.datasource.username=nombre_de_usuario
spring.datasource.password=contraseña
spring.datasource.driver-class-name=org.postgresql.Driver
```

Si utilizas application.yml:

```bash
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/gestion
    username: nombre_de_usuario
    password: contraseña
    driver-class-name: org.postgresql.Driver
```

Asegúrate de reemplazar nombre_de_base_de_datos, nombre_de_usuario y contraseña con los valores correctos para tu base de datos.


### 3. Ejecutar proyecto

Una vez que hayas clonado el proyecto y configurado la base de datos, es necesario descargar las dependencias del proyecto utilizando Maven. Para hacerlo, abre una terminal en la carpeta raíz del proyecto y ejecuta el siguiente comando:

```bash
mvn install
```

### 4. Ejecutar proyecto

Una vez que el proyecto esté en ejecución, verifica que todo funciona correctamente y que la aplicación se haya desplegado de forma adecuada en el puerto por defecto **8080**. En caso de que el puerto esté ocupado, agrega lo siguiente al archivo de propiedades:

```bash
server:
  port: 8088 // Puerto disponible
```

### 5. Consumo del servicio

