# Java GUI Starter (Swing)

This repo is a **simple Java Swing GUI application** you can run outside the terminal (it opens a desktop window).

## Requirements

- **JDK 17+** installed (JDK 21 recommended)
- Windows: `java` and `javac` available in PowerShell (`java -version` should work)

## Run (Windows PowerShell)

From the repo root:

```powershell
mkdir out -Force | Out-Null
javac -d out .\src\Main.java
java -cp out Main
```

## Run (Linux/macOS)

```bash
mkdir -p out
javac -d out ./src/Main.java
java -cp out Main
```

## What you get

- A window with:
  - input field + **Add** button (press Enter to add too)
  - scrollable list of timestamped entries
  - **Clear** button + menu items

## Next ideas (easy upgrades)

- Save/load entries to a file (JSON or TXT)
- Add a second screen (tabs or dialogs)
- Package into a runnable JAR

## License

MIT (add one if you want)

