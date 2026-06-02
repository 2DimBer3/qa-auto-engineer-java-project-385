### Hexlet tests and linter status:

[![Actions Status](https://github.com/2DimBer3/qa-auto-engineer-java-project-385/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/2DimBer3/qa-auto-engineer-java-project-385/actions)

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