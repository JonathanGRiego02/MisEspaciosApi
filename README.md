# MisEspaciosAPI

**MisEspaciosAPI** es el backend de la plataforma **MisEspacios**, un sistema que permite a los usuarios guardar, gestionar y compartir sus lugares favoritos en un mapa interactivo. Esta API está construida en **Java** utilizando **Spring Boot** y se conecta a una base de datos **SQL Server** para almacenar toda la información relacionada con usuarios, espacios y comentarios.

## Funcionalidades principales

- Gestión de usuarios (registro, login, autenticación).
- CRUD completo de **espacios** (crear, leer, actualizar y eliminar).
- Subida y gestión de imágenes asociadas a espacios.
- Asociación de espacios a usuarios.
- Comentarios y valoraciones de espacios.
- Gestión de favoritos (guardar/retirar espacios favoritos).
- Protección de endpoints mediante autenticación.
- Control de acceso para operaciones sensibles.

## Estructura del proyecto
```
MisEspaciosAPI/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │       └── misespacios/
│   │   │           ├── dto/           # Modelos para mandar datos del backend al frontend
│   │   │           ├── model/         # Entidades JPA que representan las tablas de la BD
│   │   │           ├── repository/    # Interfaces de acceso a datos (Spring Data JPA)
│   │   │           ├── service/       # Lógica de negocio (servicios)
│   │   │           ├── ApiController  # Formalización y lógica de las request
│   │   │           └── MisEspaciosApiApplication.java  # Clase principal Spring Boot
│   │   ├── resources/
│   │   │   ├── application.properties  # Configuración de la BD y otros parámetros
│   │   │   └── static/                  # Recursos estáticos (Query de la DB, inserts, etc...)
├── pom.xml         # Dependencias Maven
└── README.md       # Información del proyecto
```
## Tecnologías utilizadas

- **Java** 
- **Spring Boot** (Framework principal)
- **Spring Data JPA** (Acceso a base de datos)
- **SQL Server** (Base de datos relacional)
- **Maven** (Gestor de dependencias)

## Instalación y ejecución

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/JonathanGRiego02/MisEspaciosApi.git
    ```

2. Crear el archivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=MisEspaciosDB
    spring.datasource.username=TU_USUARIO
    spring.datasource.password=TU_CONTRASEÑA
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Ejecutar la aplicación:
    - Desde el IDE (IntelliJ, Eclipse, VS Code) ejecutando `MisEspaciosApiApplication.java`.
    - O desde la terminal:
    ```bash
    mvn spring-boot:run
    ```

4. La API estará disponible en:
    ```
    http://localhost:8080
    ```
## Endpoints principales

| Método | Endpoint               | Descripción                         |
|:-------|:------------------------|:------------------------------------|
| POST   | `/users`                 | Registro de nuevo usuario          |
| POST   | `/login`                 | Inicio de sesión                   |
| GET    | `/users`                 | Obtiene todos los usuarios         |
| GET    | `/places`                | Obtiene todos los espacios         |
| DELETE | `/places/{id}`           | Eliminar un espacio                |


> **Nota:** leer la documenación para obtener todos los endpoints

## Estado del proyecto

🚧 **En desarrollo activo** — Se están implementando nuevas funcionalidades y mejorando la gestión de seguridad, validaciones y optimización de base de datos.

## Autor

- [JonathanGRiego02](https://github.com/JonathanGRiego02)
