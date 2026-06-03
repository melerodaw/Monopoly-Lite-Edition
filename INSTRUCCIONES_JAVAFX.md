# 🔧 Instrucciones para Cargar JavaFX

El pom.xml ha sido actualizado con las dependencias correctas de JavaFX.

## ⚠️ IMPORTANTE: Pasos a seguir en IntelliJ/JetBrains

### 1️⃣ **Opción A: Recarga Manual (RECOMENDADO)**
   - Abre el menu `File` → `Invalidate Caches...`
   - Selecciona "Invalidate and Restart"
   - El IDE se reiniciará y recargará todas las dependencias

### 2️⃣ **Opción B: Desde el Maven Panel**
   - Abre la ventana de `Maven` (a la derecha del IDE)
   - Haz clic derecho en el proyecto
   - Selecciona `Reload Projects`
   - Espera a que descargue las dependencias

### 3️⃣ **Opción C: Re-importar el Proyecto**
   - Click derecho en `pom.xml`
   - Selecciona `Maven` → `Reload project`
   - Espera a que descargue las dependencias

## ✅ Verificar Instalación

Una vez recargadas las dependencias:
1. Los errores rojos en `ProyectoGrupal.java` desaparecerán
2. Verás los imports de JavaFX sin error
3. Podrás ejecutar el programa

## 🚀 Ejecutar la Aplicación

Una vez todo esté cargado:
- Click derecho en `ProyectoGrupal.java`
- Selecciona `Run 'ProyectoGrupal.main()'`

¡Debería abrirse una ventana de 800x600 con "MONOPOLY LITE EDITION"!

