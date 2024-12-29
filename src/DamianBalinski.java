import javax.swing.*;       // import biblioteki swing umożliwiającej stosowanie graficznego interfejsu
import java.time.LocalDate; // import klasy LocalDate do operowania datami
import java.util.ArrayList; // import do wykorzystanie struktury danych ArrayList
import java.util.HashMap;   // klasa do przechowywania par klucz-wartość
import java.util.Map;       // interfejs map definiujący mapy
import java.util.Random;    // klasa random do generowania rzekomo losowych wartości

public class DamianBalinski {
    public static void main(String[] args) {
        while (true) {
            // pętla nieskończona umożliwiająca dokonywanie wyborów oraz okno dialogowe z początkowymi opcjami do wyboru
            String[] startOptions = {"Start", "About", "Wyjście"};
            int startChoice = JOptionPane.showOptionDialog(null, // metoda klasy JOptionPane która tworzy okno dialogowe z przyciskami
                    "Witaj w ZodiakAPP!",                                       // wiadomość powitalna
                    "ZodiakAPP",                                                // tytuł okna dialogowego
                    JOptionPane.DEFAULT_OPTION,                                 // styl przycisków (domyślny)
                    JOptionPane.INFORMATION_MESSAGE,                            // typ wiadomości (ikona informacyjna)
                    null,                                                       // brak niestandardowej ikony
                    startOptions,                                               // tablica opcji
                    startOptions[0]);                                           // domyślnie zaznaczony przycisk (pierwszy)
            // okno wyświetlające informacje o programie i twórcy
            if (startChoice == 1) {
                JOptionPane.showMessageDialog(null, "ZodiakAPP © 2024\nSpołeczna Akademia Nauk\nDamian Baliński Nr.albumu: 123702\nWszelkie prawa zastrzeżone.");
                continue;
            // jeśli użytkownik wybierze "Wyjście" lub zamknie okno dialogowe, pętla zatrzymuje się i program się zamyka
            } else if (startChoice == 2 || startChoice == JOptionPane.CLOSED_OPTION) {
                break;
            }

            // Okno pobierające od użytkownika datę urodzenia, wartość wprowadzona jest przypisana zmiennej dateInput
            String dateInput = JOptionPane.showInputDialog("Podaj swoją datę urodzenia w formacie DD-MM (np. 25-12):");
            if (dateInput == null) {
                break; // Wyjście z programu, jeśli użytkownik anuluje
            }

            String[] dateParts = dateInput.split("-"); // rozdziela wprowadzone dane, dla 25-12 tworzy tablice ["25","12"]
            if (dateParts.length != 2) {       // jeśli dane są wprowadzone w złym formacie 2512 lub 25/12 program prosi o ponowne wpisanie daty
                JOptionPane.showMessageDialog(null, "Nieprawidłowy format daty. Spróbuj ponownie.");
                continue;
            }

            int day, month; // definiuje zmienne do przechowywania dnia i miesiąca
            try {           // konwertuje elementy tablicy dateParts na liczby całkowite int
                day = Integer.parseInt(dateParts[0]);  // konwertuje pierwszą część daty na liczbę reprezentującą dzień
                month = Integer.parseInt(dateParts[1]);// drugą część daty na liczbę reprezentującą miesiąc
            } catch (NumberFormatException e) {        // wychwytuje wyjątek jeśli któraś z części dateParts nie jest liczbą
                JOptionPane.showMessageDialog(null, "Nieprawidłowy format daty. Spróbuj ponownie.");
                continue;
            }

            String zodiacSign = getZodiacSign(day, month); // wywołuję funkcję która określa znak zodiaku na podstawie dnia i miesiąca
            if (zodiacSign == null) {  // jeśli dane wejściowe są nieprawidłowe metoda zwraca null i prosi o ponowne wpisanie daty
                JOptionPane.showMessageDialog(null, "Nieprawidłowy dzień lub miesiąc. Spróbuj ponownie.");
                continue;
            }
            // okno dialogowe prezentujące znak zodiaku użytkownika
            JOptionPane.showMessageDialog(null, "Twój znak zodiaku to: " + zodiacSign);

            while (true) {
                // Wybór wróżby lub horoskopu
                String[] options = {"Horoskop", "Wróżba na dziś", "Powrót"};
                int choice = JOptionPane.showOptionDialog(null,  // metoda klasy JOptionPane tworzy okno dialogowe z przyciskami
                        "Co chcesz zobaczyć?",                                  // pytanie w oknie dialogowym
                        "Wybór",                                                // tytuł okna dialogoweo
                        JOptionPane.DEFAULT_OPTION,                             // styl przycisków (domyślny)
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,                                                // tablica opcji
                        options[0]);                                            // domyslnie zaznaczony przycisk (pierwszy)

                if (choice == 0) {
                    // Wyświetlenie horoskopu metodą getHoroscope na podstawie znaku zodiaku zodiacSign
                    String horoscope = getHoroscope(zodiacSign);
                    JOptionPane.showMessageDialog(null, "Twój horoskop: " + horoscope);
                } else if (choice == 1) {
                    // Wyświetlenie wróżby na dziś metodą getRandomFortune
                    String fortune = getRandomFortune();
                    JOptionPane.showMessageDialog(null, "Twoja wróżba na dziś: " + fortune);
                } else {
                    break; // Powrót do ekranu wyboru daty
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Dziękujemy za skorzystanie z programu. Do zobaczenia!");
    }

    // Funkcja zwracająca znak zodiaku na podstawie daty urodzenia
    private static String getZodiacSign(int day, int month) {
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return "Wodnik";
        if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) return "Ryby";
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "Baran";
        if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return "Byk";
        if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) return "Bliźnięta";
        if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) return "Rak";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "Lew";
        if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "Panna";
        if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return "Waga";
        if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) return "Skorpion";
        if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) return "Strzelec";
        if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) return "Koziorożec";
        return null;
    }

    // Funkcja zwracająca horoskop dla danego znaku zodiaku
    private static String getHoroscope(String zodiacSign) {
        Map<String, String> horoscopes = new HashMap<>();
        horoscopes.put("Wodnik", "Dziś czeka Cię niespodziewane spotkanie z kimś wyjątkowym.");
        horoscopes.put("Ryby", "Twoja kreatywność będzie na najwyższym poziomie.");
        horoscopes.put("Baran", "Dziś jest idealny dzień na rozpoczęcie nowego projektu.");
        horoscopes.put("Byk", "Spodziewaj się dobrych wiadomości finansowych.");
        horoscopes.put("Bliźnięta", "Twoja elokwencja pomoże Ci rozwiązać konflikt.");
        horoscopes.put("Rak", "Dzień pełen emocji, ale dasz radę nad nimi zapanować.");
        horoscopes.put("Lew", "Twoja charyzma przyciągnie nowych przyjaciół.");
        horoscopes.put("Panna", "Perfekcja w pracy przyniesie Ci uznanie.");
        horoscopes.put("Waga", "Balans i harmonia będą dziś Twoimi sprzymierzeńcami.");
        horoscopes.put("Skorpion", "Twoja determinacja przyniesie dziś owoce.");
        horoscopes.put("Strzelec", "Dzień pełen przygód i nowych doświadczeń.");
        horoscopes.put("Koziorożec", "Twoja ciężka praca zostanie dziś doceniona.");
        return horoscopes.getOrDefault(zodiacSign, "Brak horoskopu dla tego znaku.");
    }

    // Funkcja zwracająca losową wróżbę na dziś
    private static String getRandomFortune() {
        // ArrayList przechowujący wróżby
        ArrayList<String> fortunes = new ArrayList<>();
        fortunes.add("Czeka Cie trudna decyzja, której konsekwencje będą Cie prześladować przez długie lata.");
        fortunes.add("Zbyt długo ignorowałeś ostrzeżenia – teraz przyjdzie zapłacić za błędy.");
        fortunes.add("Czas, który poświęciłeś na coś ważnego, okaże się całkowicie zmarnowany.");
        fortunes.add("Wkrótce spotkasz osobę, która odmieni Twoje życie na lepsze.");
        fortunes.add("W nadchodzących dniach Twoje finanse znacznie się poprawią");
        fortunes.add("W życiu nie chodzi o to, by przeczekać burzę, lecz nauczyć się tańczyć w deszczu.");
        fortunes.add("Podróż tysiąca mil… zawsze zaczyna się od jednego kroku.");
        fortunes.add("W tym roku czeka Cie wiele niespodzianek");
        fortunes.add("Czeka Cie zdrada, której nie będziesz w stanie przewidzieć.");
        fortunes.add("Lepiej być wojownikiem w ogrodzie niż ogrodnikiem na wojnie");


        // Generowanie losowego indeksu
        Random random = new Random();
        return fortunes.get(random.nextInt(fortunes.size()));
    }

}

