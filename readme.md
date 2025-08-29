# Image Plugin

Plugin de Spigot que te deja traer cualquier imagen, ya sea local o mediante una url, y verla convertida en bloques dentro de Minecraft.
Transforma los colores en materiales del juego y levanta un mural pixelado automáticamente en tu mundo.
Perfecto para decorar, divertirse y llenar tu server de arte en bloques, o directamente para joder a tu amigo con la cara de feo que tiene y ponerlo en el primer plano de tu server 👏.

## Autor
**Candela Mena Bisignano**

---

## Requisitos
- Servidor de Minecraft con soporte para Spigot (1.21.8 recomendado)
- Java 21
- Maven
- IntelliJ IDEA (u otro IDE compatible con Maven)

---

## Cómo instalar y usar el plugin

### 1️⃣ Tener un servidor de Minecraft listo
Asegurate de que tu servidor soporte **plugins de Spigot** y esté funcionando correctamente.

### 2️⃣ Clonar el repositorio
```bash
git clone <URL-del-repositorio>
```
### 3️⃣ Abrir en IntelliJ IDEA
Abre el proyecto clonado en IntelliJ IDEA (o tu IDE favorito compatible con Maven).

### 4️⃣ Build del proyecto con Maven
Desde IntelliJ o terminal (si tenes instalado Maven) ejecuta:
```bash
mvn clean package
```
Esta build genera un archivo .jar dentro de la carpeta target/.

## 5️⃣ Copiar el plugin al servidor
Copia el .jar generado en target/ a la carpeta /plugins de tu servidor de Minecraft.

## 6️⃣ Correr el servidor
Inicia tu servidor. El plugin se carga automáticamente.

Usa el comando:
```bash
/buildimage <ruta-o-url-de-imagen> [local]
```
para construir la imagen que elegiste en frente tuyo.

> [!NOTE] 
> Si usas local, la imagen debe estar en la carpeta images/ dentro de tu servidor.

## Ejemplos de uso

```bash
/buildimage ejemplo.png local 
/buildimage https://i.imgur.com/imagen.jpg
```

## Resultados en Minecraft
