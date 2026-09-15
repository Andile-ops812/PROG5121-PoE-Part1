# PROG5121 PoE — Part 1: Registration and Login Feature

A Java (Maven) application implementing the registration and login logic
for the Part 1 requirements of the Portfolio of Evidence, with JUnit 5
unit tests and GitHub Actions CI so the tests run automatically on every
push.

## Project structure

```
PoE_Part1/
├── pom.xml
├── README.md
├── .github/workflows/maven.yml     # runs `mvn test` on every push
└── src/
    ├── main/java/com/prog5121/poe/
    │   ├── Login.java              # registration + login logic
    │   └── Main.java               # console entry point (input/output)
    └── test/java/com/prog5121/poe/
        └── LoginTest.java          # JUnit 5 tests, using the exact
                                     # test data from the brief
```

## What each `Login` method does

| Method | Returns | Purpose |
|---|---|---|
| `checkUserName()` | `boolean` | Underscore present, ≤ 5 characters |
| `checkPasswordComplexity()` | `boolean` | ≥ 8 chars, capital, number, special char |
| `checkCellPhoneNumber()` | `boolean` | `+27` country code + ≤ 10-digit number |
| `registerUser()` | `String` | Registration outcome message |
| `loginUser(username, password)` | `boolean` | Checks credentials against the stored user |
| `returnLoginStatus(boolean)` | `String` | Welcome message or failure message |

## Running locally in NetBeans

1. Open NetBeans → **File → Open Project** → select the `PoE_Part1` folder
   (NetBeans recognises the `pom.xml` and imports it as a Maven project
   automatically).
2. Right-click the project → **Test** to run all JUnit tests, or
   right-click `LoginTest.java` → **Test File**.
3. Right-click `Main.java` → **Run File** to run the console app and try
   registering/logging in interactively.

## Running from the command line

```bash
mvn test      # compiles and runs all JUnit tests
mvn compile exec:java -Dexec.mainClass="com.prog5121.poe.Main"   # run the app
```

## Getting this onto GitHub (with ≥ 6 commits, as required)

1. Create a **new empty repository** on GitHub (no README/license, so it
   doesn't conflict with this project).
2. In GitHub Desktop: **File → Add Local Repository** → select this
   `PoE_Part1` folder → publish it to the repository you created.
3. Make your six-plus commits as you actually build the feature, so the
   history shows real progress rather than one dump, e.g.:
   1. `Initial commit: Maven project structure and pom.xml`
   2. `Add Login class with checkUserName()`
   3. `Add checkPasswordComplexity() and checkCellPhoneNumber()`
   4. `Add registerUser() registration logic`
   5. `Add loginUser() and returnLoginStatus()`
   6. `Add JUnit 5 tests for all Login methods`
   7. `Add Main.java console entry point`
   8. `Add GitHub Actions workflow for automated testing`
4. Push each commit (GitHub Desktop → **Push origin**). Check the
   **Actions** tab on GitHub — the workflow in this repo runs `mvn test`
   automatically on every push, satisfying the "automate your tests"
   requirement.
5. Submit the repository link on Arc, as instructed.

## Video presentation

Record an unlisted YouTube video (or similar) with your own voice-over
that:
- Shows the code and explains what each `Login` method does.
- Runs the application (`Main.java`) and demonstrates registration and
  login, including at least one success and one failure case.
- Runs the JUnit tests and shows them passing.
- Briefly explains the logic/flow (why registration checks run in the
  order they do, how login checks against stored details, etc.).

## Reference

The general structure of the cell-phone regular expression (an escaped
`+` combined with a bounded digit quantifier) was adapted from:

Baeldung (2023) *Validate Phone Numbers with Regex in Java*. Available
at: https://www.baeldung.com/java-regex-validate-phone-numbers
(Accessed: 15 September 2026).

This is also cited as a comment directly above `CELLPHONE_PATTERN` in
`Login.java`.
