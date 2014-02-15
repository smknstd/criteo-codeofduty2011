import java.io.*;
import java.util.*;

public class Equilibrer {


    private ArrayList<Liste> listes;

    private void resoudre() throws Exception {

        for (Liste li : listes) {

            li.somme();
            li.verif();

            if(li.hasSolution)
                balance(li);

        } 
    }


    /**
     * <p><3 de l'algorithme de résolution du problème :-)</p>
     * <p>on parcourt le tableau de gauche à droite. Au fur et a mesure on somme le poids des cases de la partie gauche. Pour chaque case on compare cette somme au poids theorique.</p>
     * <p>si la case ne contient aucun poids (hors poid ajouté lors de ce tour): on ne fait rien</p>
     * <p>sinon si la somme de gauche est strictement inferieure au poids theorique: on transfere une unité a gauche (et on incremente la somme)</p>
     * <p>      si la (somme de gauche + valeur de la case) est superieure au (poids theorique de gauche + 1 poids) : on transfere une unité a droite</p>
     * <p>Très légère optimisation du parcours du tableau: on limite le parcours du tableau lorsque les extremités sont au poids attendu. Entre chaque parcours, on evalue la position des bornes inferieure et superieure</p>
     * @param l
     */
    public void balance(Liste l) {

        l.calculObjectif();

        int[] previousStep = l.numbers;

        boolean unbalanced = true;

        int binf = 0;
        int bsup = l.size;

        int ct;
        int cr;

        while(unbalanced){

            unbalanced = false;

            //int[] step = previousStep.clone()
            int[] step = Arrays.copyOf(previousStep, l.size);

            //evaluation de la borne inferieure
            while (step[binf] == l.target && binf < bsup-1) {
                    binf ++;
            }

            //evaluation de la borne superieure
            while (step[bsup-1] == l.target && binf < bsup-1) {
                    bsup --;
            }

            ct = binf * l.target; //theorique
            cr = binf * l.target; //réel


            for(int i = binf; i < bsup; i++) {

                if(previousStep[i] > 0){

                    if(cr < ct){
                        unbalanced = true;
                        step[i]--;
                        step[i-1]++;
                        cr++;
                    }else if(cr+step[i] > ct+l.target){
                        unbalanced = true;
                        step[i]--;
                        step[i+1]++;
                    }else{
                        //on ne fait rien
                    }

                }

                ct = (i+1) * l.target;
                cr += step[i];
            }

            if(unbalanced)
                l.etapes.add(step);
            previousStep = step;

        }
    }


    private void lire() throws Exception {

            this.listes = new ArrayList<Liste>();
            // charger l'input
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            String str = null;
            int ligne = 1;
            while((str = in.readLine()) != null) {

                    int nbelements = Integer.parseInt(str);

                    if (nbelements > 0 && nbelements <= 64) {

                            Liste l = new Liste(nbelements);
                            this.listes.add(l);

                            str = in.readLine(); 
                            ligne ++;
                            String[] elements = str.split(" ");
                            if (elements.length != nbelements) {
                                    throw new Exception("Nombre d'elements trouvés dans la liste incohérent avec le nombre annoncé (ligne: " + ligne + ")");
                            }else{
                                for (int i=0; i<nbelements; i++) {

                                        int t = Integer.parseInt(elements[i]);

                                        if (l.numbers[i] >= 0 || l.numbers[i] < 100)
                                            l.numbers[i] = t;
                                        else
                                            throw new Exception("Les entiers du vecteur doivent etre superieur ou egale à 0 et inferieur à 100 (ligne: " + ligne + ")");
                                }
                                in.readLine(); //lire la ligne vide
                                ligne ++;
                            }
                    } else {
                        throw new Exception("Une liste d'elements doit être de taille superieure ou egale à 1 entre 1 et inferieure oue gale à 64 (ligne: " + ligne + ")");
                    }

                    ligne ++;
            }
    }

    private void ecrire() throws IOException {

            File f = new File("output.txt");
            if (!f.exists()) f.createNewFile();
            PrintWriter output = new PrintWriter(f);

            output.println();
            boolean first = true;
            for (Liste liste : this.listes) {
                    if (first){
                        first = false;
                    }else{
                        output.println();
                    }
                    if (liste.hasSolution) {
                            output.println(liste.etapes.size());

                            this.afficherEtape(output, 0, liste.numbers);

                            int num_etape = 1;
                            for (int[] etape : liste.etapes) {
                                    this.afficherEtape(output, num_etape, etape);
                                    num_etape++;
                            }
                    } else {
                            output.println("-1");
                    }
            }
            output.flush();
            output.close();
    }

    private void afficherEtape(PrintWriter bw, int ne, int[] elements) throws IOException {

            bw.print(ne+" : (");
            boolean first = true;
            for (int element : elements) {
                    if (first){
                        first = false;
                    }else{
                        bw.print(", "); 
                    } 
                    bw.print(element);
            }
            bw.println(")");
    }

    public static void main(String[] args) {

            try {
                    Equilibrer eq = new Equilibrer();
                    eq.lire();
                    eq.resoudre();
                    eq.ecrire();


            } catch (Exception e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                    System.exit(1);
            }
    }

}
