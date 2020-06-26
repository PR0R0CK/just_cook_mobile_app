## Konfiguracja projektu
### Pobieranie SDK
#### - Uruchomić Android Studio
#### - Pobrać platformę SDK
![phone](app/src/main/res/readme/configuration/scr001.jpg)
#### - Oznaczyć werjsę Android 9.0 (Pie) i pobrać(po oznaczeniu, pojawi się opcja pobrania), a w przypadku już pobranego widok będzie jak poniżej
![phone](app/src/main/res/readme/configuration/scr002.jpg)

### Uruchomienie aplikacji
#### - Pobrać zapakowany projekt (ZIP) i rozpakować w wybranej lokalizacji
#### - Uruchomić Android Studio
#### - Wybrać wskazaną opcję
![phone](app/src/main/res/readme/configuration/scr0.jpg)
#### - Odnaleźć rozpakowany projekt
![phone](app/src/main/res/readme/configuration/scr1.jpg)
#### - Możliwe, że Android Studio zasugeruje konfigurację projektu i będzie można uruchomić aplikację od razu. W przeciwnym razie należy zrobić to samemu

![phone](app/src/main/res/readme/configuration/scr2.jpg)
#### - Dodajemy emulator
![phone](app/src/main/res/readme/configuration/scr3.jpg)
#### - Wybieramy system operacyjny Android 9.0 (Pie)
![phone](app/src/main/res/readme/configuration/scr4.jpg)
#### - Możemy nadać nazwę naszemu emulatorowi, aby łatwiej go rozpoznać
![phone](app/src/main/res/readme/configuration/scr5.jpg)
#### - Po poprawnej konfiguracji nasze urządzenie powinno być widoczne w AVD Manager
![phone](app/src/main/res/readme/configuration/scr6.jpg)
#### - Przechodzimy do uruchomienia aplikacji
![phone](app/src/main/res/readme/configuration/scr7.jpg)
#### - Aplikacja została zbudowana i jest gotowa do użycia na emulatorze
![phone](app/src/main/res/readme/configuration/scr8.jpg)


### Błąd przy pierwszym uruchomieniu związany z plikami nie posiadającymi rozszerzenia .xml
#### Taki błąd może wystąpić jednorazowo podczas pierwszej próby rozruchu aplikacji. W takim przypadku należy uruchomić widok projektu, odnaleźć folder readme i usunąć go.
![phone](app/src/main/res/readme/configuration/scr_err.jpg)

# Obsługa aplikacji

### Rejestracja uzytkownika
####   Uzupelnic kolejno pola wpisujac: nazwe uzytkownika, adres mail, haslo.
####   Należy zaakceptować regulamin aplikacji oraz nacisnąć przycisk rejestracji.
####   W tym momencia nasz uzytkownik zostal dodany do bazy danych,
####   a my możemy w pelni korzystać z usług aplikacji.

![phone](app/src/main/res/readme/rejestracja.gif)
![phone](app/src/main/res/readme/rejestracja_DB.png)

### Adding user to database
<!--![phone](app/src/main/res/readme/db_added.png)-->

### Logowanie użytkownika
####    Żeby się zalogować, należy uzupełnić pola wpisując: e-mail oraz hasło.
####    Gdy użytkownik zostanie odnaleziony w bazie, a dane logowania będą się zgadzać, ten zostanie przeniesiony do ekranu głównego aplikacji.
####    W przypadku nie odnalezienia użytkownika o podanym e-mailu oraz haśle, użytkownik zostanie o tym poinformowany.

![phone](app/src/main/res/demo/logging_in.gif)