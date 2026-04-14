# API REST Escolar — Alumnos y Profesores

API REST construida con **Spring Boot 3.2** (Java 17) que gestiona dos entidades: `Alumno` y `Profesor`. Los datos se almacenan **en memoria** (se pierden al apagar la aplicación) sin ninguna conexión a base de datos.

---

## 🏗 Arquitectura del proyecto

```
api-escolar/
├── pom.xml
└── src/main/java/com/escolar/
    ├── ApiEscolarApplication.java        ← Punto de entrada Spring Boot
    ├── model/
    │   ├── Alumno.java                   ← Entidad Alumno
    │   └── Profesor.java                 ← Entidad Profesor
    ├── dto/
    │   ├── AlumnoRequest.java            ← DTO entrada + validaciones
    │   ├── ProfesorRequest.java          ← DTO entrada + validaciones
    │   └── ApiResponse.java              ← Envoltorio JSON genérico
    ├── repository/
    │   ├── AlumnoRepository.java         ← Almacén en memoria (ArrayList)
    │   └── ProfesorRepository.java       ← Almacén en memoria (ArrayList)
    ├── service/
    │   ├── AlumnoService.java            ← Lógica de negocio
    │   └── ProfesorService.java          ← Lógica de negocio
    ├── controller/
    │   ├── AlumnoController.java         ← Endpoints REST /alumnos
    │   └── ProfesorController.java       ← Endpoints REST /profesores
    └── exception/
        ├── ResourceNotFoundException.java
        └── GlobalExceptionHandler.java   ← Manejo centralizado de errores
```

---

## ▶ Cómo ejecutar

### Prerrequisitos
- Java 17+
- Maven 3.8+

### Pasos

```bash
# 1. Clonar / descomprimir el proyecto
cd api-escolar

# 2. Compilar y ejecutar
mvn spring-boot:run

# O compilar un JAR ejecutable
mvn clean package
java -jar target/api-escolar-1.0.0.jar
```

La API estará disponible en: `http://localhost:8080`

---

## 📋 Endpoints

### ALUMNOS

| Método | URL              | Descripción              | HTTP OK |
|--------|------------------|--------------------------|---------|
| GET    | /alumnos         | Listar todos             | 200     |
| GET    | /alumnos/{id}    | Obtener por ID           | 200     |
| POST   | /alumnos         | Crear nuevo              | 201     |
| PUT    | /alumnos/{id}    | Actualizar               | 200     |
| DELETE | /alumnos/{id}    | Eliminar                 | 200     |

### PROFESORES

| Método | URL                | Descripción              | HTTP OK |
|--------|--------------------|--------------------------|---------|
| GET    | /profesores        | Listar todos             | 200     |
| GET    | /profesores/{id}   | Obtener por ID           | 200     |
| POST   | /profesores        | Crear nuevo              | 201     |
| PUT    | /profesores/{id}   | Actualizar               | 200     |
| DELETE | /profesores/{id}   | Eliminar                 | 200     |

---

## 📦 Modelos JSON

### Alumno
```json
{
  "id": 1,
  "nombres": "Juan Carlos",
  "apellidos": "García López",
  "matricula": "A2024001",
  "promedio": 9.5
}
```

### Profesor
```json
{
  "id": 1,
  "numeroEmpleado": "EMP-001",
  "nombres": "María Elena",
  "apellidos": "Ramírez Torres",
  "horasClase": 20
}
```

---

## ✅ Validaciones

### Alumno (AlumnoRequest)

| Campo      | Tipo   | Reglas                                            |
|------------|--------|---------------------------------------------------|
| nombres    | String | No vacío, 2–100 caracteres, solo letras y espacios |
| apellidos  | String | No vacío, 2–100 caracteres, solo letras y espacios |
| matricula  | String | No vacío, 3–20 caracteres, letras/números/guiones  |
| promedio   | Double | No nulo, valor entre 0.0 y 10.0                   |

### Profesor (ProfesorRequest)

| Campo          | Tipo    | Reglas                                             |
|----------------|---------|----------------------------------------------------|
| numeroEmpleado | String  | No vacío, 2–20 caracteres, letras/números/guiones  |
| nombres        | String  | No vacío, 2–100 caracteres, solo letras y espacios |
| apellidos      | String  | No vacío, 2–100 caracteres, solo letras y espacios |
| horasClase     | Integer | No nulo, valor entre 1 y 60                        |

---

## 🔢 Códigos HTTP usados

| Código | Cuándo se usa                                          |
|--------|--------------------------------------------------------|
| 200    | Consulta, actualización o eliminación exitosa          |
| 201    | Creación exitosa (POST)                                |
| 400    | Error de validación o JSON malformado                  |
| 404    | Recurso no encontrado por ID                           |
| 500    | Error interno inesperado del servidor                  |

---

## 🧪 Ejemplos con cURL

### Crear un alumno
```bash
curl -X POST http://localhost:8080/alumnos \
  -H "Content-Type: application/json" \
  -d '{
    "nombres": "Juan Carlos",
    "apellidos": "García López",
    "matricula": "A2024001",
    "promedio": 9.5
  }'
```

**Respuesta 201:**
```json
{
  "success": true,
  "mensaje": "Alumno creado correctamente",
  "data": {
    "id": 1,
    "nombres": "Juan Carlos",
    "apellidos": "García López",
    "matricula": "A2024001",
    "promedio": 9.5
  }
}
```

### Obtener todos los alumnos
```bash
curl http://localhost:8080/alumnos
```

### Obtener alumno por ID
```bash
curl http://localhost:8080/alumnos/1
```

### Actualizar alumno
```bash
curl -X PUT http://localhost:8080/alumnos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombres": "Juan Carlos",
    "apellidos": "García López",
    "matricula": "A2024001",
    "promedio": 9.8
  }'
```

### Eliminar alumno
```bash
curl -X DELETE http://localhost:8080/alumnos/1
```

**Respuesta 200:**
```json
{
  "success": true,
  "mensaje": "Alumno eliminado correctamente"
}
```

### Error 404 (no encontrado)
```bash
curl http://localhost:8080/alumnos/999
```
```json
{
  "success": false,
  "mensaje": "Alumno con id 999 no encontrado"
}
```

### Error 400 (validación)
```bash
curl -X POST http://localhost:8080/alumnos \
  -H "Content-Type: application/json" \
  -d '{"nombres": "", "promedio": 15}'
```
```json
{
  "success": false,
  "mensaje": "Error de validación en los datos enviados",
  "errores": {
    "nombres": "El campo 'nombres' no puede estar vacío",
    "apellidos": "El campo 'apellidos' no puede estar vacío",
    "matricula": "El campo 'matricula' no puede estar vacío",
    "promedio": "El campo 'promedio' debe ser menor o igual a 10.0"
  }
}
```

---

### Crear un profesor
```bash
curl -X POST http://localhost:8080/profesores \
  -H "Content-Type: application/json" \
  -d '{
    "numeroEmpleado": "EMP-001",
    "nombres": "María Elena",
    "apellidos": "Ramírez Torres",
    "horasClase": 20
  }'
```

---

## 🛠 Tecnologías

- **Java 17**
- **Spring Boot 3.2** — framework principal
- **Spring Web (Spring MVC)** — motor REST
- **Spring Validation (Jakarta Bean Validation)** — validaciones declarativas
- **Jackson** — serialización/deserialización JSON
- **Maven** — gestor de dependencias
