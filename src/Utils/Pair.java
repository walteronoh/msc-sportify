package Utils;

public class Pair<F, S> {
    private F first;
    private S second;

    public Pair(F f, S s) {
        this.first = f;
        this.second = s;
    }

    public F fst() {
        return this.first;
    }
    public void setFirst(F f) {
        this.first = f;
    }

    public S snd() {
        return this.second;
    }
    public void setSecond( S s) {
        this.second = s;
    }

}
