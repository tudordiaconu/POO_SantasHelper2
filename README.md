<h4>Copyright 2022 Diaconu Tudor-Gabriel</h4>
<h1>Etapa 2 - Proiect</h1>

<h3>Bonusul de cumintenie</h3>
Pentru a adauga scorul de cumintenie, pur si simplu am aplicat formula de pe
ocw asupra scorului calculat in urma primei etape.

<h3>Elfii Mosului</h3>
Pentru a realiza implementarile fiecarui elf, am construit un Command pattern
destul de basic, el avand doar posibilitatea de execute, intrucat nu vedeam
rostul existentei operatiilor de redo si undo. Pentru a construi cele 3 comenzi
(cate o comanda pentru fiecare elf), mi-am creat 3 constante si o functie prin
care extrageam tipul de comanda dintr-un string anume dat ca si parametru in
apelul functiei de execute pe care il face clientul. In cazul elfilor negru
si roz, am realizat restructurarea bugetului, iar in cazul elfului galben
am cautat cel mai ieftin cadou din categoria preferata(lucru destul de simplu),
avand in vedere ca sortasem lista de cadouri dupa pret, iar daca acest cadou
inca mai avea cantitatea > 0, iar copilul nu primise niciun cadou, ii ofeream
cadoul. De asemenea, trebuie mentionat faptul ca, comenzile sunt create prin
Factory pattern.

<h3>Modalitatile de asignare ale cadourilor</h3>
Pentru modalitatile de asignare ale cadourilor, am implementat un Strategy
pattern, avand 3 strategii, cea dupa id, cea dupa average score si cea dupa
scorul orasului. Asadar, fiecare strategie reprezinta o clasa ce implementeaza
interfata strategiei de sortare, aceasta avand o metoda de sortare a copiilor
din database, lista sortata ajungand intr-un field al database-ului. Pentru a
crea strategia, am recurs la Factory pattern. Strategia de id reprezinta o
comparare simpla a unei simple dupa id-ul copiilor, realizata prin stream-uri.
Strategia nicescore reprezinta o comparare double a copiilor dupa average
score. Strategia scorului oraselor este putin mai complexa, iar eu am decis sa
o implementez prin intermediul a 2 HashMap-uri, unul in care stocam fiecare
oras, alaturi de o lista cu scorurile copiilor ce locuiau in acel oras, iar in
celalalt stocam orasul alaturi de scorul orasului, calculat prin intermediul
listei mentionate. Ulterior extrageam aceasta lista a oraselor, sortate dupa
scorul lor, pe care o foloseam pentru a obtine o lista sortata a copiilor.
Aceasta lista sortata este utilizata pentru impartirea cadourilor.

<h3>Scuze pentru Builder</h3>
As dori sa imi cer scuze pentru acel Builder pattern, insa in ultimul moment
al finalizarii temei am auzit de acel bonus si am zis sa incerc sa fac un
Builder, care este foarte probabil sa nu fie bun. It's not much but it's honest
work.

<h3>Modificari la scriere</h3>
Pentru scriere, am mai creat o clasa pentru scrierea cadourilor, incat nu se
dorea scrierea cantitatii cadourilor in json-uri, legatura dintre GiftWriter
si Gift fiind similara cu cea dintre ChildWriter si Child.

<h3> Feedback proiect </h3>
Mi s-a parut un proiect foarte tare, care m-a ajutat in primul rand sa inteleg
mult mai bine design pattern-urile si m-a facut sa imi intaresc propria
credinta ca Java e super misto ca limbaj de programare (>>>>>>>> C) si ca POO
e o materie si un concept foarte util si foarte interesant (my personal fav
subject this semester). Kudos and thank you for reviewing!