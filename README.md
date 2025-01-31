
# JAVA_62940 - Proyecto CoderHouse Curso de Java

Este proyecto es una aplicación desarrollada en Java como parte del curso de Java en CoderHouse. La aplicación utiliza una base de datos local para gestionar una tienda llamada `tienda_coder`, y permite realizar operaciones CRUD en categorías, productos y usuarios.

## Tabla de Contenidos

- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Estructura de la Base de Datos](#Base-de-datos)
- [Rutas Disponibles](#Rutas-disponibles)


---

## Requisitos

- Java 11 o superior
- IDE para Java (como IntelliJ IDEA o Eclipse)
- Base de datos MySQL (u otra base de datos compatible con el proyecto)
- Postman para realizar pruebas API

## Instalación

1. **Clonar el repositorio**  
   Clona este repositorio en tu máquina local:

   ```bash
   git clone <URL_DE_TU_REPOSITORIO>
   cd JAVA_62940


2. **Configurar la base de datos**  
Crea la base de datos local tienda_coder. Esto puede hacerse mediante una herramienta como MySQL Workbench o directamente desde el terminal:

    ```bash
       CREATE DATABASE tienda_coder;   


3. **Configurar el archivo de propiedades**  
Crea la base de datos local tienda_coder. Esto puede hacerse mediante una herramienta como MySQL Workbench o directamente desde el terminal:

    ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/tienda_coder
    spring.datasource.username=TU_USUARIO
    spring.datasource.password=TU_CONTRASEÑA
    spring.jpa.hibernate.ddl-auto=update
   
4. **Levantar la aplicación**  

## Base de datos
Insertar algunos datos en la base de datos


    INSERT INTO categorias (nombre) VALUES ('Pizza'), ('Empanada');
    
    INSERT INTO productos (nombre, precio, categoria_id, stock) VALUES 
    ('Pizza Napolitana', 800.00, 1, 50),
    ('Pizza Cuatro Quesos', 900.00, 1, 20),
    ('Empanada de Carne', 200.00, 2, 36),
    ('Empanada de Jamón y Queso', 250.00, 2, 48);

    INSERT INTO usuarios (username, password, email, created_at)
    VALUES
    ('facundo', 'clave123', 'facundo@correo.com', NOW()),
    ('martina', 'secreto456', 'martina@correo.com', NOW());      

## Rutas disponibles

1. **Donde obtener los endpoints**
    - **URL**: `http://localhost:8080/swagger-ui/index.html#/`
    - **Repositorio**: Archivo adjunto en la entrega.
   
