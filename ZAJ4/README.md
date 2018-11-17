# LAB4
3. **[2 pkt]** Napisz program składający się z następujących klas
    1.  Klasa **Cryptographer**, zawierająca niestepujące pola i metody:
        * statyczna metoda **_cryptfile_**, przyjmująca jako parametry plik do zaszyfrowania, plik w którym powinien zostać zapisany zaszyfrowany tekst oraz referencje do typu **Algorithm**.
        * statyczna metoda **_decryptfile_** działająca odwrotnie do **_cryptfile_**
    1. Interfejs **Algorithm** posiadający następujące metody:
        * metoda **_crypt_** szyfrująca pojedyncze słowo
        * metoda **_decrypt_** deszyfrująca pojedyncze słowo
    1. Klasa **ROT11** implementująca interfejs **Algorithm**
        * Klasa powinna posiadać statyczną, stałą zmienną określającą alfabet używany w algorytmie, oraz przesunięcie - rotację (w tym wypadku 11) ROT13 (dla ułatwienia zakładamy, że szyfrujemy tylko teksty składające się z liter a-z. Dopuszczalne są duże i małe litery. Spacji i znaków końca linii nie szyfrujemy: w pliku wejściowym jak i wyjściowym pozostają bez zmian).
    1. Klasa **Polibiusz** implementująca interfejs **Algorithm**
        * Klasa powinna realizować algorytm szyfrujący Polibiusza. Nie szyfrujemy spacji; algorytm nie powinien być case sensitive
    1. W funkcji **_main_** umieść kod który pobierze z parametrów programu ścieżki do pliku wejściowego i wyjściowego, zada pytanie użytkownikowi czy chce szyfrować, czy deszyfrować, określi jakim algorytmem i wykona szyfrowanie/deszyfrowanie pliku wejściowego i wynik umieści w pliku wyjściowym.
