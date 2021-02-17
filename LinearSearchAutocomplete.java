import java.util.*;

public class LinearSearchAutocomplete implements Autocomplete {
    private final List<CharSequence> terms;

    public LinearSearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Your code here
        this.terms.addAll(terms);
        //size = terms.size();
        //for (int i = 0; i < size; i++) {
          //  this.terms.add(terms.get(i));
        //}
        
    }

    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        // TODO: Your code here
        // [alpha, delta, do, cats, dodgy, pilot, dog], allMatches("do") should return [do, dodgy, dog] 
        // terms[i] 
        for(int i = 0; i<terms.size(); i++){
            CharSequence term = terms.get(i);
            if(prefix.length() <= term.length()){
                CharSequence subTerm = term.subSequence(0, prefix.length());
                if(CharSequence.compare(prefix, subTerm) == 0){
                    result.add(term);
                }
            }
        }
        return result;
    }
}
