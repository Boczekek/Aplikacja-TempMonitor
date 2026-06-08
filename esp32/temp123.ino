#include <HTTPClient.h>
#include <WiFi.h>
#include "DHTesp.h"
#define DHTpin 15
DHTesp dht;

#define WIFI_NETWORK "WadowiceNet_i56p_5G"
#define WIFI_PASSWORD "s8ym4jce"
#define WIFI_TIMEOUT_MS 20000

// Definicja strefy czasowej dla Polski (obsługuje czas letni i zimowy)
//const char* TZ_INFO = "CET-1CEST,M3.5.0,M10.5.0/3";

void connectToWiFi(){
  Serial.print("Connecting to WiFi");
  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_NETWORK, WIFI_PASSWORD);

  unsigned long startAttemptTime = millis();

  while(WiFi.status() != WL_CONNECTED && millis() - startAttemptTime < WIFI_TIMEOUT_MS){
    Serial.print(".");
    delay(100);
  }

  if(WiFi.status() != WL_CONNECTED){
    Serial.println(" Failed!");
    // take action
  }else{
    Serial.print("Connected!");
    Serial.println(WiFi.localIP());
  }

  // Konfiguracja czasu z uwzględnieniem strefy dla Polski
  //configTzTime(TZ_INFO, "pool.ntp.org", "time.nist.gov");
}

void setup() {
dht.setup(DHTpin, DHTesp::DHT11);
Serial.begin(9600);
  connectToWiFi();
}

void wyslijDane(float temp) {
    HTTPClient http;
    // Zmień IP na IP swojego serwera (komputera w tej samej sieci)
    http.begin("http://192.168.100.16:8080/api/pomiary/arduino");
    http.addHeader("Content-Type", "application/json");

    // Tworzenie JSONa
    String httpRequestData = "{\"temperatura\":" + String(temp) + "}";

    // Wysłanie żądania POST
    int httpResponseCode = http.POST(httpRequestData);

    if (httpResponseCode > 0) {
        Serial.print("Sukces, kod: ");
        Serial.println(httpResponseCode);
    } else {
        Serial.print("Błąd wysyłania: ");
        Serial.println(httpResponseCode);
    }
    http.end();
}
unsigned long poprzedniCzas = 0;
const long interwal = 60000; // 60 000 ms = 1 minuta

void loop() {
  unsigned long obecnyCzas = millis();
  if (obecnyCzas - poprzedniCzas >= interwal) {
    poprzedniCzas = obecnyCzas;

    float temperature = dht.getTemperature();

    // Sprawdź czy odczyt jest poprawny (nie jest NaN)
    if (isnan(temperature)) {
      Serial.println("Błąd odczytu z czujnika DHT!");
      return;
    }

    // Wyślij dane tylko jeśli WiFi jest połączone
    if (WiFi.status() == WL_CONNECTED) {
      wyslijDane(temperature);
    } else {
      Serial.println("Błąd: Brak połączenia z WiFi!");
      // Opcjonalnie spróbuj połączyć się ponownie
    }
  }
}