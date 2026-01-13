cat > README.md <<'EOF'
# Ejercicio – Content Providers (PDDM)

Este repositorio contiene **dos aplicaciones Android** que trabajan juntas mediante un ContentProvider para exponer y consumir datos de usuarios almacenados en SQLite.

# Estructura del repositorio

- App1_SQLiteUsers
  Aplicación **proveedora**. Contiene la base de datos SQLite (tabla: Usuarios) y un ContentProvider publicado para exponer operaciones CRUD.

- App2_CP_Client 
  Aplicación **cliente**. Solicita : User Name y Password y valida credenciales **consultando el ContentProvider** de la App 1 (sin acceso directo a la BD).

# Objetivo cumplido

# App 1 (Provider)
- Expone datos de la tabla Usuarios mediante ContentProvider.
- Soporta operaciones:
  - query() (consulta)
  - insert() (inserción)
  - update() (actualización)
  - delete() (eliminación)
- Publicación mediante AndroidManifest.xml con authority y URIs soportadas.

# App 2 (Client)
- UI con:
  - Campo User Name
  - Campo Password
  - Botón Login
- Comportamiento:
  - Credenciales correctas → acceso permitido (navega a pantalla de bienvenida).
  - Credenciales incorrectas → muestra Toast: "Error usuario/password incorrectos"
- Validación realizada exclusivamente** mediante:
  - contentResolver.query(...)

# Datos de prueba

En la App 1 existe un usuario demo creado al inicializar la BD:
- **username: lolo
- **password: 1234

# URI / Authority usados

El cliente consulta el provider de App 1 con:

- **Authority: com.mastermovilesua.persistencia.preferencias.sqliteusers.usersprovider
- **URI: content://com.mastermovilesua.persistencia.preferencias.sqliteusers.usersprovider/usuarios

## Cómo ejecutar (paso a paso)

1. Abrir App1_SQLiteUsers en Android Studio y ejecutar en el emulador/dispositivo.
2. Abrir App2_CP_Client en Android Studio y ejecutar en el mismo emulador/dispositivo.
3. Probar en App 2:
   - Login incorrecto (ej. lolo / 0000) → debe aparecer el Toast de error.
   - Login correcto ( lolo / 1234) → debe permitir el acceso y mostrar pantalla Welcome con el username.

# Evidencias (vídeo)

Se han realizado vídeos demostrativos:
- Vídeo 1 (App 1 - Provider): evidencia de que el ContentProvider está operativo (y opcionalmente consulta con adb).
- Vídeo 2 (App 2 - Client): validación incorrecta y correcta consultando el ContentProvider y navegación a pantalla Welcome.

# Problemas encontrados y solución
 1) adb: command not found (macOS)
Problema:** el comando adb no estaba disponible en PATH.  
Solución: usar la ruta completa del SDK:

/Users/carls_c/Library/Android/sdk/platform-tools/adb

Ejemplo:
bash
/Users/carls_c/Library/Android/sdk/platform-tools/adb devices
/Users/carls_c/Library/Android/sdk/platform-tools/adb shell content query uri "content://com.mastermovilesua.persistencia.preferencias.sqliteusers.usersprovider/usuarios"
