import java.util.*;

public class BinarySearchAutocomplete implements Autocomplete {
    private final List<CharSequence> terms;

    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Your code here
        this.terms.addAll(terms);
        this.terms.sort(CharSequence::compare);
    }

    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        // TODO: Your code here
        int low = 0;
        int high = terms.size();
        return binarySearch(prefix, result, low, high);
    }
    public List<CharSequence> binarySearch(CharSequence prefix, List<CharSequence> result, int low, int high){
        if(low > high){
            return result;
        }
        int mid = low + (high-low)/2;
        CharSequence term = terms.get(mid);
        if(prefix.length() <= term.length()){
            term = term.subSequence(0, prefix.length());
        }        
        if(CharSequence.compare(prefix, term) > 0){    //prefix > term
            return binarySearch(prefix, result, mid + 1, high);
        }else if(CharSequence.compare(prefix, term) < 0){      //prefix > term
            return binarySearch(prefix, result, low, mid - 1);
        }else{
            result.add(terms.get(mid));
            terms.remove(mid);
            return binarySearch(prefix, result, low, high);
        }        
    }
}
