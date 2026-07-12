# 🎓 CursosCG — Gestión de Cursos del Bootcamp

---

## Contexto del negocio

**CodiGo** está creciendo: cada mes abren más cohortes, contratan más docentes y matriculan más estudiantes. Hoy el registro de cursos se lleva en una hoja de cálculo compartida — y ya se están perdiendo datos, duplicando docentes y borrando cursos por accidente.

Nos pidieron construir la **API REST** que va a reemplazar esa hoja de cálculo. El frontend lo hará otro equipo; nuestro contrato con ellos son los endpoints y sus respuestas JSON.

---

## Fase 0 — Verificación de vida

Antes de escribir la API real, necesitamos un endpoint de bienvenida que responda con un texto. Sirve para verificar que el proyecto arrancó correctamente y para familiarizarnos con la anatomía de un controller.

| Método | Ruta | Respuesta |
|---|---|---|
| GET | `/api/v1/saludo` | `¡Hola desde Spring Boot!` |
| GET | `/api/v1/adios`  | `¡Nos vemos!` |

---

## Fase 1 — La API de Docentes

Los docentes son el primer recurso a gestionar, porque sin docentes no hay cursos. Empezamos por acá.

### RF-01 · Registro de docentes

Registrar un docente con:

| Campo | Reglas |
|---|---|
| `nombre` | Obligatorio, entre 2 y 60 caracteres |
| `apellido` | Obligatorio, entre 2 y 60 caracteres |
| `especialidad` | Obligatorio (ej. "Backend", "Frontend", "Data", "QA") |
| `email` | Obligatorio, formato de correo válido, único en el sistema |

Cada docente recibe un identificador numérico único generado por la base de datos.

### RF-02 · Consulta de docentes

- Obtener un docente por su id.
- Buscar docentes por especialidad — útil para el frontend cuando el administrador quiere ver *"todos los docentes de Backend"*. Si no hay resultados, la búsqueda devuelve una lista vacía, no un error.

### Requerimientos técnicos (acordados con el equipo de frontend)

| Tema | Acuerdo |
|---|---|
| Arquitectura | Capas: controller → service → repository, con entidades JPA y PostgreSQL |
| Contrato de entrada/salida | La API **nunca** expone entidades: recibe y devuelve **DTOs** |
| Validaciones | Todo dato de entrada se valida; los errores viajan con detalle por campo |
| Códigos HTTP | `201` creación exitosa · `200` consulta exitosa · `404` recurso inexistente · `400` datos inválidos |
| Formato de errores de validación | JSON con estructura `{ "nombreDelCampo": "mensaje del error", ... }` |
| Datos semilla | El proyecto arranca con algunos docentes de prueba ya cargados |

### Endpoints esperados (Fase 1)

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/v1/docentes/crear` | Registrar docente |
| GET  | `/api/v1/docentes/buscar/{id}` | Consultar docente por id |
| GET  | `/api/v1/docentes/buscar-especialidad?especialidad=...` | Buscar por especialidad |

---

## Fase 2 — Cursos y estudiantes

Con docentes registrados, ya podemos modelar los **cursos** que ellos dictan y los **estudiantes** que se inscriben.

### RF-03 · Cursos

Un curso tiene:

| Campo | Descripción |
|---|---|
| `nombre` | Nombre del curso |
| `descripcion` | Descripción breve |
| `duracionHoras` | Duración en horas |
| `docente` | Docente responsable del curso |

- Un docente puede dictar varios cursos, pero cada curso tiene un solo docente responsable **(relación 1—N)**.

### RF-04 · Estudiantes

Un estudiante tiene: **nombre**, **apellido** y **email**.

- Un estudiante puede estar inscrito en varios cursos.
- Un curso puede tener varios estudiantes inscritos.
- Relación **N—M** entre cursos y estudiantes.
- Los estudiantes son **datos maestros**: los mantiene el equipo administrativo, no se crean desde esta API.

### RF-05 · Consulta de curso

Al obtener un curso por su id, la respuesta debe **aplanar** las relaciones — no exponer objetos anidados:

- El **nombre completo del docente** (nombre + apellido) como un solo string, no el objeto Docente entero.
- La **lista de nombres completos de los estudiantes** inscritos como `List<String>`, no la lista de objetos Estudiante.

Ejemplo de respuesta esperada:

```json
{
  "idCurso": 1,
  "nombre": "Spring Boot Fundamentos",
  "descripcion": "Introducción a APIs REST con Spring",
  "duracionHoras": 40,
  "nombreDocente": "Ana Torres",
  "estudiantes": [
    "Carlos Mendoza",
    "Sofía Quispe",
    "Jorge Flores"
  ]
}
```

### Endpoints esperados (Fase 2)

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/v1/cursos/buscar/{id}` | Consultar curso con docente y estudiantes |

---

## Estructura del repositorio

El proyecto base ya tiene la estructura de paquetes y las dependencias configuradas (`Spring Web`, `Spring Data JPA`, `Validation`, `Lombok`, `PostgreSQL Driver`). El código lo escribiremos en vivo durante la tutoría.

```
com.codigo.cursos
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── mapper
├── repository
└── service
```

---

## Criterios de aceptación (los probaremos en Postman)

1. `GET /api/v1/saludo` responde `200` con el texto de bienvenida.
2. Crear un docente válido responde `201 Created` con el docente creado (id incluido).
3. Crear un docente con body vacío o con email inválido responde `400 Bad Request` con un JSON del tipo `{ "campo": "mensaje del error" }` para CADA campo que falló.
4. Buscar un docente por id inexistente responde `404 Not Found` sin body.
5. Buscar docentes por una especialidad que no existe responde `200 OK` con lista vacía `[]`.
6. Al consultar un curso, la respuesta trae `nombreDocente` como string y `estudiantes` como lista de strings — no entidades anidadas.
7. Al consultar un curso inexistente, la respuesta es `404 Not Found`.

---


Los actos que vamos a recorrer:

1. **Un endpoint que devuelve texto** — para entender `@RestController`, `@RequestMapping`, `@GetMapping`.
2. **CRUD de docentes exponiendo entidades** — funciona, pero es peligroso.
3. **DTOs** — para desacoplar la API del modelo interno.
4. **Mapper** — para centralizar las conversiones y no duplicar código.
5. **ResponseEntity** — para devolver códigos HTTP honestos (201, 200, 404).
6. **Validation + GlobalExceptionHandler** — para rechazar datos inválidos con mensajes claros.
7. **Relaciones ManyToOne y ManyToMany** — para modelar cursos, docentes y estudiantes juntos, y exponerlos por la API sin caer en bucles ni datos de más.
