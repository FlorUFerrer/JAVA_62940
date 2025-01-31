
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

     ```bash
    INSERT INTO categorias (nombre) VALUES ('Pizza'), ('Empanada');
    
    INSERT INTO productos (nombre, precio, categoria_id) VALUES 
    ('Pizza Napolitana', 800.00, 1),
    ('Pizza Cuatro Quesos', 900.00, 1),
    ('Empanada de Carne', 200.00, 2),
    ('Empanada de Jamón y Queso', 250.00, 2);

    INSERT INTO usuarios (username, password, email, created_at)
    VALUES
    ('facundo', 'clave123', 'facundo@correo.com', NOW()),
    ('martina', 'secreto456', 'martina@correo.com', NOW());      

## Rutas disponibles

1. **Obtener todos los productos**
    - **Método**: `GET`
    - **URL**: `/productos`
    - **Body**: No requiere.
   

2. **Obtener un producto por ID**
    - **Método**: `GET`
    - **URL**: `/productos/{id}`
    - **Body**: No requiere.
    - **Descripción**: Reemplaza `{id}` con el ID del producto que deseas consultar.


3. **Crear un nuevo producto**
    - **Método**: `POST`
    - **URL**: `/productos`
    - **Body** (JSON):
      ```json
      {
          "nombre": "Nombre del producto",
          "precio": 0.00,
          "categoria": {
              "id": 1
          }
      }
      ```
    - **Descripción**: Proporciona los datos del nuevo producto, incluyendo el nombre, precio y la categoría asociada.


4. **Actualizar un producto existente**
    - **Método**: `PUT`
    - **URL**: `/productos/{id}`
    - **Body** (JSON):
      ```json
      {
          "nombre": "Nuevo nombre del producto",
          "precio": 0.00,
          "categoria": {
              "id": 1
          }
      }
      ```
    - **Descripción**: Reemplaza `{id}` con el ID del producto que deseas actualizar y proporciona los datos a modificar.


5. **Eliminar un producto por ID**
    - **Método**: `DELETE`
    - **URL**: `/productos/{id}`
    - **Body**: No requiere.
    - **Descripción**: Reemplaza `{id}` con el ID del producto que deseas eliminar.
