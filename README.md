# Sistema de Integración y Análisis de Datos de Ventas

**Proyecto Final de Programación en Java**  

**Integrantes:** Kevin Josue Orellana Perez y Erick Garcia

## 1. Descripción del Proyecto
Aplicación de consola en Java orientada a la ingeniería y análisis de datos. El sistema extrae información de ventas desde múltiples fuentes (CSV, JSON, XML), 
normaliza los textos, valida reglas de negocio, evita duplicados y almacena los registros válidos en una base de datos PostgreSQL. Finalmente, calcula indicadores y genera reportes de texto plano.

## 2. Requisitos Previos
*   **Java Development Kit (JDK):** Versión 17 o superior (el proyecto está configurado para Java 25 en Maven).
*   **Gestor de dependencias:** Apache Maven .
*   **Base de datos:** PostgreSQL instalado y ejecutándose localmente .
*   **Librerías externas:** PostgreSQL JDBC Driver y Google Gson .

## 3. Configuración de la Base de Datos
1.  Abre pgAdmin y crea una base de datos llamada `sistema_ventas_db`.
2.  Ejecuta el script SQL adjunto en los entregables (`script_tablas.sql`) para crear las tablas `ventas_validas` y `ventas_rechazadas`.
3.  Si tus credenciales locales de PostgreSQL son diferentes, debes actualizarlas en la clase `ConexionBD.java` ubicada en el paquete `persistencia`:
    ```java
    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_ventas_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password"; // Cambiar por tu contraseña
    ```

## 4. Instrucciones de Compilación y Ejecución
**Desde NetBeans:**
1.  Abre NetBeans y ve a `File > Open Project...` y selecciona la carpeta del proyecto.
2.  Haz clic derecho sobre el proyecto y selecciona **"Clean and Build"** para que Maven descargue las dependencias (Gson y PostgreSQL JDBC).
3.  Haz clic en el botón de **"Run"** (o clic derecho > Run) para iniciar la aplicación.
4.  La consola te pedirá la ruta donde están los archivos. Debes ingresar la ruta absoluta de la carpeta (ej. `C:\Users\user\Desktop\Archivos`).
5.  El programa va a leer los archivos, va a extraer los datos y los va a almacenar en la base de datos, y va a generar los reportes en la carpeta asignada.
6.  observe los reportes creados con la informacion y resultados
