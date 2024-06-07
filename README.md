# APP_TFG: La Revolución de las clases particulares

## Ciclo: Desarrollo de Aplicaciones Multiplataforma (DAM)

### Autor: Ignacio Ruiz

---

## Índice

1. [Introducción](#introducción)
2. [Funcionalidades y Tecnologías](#funcionalidades-y-tecnologías)
3. [Guía de Instalación](#guía-de-instalación)
4. [Guía de Uso](#guía-de-uso)
5. [Documentación](#documentación)
6. [Interfaz de Usuario](#interfaz-de-usuario)
7. [Conclusión](#conclusión)
8. [Contribuciones y Agradecimientos](#contribuciones-y-agradecimientos)
9. [Licencias](#licencias)
10. [Contacto](#contacto)

---

## Introducción

### Descripción del Proyecto

**APP_TFG** es una aplicación innovadora diseñada para facilitar la busqueda tanto de alumnos como de profesores para clases particulares. Con una interfaz intuitiva y funcional, esta aplicación permite a los estudiantes y profesores gestionar su formacion extracademica.

### Justificación

En el contexto educativo actual, encontrar un buen profesor particular puede ser un desafío. Esta aplicación surge como una solución integral para simplificar este proceso y crear nuna comunidad en la que se retroalimenten tanto alumnos como profesores.

### Objetivos

- Facilitar la busqueda de clases particulares para estudiantes y tutores.
- Proveer una plataforma centralizada para facilitar la tarea.
- Mejorar la comunicación entre estudiantes y tutores.

### Motivación

La motivación principal detrás de este proyecto es mejorar la experiencia de los estudiantes y tutores asi como crear una comunidad entre ellos, promoviendo una mayor eficiencia y organización.

---

## Funcionalidades y Tecnologías

### Funcionalidades

- **Creacion de anuncio de clases particulares**: Creación y seguimiento de anuncio de clase.
- **Busqueda de clases existentes**: Busqueda de clases ya existentes.
- **Seguimiento del Progreso**: Registro y visualización del avance.
- **Visualizacion**: Visualizacion de las clases particulares existentes.

### Tecnologías Utilizadas

- **Frontend**: Andorid Studio
- **Backend**: Spring Framework, Java
- **Base de Datos**: MySQL
- **Autenticación**: JWT (JSON Web Tokens)
- **Despliegue**: Azure

---

## Guía de Instalación

### Requisitos Previos

- Android Studio
- JDK 8 o superior
- MySQL
- Docker (opcional)

### Pasos de Instalación

1. Clona el repositorio:

    ```bash
    git clone https://github.com/IgnacioRuiz01/APP_TFG.git
    cd APP_TFG
    ```

2. Configura la base de datos:

    ```sql
    CREATE DATABASE classadconnect;
    CREATE USER 'classaduser'@'localhost' IDENTIFIED BY 'password';
    GRANT ALL PRIVILEGES ON classadconnect.* TO 'classaduser'@'localhost';
    FLUSH PRIVILEGES;
    ```

3. Configura las variables de entorno:

    ```bash
    cp .env.example .env
    ```

    Edita el archivo `.env` con las configuraciones específicas.

4. Construye y ejecuta la API:

    ```bash
    ./mvnw spring-boot:run
    ```

5. Abre Android Studio y carga el proyecto de la carpeta `android-app`.
6. Conecta un dispositivo Android o usa un emulador y ejecuta la aplicación.


---

## Guía de Uso

1. **Registro e Inicio de Sesión**: Los usuarios pueden registrarse y autenticarse mediante el sistema de autenticación de Spring Security.
2. **Publicación de Anuncios**: Los profesores pueden crear nuevos anuncios de clases particulares.
3. **Búsqueda de Clases**: Los estudiantes pueden buscar anuncios usando filtros por materia, ubicación y disponibilidad.
4. **Visualizacion**: Permite ver todos los anuncios.


---

## Documentación

Para más detalles sobre la API y otras funcionalidades, visita nuestra [documentación](https://github.com/IgnacioRuiz01/API_TFG).

---

## Interfaz de Usuario


Aquí puedes ver algunas capturas de pantalla de la interfaz de usuario de la aplicación:

### Pantalla de Inicio de Sesión
![LoginActivity]("C:\Users\ignac\OneDrive\Escritorio\DAM\2\Multimedia\AndroidStudio\TFG\img\LoginActivity.png")

### Pantalla de Registro
![RegisterForm]("C:\Users\ignac\OneDrive\Escritorio\DAM\2\Multimedia\AndroidStudio\TFG\img\RegisterForm.png")

### Lista de Anuncios
![homeFragment]("C:\Users\ignac\OneDrive\Escritorio\DAM\2\Multimedia\AndroidStudio\TFG\img\homeFragment.png")

### Buscador de Anuncios
![dashboardFragment](path/to/ad_list_screenshot.png)

### Crear Anuncio
![createFragment]("C:\Users\ignac\OneDrive\Escritorio\DAM\2\Multimedia\AndroidStudio\TFG\img\dashboardFragment.png")

### Perfil Usuario
![perfilFragment]("C:\Users\ignac\OneDrive\Escritorio\DAM\2\Multimedia\AndroidStudio\TFG\img\perfilFragment.png")

---


---

## Conclusión

**LinkLearn** es una herramienta poderosa que busca mejorar la experiencia de búsqueda y oferta de clases particulares, promoviendo una mayor eficiencia y organización tanto para profesores como para estudiantes.

---

## Contribuciones y Agradecimientos

Agradecemos a todos los colaboradores y a la comunidad que ha apoyado este proyecto. Si deseas contribuir, por favor, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commits descriptivos.
4. Envía un pull request a la rama `main`.

---

## Licencias

Este proyecto está licenciado bajo la Licencia MIT. Para más detalles, consulta el archivo [LICENSE](LICENSE).

---

## Contacto

Para cualquier consulta o sugerencia, puedes contactarme a través de:

- **Email**: ignacioidigorass@gmail.com
- **LinkedIn**: [Ignacio Ruiz](https://www.linkedin.com/in/ignacio-ruiz/)

