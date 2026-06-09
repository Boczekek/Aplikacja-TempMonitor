# 🌡️ TempMonitor - Aplikacja mobilna do monitorowania temperatury

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Retrofit](https://img.shields.io/badge/Retrofit-2.9.0-3F51B5?style=for-the-badge)](https://square.github.io/retrofit/)
[![Material Design](https://img.shields.io/badge/Material_Design-757575?style=for-the-badge&logo=material-design)](https://m3.material.io/)

---

## 📌 **O projekcie**
**TempMonitor** to natywna aplikacja mobilna na system **Android**, zaprojektowana do monitorowania parametrów środowiskowych (głównie temperatury) w czasie rzeczywistym. Aplikacja pobiera dane z zewnętrznego serwera za pomocą **REST API** i wyświetla je w czytelnej, responsywnej formie. Rozwiązanie oferuje **personalizację interfejsu** oraz **elastyczną konfigurację połączenia sieciowego**, co czyni ją uniwersalnym narzędziem do współpracy z dowolnym systemem pomiarowym.

---

## ✨ **Funkcjonalności**
✅ **Monitorowanie w czasie rzeczywistym** – Aktualna temperatura wyświetlana na karcie w górnej części ekranu.
✅ **Historia pomiarów** – Przewijalna lista z datą, godziną i wartościami, posortowana chronologicznie (najnowsze na górze).
✅ **Konfiguracja IP serwera** – Możliwość ręcznego wpisania adresu IP serwera (domyślnie `10.0.2.2` dla emulatora).
✅ **Regulacja czasu odświeżania** – Ustawienie interwału pobierania danych (domyślnie co 10 sekund).
✅ **Motywy graficzne** – Wybór między trybem **jasnym**, **ciemnym** lub **systemowym** (dostosowującym się do ustawień Androida).
✅ **Obsługa błędów** – Interaktywny **Snackbar** z przyciskiem "PONÓW" w przypadku braku połączenia.

---

## 📋 **Wymagania**
| **Typ**          | **Wymaganie**                     |
|------------------|-----------------------------------|
| **System**       | Android 8.0 (API 26) lub nowszy   |
| **Pamięć**       | 10 MB wolnej przestrzeni          |
| **Sieć**         | Aktywne połączenie Wi-Fi         |
| **Serwer**       | Dostępny REST API na porcie 8080 |

---

## 🚀 **Instalacja**
1. **Pobierz plik APK**:
   - Plik `TempMonitor.apk` znajduje się w folderze projektu.
   - Alternatywnie, skompiluj projekt w **Android Studio** (kliknij *Build > Build Bundle(s) / APK(s) > Build APK*).

2. **Zainstaluj na urządzeniu**:
   - Przenies plik `.apk` na telefon (np. przez USB, e-mail lub chmurę).
   - Zezwól na instalację z **nieznanych źródeł** (Ustawienia > Bezpieczeństwo > Źródła nieznane).
   - Uruchom instalator i postępuj zgodnie z instrukcjami.

---

## ⚙️ **Konfiguracja**
Aby aplikacja działała poprawnie, należy skonfigurować **adres IP serwera** oraz **czas odświeżania**:

1. **Otwórz ustawienia**:
   - Kliknij ikonę **⚙️** (koło zębate) w prawym górnym rogu ekranu głównego.

2. **Wpisz adres IP serwera**:
   - Dla **emulatora** użyj: `10.0.2.2`
   - Dla **fizycznego telefonu** w tej samej sieci Wi-Fi: wpisz IP komputera (np. `192.168.1.15`).
   - *Uwaga*: Upewnij się, że serwer działa na porcie **8080**.

3. **Ustaw czas odświeżania**:
   - Wpisz liczbę sekund (np. `5` lub `30`).
   - *Minimalna wartość*: 1 sekunda.

4. **Wybierz motyw**:
   - **Systemowy** – Dostosowuje się do ustawień Androida.
   - **Jasny** – Stały jasny interfejs.
   - **Ciemny** – Stały ciemny interfejs.

5. **Zapisz zmiany**:
   - Kliknij przycisk **"Zapisz i wróć"**.

---
## 🔧 **Technologie**
| **Komponent**       | **Technologia**                     |
|---------------------|-------------------------------------|
| **Język**           | Java                                |
| **Sieć**            | Retrofit 2 + GSON                   |
| **UI**              | Material Design, RecyclerView       |
| **Przechowywanie**   | SharedPreferences                   |
| **Architektura**    | Modułowa (MVP-like)                 |

---
## 💡 **Uwagi**
- Aplikacja **nie działa bez aktywnego serwera** – upewnij się, że serwer REST API jest uruchomiony i dostępny pod wpisanym adresem IP.
- W przypadku **błędów połączenia**, sprawdź:
  - Czy telefon i serwer są w **tej samej sieci Wi-Fi**.
  - Czy **zapora systemowa** nie blokuje portu `8080` na komputerze.
  - Czy adres IP w ustawieniach jest **poprawny** (np. `10.0.2.2` dla emulatora).
