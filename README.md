# Matheo PvP Client

Matheo PvP Client is an open source Minecraft PvP Client for **1.21.11** using [Fabric](https://fabricmc.net/).

## Downloading

- Download the mod from the **Releases** section here
- Or build from source (see [Building](#building)) and copy the JAR from `1.21.11/matheo-pvp-client/build/libs/` to `.minecraft/mods`

## Screenshots

### Logo

![Logo](screenshots/Logo.png)

### Menu (Right Shift)

![Menu](screenshots/Menu.png)

### HUD

![HUD](screenshots/HUD.png)

## Workspace Setup

1. Clone or download the repository (git or zip).
2. Open the folder **`1.21.11/matheo-pvp-client`** and copy its path.
3. Open a command prompt or terminal and change directory to that path:

   ```
   cd path/to/Matheo-lager-pvp-client/1.21.11/matheo-pvp-client
   ```

4. Create the workspace for your IDE:
   - **IntelliJ IDEA:** Open the `1.21.11/matheo-pvp-client` folder as a Gradle project (Fabric Loom).
   - **VS Code:** Open the folder and use the Gradle extension.
5. Run the client in development:

   ```
   .\gradlew.bat runClient
   ```
   (Linux/Mac: `./gradlew runClient`)

## Building

1. Open the folder **`1.21.11/matheo-pvp-client`** and copy its path.
2. Open a command prompt or terminal and change directory to that path:

   ```
   cd path/to/Matheo-lager-pvp-client/1.21.11/matheo-pvp-client
   ```

3. Make a build:

   ```
   .\gradlew.bat build
   ```
   (Linux/Mac: `./gradlew build`)

4. You will find the JAR in:

   ```
   1.21.11/matheo-pvp-client/build/libs/matheo-pvp-client-1.0.0.jar
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
Repository structure inspired by [Cloud Client](https://github.com/cloudclientdev/cloudclient).
