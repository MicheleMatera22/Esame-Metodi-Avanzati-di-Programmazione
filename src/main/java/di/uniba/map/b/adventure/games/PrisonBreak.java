package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.Utils;
import di.uniba.map.b.adventure.database.Database;
import di.uniba.map.b.adventure.gui.EngineGUI;
import di.uniba.map.b.adventure.gui.Music;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import di.uniba.map.b.adventure.type.AdvObject;
import java.util.Random;

import static di.uniba.map.b.adventure.Utils.readFile;
import static di.uniba.map.b.adventure.type.CommandType.SAVE;

public class PrisonBreak extends GameDescription {

    //lettura file introduzione partita
    /**
     * Metodo che permette la lettura del del file di introduzione.
     */
    @Override
    public void Intro() {
        String intro = readFile(".//resources//txt//Intro.txt");
        EngineGUI.displayIntroTextSet(intro);
    }

    /**
     * Metodo per l'inizializzazione dei comandi, delle stanze e degli oggetti
     * presenti nel gioco.
     */
    @Override
    public void initGame() {

        //set stanza, con il metodo getRooms().add creo una lista di stanze
        Room courtyard = new Room(0, "Cortile", "Il cortile di Fox River è un'area aperta circondata da alte mura con filo spinato, dove i detenuti socializzano, fanno esercizio o giocano a carte.\nTavoli di legno e zone in cemento segnano lo spazio, mentre torrette di guardia vigilano ogni movimento. L'atmosfera è tesa, con sguardi sospettosi e un costante senso di pericolo, ma per molti è un momento di respiro lontano dalle celle soffocanti.\n");
        getRooms().add(courtyard);

        Room guardroom = new Room(1, "Sala delle guardie", "La sala delle guardie ristrutturata è un ambiente essenziale e funzionale, con pareti rivestite in legno grezzo.\nAl centro si trova un grande tavolo circondato da sedie robuste, mentre lungo le pareti sono stati sistemati scaffali ordinati con registri, attrezzi e materiali utili. L'illuminazione è semplice, con lampadine appese a fili, conferendo alla stanza un'atmosfera operativa e ordinata.");
        getRooms().add(guardroom);

        Room directoroffice = new Room(2, "Ufficio del direttore", "L'ufficio del direttore Pope è accogliente e ben curato, con pareti rivestite in legno scuro che danno un tocco di calore e autorità.\nAl centro spicca una grande scrivania in legno, sempre ordinata, con una lampada da tavolo e qualche documento ben sistemato.\nDietro, una libreria piena di libri e fascicoli racconta la vita lavorativa del direttore, mentre una vetrinetta espone premi e foto personali.");
        getRooms().add(directoroffice);

        Room cellblock = new Room(3, "Blocco celle", "Il blocco celle è un luogo freddo e opprimente, dominato da lunghe file di celle di metallo disposte su più livelli.\nLe pareti sono spoglie e segnate dal tempo, con una vernice grigia ormai sbiadita.\nOgni cella è piccola e spartana, con una branda, un lavabo e un WC, il minimo indispensabile per vivere.\nUn corridoio centrale separa le file di celle, con grate che lasciano intravedere gli interni.");
        getRooms().add(cellblock);

        Room michaelcell = new Room(4, "Cella di Michael", "La cella di Michael è piccola e spartana, come tutte le altre, ma ha un'aria diversa grazie alla sua precisione e organizzazione.\nSul muro accanto al letto si notano piccoli segni, appunti o disegni che sembrano casuali, ma nascondono un significato preciso.\nIl lavabo e il WC in acciaio sono puliti, segno di un'attenzione ai dettagli che Michael applica a tutto. Sotto il letto o in angoli nascosti potrebbero esserci oggetti apparentemente insignificanti, ma che fanno parte di un piano più grande.");
        getRooms().add(michaelcell);

        Room tunnel = new Room(5, "Tunnel sotterraneo", "Un passaggio stretto e buio scavato sotto Fox River, con pareti di terra grezze e travi di legno instabili.\nL'aria e' umida, l'illuminazione precaria, e tubi e cavi corrono lungo il soffitto.\nIl tunnel e' insidioso, ma rappresenta la via verso la liberta' per i detenuti.\nIl capo delle guardie Bellick che avevi tramortito nel tunnel inizia a gridare e chiedere aiuto e attira l'attenzione delle guardie.\nAffrettati le guardie stanno arrivvando");
        getRooms().add(tunnel);

        Room infirmary = new Room(6, "Infermeria", "L'infermeria, sterile e ordinata, diventa il punto finale della fuga.\nSituata vicino al perimetro esterno, con finestre sbarrate che offrono uno scorcio della liberta', ospita l'uscita del tunnel.\nOgni passo qui e' cruciale, con i fuggitivi che rischiano tutto per evitare di essere scoperti.       ");
        getRooms().add(infirmary);

        Room freedom = new Room(7, "Liberta'", "");
        getRooms().add(freedom);

        courtyard.setLook("Nel cortile di Fox River, le panchine di legno vecchio scricchiolano, e un chiodo sporgente brilla al sole: potrebbe essere la chiave per aprire le porte... o convincere qualcuno a collaborare.\nA Nord intravedi la sala delle guardie ed è stranamente incustodita....");

        guardroom.setLook("Questo e'il punto da dove comincia la fuga! Mentre tu e tuoi compagni lavoravate alla costruzione della sala, avete scavato un tunnel che vi portera' direttamente all'infermeria che e' la stanza piu' vicina alla liberta'.\nProseguendo a nord trovi l'ufficio del direttore Pope.");

        directoroffice.setLook("Herny Pope, il direttore, ti ha affidato il compito di costruire un modellino del Taj Mahal come regalo per tua moglie.\nC'e' una porta chiusa a chiave che porta direttamente al blocco celle.\nIl direttore ha la chiave ma e' anche l'unico che puo' farti guadagnare tempo con le guardie.");

        cellblock.setLook("Ci sono i tuoi compagni di fuga che aspettano solo te per iniziare la fuga, accompagnali alla tua cella! Ma c'e' un problema: le celle sono chiuse.\nDovrebbe esserci un pulsante per aprire le celle.");

        michaelcell.setLook("Il lavandino e' il punto per iniziare la fuga, ma delle viti ti impediscono di spostarlo.\nDovrebbe esserci un cacciavite che hai nascostosotto il letto!");

        tunnel.setLook("Il custode Brad Bellick e' legato e imbavagliato, ma i suoi urli soffocati si sentono chiaramente.\nOgni suo tentativo di farsi notare aumenta il rischio che qualcuno lo senta, mettendo in pericolo l'intero piano di fuga.\nHa con se il badge per accedere all'infermeria.\nC'e' un bastone di ferro per terra...potrebbe essere utile.");

        infirmary.setLook("C'e' una corda per terra, pronta per essere usata dai fuggitivi per arrampicarsi fino alle mura esterne.\nDa li' un salto verso l'ignoto li separa dalla liberta', rendendo questo luogo il crocevia tra prigionia e salvezza.");
        // Stanza di INIZIO GIOCO
        setCurrentRoom(courtyard);

        //setAccesible, stanza accessibile = true / stanza non accessibile = false
        guardroom.setAccessible(true);
        directoroffice.setAccessible(true);
        cellblock.setAccessible(false);
        michaelcell.setAccessible(false);
        tunnel.setAccessible(false);
        infirmary.setAccessible(false);
        freedom.setAccessible(false);

        //set mappa del gioco
        courtyard.setBorders(guardroom, null, null, null);
        guardroom.setBorders(directoroffice, courtyard, null, null);
        directoroffice.setBorders(null, guardroom, cellblock, null);
        cellblock.setBorders(null, michaelcell, null, directoroffice);
        michaelcell.setBorders(cellblock, tunnel, null, null);
        tunnel.setBorders(michaelcell, null, infirmary, null);
        infirmary.setBorders(null, null, freedom, tunnel);
        freedom.setBorders(null, null, null, infirmary);

        //set comandi del gioco
        Command north = new Command(CommandType.NORTH, "nord"); //si chiama nord
        north.setAlias(new String[]{"n", "N", "Nord", "NORD"}); //ed ha i suoi alias
        getCommands().add(north);

        Command south = new Command(CommandType.SOUTH, "sud");
        south.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(south);

        Command east = new Command(CommandType.EAST, "est");
        east.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(east);

        Command west = new Command(CommandType.WEST, "ovest");
        west.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(west);

        Command look = new Command(CommandType.LOOK, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);

        Command examine = new Command(CommandType.EXAMINE, "esamina");
        examine.setAlias(new String[]{"ispeziona"});
        getCommands().add(examine);

        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{"aprire"});
        getCommands().add(open);

        Command use = new Command(CommandType.USE, "utilizza");
        use.setAlias(new String[]{"usa", "estrai", "utilizza"});
        getCommands().add(use);

        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva"});
        getCommands().add(push);

        Command take = new Command(CommandType.TAKE, "prendi");
        take.setAlias(new String[]{"raccogli"});
        getCommands().add(take);

        Command save = new Command(CommandType.SAVE, "salva");
        save.setAlias(new String[]{"salvataggio"});
        getCommands().add(save);

        AdvObject nail = new AdvObject(0, "Chiodo", "Chiodo preso dalle panchine del cortile della prigione, puo' aprire delle porte o essere usato come arma!");
        nail.setAlias(new String[]{"spillo", "perno", "vite", "bullone", "chiodo"});
        courtyard.getObjects().add(nail);
        nail.setTakeable(true);
        nail.setUsable(true);

        AdvObject pit = new AdvObject(1, "tunnel", "Tunnel verso l'infermeria che ti condurr\u00e0 alla libert\u00e0");
        pit.setAlias(new String[]{"passaggio sotterraneo", "condotto", "tunnel"});
        guardroom.getObjects().add(pit);
        pit.setTakeable(false);
        pit.setUsable(true);

        AdvObject officeKey = new AdvObject(2, "Chiave", "Chiave che ti consente di arrivare direttamente al blocco celle");
        officeKey.setAlias(new String[]{"chiave"});
        directoroffice.getObjects().add(officeKey);
        officeKey.setTakeable(true);
        officeKey.setUsable(true);

        AdvObject officeDoor = new AdvObject(3, "porta", "Porta che ti consente di arrivare al blocco celle dei detenuti.");
        officeDoor.setAlias(new String[]{"porta"});
        directoroffice.getObjects().add(officeDoor);
        officeDoor.setOpenable(true);
        officeDoor.setOpen(false);

        AdvObject cellButton = new AdvObject(4, "Bottone", "Pulsante che ti consente di aprire la tua cella.");
        cellButton.setAlias(new String[]{"bottone", "Pulsante", "pulsante"});
        cellblock.getObjects().add(cellButton);
        cellButton.setUsable(true);
        cellButton.setPushable(true);

        AdvObject screwdriver = new AdvObject(5, "cacciavite", "L'hai rubato dal magazzino della prigione, potrebbe servirti ad aprire qualche accesso...");
        screwdriver.setAlias(new String[]{"giravite", "avvitatore"});
        michaelcell.getObjects().add(screwdriver);
        screwdriver.setTakeable(true);
        screwdriver.setUsable(true);

        AdvObject infirmarybadge = new AdvObject(6, "badge", "L'unico strumento che ti permette di aprire la porta dell'infermeria.");
        infirmarybadge.setAlias(new String[]{"pass"});
        tunnel.getObjects().add(infirmarybadge);
        infirmarybadge.setTakeable(true);
        infirmarybadge.setUsable(true);

        AdvObject rod = new AdvObject(7, "bastone", "Potrebbe servire per difenderti o per chiudere la bocca a qualcuno..");
        rod.setAlias(new String[]{"mazza", "stanga"});
        tunnel.getObjects().add(rod);
        rod.setTakeable(true);
        rod.setUsable(true);

        AdvObject rope = new AdvObject(8, "corda", "Puoi usarla per collegarti alle mura della prigione e scappare!");
        rope.setAlias(new String[]{"fune", "cavo"});
        infirmary.getObjects().add(rope);
        rope.setTakeable(true);
        rope.setUsable(true);

        if (GameDescription.loadedGame) {
            for (AdvObject object : getObjects()) {
                int objectId = object.getId();
                if (Database.loadTakedObjects(objectId) && !Database.loadUsedObjects(objectId)) {
                    getInventory().add(object);
                } else if (Database.loadUsedObjects(objectId)) {
                    switch (objectId) {
                        case 0 -> {
                            getInventory().remove(nail);
                        }
                        case 2 -> {
                            cellblock.setAccessible(true);
                            officeDoor.setOpen(true);
                            getInventory().remove(officeKey);
                        }
                        case 4 -> {
                            michaelcell.setAccessible(true);
                        }
                        case 5 -> {
                            tunnel.setAccessible(true);
                            getInventory().remove(screwdriver);
                        }
                        case 6 -> {
                            infirmary.setAccessible(true);
                            getInventory().remove(infirmarybadge);
                        }
                        case 8 -> {
                            getInventory().remove(rope);
                            freedom.setAccessible(true);
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo che analizza il risultato del parser e agisce a seconda dei casi.
     *
     * @param p
     */
    @Override
    public void nextMove(ParserOutput p) {

        byte move = 0; // 1: hai cambiato stanza, 2: è chiusa a chiave, 3: c'è un muro
        boolean noItem = false; // Non ho digitato il nome dell'oggetto (true)
        switch (p.getCommand().getType()) {
            case NORTH -> {
                //se comando è di tipo Nord
                //se veramente c'è la stanza a nord la imposto come stanza corrente
                if (getCurrentRoom().getNorth() != null) {
                    if (getCurrentRoom().getNorth().isAccessible()) { //verifico se la stanza è accessibile
                        setCurrentRoom(getCurrentRoom().getNorth());
                        move = 1;
                    } else {
                        move = 2; //se la stanza è chiusa e bisogna aprirla per andarci
                    }

                } else {
                    move = 3;
                }
            }
            case SOUTH -> {
                //se comando è di tipo Sud
                //se veramente c'è la stanza a sud la imposto come stanza corrente
                if (getCurrentRoom().getSouth() != null) { // Se c'è una stanza a nord
                    if (getCurrentRoom().getSouth().isAccessible()) { //verifico se la stanza è accessibile
                        setCurrentRoom(getCurrentRoom().getSouth()); // Imposta la stanza a sud come attuale
                        move = 1; // Hai cambiato stanza
                        if (getCurrentRoom().getId() == 5) {
                            EngineGUI.getInstance().modifyTimer((Utils.getRemainingTime()) - (Utils.getRemainingTime() / 2));
                        }
                    } else {
                        move = 2; //se la stanza è chiusa e bisogna aprirla per andarci
                    }

                } else {
                    move = 3;
                }

            }
            case WEST -> {
                //se il comando è di tipo ovest
                if (getCurrentRoom().getWest() != null) {
                    if (getCurrentRoom().getWest().isAccessible()) { //verifico se la stanza è accessibile
                        setCurrentRoom(getCurrentRoom().getWest());
                        move = 1;
                        if (getCurrentRoom().getId() == 7) {
                            if (Utils.getRemainingTime() > 0) {  //se hai raggiunto l'uscita e il tempo non è scaduto
                                String win = readFile(".//resources//txt//Win.txt");
                                EngineGUI.displayTextSetDelayEnd(win);
                                EngineGUI.hideLabels();
                                EngineGUI.hideFileEnd();
                                Database.deletePlayerData();
                                Database.deleteUsedObjects();

                            } else {  //se hai raggiunto l'uscita e il tempo è scaduto
                                String gameoverend = readFile(".//resources//txt//GameOverEnd.txt");
                                EngineGUI.displayTextSetDelayEnd(gameoverend);
                                EngineGUI.hideLabels();
                                EngineGUI.hideFileEnd();
                                Database.deletePlayerData();
                                Database.deleteUsedObjects();
                            }
                        }

                    } else {
                        move = 2; //se la stanza è chiusa e bisogna aprirla per andarci
                    }

                } else {
                    move = 3;
                }
            }
            case EAST -> {
                //se il comando è di tipo est
                //se veramente c'è la stanza a est la imposto come stanza corrente
                if (getCurrentRoom().getEast() != null) {
                    if (getCurrentRoom().getEast().isAccessible()) { //verifico se la stanza è accessibile
                        setCurrentRoom(getCurrentRoom().getEast());
                        move = 1; // Hai cambiato stanza
                    } else {
                        move = 2; //se la stanza è chiusa e bisogna aprirla per andarci
                    }
                } else {
                    move = 3;
                }
            }
            case LOOK -> {
                if (getCurrentRoom().getLook() != null) { // Se il comando "OSSERVA" della stanza attuale contiene una descrizione
                    EngineGUI.displayTextSetDelay(getCurrentRoom().getLook()); // Contenuto del comando "OSSERVA"
                } else { // Se la descrizione del comando "OSSERVA" è vuota
                    EngineGUI.displayTextSetDelay("Non c'è niente di interessante qui.");
                }
            }
            case EXAMINE -> {
                if (p.getObject() != null) { // Se l'oggetto è nella stanza
                    if (p.getObject().getDescription() != null && getCurrentRoom().isLookable()) { // Se l'oggetto ha una descrizione
                        EngineGUI.displayTextSetDelay(p.getObject().getDescription());
                    } else {
                        EngineGUI.displayTextSetDelay("Non riesco a vedere nulla!");
                    }
                } else if (p.getInvObject() != null) {
                    AdvObject advObject = p.getInvObject();
                    EngineGUI.displayTextSetDelay("- " + advObject.getName() + ": " + advObject.getDescription());

                } else {
                    EngineGUI.displayTextSetDelay("Non c'è nulla da esaminare!");
                }
            }
            case PUSH -> {
                if (p.getObject() != null) {
                    if (p.getObject().isPushable()) {
                        if (p.getObject().getId() == 4) {
                            //bottone per aprire la cella nel blocco celle
                            Music.playEffect(".//resources//soundEffects//button.wav");
                            EngineGUI.displayTextSetDelay("""
                                              Hai premuto il pulsante!
                                              puoi accedere alla tua cella!""");
                            getCurrentRoom().getSouth().setAccessible(true);
                            Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                            Music.playEffect(".//resources//soundEffects//cellportsound.wav");
                            p.getObject().setPushable(false);

                        }
                    } else {
                        EngineGUI.displayTextSetDelay("Non hai niente da premere!");
                    }
                } else {
                    EngineGUI.displayTextSetDelay("Non trovi nulla da poter premere!");
                }
            }
            case USE -> {

                AdvObject invObject = p.getInvObject();

                if (p.getObject() != null) { // Se l'oggetto è nella stanza
                    if (p.getObject().isUsable()) { // Se l'oggetto è utilizzabile
                        if (!p.getObject().isUsed() && getCurrentRoom().getId() == 1) { // Se l'oggetto non è stato utilizzato e la stanza corrente è l'ufficio del direttore
                            EngineGUI.displayTextSetDelayOpen("Il tunnel che avevi pianificato di usare per la fuga e' stato ricoperto.\nDevi pensare velocemente. Guardandoti attorno, capisci che non hai altra scelta: devi tornare al piano originale, quello che partiva dalla tua cella.\nMa c'e' un problema: il blocco delle celle e' attualmente chiuso, e non puoi tornare direttamente alla tua cella senza attirare l'attenzione delle guardie.\nOgni movimento deve essere calcolato, ogni passo deve essere discreto.");
                        }
                    } else { // Se l'oggetto non si può utilizzare
                        EngineGUI.displayTextSetNormal("Non puoi utilizzare questo oggetto.");
                        EngineGUI.displayTextSetDelay(""); //faccio ritorno la inputArea Editabile
                    }

                    //USARE OGGETTI NELL'INVENTARIO
                } else if (p.getInvObject() != null) { // Se l'oggetto è nell'inventario
                    if (p.getInvObject().isUsable()) { // Se l'oggetto nell'inventario si può utilizzare

                        switch (p.getInvObject().getId()) { // Se l'ID dell'oggetto nell'inventario corrisponde a...
                            case 0 -> {
                                if (!p.getInvObject().isUsed() && getCurrentRoom().getId() == 2) {
                                    EngineGUI.displayTextSetDelay("Hai obbliga il direttore a ritardare il rientro delle guardie!");
                                    Database.saveObject(p.getInvObject().getId(), p.getInvObject().getName(), true);
                                    getInventory().remove(invObject);
                                    EngineGUI.getInstance().modifyTimer((Utils.getRemainingTime()) + (Utils.getRemainingTime() / 2));
                                } else {
                                    EngineGUI.displayTextSetDelay("Non trovi nessuno oggetto simile!");
                                }
                            }
                            case 5 -> {

                                if (!p.getInvObject().isUsed() && getCurrentRoom().getId() == 4) {
                                    EngineGUI.displayTextSetDelay("La via è libera, puoi accedere al tunnel");
                                    invObject.setUsed(true);
                                    invObject.setTaken(true);
                                    Database.saveObject(p.getInvObject().getId(), p.getInvObject().getName(), true);
                                    getInventory().remove(invObject);
                                    getCurrentRoom().getSouth().setAccessible(true);
                                } else {
                                    EngineGUI.displayTextSetDelay("Non puoi usare il cacciavite qui.");
                                }
                            }
                            case 6 -> {
                                if (!p.getInvObject().isUsed() && getCurrentRoom().getId() == 5) {
                                    EngineGUI.displayTextSetDelay("Ora puoi accedere all'infermeria");
                                    invObject.setUsed(true);
                                    invObject.setTaken(true);
                                    Database.saveObject(p.getInvObject().getId(), p.getInvObject().getName(), true);
                                    getInventory().remove(invObject);
                                    getCurrentRoom().getWest().setAccessible(true);
                                } else {
                                    EngineGUI.displayTextSetDelay("Non puoi usare il bastone di ferro qui!");
                                }
                            }
                            case 7 -> {
                                if (!p.getInvObject().isUsed() && getCurrentRoom().getId() == 5) {
                                    EngineGUI.displayTextSetDelay("Hai zittito Bellick, forse hai qualche minuto in piu'!");
                                    invObject.setUsed(true);
                                    invObject.setTaken(true);
                                    Database.saveObject(p.getInvObject().getId(), p.getInvObject().getName(), true);
                                    getInventory().remove(invObject);
                                    EngineGUI.getInstance().modifyTimer((Utils.getRemainingTime()) + (Utils.getRemainingTime() / 2));
                                } else {
                                    EngineGUI.displayTextSetDelay("Non puoi usare il bastone di ferro qui!");
                                }
                            }
                            case 8 -> {
                                if (!p.getInvObject().isUsed() && getCurrentRoom().getId() == 6) {
                                    EngineGUI.displayTextSetDelay("Adesso puoi oltrepassare le mura!");
                                    invObject.setUsed(true);
                                    invObject.setTaken(true);
                                    Database.saveObject(p.getInvObject().getId(), p.getInvObject().getName(), true);
                                    getInventory().remove(invObject);
                                    getCurrentRoom().getWest().setAccessible(true);
                                } else {
                                    EngineGUI.displayTextSetDelay("Non puoi usare la corda qui!");
                                }
                            }
                            default ->
                                EngineGUI.displayTextSetDelay("Non capisco.");
                        }
                        // Se l'ID dell'oggetto nell'inventario corrisponde a...

                    } else { // Se l'oggetto non si può utilizzare
                        EngineGUI.displayTextSetDelay("Non puoi utilizzare questo oggetto.");
                    }
                } else { // Se non ho digitato il nome dell'oggetto
                    noItem = true;
                }
            }
            case SAVE -> {
                try {
                    Database.deletePlayerData();
                    Database.savePlayerRoom(getCurrentRoom().getId(), getCurrentRoom().getName(), Utils.getRemainingTime());
                    EngineGUI.displayTextSetDelay("Salvataggio eseguito!");
                } catch (Exception ex) {
                    EngineGUI.displayTextSetDelay("Si è verificato un errore con il salvataggio!");
                }
            }

            case TAKE -> {
                AdvObject object = p.getObject();
                if (object == null) {
                    EngineGUI.displayTextSetDelay("Non trovo nessun oggetto!");
                    return;
                }

                if (object.isTakeable()) {
                    if (!getInventory().contains(object)) {
                        getInventory().add(object);
                        Database.saveObject(object.getId(), object.getName(), false);
                        EngineGUI.displayTextSetDelay("Hai preso: " + object.getName());
                        object.setTaken(true);

                        if (object.getId() == 0) {
                            Database.saveObject(object.getId(), object.getName(), false);
                            getCurrentRoom().getObjects().remove(object);
                        }
                        if (object.getId() == 2) {
                            Database.saveObject(object.getId(), object.getName(), false);
                            getCurrentRoom().getObjects().get(1).setOpenable(true);
                            EngineGUI.displayTextSetDelay("Hai la chiave per accedere al blocco celle!");
                            getCurrentRoom().getObjects().remove(object);
                        }

                        if (object.getId() == 5) {
                            Database.saveObject(object.getId(), object.getName(), false);
                            getCurrentRoom().getObjects().remove(object);
                        }

                        if (object.getId() == 6) {
                            Database.saveObject(object.getId(), object.getName(), false);
                            getCurrentRoom().getObjects().remove(object);
                        }
                        if (object.getId() == 7) {
                            Database.saveObject(object.getId(), object.getName(), false);
                            getCurrentRoom().getObjects().remove(object);
                        }

                        if (object.getId() == 8) {
                            Database.saveObject(object.getId(), object.getName(), false);
                            getCurrentRoom().getObjects().remove(object);
                        }
                    } else {
                        EngineGUI.displayTextSetDelay("Hai già preso questo oggetto!");
                    }

                } else {
                    EngineGUI.displayTextSetDelay("Non puoi prendere questo oggetto.");
                }
            }

            case OPEN -> {
                AdvObject object = p.getObject();

                if (object == null) {
                    EngineGUI.displayTextSetDelay("Non trovo nessun oggetto!");
                    return;
                }
                if (!object.isOpenable()) {
                    EngineGUI.displayTextSetDelay("L'oggetto non può essere aperto.");
                    return;
                } else if (getCurrentRoom().getId() == 2) {
                    EngineGUI.displayTextSetDelay("Ora puoi accedere al blocco celle!");
                    object.setOpen(true);
                    object.setOpenable(false);
                    getCurrentRoom().getWest().setAccessible(true);
                }
            }
        }

        switch (move) {
            case 1 -> {
                EngineGUI.displayTextSetDelay(getCurrentRoom().getDescription()); // Descrizione della stanza attuale
                EngineGUI.roomsDisplayTextSet("Stanza attuale: " + getCurrentRoom().getName()); // nome della stanza attuale
            }
            case 2 -> //Se ti sei mosso e c'è una porta chiusa
                EngineGUI.displayTextSetDelay("Dove vai? La porta è chiusa.");
            case 3 -> //Se ti sei mosso e c'è un muro
                randomMessageWall();
        }

        if (noItem) {
            EngineGUI.displayTextSetDelay("Non capisco di cosa tu stia parlando.");

        }
    }

    /**
     * Metodo d'istanza per la stampa di messaggi casuali quando il giocatore
     * tenta di muoversi in spazi non permessi.
     */
    private void randomMessageWall() {

        Random randomMessage = new Random();
        int randomMessageWall = randomMessage.nextInt(3);

        switch (randomMessageWall) {
            case 0 ->
                EngineGUI.displayTextSetDelay("Quelle che vedi sono mura, tranquillo non c'è nessun passaggio segreto.\n");
            case 1 ->
                EngineGUI.displayTextSetDelay("OUCH!! Che male! Qui c'è un muro!.\n");
            case 2 ->
                EngineGUI.displayTextSetDelay("Inutile che ci sbatti contro, è difficile buttare giù il muro.\n");
        }
    }
}
