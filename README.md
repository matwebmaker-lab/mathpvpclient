# Matheo PvP Client

Matheo PvP Client is an open source Minecraft PvP Client for **1.21.11** using [Fabric](https://fabricmc.net/).  
README-struktur og stil er inspirert av [Cloud Client](https://github.com/cloudclientdev/cloudclient).

## Downloading

- Download the mod from the **Releases** section here
- Or build from source (see [Building](#building)) and copy the JAR from `build/libs/` to `.minecraft/mods`

## Screenshots

### Logo

![Logo](screenshots/Logo.png)

### Menu (Right Shift)

![Menu](screenshots/Menu.png)

### HUD

![HUD](screenshots/HUD.png)

## Workspace Setup

1. Clone or download the repository (git or zip).
2. Open the project folder in a terminal and go to that directory:

   ```
   cd path/to/Matheo-lager-pvp-client
   ```

3. Open the folder in **IntelliJ IDEA** or **VS Code** as a Gradle project (Fabric Loom).
4. Run the client in development:

   ```
   .\gradlew.bat runClient
   ```
   (Linux/Mac: `./gradlew runClient`)

## Building

1. Open the project folder and copy its path.
2. Open a command prompt or terminal and change directory to that path:

   ```
   cd path/to/Matheo-lager-pvp-client
   ```

3. Make a build:

   ```
   .\gradlew.bat build
   ```
   (Linux/Mac: `./gradlew build`)

4. You will find the JAR in:

   ```
   build/libs/matheo-pvp-client-1.0.0.jar
   ```

5. Copy the JAR into your `.minecraft/mods` folder and launch Minecraft with Fabric 1.21.11.

## Features

- **Auto sprint** – Sprint automatically when holding W (toggle in menu)
- **Settings menu (Right Shift)** – Lunar-style menu with logo, Visuals and HUD categories
- **Fullbright** – Increased gamma
- **TNT timer** – Countdown for nearby TNT on HUD
- **Reach hitbox** – Glow on players within melee range
- **HUD** – FPS, coordinates, armor status

## Requirements

- Java 21+
- Minecraft 1.21.11
- [Fabric Loader](https://fabricmc.net/use/) and [Fabric API](https://modrinth.com/mod/fabric-api)

## Contributions

Feel free to fork this project, make changes and submit a pull request.

## License

This project is licensed under the **MIT License**.

Permissions:

- Modification
- Distribution
- Private use

Conditions:

- License and copyright notice must be included

See [LICENSE](LICENSE) for full text.

## About

An open source Minecraft PvP Client for 1.21.11 (Fabric).
