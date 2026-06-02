### Hexlet tests and linter status:

[![Actions Status](https://github.com/2DimBer3/qa-auto-engineer-java-project-385/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/2DimBer3/qa-auto-engineer-java-project-385/actions)

### GitHub Actions for App

[![Java CI with Gradle](https://github.com/2DimBer3/qa-auto-engineer-java-project-385/actions/workflows/gradle.yml/badge.svg)](https://github.com/2DimBer3/qa-auto-engineer-java-project-385/actions/workflows/gradle.yml)

### Sonar badges

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=bugs)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=2DimBer3_qa-auto-engineer-java-project-385&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=2DimBer3_qa-auto-engineer-java-project-385)

### qa-auto-engineer-java-project-385

Проект автоматизированного тестирования для веб-приложения **«Task manager»**. Написан на Java с использованием паттерна
**Page Object Model (POM)** для создания читаемых и поддерживаемых UI-тестов.

### Основные возможности

- **UI-тесты** основных сущностей приложения: задачи (Tasks), статусы (Statuses), метки (Labels), пользователи (Users)
  и навигация.
- **Page Object Model** — все страницы вынесены в отдельный пакет `page_object`, что упрощает поддержку тестов при
  изменениях интерфейса.
- **Docker-контейнер** с тестируемым приложением для изолированного запуска.
- **Готовые Makefile-команды** для быстрого старта и запуска тестов.

### Настройка окружения

Для работы с проектом потребуются **Docker** и **Java 21** (или новее).

1. **Установите Docker**, если он ещё не установлен:
    ```bash
    # Например, для Ubuntu:
    sudo apt update && sudo apt install docker.io
    # Или скачайте с официального сайта: https://www.docker.com/products/docker-desktop/
    ```
2. Убедитесь, что установлена Java 21:
    ```bash
    java --version
    ```
3. Убедитесь, что Gradle установлен:
    ```bash
    # Проверить версию Gradle (если установлен глобально)
    gradle --version
    ```

### Запуск тестов

1. Запустите тестируемое приложение в Docker:
    ```bash
    make start-docker-app
    ```
2. Выполните тесты:
    ```bash
    make test
    ```
   Эта команда запустит все тесты с использованием gradlew clean test. Результаты будут выведены в консоль.