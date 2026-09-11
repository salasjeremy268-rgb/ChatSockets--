# ChatSockets_JLSP

Aplicación de chat en tiempo real desarrollada en **Java**, basada en una arquitectura **cliente-servidor** que utiliza **Sockets TCP** para la comunicación en red. El proyecto incluye interfaces gráficas construidas con **Swing** tanto para el servidor como para los clientes, permitiendo una gestión visual e intuitiva de las conexiones y la conversación.

## Características

- 🔌 **Comunicación por Sockets TCP** sobre el puerto 5000.
- 🧵 **Multithreading**: el servidor atiende a cada cliente en un hilo independiente, permitiendo conexiones simultáneas sin bloquear el resto del sistema.
- 📢 **Broadcast de mensajes**: todo mensaje enviado por un cliente es retransmitido automáticamente al resto de usuarios conectados.
- 🖥️ **Interfaz gráfica del servidor**: muestra en tiempo real el log de conexiones, desconexiones y mensajes, además del número de clientes activos.
- 💬 **Interfaz gráfica del cliente**: ventana de chat con campo de texto y área de mensajes, donde cada usuario elige un nickname al conectarse.
- 🔔 **Notificaciones del sistema**: mensajes automáticos cuando un usuario se une o abandona el chat.
- 🚪 **Comando de salida**: los clientes pueden desconectarse enviando `/salir`.

## Estructura del proyecto

```
ChatSockets_JLSP/
├── src/main/java/
│   ├── com/mycompany/chatsockets_jlsp/
│   │   ├── ChatSockets_JLSP.java   # Punto de entrada del servidor
│   │   ├── Server.java             # Lógica del servidor y gestión de clientes
│   │   ├── ServerGUI.java          # Interfaz gráfica del servidor
│   │   └── ClientHandler.java      # Manejo individual de cada conexión cliente
│   └── Cliente/
│       ├── ClienteMain.java        # Punto de entrada del cliente
│       ├── Cliente.java            # Lógica de conexión y comunicación
│       └── ClienteGUI.java         # Interfaz gráfica del cliente
└── pom.xml                         # Configuración del proyecto (Maven)
```

## Tecnologías utilizadas

- **Java** (Sockets, Threads, Swing)
- **Maven** para la gestión del proyecto y las dependencias

## Cómo ejecutar

1. Clona el repositorio y ábrelo en tu IDE favorito (NetBeans, IntelliJ, Eclipse, etc.) o compílalo con Maven.
2. Ejecuta primero el servidor (`ChatSockets_JLSP.java`) para iniciar la escucha en el puerto 5000.
3. Ejecuta el cliente (`ClienteMain.java`) tantas veces como usuarios quieras simular; se te pedirá un nombre al abrir la ventana.
4. Empieza a chatear: los mensajes se retransmitirán a todos los clientes conectados en tiempo real.

## Posibles mejoras futuras

- Cifrado de la comunicación (SSL/TLS).
- Persistencia de historial de mensajes.
- Mensajes privados entre usuarios.
- Lista de usuarios conectados visible en el cliente.
