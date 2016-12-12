
public class ReverseWords {
	//-------------------  Solution 1 ------------------------//
	// use String's build-in methods
    public String reverseWords(String s) {
        // validate input
        if (s == null) {
            return s;
        }
        
        // split strings (first replace, then trim!)
        String[] words = s.replaceAll("\\s+", " ").trim().split(" ");
        
        // concatenate reversed string
        int numWords = words.length;
        if (numWords == 0) {
            return "";
        }
        StringBuilder reversedStr = new StringBuilder();
        reversedStr.append(words[numWords - 1]);
        for (int i = numWords - 2; i >= 0; i--) {
            reversedStr.append(" " + words[i]);
        }
        return reversedStr.toString();
    }
    
	//-------------------  Solution 2 ------------------------//
	// another way of implementing solution 1
    public String reverseWords2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; --i) {
            if (!words[i].equals("")) {
                sb.append(words[i]).append(" ");
            }
        }
        return sb.toString().trim();         //remove the last " "
    }
    
    
	//-------------------  Solution 3 ------------------------//
	// split the string by hand (same algorithm as above 2)
    public String reverseWords3(String s) {
        // validate input
        if (s == null || s.length() == 0) {
            return "";
        }

        // iterate backwards and append words one-by-one
        StringBuilder reversedStr = new StringBuilder();
        StringBuilder curWord = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {   // skip all spaces
                continue;
                
            } else {                    // for non-spaces
                // starter of a new word
                if (i == s.length() - 1 || s.charAt(i + 1) == ' ') {
                    if (curWord.length() > 0 ) {
                        reversedStr.append(curWord.reverse().toString() + " ");
                    }
                    curWord = new StringBuilder();
                }
                curWord.append(s.charAt(i));
            }
        }
        // don't forget to add the last word!
        reversedStr.append(curWord.reverse().toString()); 
        return reversedStr.toString();
    }
}


// ERROR : for (i = n-1; i >= 0, i++) -> i--;
