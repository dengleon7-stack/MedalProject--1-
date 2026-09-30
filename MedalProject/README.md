# Medal Standings Project

A small Java project with three classes and their hand-rolled test suites (no JUnit — each test file has its own `main` method).

## Structure

```
MedalProject/
├── .vscode/
│   ├── launch.json      # Run configurations for Main and each test class
│   └── settings.json    # Tells the Java extension where the source lives
├── src/
│   ├── Main.java              # Swing GUI entry point
│   ├── ResultRecord.java
│   ├── ResultRecordTest.java
│   ├── ResultManager.java
│   ├── ResultManagerTest.java
│   ├── MedalStandings.java
│   └── MedalStandingsTest.java
└── README.md
```

## Classes

- **Main** — the Swing GUI: a form for entering results (athlete, event, result) plus a medal standings panel. Wires up `ResultManager` and `MedalStandings` to the UI.
- **ResultRecord** — holds a single athlete/event/result entry.
- **ResultManager** — validates and stores `ResultRecord`s against a fixed public list of allowed events (`ResultManager.VALID_EVENTS` = `100m`, `200m`, `Relay`, used directly by the UI's dropdown), and can filter results by event or list all of them via `getAllResults()`.
- **MedalStandings** — tracks gold medal counts per country and returns them sorted highest-to-lowest.

## Running the GUI

Use the **"Run Main (GUI)"** launch configuration in VS Code's Run and Debug panel, or from the command line:

```bash
cd src
javac *.java
java Main
```

A window titled "Sports Federation Manager" should open. Note: `Main` tries to load an `emblem.png` icon file which isn't included — this is harmless and just prints "Icon not found" to the console if missing.

## Opening in VS Code

1. Install the **Extension Pack for Java** (Microsoft) if you don't already have it — VS Code will prompt you when you open a `.java` file.
2. Open this folder in VS Code: `File → Open Folder...` and select `MedalProject`.
3. Wait for the Java extension to finish loading/indexing the project (bottom-right status bar).
4. Open any `*Test.java` file. You'll see a **Run | Debug** codelens link above the `main` method — click **Run**.
   - Alternatively, use the **Run and Debug** panel (`Ctrl+Shift+D` / `Cmd+Shift+D`) and pick one of the three configurations from the dropdown.

## Running from the command line instead

```bash
cd src
javac *.java
java ResultRecordTest
java ResultManagerTest
java MedalStandingsTest
```

All three suites currently pass (16 tests total: 4 + 7 + 5).
