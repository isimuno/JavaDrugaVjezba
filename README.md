# JavaDrugaVjezba

2.1. TEMA VJEŽBE
Svrha laboratorijske vježbe je korištenje koncepata objektno
orijentiranog programiranja u programskom jeziku Java, uz korištenje
podklasa i nadklasa kod nasljeđivanja, korištenje modifikatora
„protected“ i „super“, konstruktora u podklasama, nadjačavanje metoda,
polimorfizma, apstraktnog metoda i sučelja.
2. 2. ZADATAK ZA PRIPREMU
Proširiti rješenje prve laboratorijske vježbe na način da se kopira
rješenje te preimenuje u naziv koji sadrži indeks „2“, umjesto „1“. Osim
same mape s projektom, potrebno je promijeniti i naziv projekta unutar
IntelliJ-a korištenjem opcije „Refactor->Rename“. Program je potrebno
proširiti na sljedeći način:
1. U paket „model“ dodati novu apstraktnu klasu „ImenovaniEntitet“
koji sadrži „naziv“ tipa „String“, konstruktor koji postavlja taj objekt
te „getter“ i „setter“ metode za taj objekt.
2. Sve klase koje imaju objekt „naziv“ promijeniti na način da nasljeđuju
klasu „ImenovaniEntitet“, a samim time i objekt „naziv“, koriste njen
konstruktor te „getter“ i „setter“ metode.
3. U paket „model“ dodati novo sučelje „Zarazno“ koja ima metodu
„prelazakZarazeNaOsobu“, prima objekt klase „osoba“ i ne vraća
ništa.
4. U paket „model“ dodati novu klasu „Virus“ koja nasljeđuje klasu bolest
i implementira sučelje „Zarazno“. U klasi „Virus“ je potrebno kreirati
konstruktor koji prima sve potrebne parametre i implementira
metodu iz sučelja „Zarazno“. U toj metodi je potrebno objektu klase
„osoba“ postaviti da je zaražena tim virusom u kojem se metoda
nalazi.
5. Doraditi konstruktor klase „Osoba“ na način da se na kraju
konstruktora doda logika koja svim osobama unutar polja
„kontaktiraneOsobe“ postavlja zaraženost virusom u slučaju kad je za
objekt „zarazenBolescu“ postavljen objekt tipa „Virus“. U ovoj
implementaciji je potrebno koristiti novu funkcionalnost Jave 15
„Pattern Matching“.
6. Doraditi „main“ metodu da se prilikom unošenja podataka o bolesti
osobe postavi pitanje unosi li se virus ili bolest i prema tome kreira
odgovarajući objekt i postavi kao objekt „zarazenBolescu“. U
aplikaciju je prije toga potrebno unijeti dvije bolesti i dva virusa te ih
sve postaviti u isto polje u programu (strogo je zabranjeno koristiti
dva odvojena polja za bolesti i viruse).
7. Implementirati „Builder pattern“ za klasu „Osoba“ te umjesto poziva
konstruktora za klasu „Osoba“ unutar „main“ metode iskoristiti
„BuilderPattern“.
