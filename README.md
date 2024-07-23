# Historias de Usuario para Proyecto de Sistema de Reserva de Espacios

## Gestión de Usuarios

### Registro de Usuario

**Descripción:**
Como usuario, quiero poder registrarme en la aplicación para poder acceder a todas las funcionalidades.

**Criterios de Aceptación:**
- El usuario debe poder ingresar su nombre, correo electrónico, contraseña y rol.
- El sistema debe validar que el correo no esté en uso.
- El sistema debe enviar un correo de verificación.

### Inicio de Sesión

**Descripción:**
Como usuario registrado, quiero poder iniciar sesión para acceder a mi cuenta.

**Criterios de Aceptación:**
- El usuario debe poder ingresar su correo y contraseña.
- El sistema debe validar las credenciales y permitir el acceso.
- En caso de error, se debe mostrar un mensaje de error.

### Perfil de Usuario

**Descripción:**
Como usuario, quiero poder ver y editar mi perfil para mantener mi información actualizada.

**Criterios de Aceptación:**
- El usuario debe poder ver su información de perfil.
- El usuario debe poder actualizar su nombre, correo, contraseña y otros detalles personales.
- El sistema debe validar los cambios y guardarlos.

### Roles y Permisos

**Descripción:**
Como administrador, quiero poder asignar roles y permisos a los usuarios para gestionar el acceso a diferentes funcionalidades de la aplicación.

**Criterios de Aceptación:**
- El administrador debe poder asignar roles como ADMIN y USER.
- Los permisos deben ser gestionados según el rol asignado.
- El sistema debe restringir el acceso a funcionalidades basadas en el rol del usuario.

## Gestión de Espacios

### Visualización de Espacios como Invitado

**Descripción:**
Como invitado (Guest), quiero poder ver todas las opciones de espacio disponibles para tener una idea de las ofertas sin necesidad de registrarme.

**Criterios de Aceptación:**
- El invitado debe poder ver la lista completa de espacios disponibles.
- El invitado debe poder ver detalles de cada espacio, como nombre, ubicación, capacidad y disponibilidad.
- El sistema no debe permitir realizar reservas sin registrarse, pero debe mostrar un mensaje invitando al usuario a registrarse para hacerlo.

### Registro de Espacios

**Descripción:**
Como administrador, quiero poder registrar nuevos espacios en la aplicación para ofrecer más opciones a los usuarios.

**Criterios de Aceptación:**
- El administrador debe poder ingresar detalles del espacio como nombre, ubicación, capacidad, descripción y disponibilidad.
- El sistema debe validar que la información ingresada es correcta.
- El sistema debe guardar el nuevo espacio en la base de datos y estar disponible para los usuarios.

### Actualización de Espacios

**Descripción:**
Como administrador, quiero poder actualizar la información de los espacios existentes para mantener los detalles correctos y actualizados.

**Criterios de Aceptación:**
- El administrador debe poder actualizar cualquier detalle del espacio.
- El sistema debe validar los cambios y guardarlos.
- Los usuarios deben ver la información actualizada inmediatamente.

### Eliminación de Espacios

**Descripción:**
Como administrador, quiero poder eliminar espacios que ya no están disponibles para mantener la lista de espacios actualizada.

**Criterios de Aceptación:**
- El administrador debe poder eliminar un espacio de la lista.
- El sistema debe confirmar la acción antes de eliminar el espacio.
- El espacio eliminado no debe ser visible para los usuarios ni estar disponible para reservas.

## Gestión de Reservas

### Crear Reserva

**Descripción:**
Como usuario, quiero poder reservar un espacio para un tiempo específico.

**Criterios de Aceptación:**
- El usuario debe poder seleccionar un espacio y un tiempo de reserva.
- El sistema debe verificar la disponibilidad del espacio para el tiempo seleccionado.
- La reserva debe ser guardada y confirmada al usuario.

### Ver Reservas de Usuario

**Descripción:**
Como usuario, quiero poder ver una lista de todas mis reservas para poder gestionarlas.

**Criterios de Aceptación:**
- El usuario debe poder ver una lista de sus reservas con detalles.
- El sistema debe permitir al usuario cancelar o modificar reservas existentes.

# TAREAS

## ENTORNO

- Configuración del proyecto, agregar las dependencias necesarias para el proyecto.
- Configuración de docker-compose.yml con mysql
- Crear la base de datos.

## REGISTRO DE USUARIOS
- Crear las entidad usuario.
- Crear repositorio.
- Crear servicio.
- Crear controlador.
- Crear seguridad.
- Crear validación.
- Encriptar contraseña
- Manejo de errores.
- Creación de pruebas unitarias.

## INICIO DE SESIÓN
- Crear endpoint para iniciar sesión.
- Agregar validaciones de inicio de sesión.
- Añadir seguridad a la contraseña.
- Pruebas unitarias.

## ROLES Y PERMISOS
- Crear los roles (User, Admin, Guest).
- Añadir seguridad a la contraseña.
- Pruebas unitarias.

## REGISTRO DE ESPACIOS
- Crear las entidad spaces.
- Crear repositorio.
- Crear servicio.
- Crear controlador.
- Crear seguridad.
- Crear validación.
- Encriptar contraseña
- Manejo de errores.
- Creación de pruebas unitarias.


## ENDPOINT
- ´ /api/users/register ´
```json
 {
  "name": "string",
  "lastName": "string",
  "email": "string",
  "password": "string"
  }
  ```

- /api/spaces/register
```json
  {
  "name": "string",
  "location": "string",
  "capacity": "int",
  "description": "string",
  "availability": "boolean"
  }
```