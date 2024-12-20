
# 🃏 **Magic Project** 🎴

![CI](https://github.com/PCalvoGarcia/Magic/actions/workflows/ci.yml/badge.svg)

## 📖 Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Características Principales](#características-principales)
3. [Organización del Backlog y Historias de Usuario](#organización-del-backlog-y-historias-de-usuario)
4. [Estructura del Proyecto](#estructura-del-proyecto)
5. [Instalación y Configuración](#instalación-y-configuración)
6. [Pruebas](#pruebas)
7. [Contribución](#contribución)
8. [Licencia](#licencia)
9. [Detalles Adicionales](#detalles-adicionales)

## 💡 **Descripción del Proyecto**
Magic es un sistema de gestión de cartas para un juego similar a Magic: The Gathering. Ofrece operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre cartas del juego, integrando la lógica de negocio y la validación mediante excepciones personalizadas y DTOs (Data Transfer Objects).

Este proyecto está desarrollado siguiendo principios de **Programación Orientada a Objetos (OOP)** y **Arquitectura Modular**, lo que permite una fácil ampliación y mantenimiento.

## 🚀 **Características Principales**
- **Creación de cartas**: ✍️
    - Permite añadir nuevas cartas al sistema con atributos específicos.
  
- **Consulta de cartas**: 🔍
    - Recupera todas las cartas o busca por nombre o ID.
  
- **Actualización de cartas**: 🔄
    - Modifica los atributos de una carta existente.
  
- **Eliminación de cartas**: 🗑️
    - Elimina cartas por ID.
  
- **Validación de errores**: ⚠️
    - Gestión de excepciones personalizadas como `CardExistException`, `NoIdFoundException`, entre otras.

## 🗂️ **Organización del Backlog y Historias de Usuario**
La gestión del backlog y las historias de usuario de este proyecto se encuentra organizada en dos plataformas:

1. **Notion**: 📝 Las historias de usuario se pueden consultar y actualizar en Notion. En este documento se detalla el backlog, las prioridades y los pasos necesarios para desarrollar las funcionalidades del proyecto. Puedes acceder a la información completa [aquí](https://nettle-talos-25d.notion.site/Magic-15f2e0f9eda080cfb762ca9b09ea3eaa?pvs=4).

2. **GitHub**: 📊 El proyecto en GitHub también incluye un tablero Kanban para la gestión del backlog. Puedes seguir el progreso del proyecto y ver las tareas asignadas, en progreso y completadas. Accede al tablero de GitHub [aquí](https://github.com/users/PCalvoGarcia/projects/1/views/1).


## 📂 **Estructura del Proyecto**
El proyecto se organiza en varias capas que reflejan las buenas prácticas de separación de responsabilidades y modularidad.

### 🏗️ **Estructura de Carpetas**:
- **`dto/`**: 📦 Contiene las clases DTO (Data Transfer Object) que permiten la transferencia de datos entre capas, como `RequestCardDto` y `ResponseCardDto`.
- **`entity/`**: 🏷️ Define las entidades principales, en este caso la clase `Card`, que representa a las cartas.
- **`exceptions/`**: ❗ Incluye excepciones personalizadas para controlar errores en las operaciones CRUD.
- **`repository/`**: 🗃️ Define las interfaces para interactuar con la base de datos (repositorios de cartas).
- **`services/`**: 🔧 Contiene la lógica de negocio y las operaciones CRUD principales.

### 🔑 **Dependencias Principales**:
- **Spring Boot**: Para la configuración y ejecución del servicio.
- **JPA**: Para la integración con la base de datos.
- **JUnit 5**: Para pruebas unitarias.

## 📦 **Instalación y Configuración**
Sigue estos pasos para clonar, configurar e instalar el proyecto en tu máquina local.

1. **Clonar el repositorio**: 📂
   ```bash
   git clone https://github.com/<tu-repositorio>.git
   cd Magic
   ```

2. **Configurar Maven**: ⚙️
- Asegúrate de tener **Maven** instalado. Si no lo tienes, puedes instalarlo desde [aquí](https://maven.apache.org/install.html).
- Verifica que el archivo `pom.xml` tenga las dependencias necesarias para **Spring Boot** y **JPA**.

3. **Compilar y ejecutar el proyecto**: 🏃‍♂️
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**: 🌐
  - La aplicación estará disponible en `http://localhost:8080`.

## 🧪 **Pruebas**
El proyecto incluye pruebas unitarias con **JUnit 5** para asegurar que las funcionalidades esenciales estén funcionando correctamente.

### Ejecutar las pruebas con Maven: 🧑‍💻
```bash
mvn test
```
Los resultados de las pruebas se mostrarán en la terminal o en el directorio `target/test-classes`.

### Ejemplos de pruebas: 📋
- **Crear carta:** Verifica que se pueda crear una carta correctamente.
- **Consultar todas las cartas:** Asegura que se recuperen todas las cartas almacenadas.
- **Actualizar carta:** Comprueba que los datos de una carta puedan ser modificados.
- **Eliminar carta:** Garantiza que una carta sea eliminada correctamente por ID.

## 🔧 **Contribución**
Si deseas contribuir al proyecto, sigue estos pasos:

1. Realiza un **fork** del repositorio. 🍴
2. Crea una **nueva rama** para tus cambios:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza tus cambios y súbelos a la rama:
   ```bash
   git commit -m "Descripción de los cambios"
   git push origin feature/nueva-funcionalidad
   ```
4. Abre un **Pull Request** para que podamos revisar tus cambios. 🔀

## 🧾 **CardController** 🃏
El `CardController` maneja todas las solicitudes HTTP relacionadas con las cartas. Proporciona métodos para crear, leer, actualizar y eliminar cartas (CRUD) y responde con los DTOs correspondientes.

**Métodos**:
- `getCardList()`: 📜 Recupera todas las cartas del sistema.
- `getCardById(Long id)`: 🏷️ Recupera una carta específica por su ID.
- `getCardListByName(String name)`: 🔠 Recupera cartas filtradas por nombre.
- `createCard(RequestCardDto requestCard)`: ✨ Crea una nueva carta.
- `updateCard(Long id, RequestCardDto request)`: 🔄 Actualiza una carta existente.
- `deleteById(Long id)`: 🗑️ Elimina una carta por su ID.

## 🔧 **CardService** 🔧
La clase `CardService` contiene toda la lógica de negocio relacionada con las cartas. Se encarga de crear, actualizar, consultar y eliminar cartas en el repositorio, gestionando las excepciones adecuadamente.

**Métodos**:
- `createCard(RequestCardDto cardDto)`: 🆕 Crea una nueva carta. Verifica si ya existe una carta con los mismos atributos antes de guardarla.
- `findAllCards()`: 🔍 Recupera todas las cartas, lanzando una excepción `NoRegistersFoundException` si no se encuentra ninguna.
- `findCardById(Long id)`: 🏷️ Busca una carta por su ID. Lanza una excepción `NoIdFoundException` si no se encuentra.
- `findCardsByName(String name)`: 🔠 Busca cartas por nombre. Si no se encuentra ninguna, lanza una excepción `NoNameFoundException`.
- `updateCard(Long id, RequestCardDto request)`: 🔄 Actualiza una carta existente. Verifica que la carta no exista previamente antes de realizar la actualización.
- `deleteCardById(Long id)`: 🗑️ Elimina una carta por su ID.

## 🃏 **Card Entity** 🎴
La entidad `Card` representa las cartas del juego en la base de datos, incluyendo atributos como el nombre, tipo de mana, coste de mana, tipo de carta, y más.

**Atributos**:
- `id`: ID de la carta.
- `name`: Nombre de la carta.
- `manaType`: Tipo de mana (representado por un `enum`).
- `manaCost`: Coste de mana.
- `type`: Tipo de carta (representado por un `enum`).
- `typeLine`: Línea de tipo.
- `expansionSymbol`: Símbolo de expansión.
- `skills`: Habilidades de la carta.
- `textEnvironment`: Descripción de entorno de la carta.
- `force`: Fuerza de la carta.
- `resistance`: Resistencia de la carta.

## 📝 Resumen de funcionamiento:
1. **El controlador** (`CardController`) recibe las solicitudes HTTP y delega la lógica de negocio al **servicio** (`CardService`).
2. **El servicio** (`CardService`) maneja la creación, actualización, búsqueda y eliminación de cartas, interactuando con el **repositorio** (`CardRepository`), que realiza las consultas a la base de datos.
3. **La entidad** (`Card`) representa los datos de las cartas que se almacenan y recuperan de la base de datos, utilizando JPA.
