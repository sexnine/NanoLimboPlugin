## NanoLimbo

This is a lightweight minecraft limbo server, written on Java with Netty.
The main goal of the project is maximum simplicity with a minimum number of sent and processed packets.
This limbo is empty, there are no ability to set schematic building since
this is not necessary. You can send useful information in chat or BossBar.

No plugins, no logs. The server is fully clear. It is able keep a lot of players while the main server is down.

The general features:
* High performance. The server does not save or cache any useless (for limbo) data.
* Doesn't spawn threads per player. Uses fixed threads pool.
* Support for **BungeeCord** and **Velocity** info forwarding.
* Support for [BungeeGuard](https://www.spigotmc.org/resources/79601/) handshake format.
* Multiple versions support.
* Fully configurable.
* Lightweight. Software size around **2MB.**

![](https://i.imgur.com/sT8p1Gz.png)

### Versions support

Symbol `X` means all minor versions.

- [x] 1.7.X
- [x] 1.8.X
- [x] 1.9.X
- [x] 1.10.X
- [x] 1.11.X
- [x] 1.12.X
- [x] 1.13.X
- [x] 1.14.X
- [x] 1.15.X
- [x] 1.16.X
- [x] 1.17.X
- [x] 1.18.X
- [x] 1.19.X

The server **doesn't** support snapshot versions.

### Commands

* `help` - Show help message
* `conn` - Display amount of connections
* `mem` - Display memory usage stats
* `stop` - Stop the server

Note, that it also will be closed correctly if you just press `Ctrl+C`.

### Installation

The installation process is simple.

1. Download the latest version of the software **[here](https://github.com/Nan1t/NanoLimbo/releases)**
2. Put the jar file in your server's main directory
3. Create a start script like you did for Bungeecord / Velocity:
   `java -jar NanoLimbo-<version>.jar`
4. The server will create the `settings.yml` file. You can configure the server here.
5. Configure it to your liking and restart the server.

### About player info forwarding

The server supports player info forwarding from the proxy. There are several types of info forwarding:

* `LEGACY` - The **BungeeCord** IP forwarding.
* `MODERN` - **Velocity** native info forwarding type.
* `BUNGEE_GUARD` - **BungeeGuard** forwarding type.

If you use BungeeCord, or Velocity with `LEGACY` forwarding, just set this type in the config.  
If you use Velocity with `MODERN` info forwarding, set this type and paste secret key from Velocity
config into `secret` field.
If you installed BungeeGuard on your proxy, then use `BUNGEE_GUARD` forwarding type.
Then add your tokens to `tokens` list.

### Contributing

You can create pull request, if you find bugs, ways for optimization, or you want to add some functional,
which is suitable for limbo server and won't significantly bloat the server.

All PR's should be targeted to the `dev` branch to keep the `main` stable and clear.

### Building

Required software:

* JDK 1.8+
* Gradle 7+ (optional)

To build minimized .jar, go to project root and write in terminal:

```
./gradlew shadowJar
```

### Contacts

If you have any question or suggestion, join to [Discord server](https://discord.gg/4VGP3Gv)
