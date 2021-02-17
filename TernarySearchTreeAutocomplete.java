import java.util.*;

public class TernarySearchTreeAutocomplete implements Autocomplete {
    private Node overallRoot;
    private final List<CharSequence> terms;

    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
        this.terms = new ArrayList<>();
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Your code here
        this.terms.addAll(terms);
        if (terms.isEmpty()) {
            throw new NullPointerException("calls addAll() with null terms");
        }
        List<CharSequence> term = new ArrayList<CharSequence>();
        term.addAll(terms);
        for(int i = 0; i<term.size(); i++){
            overallRoot = addAll(overallRoot, term.get(i), 0);
        }      
    }

    private Node addAll(Node overallRoot, CharSequence terms, int d){
        //terms = [alpha, delta, do, cats, dodgy, pilot, dog]
        char term = terms.charAt(d);
        if(overallRoot == null){
            overallRoot = new Node(term);
        }    
        if(term > overallRoot.data){
            overallRoot.right = addAll(overallRoot.right, terms, d);
        }else if(term < overallRoot.data){
            overallRoot.left = addAll(overallRoot.left, terms, d);
        }else{
            if(d < terms.length() - 1){
                overallRoot.mid = addAll(overallRoot.mid, terms, d+1);
            }else{
                overallRoot.isTerm = true;
            }
        }
        return overallRoot;
    }



    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        // TODO: Your code here
        Node x = get(overallRoot, prefix, 0);
        collect(x, new StringBuilder(prefix), result, prefix);
        CharSequence first = result.get(0);
        boolean contain = false;
        for(int i = 0; i < terms.size();i++) {
        	if (CharSequence.compare(first, terms.get(i)) == 0) {
        		contain = true;
        	}
        }
        if(!contain) {
        	result.remove(0);
        }
        return result;
    }


    private Node get(Node x, CharSequence newTerms, int d){
        if(x == null){
            return null;
        }
        char term = newTerms.charAt(d);
        if(term > x.data){
            x.right = addAll(x.right, newTerms, d);
        }else if(term < x.data){
            x.left = addAll(x.left, newTerms, d);
        }else if(d < newTerms.length() - 1){
            return addAll(x.mid, newTerms, d+1);
        }else{
            if(d == newTerms.length() - 1){
                return x;
            }else{
                return addAll(x.mid, newTerms, d+1);
            }
        }
        return x;
    }  
     
    private void collect(Node x, StringBuilder prefix, List<CharSequence> result, CharSequence str){
        if(x == null){
            return;
        }
        collect(x.left, prefix, result, str);
        if(x.isTerm){
            CharSequence temp = prefix + ""+x.data;
            temp = temp.subSequence(str.length(), temp.length());
            CharSequence pre = prefix + ""+x.data;
            pre = pre.subSequence(0, str.length());
            CharSequence tempCompare = temp;
            if(str.length() <= tempCompare.length()){
                tempCompare = tempCompare.subSequence(0, str.length());
            }
            if(CharSequence.compare(pre, tempCompare) == 0){
                result.add(temp);
            }
        }
        prefix.append(x.data);
        collect(x.mid, prefix, result, str);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, result, str);
    }

    private static class Node {
        public char data;
        public boolean isTerm;
        public Node left, mid, right;

        public Node(char data) {
            this.data = data;
            this.isTerm = isTerm;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
