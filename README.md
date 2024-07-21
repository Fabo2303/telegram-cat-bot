# Telegram Bot en Java

## Descripción

Este es un bot para Telegram desarrollado en Java utilizando Maven. El bot presenta la aventura de un gato hacker que enfrenta diversas dificultades.

### Requisitos

- Java JDK
- Maven
- Telegram
- IDE o editor de código

## Configuración

1. **Crear un bot en Telegram:**
    - Utiliza el bot @BotFather en Telegram para crear un nuevo bot.
    - Obtén el nombre del bot y su token de acceso (¡No compartas este token! Si lo compartes por error, usa el método `/revoke` para generar uno nuevo).

2. **Configurar el archivo MyFirstTelegramBot.java:**
    - Encuentra el archivo `MyFirstTelegramBot.java` en la ruta:
      ```
      src/main/java/es/codegym/telegrambot/MyFirstTelegramBot.java
      ```
    - En las primeras líneas de este código, ingresa el nombre y el token del bot:
      ```java
      public class MyFirstTelegramBot extends MultiSessionTelegramBot {
          public static final String NAME = "Nombre del bot";
          public static final String TOKEN = "Token del bot";
      }
      ```

3. **Ejecutar el bot:**
    - Ejecuta el bot y asegúrate de que funcione correctamente. Es normal ver algunos mensajes en la consola como:
      ```
      SLF4J: No SLF4J providers were found.
      SLF4J: Defaulting to no-operation (NOP) logger implementation
      SLF4J: See https://www.slf4j.org/codes.html#noProviders for further details.
      ```

### Instalación

1. **Clonar el repositorio:**
    ```bash
    git clone git@github.com:Fabo2303/telegram-cat-bot.git
    ```

## Uso

1. **Comenzar el juego:**
    - Envía el mensaje `/start` al bot para iniciar el juego.

2. **Ingresar nombre de usuario:**
    - Cuando se te solicite el nombre de usuario, responde con un mensaje que contenga alguna de estas palabras (soy, llamo, nombre), seguido por el nombre de usuario que quieres usar.

3. **Interactuar con los botones:**
    - Haz clic en los botones proporcionados por el bot para continuar la aventura.

![bot](https://github.com/Fabo2303/telegram-cat-bot/assets/115574987/609d24c8-d5e6-453d-8d15-059fd355c9f0)

Si tienes alguna pregunta o puedes mejorar este README, ¡no dudes en contribuir!
