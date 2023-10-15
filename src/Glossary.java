import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Creating a glossary that the user can click on hyperlinks to find the
 * definition.
 *
 * @author Jordyn Liegl
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Parsing through the input file and assigning the terms to the sequence
     * and the keys in the map and the corresponding definitions to the values
     * in the map.
     *
     * @param inputFile
     *            the input stream of the file entered by the user
     * @param allTerms
     *            the sequence of the terms
     * @param glossary
     *            the map of terms and their definitions
     * @requires the inputFile to be open
     * @ensures glossary's keys will be the terms from the inputFile, glossary's
     *          values will be the term's definition from inputFile, and
     *          allTerm's elements are the same as the glossary's keys
     * @replaces allTerms and glossary
     * @restores inputFile
     */
    public static void createMapAndSequence(SimpleReader inputFile,
            Sequence<String> allTerms, Map<String, String> glossary) {

        assert inputFile != null : "Violation of: out is not null";
        assert inputFile.isOpen() : "Violation of: out.is_open";

        int i = 0;
        String term = "";
        String finalDefinition = "";

        /*
         * loop until the end of the text file
         */
        while (!inputFile.atEOS()) {
            /*
             * first line will be the term
             */
            term = inputFile.nextLine();

            /*
             * update the sequence of just the terms
             */
            allTerms.add(i, term);
            i++;

            /*
             * the following line is the definition
             */
            String definition = inputFile.nextLine();
            finalDefinition = definition;

            /*
             * if the definition is more than one line, loop until there is
             * whitespace
             */
            while (!definition.equals("")) {
                definition = inputFile.nextLine();
                finalDefinition = finalDefinition + " " + definition;
            }

            /*
             * update the map
             */
            glossary.add(term, finalDefinition);
        }

    }

    /**
     * Updating the sequence to have the terms be in alphabetical order.
     *
     * @param allTerms
     *            the sequence of terms
     * @ensures allTerms to be in alphabetical order
     * @updates allTerms
     */
    public static void sequenceAlphabeticalOrder(Sequence<String> allTerms) {
        /*
         * loop and compare two consecutive elements and switch if one comes
         * before the other alphabetically
         */
        for (int first = 0; first < allTerms.length(); first++) {
            for (int second = first + 1; second < allTerms.length(); second++) {
                if ((allTerms.entry(first))
                        .compareTo(allTerms.entry(second)) > 0) {
                    String temp = allTerms.entry(first);
                    allTerms.replaceEntry(first, allTerms.entry(second));
                    allTerms.replaceEntry(second, temp);
                }
            }
        }
    }

    /**
     * Generates the set of characters in the string of separators into the set
     * of separators as characters.
     *
     * @param separatorStr
     *            the string consisting of separators
     * @param charSeparatorSet
     *            the set that consists of the separators as characters
     * @replaces charSeparatorSet
     * @restores separatorStr
     * @ensures charSeparatorSet = entries(separatorStr)
     */
    public static void generateSeparators(String separatorStr,
            Set<Character> charSeparatorSet) {
        assert separatorStr != null : "Violation of: str is not null";
        assert charSeparatorSet != null : "Violation of: charSet is not null";

        /*
         * creates a set of values that are considered separators from the
         * entered string
         */
        for (int i = 0; i < separatorStr.length(); i++) {
            char j = separatorStr.charAt(i);
            if (!charSeparatorSet.contains(j)) {
                charSeparatorSet.add(j);
            }
        }

    }

    /**
     * Outputs the "opening" tags in the output HTML file.
     *
     * @param indexWriter
     *            the output stream to index.html
     * @updates indexWriter.content
     * @requires indexWriter.is_open
     * @ensures indexWriter.content = #indexWriter.content * [the HTML "opening"
     *          tags]
     * @restores allTerms
     */
    public static void outputHeader(SimpleWriter indexWriter) {
        assert indexWriter != null : "Violation of: out is not null";
        assert indexWriter.isOpen() : "Violation of: out.is_open";

        /*
         * print the header that is acceptable for HTML standards
         */
        indexWriter.println("<html>");
        indexWriter.println("<head>");
        indexWriter.println("<title>Glossary</title>");
        indexWriter.println("</head>");
        indexWriter.println("<h2>Glossary</h2>");
        indexWriter.println("<body>");
        indexWriter.println("    <hr>");
        indexWriter.println("    <h3>Index</h3>");
        indexWriter.println("    <ul>");

    }

    /**
     * Adding the hyperlink of each individual term's page to the top output
     * page.
     *
     * @param indexWriter
     *            the output stream to index.html
     * @param allTerms
     *            the sequence of the terms in alphabetical order
     * @param glossary
     *            the map of the terms and their corresponding definitions
     * @param separators
     *            the set of characters that separate words in a sentence
     * @requires indexWriter.is_open
     * @ensures indexWriter.content = #indexWriter.content * [links to the
     *          term's HTML pages]
     * @restores allTerms, glossary, and separators
     * @updates indexWriter.contents
     */
    public static void outputBody(SimpleWriter indexWriter,
            Sequence<String> allTerms, Map<String, String> glossary,
            Set<Character> separators) {
        assert indexWriter != null : "Violation of: out is not null";
        assert indexWriter.isOpen() : "Violation of: out.is_open";

        /*
         * loop through the sequence's elements to make a bullet point for each
         * term
         */
        for (int i = 0; i < allTerms.length(); i++) {

            indexWriter.println("        <li><a href=\"" + allTerms.entry(i)
                    + ".html\">" + allTerms.entry(i) + "</a></li>");

            /*
             * create a new HTML page to write each term and it's definition to
             * by calling the termHTMLWriter method
             */
            SimpleWriter termWriter = new SimpleWriter1L(
                    allTerms.entry(i) + ".html");
            termHTMLWriter(termWriter, allTerms, glossary, i, separators);

            /*
             * close the termWriter because this is the only method it is used
             * in
             */
            termWriter.close();

        }

    }

    /**
     * Creating and outputting each individual term's HTML page.
     *
     * @param termWriter
     *            the output stream to each term's HTML
     * @param allTerms
     *            the sequence of the terms in alphabetical order
     * @param glossary
     *            the map of the terms and their corresponding definitions
     * @param i
     *            the index of the element currently used in allTerms
     * @param separators
     *            the set of characters that separate words in a sentence
     * @requires termWriter.is_open
     * @ensures termWriter.content = #termWriter.content * [HTML format of the
     *          term with their definition]
     * @updates termWriter
     * @restores allTerms, glossary, i, separators
     */
    public static void termHTMLWriter(SimpleWriter termWriter,
            Sequence<String> allTerms, Map<String, String> glossary, int i,
            Set<Character> separators) {
        /*
         * print header
         */
        termWriter.println("<html>");
        termWriter.println("<head>");
        termWriter.println("<title>" + allTerms.entry(i) + "</title>");
        termWriter.println("</head>");
        termWriter.println("<body>");
        termWriter.println("    <h1><b><i><font color =\"red\">"
                + allTerms.entry(i) + "</font></i></b></h1>");

        /*
         * loop through each word in the definition calling the
         * nextWordOrSeparator method to input each word into the page
         */
        int j = 0;
        String wordInDef = "";
        termWriter.println("    <p>");
        String definition = glossary.value(allTerms.entry(i));
        while (j < definition.length()) {
            wordInDef = nextWordOrSeparator(definition, j, separators);

            /*
             * if a word in the definition is also another term, create a
             * hyperlink
             */
            if (glossary.hasKey(wordInDef)) {
                termWriter.print("<a href=\"" + wordInDef + ".html\">"
                        + wordInDef + "</a>");
            } else {
                termWriter.print(wordInDef);
            }

            /*
             * increase the position to the end of each word in the definition
             */
            j += wordInDef.length();
        }
        termWriter.println("\n    </p>");
        termWriter.println("    <hr>");

        /*
         * create a link to return back to the index page
         */
        termWriter.println(
                "    <p>Return to <a href=\" index.html\">index</a></p>");
        termWriter.println("</body>");
        termWriter.println("</html>");
    }

    /**
     * Returns the first "word" (maximal length string of characters not in the
     * set of separators) or "separator string" (maximal length string of
     * characters in the set of separators) in the string definition starting at
     * the int position.
     *
     * @param definition
     *            the string from which to get the word or separator string
     * @param position
     *            the starting index
     * @param separators
     *            the set of separator characters
     * @return the first word or separator string found in definition starting
     *         at index position
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   definition[position, position + |nextWordOrSeparator|)  and
     * if entries(definition[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |definition|  or
     *    entries(definition[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |definition|  or
     *    entries(definition[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String definition, int position,
            Set<Character> separators) {
        assert definition != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < definition
                .length() : "Violation of: position < |text|";

        char ch1 = definition.charAt(position);
        int i = position;
        String str = "";

        /*
         * check if the character at the position is a separator character or a
         * character used for a word
         */
        if (separators.contains(ch1)) {
            /*
             * loop through the string until it is no longer a separator
             * character
             */
            while (i < definition.length()
                    && separators.contains(definition.charAt(i))) {
                ch1 = definition.charAt(i);
                str = str + ch1;
                i++;
            }
        } else {
            /*
             * loop through the string until it is a separator character
             */
            while (i < definition.length()
                    && !separators.contains(definition.charAt(i))) {
                ch1 = definition.charAt(i);
                str = str + ch1;
                i++;
            }
        }
        return str;
    }

    /**
     * Outputs the "closing" tags in the output HTML file.
     *
     * @param indexWriter
     *            the output stream to index.html
     * @updates indexWriter.contents
     * @requires indexWriter.is_open
     * @ensures indexWriter.content = #indexWriter.content * [the HTML "closing"
     *          tags]
     */
    public static void outputFooter(SimpleWriter indexWriter) {
        assert indexWriter != null : "Violation of: out is not null";
        assert indexWriter.isOpen() : "Violation of: out.is_open";

        /*
         * print the footer
         */
        indexWriter.println("    </ul>");
        indexWriter.println("</html>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * initialize the simple reader and writer
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * prompt user to enter their input file of terms and definitions
         */
        out.println("Enter an input file");
        SimpleReader inputFile = new SimpleReader1L(in.nextLine());

        /*
         * create an empty map and sequence
         */
        Map<String, String> glossary = new Map1L<>();
        Sequence<String> allTerms = new Sequence1L<>();

        /*
         * call createMapAndSequence to go through the input
         */
        createMapAndSequence(inputFile, allTerms, glossary);

        /*
         * call sequenceAlphabeticalOrder to organize the sequence of terms
         */
        sequenceAlphabeticalOrder(allTerms);

        /*
         * create a set of separators for the nextWordOrSeparator method
         */
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        generateSeparators(separatorStr, separatorSet);

        /*
         * call the output header, body and footer methods to format the results
         * in an HTML
         */
        SimpleWriter indexWriter = new SimpleWriter1L("index.html");
        outputHeader(indexWriter);
        outputBody(indexWriter, allTerms, glossary, separatorSet);
        outputFooter(indexWriter);

        /*
         * close the simple readers and writers
         */
        in.close();
        out.close();
        inputFile.close();
        indexWriter.close();
    }

}
