# System Monitorowania Temperatury w Architekturze Mikroserwisowej


![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![C++](https://img.shields.io/badge/C%2B%2B-00599C?style=for-the-badge&logo=c%2B%2B&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000f?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Arduino](https://img.shields.io/badge/Arduino_IDE-00979D?style=for-the-badge&logo=arduino&logoColor=white)
![LaTeX](	https://img.shields.io/badge/LaTeX-47A141?style=for-the-badge&logo=LaTeX&logoColor=white)


---

## Opis projektu

Projekt przedstawia kompletny system monitorowania temperatury oparty na architekturze mikroserwisowej. Rozwiązanie integruje urządzenia IoT z aplikacjami webowymi i mobilnymi, umożliwiając zbieranie, archiwizację oraz wizualizację danych pomiarowych w czasie rzeczywistym.

System składa się z:

* mikrokontrolera ESP32,
* czujnika temperatury DHT11,
* serwera backendowego Spring Boot,
* bazy danych MySQL,
* aplikacji mobilnej Android,
* interfejsu webowego.

Dane z czujnika są przesyłane do serwera poprzez REST API, zapisywane w bazie danych, a następnie udostępniane użytkownikowi poprzez aplikację mobilną i stronę internetową.

---

# Architektura systemu

System został zaprojektowany zgodnie z podejściem mikroserwisowym.

## Główne moduły:

### Warstwa IoT

* ESP32 Wroom
* Czujnik temperatury DHT11
* Komunikacja HTTP/JSON

### Warstwa serwerowa

* Java + Spring Boot
* REST API
* Obsługa zapisu danych
* Integracja z MySQL

### Warstwa danych

* MySQL
* Docker

### Warstwa kliencka

* Aplikacja Android
* Frontend webowy HTML/CSS/JS

---

# Technologie

| Obszar         | Technologie           |
| -------------- | --------------------- |
| Backend        | Java, Spring Boot     |
| Frontend       | HTML, CSS, JavaScript |
| Mobile         | Android Studio, Java  |
| IoT            | ESP32, Arduino, C++   |
| Baza danych    | MySQL                 |
| Konteneryzacja | Docker                |
| Komunikacja    | REST API, JSON        |

---

# Schemat działania systemu

1. ESP32 odczytuje temperaturę z czujnika DHT11.
2. Dane są przesyłane do serwera metodą HTTP POST w formacie JSON.
3. Serwer zapisuje dane do bazy MySQL wraz z datą i godziną.
4. Aplikacje klienckie pobierają dane z REST API.
5. Interfejs webowy i mobilny aktualizują dane automatycznie co 10 sekund.

---

# Struktura projektu

```bash
project/
│
├── backend/              # Serwer Spring Boot
├── mobile-app/           # Aplikacja Android
├── web-app/              # Frontend webowy
├── esp32/                # Kod mikrokontrolera
├── docker/               # Konfiguracja Dockera
└── README.md
```

---

# Instalacja i uruchomienie

## 1. Wymagania

### Sprzęt

* ESP32 Wroom
* Czujnik DHT11
* Smartfon Android 8+
* Komputer z minimum 8 GB RAM

### Oprogramowanie

* Docker Desktop
* Java JDK 24
* Arduino IDE
* Android Studio
* Przeglądarka internetowa

---

# Uruchomienie backendu i bazy danych

Przejdź do katalogu "container things" i uruchom go w terminalu.

Uruchom kontenery:

```bash
docker-compose up --build
```

Po poprawnym uruchomieniu:

* MySQL będzie dostępny lokalnie,
* Spring Boot uruchomi REST API.

---

# Konfiguracja ESP32

1. Otwórz plik temp123.ino za pomocą Arduino IDE.
2. Wgraj kod na ESP32.

---

# Uruchomienie interfejsu webowego

W dowolnej nowoczesnej przeglądarce przejdź do adresu http://localhost:8080/

Interfejs umożliwia:

* podgląd aktualnych danych,
* przegląd historii pomiarów,
* automatyczne odświeżanie.

---

# Funkcjonalności

## Zrealizowane

* komunikacja ESP32 ↔ serwer,
* REST API,
* zapis danych do MySQL,
* aplikacja mobilna Android,
* interfejs webowy,
* automatyczne odświeżanie danych,
* obsługa JSON,
* architektura mikroserwisowa,
* konteneryzacja Docker.

---

# Testy

Przeprowadzone testy potwierdziły:

* poprawność komunikacji sieciowej,
* stabilność działania systemu,
* poprawny zapis danych,
* automatyczne odświeżanie,
* średni czas odpowiedzi API ≈ 50 ms.

---

# Możliwe rozszerzenia

Planowane kierunki rozwoju:

* powiadomienia alarmowe,
* dodatkowe czujniki,
* wdrożenie chmurowe,
* autoryzacja użytkowników,
* wykresy i analityka danych,
* obsługa MQTT.

---

# Autorzy

* Franciszek Dębski — IoT / PCB
* Patryk Wilk — Backend / Web
* Krystian Zając — Android / Dokumentacja

---

# Licencja

Projekt edukacyjny realizowany w ramach studiów inżynierskich.

Małopolska Uczelnia Państwowa im. rotmistrza Witolda Pileckiego w Oświęcimiu
Kierunek: Informatyka
Rok: 2026

---

# Dokumentacja

Pełna dokumentacja projektu została przygotowana w systemie LaTeX i zawiera:

* analizę wymagań,
* architekturę systemu,
* diagramy UML,
* schemat PCB,
* instrukcję instalacji,
* testy i weryfikację systemu.
