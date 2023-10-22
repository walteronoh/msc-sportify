
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

    public S snd() {
        return this.second;
    }

}
