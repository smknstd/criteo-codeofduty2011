import java.util.*;

public class Liste {

    public int size;
    public int[] numbers;
    public int total;
    public int target;

    public boolean hasSolution;
    public Collection<int[]> etapes;

    public Liste(int size) {
        this.size = size;
        this.numbers = new int[size];
        this.hasSolution = false;
        this.etapes = new LinkedList<int[]>();
    }

    public void somme() {

        this.total = 0;
        for (int c : this.numbers) {
            this.total += c;
        }
    }

    public void verif() {

        this.hasSolution = this.total % this.size == 0;
    }

    public void calculObjectif(){

        this.target = this.total / this.size;
    }

}
