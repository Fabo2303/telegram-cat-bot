package org.example;

public class TelegramBotContent {

    private TelegramBotContent() {
        throw new IllegalStateException("Utility class");
    }

    public static final String STEP_1_TEXT = """
            *¡BIENVENIDO AL NIVEL UNO!*

            Te despiertas y te das cuenta de que eres un gato. Es una buena mañana, pero ¿qué son estos extraños sentimientos? Tu estómago está rugiendo de hambre. No hay comida en el apartamento, excepto en la nevera cerrada. ¿Qué hacer?
            Tendrás que recordar cómo piratear la nevera digital. Abre el libro "Hacking Avanzado para Gatos":

                1. Completa la primera tarea y luego pasa la página.
                2. Avanza y ve a lo más interesante.
                3. Realiza acciones para ganarte el respeto de todos los gatos locales.

            *Gato doméstico común, nivel uno.*

            ¡Hackea la nevera digital y completa la primera tarea!

            """;

    public static final String STEP_2_TEXT = """
            *¡BIENVENIDO AL SEGUNDO NIVEL!*

            ¡Bien! Has derrotado la nevera. ¡Ganas +20 de fama!

            _Acumulado: 20 de fama._

            Elige tu recompensa:

            """;

    public static final String STEP_3_TEXT = """
            *Gato programador, nivel dos.*
            De repente, desde la esquina, apareció un robot aspiradora zumbante. ¡Es hora de vengarse!
            Completa la segunda tarea y ¡hackéalo!
            """;

    public static final String STEP_4_TEXT = """
            *¡BIENVENIDO AL TERCER NIVEL!*

            ¡Has derrotado al robot aspiradora! ¡Ganas +30 de fama!

            _Acumulado: 70 de fama._

            Elige tu recompensa:

            """;

    public static final String STEP_5_TEXT = """
            *Gato Robosubyugador, nivel tres.*
            ¡Jaja! ¡Se encontró una GoPro abandonada en el estante!
            Completa la tercera tarea: ¡póntela y enciéndela!
            """;

    public static final String STEP_6_TEXT = """
            *¡BIENVENIDO AL CUARTO NIVEL!*

            ¡Te pusiste la GoPro! ¡Ganas +40 de fama!

            _Acumulado: 140 de fama._

            Elige tu recompensa:

            """;

    public static final String STEP_7_TEXT = """
            *Gato Videobloguero, nivel cuatro.*
            Ahora, el material grabado debe ser transferido a la computadora.
            Completa la cuarta tarea: ¡hackea la contraseña de la computadora!
            """;

    public static final String STEP_8_TEXT = """
            *¡BIENVENIDO AL QUINTO NIVEL!*

            ¡Has superado la computadora! ¡Ganas +50 de fama!

            _Acumulado: 230 de fama._

            """;

    public static final String FINAL_TEXT = """
            *Gato Hacker, nivel cinco.*
            El día no pasó en vano: el gato hacker tomó medidas y ganó el respeto de los gatos callejeros locales.
            """;
}
