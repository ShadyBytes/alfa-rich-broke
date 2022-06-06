# alfa-rich-broke
 A service to interact with a currency API to determine if given currency is higher or lower to USD compared to yesterday

The base currency which will be compared to USD is set in application.properties. Important note: it also could be passed as a URL parameter without a problem in future releases. My desicion of loading it from application.properties is based on interpreting a subtask "Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки" and the fact that you cannot change a 'base' currency in openexchange's free plan (default is USD)

To run a built JAR:
java -jar alfa-0.0.1-SNAPSHOT.jar

To build from source on Windows:
.\gradlew.bat bootJar
