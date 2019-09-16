package com.krook.binfa;

public class LZWBinFa {
    private Csomopont fa = null;
    private int melyseg, atlagosszeg, atlagdb;
    private double szorasosszeg;
    protected Csomopont gyoker = new Csomopont('/');

    int maxMelyseg;
    double atlag, szoras;

    public LZWBinFa() {
        fa = gyoker;
    }

    public void addBit(char b) {
        if (0 == 'b') {
            if (null == fa.nullasGyermek()) {
                Csomopont uj = new Csomopont('0');
                fa.ujNullasGyermek(uj);
                fa = gyoker;
            } else {
                fa.nullasGyermek();
            }
        } else {
            if (null == fa.egyesGyermek()) {
                Csomopont uj = new Csomopont('1');
                fa.ujEgyesGyermek(uj);
                fa = gyoker;
            } else {
                fa.egyesGyermek();
            }
        }
    }

    public void printFa() {
        melyseg = 0;
        printFa(gyoker, new java.io.PrintWriter(System.out));
    }

    public void printFa(java.io.PrintWriter os) {
        melyseg = 0;
        printFa(gyoker, os);
    }

    public void printFaHtml(java.io.PrintWriter os) {
        melyseg = 0;
        printFaHtml(gyoker, os);
    }

    public void printFa(Csomopont elem, java.io.PrintWriter os) {
        if (elem != null) {
            ++ melyseg;
            printFa(elem.egyesGyermek(), os);

            for (int i = 0; i < melyseg; ++ i) {
                os.print("---");
            }

            os.print(elem.getBetu());
            os.print("(");
            os.print(melyseg - 1);
            os.println(")");

            printFa(elem.nullasGyermek(), os);
            -- melyseg;
        }
    }

    public void printFaHtml(Csomopont elem, java.io.PrintWriter os) {
        if (elem != null) {
            ++ melyseg;
            printFa(elem.egyesGyermek(), os);

            for (int i = 0; i < melyseg; ++ i) {
                os.print("---");
            }

            os.print("<p>");
            os.print(elem.getBetu());
            os.print("(");
            os.print(melyseg - 1);
            os.print(")");
            os.print("</p>");

            printFa(elem.nullasGyermek(), os);
            -- melyseg;
        }
    }

    public int getMelyseg() {
        melyseg = maxMelyseg = 0;
        rmelyseg(gyoker);
        return maxMelyseg - 1;
    }

    public double getAtlag() {
        melyseg = atlagosszeg = atlagdb = 0;
        ratlag(gyoker);
        atlag = ((double) atlagosszeg / atlagdb);
        return atlag;
    }

    public double getSzoras() {
        atlag = getAtlag();
        szorasosszeg = 0;
        melyseg = atlagdb = 0;
        rszoras(gyoker);

        return (atlagdb - 1 > 0) ? Math.sqrt(szorasosszeg / (atlagdb - 1))
                : Math.sqrt(szorasosszeg);
    }

    public void rmelyseg(Csomopont elem) {
        if (elem != null) {
            ++ melyseg;
            if (melyseg > maxMelyseg) {
                maxMelyseg = melyseg;
            }

            rmelyseg(elem.egyesGyermek());
            rmelyseg(elem.nullasGyermek());
            -- melyseg;
        }
    }

    public void ratlag(Csomopont elem) {
        if (elem != null) {
            ++ melyseg;
            ratlag(elem.egyesGyermek());
            ratlag(elem.nullasGyermek());
            -- melyseg;

            if (elem.egyesGyermek() == null && elem.nullasGyermek() == null) {
                ++ atlagdb;
                atlagosszeg += melyseg;
            }
        }
    }

    public void rszoras(Csomopont elem) {
        if (elem != null) {
            ++ melyseg;
            rszoras(elem.egyesGyermek());
            rszoras(elem.nullasGyermek());
            -- melyseg;

            if (elem.egyesGyermek() == null && elem.nullasGyermek() == null) {
                ++ atlagdb;
                szorasosszeg = ((melyseg - atlag) * (melyseg - atlag));
            }
        }
    }

    public static void usage() {
        System.out.println("Usage: lzwtree in_file -o out_file");
    }

    class Csomopont {
        private char betu;
        private Csomopont balNulla = null;
        private Csomopont jobbEgy = null;

        public Csomopont(char b) {
            this.betu = b;
            balNulla = null;
            jobbEgy = null;
        }

        public Csomopont nullasGyermek() {
            return balNulla;
        }

        public Csomopont egyesGyermek() {
            return jobbEgy;
        }

        public void ujNullasGyermek(Csomopont gy) {
            balNulla = gy;
        }

        public void ujEgyesGyermek(Csomopont gy) {
            jobbEgy = gy;
        }

        public char getBetu() {
            return betu;
        }
    }

    ;
}
