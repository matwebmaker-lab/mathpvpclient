# Matheo PvP Client

En Fabric PvP-klient for Minecraft 1.20.1 med **auto sprint**.

## Funksjoner

- **Auto sprint** – Du sprinter automatisk når du trykker W (fremover) og kan sprinte (på bakken, ikke i vann, ikke mens du bruker gjenstand).
- **Høyre Shift** – Åpner innstillingsmenyen (fullbright og andre innstillinger).
- **Fullbright** – Slå på/av i menyen for å se i mørke områder som om det var full belysning.
- **TNT-timer** – Viser nedtelling (i sekunder) for TNT i nærheten (64 blokker) på HUD. Slå på/av i menyen.
- **Rød hitbox ved slåavstand** – Tegner rød hitbox rundt andre spillere når du er nær nok til å slå (ca. 3 blokker). Slå på/av i menyen.

## Krav

- Java 21+
- Minecraft 1.21.11
- [Fabric Loader](https://fabricmc.net/use/) og [Fabric API](https://modrinth.com/mod/fabric-api)

## Bygging

1. **Gradle Wrapper:** Hvis `gradlew.bat build` feiler med «no main manifest attribute», generer wrapper på nytt (krever [Gradle](https://gradle.org/install/) installert):
   ```bash
   gradle wrapper --gradle-version 8.4
   ```
2. Bygg moden:
   ```bash
   .\gradlew.bat build
   ```
   (På Linux/Mac: `./gradlew build`)

JAR-filen ligger i `build/libs/` etter bygg.

## Installasjon

1. Installer [Fabric](https://fabricmc.net/use/) for Minecraft 1.21.11.
2. Last ned [Fabric API](https://modrinth.com/mod/fabric-api) og legg den i `.minecraft/mods`.
3. Kopier `matheo-pvp-client-1.0.0.jar` fra `build/libs/` til `.minecraft/mods`.

## Sende til GitHub (matwebmaker-lab)

1. Opprett et nytt repository under [github.com/matwebmaker-lab](https://github.com/matwebmaker-lab) (f.eks. `matheo-pvp-client`).
2. I prosjektmappen:
   ```bash
   git add .
   git commit -m "Matheo PvP Client – Fabric 1.21.11"
   git branch -M main
   git remote add origin https://github.com/matwebmaker-lab/matheo-pvp-client.git
   git push -u origin main
   ```
   (Bytt `matheo-pvp-client` i URL til det faktiske repo-navnet.)

## Lisens

MIT
