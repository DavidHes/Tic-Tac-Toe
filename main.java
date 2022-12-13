import java.util.List;
import java.util.Scanner;

public class main {

    private static java.util.Scanner scanZeile = new Scanner(System.in);
    private static java.util.Scanner scanSpalte = new Scanner(System.in);
    static boolean abbruch = false;


    /**
     * TODO: Nach wie vor kann man X bzw 0 ersetzen. Manchmal gehts, manchmal geht nicht.
     * TODO: Wenn man "Abbruch" als Text in die Konsole eingibt, soll das Programm auch abbrechen
     * TODO: Evtl. mehr vom Hauptteil als Hilfsmethoden umformen
     * @param args
     */

    public static void main(String[] args) {

        String SPIELFELD[][] = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
        final String[] VALID_COLUMN = {"A", "B", "C",};
        final String[] VALID_ROW = {"1", "2", "3",};

        System.out.println("Willkommen, das Spiel ist wie folgt ausgebaut: \n Die Spalten sind A, B und C \n Die Zeilen" +
                " sind 1, 2 und 3 \n Um ein Feld zu setzen, gibst du erst die Spalte und dann die Zeile ein");
        System.out.println("Wenn du \"STOP\" eingibst, wird das Spiel abgebrochen");

        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8) {
                System.out.println("Spieler 1 ist an der Reihe. Gebe die Spalte ein.");
            } else {
                System.out.println("Spieler 2 ist an der Reihe. Gebe die Spalte ein.");
            }

            String Spalte = scanSpalte.nextLine();
            if (Spalte.equalsIgnoreCase("A") || Spalte.equalsIgnoreCase("B")
                    || Spalte.equalsIgnoreCase("C")) {
            } else {
                System.out.println("Gebe eine Spalte ein! Spalten sind A, B oder C!");
                //break;
                Abbruch();
                //zurückspringen noch besser lösen und nicht durch Abbruch! Wirklich bullshit; i--?
            }
            System.out.println("Gebe eine Zeile ein!");
            String Zeile = scanZeile.nextLine();
            if (Zeile.equalsIgnoreCase("1") || Zeile.equalsIgnoreCase("2")
                    || Zeile.equalsIgnoreCase("3")) {
                if (!SPIELFELD[UmwandlerSpalte(Spalte)][UmwandlerZeile(Zeile)].equalsIgnoreCase("0")) {
                    if (!SPIELFELD[UmwandlerSpalte(Spalte)][UmwandlerZeile(Zeile)].equalsIgnoreCase("X")) {
                        if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8) {
                            
                            SPIELFELD[UmwandlerSpalte(Spalte)][UmwandlerZeile(Zeile)] = "X";
                            
                            Bsp.: Spalte = A; Zeile = 3
                                
                            SPIELFELD[A][3] = "X";
                            SPIELFELD[0][2] = "X";
                            
                        } else {
                            SPIELFELD[UmwandlerSpalte(Spalte)][UmwandlerZeile(Zeile)] = "0";
                        }

                    /* DAS GESAMTE SPIELFELD WIRD GEPRINTET. System.out.println() zwischen den for-Schleifen ist notwendig,
                       da sonst, alles in einer Zeile landen würde */

                        for (int j = 0; j < 3; j++) {
                            System.out.print(SPIELFELD[0][j] + " ");
                        }
                        System.out.println();
                        for (int j = 0; j < 3; j++) {
                            System.out.print(SPIELFELD[1][j] + " ");
                        }
                        System.out.println();
                        for (int j = 0; j < 3; j++) {
                            System.out.print(SPIELFELD[2][j] + " ");
                        }
                        System.out.println();

                        //Prüfen ob jemand gewonnen hat oder nicht
                        CheckWinner("Spieler 1", "X", SPIELFELD);
                        CheckWinner("Spieler 2", "0", SPIELFELD);

                    } else {
                        System.out.println("Diese Spalte ist bereits belegt!");
                        i--;
                    }
                } else {
                    System.out.println("Diese Spalte ist bereits belegt!");
                    i--;
                }
            } else {
                System.out.println("Falsche Eingabe! Gebe eine Zeile ein! Spalten sind 1, 2 oder 3!");

            }
        }
    }


    public static int UmwandlerSpalte(String s) {
        if (s.equalsIgnoreCase("A"))
            return 0;
        else if (s.equalsIgnoreCase("B"))
            return 1;
        return 2; // Hier wird auf eine Abfrage verzichtet, da es logisch ist, dass es nur "C" sein kann.
        // Andernfalls müssten wir 4 returns hinzufügen, da es immer ein allgemeingültiges return geben muss!
    }

    public static int UmwandlerZeile(String s) {
        if (s.equalsIgnoreCase("1"))
            return 0;
        else if (s.equalsIgnoreCase("2"))
            return 1;
        else if (s.equalsIgnoreCase("3"))
            return 2;
        return 0;
    }

    public static void CheckWinner(String spieler, String s, String SPIELFELD[][]) {
        if (SPIELFELD[0][0].equals(s) && SPIELFELD[0][1].equals(s) && SPIELFELD[0][2].equals(s)
                || SPIELFELD[1][0].equals(s) && SPIELFELD[1][1].equals(s) && SPIELFELD[1][2].equals(s)
                || SPIELFELD[2][0].equals(s) && SPIELFELD[2][1].equals(s) && SPIELFELD[2][2].equals(s)
                || SPIELFELD[0][0].equals(s) && SPIELFELD[1][1].equals(s) && SPIELFELD[2][2].equals(s)
                || SPIELFELD[0][2].equals(s) && SPIELFELD[1][1].equals(s) && SPIELFELD[2][0].equals(s)
                || SPIELFELD[0][0].equals(s) && SPIELFELD[1][0].equals(s) && SPIELFELD[2][0].equals(s)
                || SPIELFELD[0][1].equals(s) && SPIELFELD[1][1].equals(s) && SPIELFELD[2][1].equals(s)
                || SPIELFELD[0][2].equals(s) && SPIELFELD[1][2].equals(s) && SPIELFELD[2][2].equals(s)) {
            System.out.println(spieler + " hat gewonnen!");
            Abbruch();
        }
    }

    //Evtl. umbenennen zu "Ende" bzw "Beendung"?
    public static void Abbruch() {
        abbruch = true;
        System.out.println("Das Spiel wird abgebrochen.");
        System.exit(0);
    }
}
