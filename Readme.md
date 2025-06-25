<!--suppress HtmlDeprecatedAttribute -->
<p align="center"><img src="https://i.imgur.com/A6bWGFl.gif" alt=""/></p>


<!--suppress HtmlDeprecatedAttribute -->
<div align="center" style="font-size: 24px; font-weight: bold;">🛒 E-Commerce UI Automation Framework 🧪✨
</div>

---

- 🧰 This repository contains the implementation of a **Hybrid Test Automation Framework** for an 🛍️ **E-Commerce Website**.
- Powered by:
  - **Selenium** 🕷️
  - **TestNG** 🧪
  - **Extent Reports** 📊
  - **Apache POI** 📂
- Ensures:
  - ✅ Functionality
  - 🔎 Regression Coverage
  - 📉 Reliability
- Built for scalable test coverage with clean page-object structure 🧱.

---

## ✨ Key Features

### 📁 Modular & Scalable Folder Structure

- `base/`: Common test setup with `BasePage` and `BaseTest`.
- `pages/`: Page Object Model implementation (`HomePage`, `CheckoutPage`, etc.).
- `tests/`: All TestNG test classes (e.g., `RegistrationTest`).
- `utils/`: Utility classes (`ExtentReportManager`, `PropertiesUtil`, `ScreenshotUtil`, etc.).
- `resources/`: Configuration files like `log4j2.xml`, `config.properties`.

---

## 🧩 Technology Stack

- **Java 11+** ☕
- **TestNG** 🧪
- **Selenium WebDriver** 🖱️
- **Apache POI** 📊
- **Lombok** 🦾
- **ExtentReports** 📈
- **Log4j2** 📜
- **Java Faker** 🎭
- **Maven** 📦

---

## 🔑 Dependencies Used

- `junit`
- `lombok`
- `selenium-java`
- `poi`
- `poi-ooxml`
- `log4j-core`
- `log4j-api`
- `commons-io`
- `commons-lang3`
- `extentreports`
- `testng`
- `log4j-layout-template-json`
- `javafaker`

---

## 🛠️ How to Build and Run the Tests

This project uses **Maven** 📦 to manage builds and dependencies.

### ✅ Prerequisites

- JDK 8+
  ```bash
  java -version
    ```

* Maven

  ```bash
  mvn -version
  ```

### ⚙️ Maven Lifecycle Commands

| Command       | Purpose                                      |
| ------------- | -------------------------------------------- |
| `mvn clean`   | Removes `target/` files 🧹                   |
| `mvn compile` | Compiles the Java source code 🛠️            |
| `mvn test`    | Executes TestNG tests 🧪                     |
| `mvn install` | Builds and installs the JAR to local repo 📦 |

### 🧪 Run Tests with Browser Parameter
To run tests with a specific browser in testng.xml update the `browser` parameter:


✅ **Supported browsers**:

* `chrome` 🌍
* `firefox` 🦊
* `edge` 💻

📝 **Default:** Chrome

---

## 📊 Reports

🪄 **ExtentReports** is used for reporting.

* Reports are generated at:

  ```
  /reports/index.html
  ```
* Includes:

  * Test Pass/Fail Summary
  * Screenshots on failure 📸
  * Timestamped logs ⏱️

---

## 🗃️ Sample Folder Structure

```
src/
└── test/
    └── java/
        └── org/com/
            ├── base/
            ├── pages/
            ├── tests/
            └── utils/
                ├── data/
                ├── exceptions/
                └── factories/
resources/
├── config.properties
├── log4j2.xml
```

---

## 📸 Screenshots

🖼️ All failure screenshots are automatically saved to:

```
/screenshots
```

---

## 🧾 Logging

* ✅ Integrated with **Log4j2**
* Logs saved to:

  ```
  /logs/test.log
  ```

---

## 🧑‍💻 Author & Credits

**Authored by:** [Debadatta Pujhari](https://github.com/datt03) 👨‍💻
📧 For queries: [debadatta.pujhari@gmail.com](mailto:debadatta.pujhari@gmail.com)

---

## 📜 License

This project is licensed under **GPL-3.0**.
Refer to [LICENSE.md](LICENSE.md) for full details.

---

<p align="center">
<img src="https://github.com/thompsonemerson/thompsonemerson/raw/master/cover-thompson.png"  alt=""/>
</p>
```

---