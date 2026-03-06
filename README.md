# Matheo PvP Client

En Fabric PvP-klient for Minecraft **1.21.11** med auto sprint, Lunar-inspirert meny og nyttige HUD-funksjoner.

## Funksjoner

### Bevegelse
- **Auto sprint** – Sprinter automatisk når du holder W (fremover), på bakken, uten sneak/vann/gjenstand. Kan slås av/på i menyen.

### Meny (Høyre Shift)
- **Lunar-inspirert design** – Mørk sidebar, kategorier (Visuals / HUD), Math PvP Client-logo øverst.
- **Visuals:** Auto sprint, Fullbright, TNT-timer, Reach hitbox (glow).
- **HUD:** FPS-visning, Koordinater (XYZ), Rustningsstatus.
- Alle valg kan slås på/av med knapper i menyen.

### Visuelt
- **Fullbright** – Økt gamma så du ser tydelig i mørke områder.
- **TNT-timer** – Viser nedtelling (sekunder) for TNT innen 64 blokker på HUD.
- **Reach hitbox** – Spillere innenfor slåavstand (~3 blokker) får glow/outline.

### HUD
- **FPS** – Viser FPS øverst til venstre.
- **Koordinater** – Viser XYZ (blokkposisjon).
- **Rustning** – Toggle for rustningsstatus i HUD.

## Krav

- **Java 21+**
- **Minecraft 1.21.11**
- [Fabric Loader](https://fabricmc.net/use/) og [Fabric API](https://modrinth.com/mod/fabric-api)

## Bygging

I prosjektmappen:

```bash
.\gradlew.bat build
```

(På Linux/Mac: `./gradlew build`)

JAR-filen ligger i **`build/libs/`** (f.eks. `matheo-pvp-client-1.0.0.jar`).

## Installasjon

1. Installer [Fabric](https://fabricmc.net/use/) for Minecraft 1.21.11.
2. Last ned [Fabric API](https://modrinth.com/mod/fabric-api) og legg den i `.minecraft/mods`.
3. Kopier `matheo-pvp-client-1.0.0.jar` fra `build/libs/` til `.minecraft/mods`.
4. Start spillet og åpne innstillingene med **Høyre Shift**.

## Sende til GitHub

1. Opprett et repository under [github.com/matwebmaker-lab](https://github.com/matwebmaker-lab) (f.eks. `matheo-pvp-client`).
2. I prosjektmappen:

   ```bash
   git add .
   git commit -m "Matheo PvP Client – Fabric 1.21.11"
   git branch -M main
   git remote add origin https://github.com/matwebmaker-lab/matheo-pvp-client.git
   git push -u origin main
   ```

   Bytt repo-URL til det du faktisk bruker.

## Lisens

MIT
