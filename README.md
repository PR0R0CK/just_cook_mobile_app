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

<!--![phone](app/src/main/res/readme/db_added.png)-->

### Logowanie użytkownika
####    Żeby się zalogować, należy uzupełnić pola wpisując: e-mail oraz hasło.
####    Gdy użytkownik zostanie odnaleziony w bazie, a dane logowania będą się zgadzać, ten zostanie przeniesiony do ekranu głównego aplikacji.
####    W przypadku nie odnalezienia użytkownika o podanym e-mailu oraz haśle, użytkownik zostanie o tym poinformowany.

![phone](app/src/main/res/readme/demo/logging_in.gif)

### Logowanie automatyczne
####    Logowanie użytkownika odbywa się automatycznie przy każdym starcie aplikacji, pod warunkiem, że ten nie użył opcji wylogowania.

![phone](app/src/main/res/readme/demo/auto_login.gif)

### Wylogowanie użytkownika
####    Wylogowanie użytkownika odbywa się poprzez wybranie z menu nawigacyjnego opcji "Logout".
####    Użycie tej opcji spowoduje, że użytkownik nie zostanie automatycznie zalogowany przy następnym starcie aplikacji.

![phone](app/src/main/res/readme/demo/logout.gif)

### Wyszukiwanie przepisów
####    Aby wyszukać interesujące nas przepisy, należy wybrać z menu nawigacyjnego interesującą nas kategorię.
####    Alternatywą jest wyszukiwanie przepisów po nazwie, które odbywa się po kliknięciu ikony lupy, wpisaniu poszukiwanej frazy oraz wyszukaniu przez kliknięcie lupy przy polu do wprowadzania.

![phone](app/src/main/res/readme/demo/search.gif)

### Wyświetlanie / ocenianie / komentowanie przepisu
####    Żeby wyświetlić przepis, należy kliknąć na blok zawierający ten, który nas interesuje.
####    Ocenianie odbywa się poprzez nasiścięcie gwiazdki. Akcja ta doda 1 do całkowicej oceny przepisu.
####    Żeby skomentować przepis, należy nacisnąć pasek "Show comments", by wyświetlić komentarze, następnie należy napisać treść komentarza w przeznaczonym do tego polu, a następnie nacisnąć przycisk odpowiedzialny za wysłanie komentarza.
####    Chcąc wrócić do menu głównego aplikacji należy nacisnąć przycisk cofania w androidzie.

![phone](app/src/main/res/readme/demo/recipe_details_rate_comment.gif)

### Dodanie nowego przepisu
####    By dodać nowy przepis, należy z menu nawigacyjnego wybrać opcję "Add recipe", która przeniesie nas do ekranu dodawania nowego przepisu.
####    Należy w tym miejscu uzupełnić informacje takie jak: nazwa przepisu, poziom trudności, typ przepisu, składniki, oraz kroki wykonywania przepisu.
####    Po upewnieniu się, że informacje się zgadzają, należy nacisnąć przycisk "ADD RECIPE".
####    Naciśnięcie przycisku doda przepis do bazy, oraz wróci użytkownika do ekranu głównego.

![phone](app/src/main/res/readme/demo/add_recipe.gif)

### Wyszukanie autorskich przepisów
####    By wyszukać autorskie przepisy, należy wybrać z menu nawigacyjnego opcję "My Recipes".

![phone](app/src/main/res/readme/demo/my_recipes.gif)

### Edytowanie przepisu
####    W aplikacji opcja ta jest jedynie dostępna dla autora przepisu.
####    By edytować przepis, należy wybrać stworzony przez nas przepis z ekranu menu głównego.
####    Po wyświetleniu przepisu, dostępna będzie opcja "EDIT RECIPE", która przeniesie użytkownika do ekranu edycji przepisu.
####    Edycja przepisu wygląda analogicznie do dodawania nowego przepisu.
####    Po zakończeniu edytowania, należy wybrać przycisk "EDIT RECIPE", który zaktualizuje dane w bazie, oraz wróci użytkownika do podglądu starej wersji przepisu.
####    Chcąc wyświetlić nową wersję przepisu należy odświeżyć listę przepisów i następnie wybrać edytowany przez nas przepis.

![phone](app/src/main/res/readme/demo/edit_recipe.gif)

### Usunięcie przepisu
####    W aplikacji opcja ta jest jedynie dostępna dla autora przepisu.
####    By usunąć przepis, należy wybrać stworzony przez nas przepis z menu głównego.
####    Po wyświetleniu przepisu, dostępna będzie opcja "DELETE RECIPE", która wyświetli menu dialogowe, oczekujące potwierdzenia decycji.
####    Po usunięciu przepisu, użytkownik zostanie przeniesiony do menu głównego.

![phone](app/src/main/res/readme/demo/delete_recipe.gif)

