import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
 *
 * @author Jordyn Liegl
 *
 */
public class GlossaryTest {

    /**
     * Tests of createMapAndSequence.
     */

    /**
     * boundary.
     */
    @Test
    public void testCreateMapAndSequence_test1() {
        SimpleReader input = new SimpleReader1L("test1.txt");

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "book");
        Sequence<String> termsActual = new Sequence1L<>();

        Map<String, String> glossaryExpected = new Map1L<>();
        glossaryExpected.add("book", "a printed or written literary work ");
        Map<String, String> glossaryActual = new Map1L<>();

        Glossary.createMapAndSequence(input, termsActual, glossaryActual);
        assertEquals(termsExpected, termsActual);
        assertEquals(glossaryExpected, glossaryActual);

        input.close();
    }

    /**
     * routine.
     */
    @Test
    public void testCreateMapAndSequence_test2() {
        SimpleReader input = new SimpleReader1L("test2.txt");

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "makeup");
        termsExpected.add(1, "lipstick");
        termsExpected.add(2, "mascara");
        termsExpected.add(3, "blush");
        Sequence<String> termsActual = new Sequence1L<>();

        Map<String, String> glossaryExpected = new Map1L<>();
        glossaryExpected.add("makeup",
                "cosmetics (such as lipstick, mascara, and eye shadow) used to color and beautify the face ");
        glossaryExpected.add("lipstick",
                "a waxy solid usually colored cosmetic in stick form for the lips ");
        glossaryExpected.add("mascara",
                "a cosmetic especially for making the eyelashes darker and more prominent ");
        glossaryExpected.add("blush",
                "a cosmetic applied to the face to give a usually pink color or to accent the cheekbones ");
        Map<String, String> glossaryActual = new Map1L<>();

        Glossary.createMapAndSequence(input, termsActual, glossaryActual);
        assertEquals(termsExpected, termsActual);
        assertEquals(glossaryExpected, glossaryActual);

        input.close();
    }

    /**
     * challenging.
     */
    @Test
    public void testCreateMapAndSequence_test3() {
        SimpleReader input = new SimpleReader1L("test3.txt");

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "pneumonoultramicroscopicsilicovolcanoconiosis");
        Sequence<String> termsActual = new Sequence1L<>();

        Map<String, String> glossaryExpected = new Map1L<>();
        glossaryExpected.add("pneumonoultramicroscopicsilicovolcanoconiosis",
                "a pneumoconiosis caused by inhalation of very fine silicate or quartz dust ");
        Map<String, String> glossaryActual = new Map1L<>();

        Glossary.createMapAndSequence(input, termsActual, glossaryActual);
        assertEquals(termsExpected, termsActual);
        assertEquals(glossaryExpected, glossaryActual);

        input.close();
    }

    /**
     * Tests of sequenceAlphabeticalOrder.
     */

    /**
     * boundary.
     */
    @Test
    public void testSequenceAlphabeticalOrder_1Term() {
        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "meaning");

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "meaning");

        Glossary.sequenceAlphabeticalOrder(terms);
        assertEquals(termsExpected, terms);

    }

    /**
     * routine.
     */
    @Test
    public void testSequenceAlphabeticalOrder_4Terms() {
        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "makeup");
        terms.add(1, "lipstick");
        terms.add(2, "mascara");
        terms.add(3, "blush");

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "blush");
        termsExpected.add(1, "lipstick");
        termsExpected.add(2, "makeup");
        termsExpected.add(3, "mascara");

        Glossary.sequenceAlphabeticalOrder(terms);
        assertEquals(termsExpected, terms);
    }

    /**
     * challenging.
     */
    @Test
    public void testSequenceAlphabeticalOrder_TermsWithSimilarLetters() {
        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "stick");
        terms.add(1, "lipstick");
        terms.add(2, "lip");
        terms.add(3, "lips");

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "lip");
        termsExpected.add(1, "lips");
        termsExpected.add(2, "lipstick");
        termsExpected.add(3, "stick");

        Glossary.sequenceAlphabeticalOrder(terms);
        assertEquals(termsExpected, terms);
    }

    /**
     * Tests of generateSeparators.
     */

    /**
     * boundary.
     */
    @Test
    public void testGenerateSeparators_1Char() {
        String separators = " ";
        Set<Character> setSeparators = new Set1L<>();
        String separatorsExpected = " ";
        Set<Character> setSeparatorsExpected = new Set1L<>();
        setSeparatorsExpected.add(' ');

        Glossary.generateSeparators(separators, setSeparators);
        assertEquals(separatorsExpected, separators);
        assertEquals(setSeparatorsExpected, setSeparators);
    }

    /**
     * routine.
     */
    @Test
    public void testGenerateSeparators_MultipleChar() {
        String separators = " \t, ";
        Set<Character> setSeparators = new Set1L<>();
        String separatorsExpected = " \t, ";
        Set<Character> setSeparatorsExpected = new Set1L<>();
        setSeparatorsExpected.add(' ');
        setSeparatorsExpected.add('\t');
        setSeparatorsExpected.add(',');

        Glossary.generateSeparators(separators, setSeparators);
        assertEquals(separatorsExpected, separators);
        assertEquals(setSeparatorsExpected, setSeparators);
    }

    /**
     * challenging.
     */
    @Test
    public void testGenerateSeparators_CharThatAreLettersToo() {
        String separators = "b \ta, c";
        Set<Character> setSeparators = new Set1L<>();
        String separatorsExpected = "b \ta, c";
        Set<Character> setSeparatorsExpected = new Set1L<>();
        setSeparatorsExpected.add('b');
        setSeparatorsExpected.add(' ');
        setSeparatorsExpected.add('\t');
        setSeparatorsExpected.add('a');
        setSeparatorsExpected.add(',');
        setSeparatorsExpected.add('c');

        Glossary.generateSeparators(separators, setSeparators);
        assertEquals(separatorsExpected, separators);
        assertEquals(setSeparatorsExpected, setSeparators);
    }

    /**
     * Tests of outputHeader.
     */
    @Test
    public void testOutputHeader() {
        SimpleWriter output = new SimpleWriter1L("outputTestHeader.html");
        Glossary.outputHeader(output);
        SimpleReader input = new SimpleReader1L("outputTestHeader.html");

        assertEquals(input.nextLine(), "<html>");
        assertEquals(input.nextLine(), "<head>");
        assertEquals(input.nextLine(), "<title>Glossary</title>");
        assertEquals(input.nextLine(), "</head>");
        assertEquals(input.nextLine(), "<h2>Glossary</h2>");
        assertEquals(input.nextLine(), "<body>");
        assertEquals(input.nextLine(), "    <hr>");
        assertEquals(input.nextLine(), "    <h3>Index</h3>");
        assertEquals(input.nextLine(), "    <ul>");

        output.close();
        input.close();
    }

    /**
     * Tests of outputBody.
     */

    /**
     * boundary.
     */
    @Test
    public void testOutputBody_test1() {
        SimpleWriter outputIndex = new SimpleWriter1L("outputTestBody.html");
        Sequence<String> allTerms = new Sequence1L<>();
        allTerms.add(0, "book");
        Map<String, String> glossary = new Map1L<>();
        glossary.add("book", "a printed or written literary work");
        Set<Character> separators = new Set1L<>();
        separators.add(' ');

        Glossary.outputBody(outputIndex, allTerms, glossary, separators);
        SimpleReader inputIndex = new SimpleReader1L("outputTestBody.html");
        assertEquals("        <li><a href=\"book.html\">book</a></li>",
                inputIndex.nextLine());

        inputIndex.close();
        outputIndex.close();
    }

    /**
     * routine.
     */
    @Test
    public void testOutputBody_test2() {
        SimpleWriter outputIndex = new SimpleWriter1L("outputTestBody.html");
        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "blush");
        terms.add(1, "lipstick");
        terms.add(2, "makeup");
        terms.add(3, "mascara");

        Map<String, String> glossary = new Map1L<>();
        glossary.add("makeup",
                "cosmetics (such as lipstick, mascara, and eye shadow) used to color and beautify the face ");
        glossary.add("lipstick",
                "a waxy solid usually colored cosmetic in stick form for the lips ");
        glossary.add("mascara",
                "a cosmetic especially for making the eyelashes darker and more prominent ");
        glossary.add("blush",
                "a cosmetic applied to the face to give a usually pink color or to accent the cheekbones ");

        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('\t');
        separators.add(',');

        Glossary.outputBody(outputIndex, terms, glossary, separators);
        SimpleReader inputIndex = new SimpleReader1L("outputTestBody.html");
        assertEquals("        <li><a href=\"blush.html\">blush</a></li>",
                inputIndex.nextLine());
        assertEquals("        <li><a href=\"lipstick.html\">lipstick</a></li>",
                inputIndex.nextLine());
        assertEquals("        <li><a href=\"makeup.html\">makeup</a></li>",
                inputIndex.nextLine());
        assertEquals("        <li><a href=\"mascara.html\">mascara</a></li>",
                inputIndex.nextLine());

        outputIndex.close();
        inputIndex.close();
    }

    /**
     * challenging.
     */
    @Test
    public void testOutputBody_test3() {
        SimpleWriter outputIndex = new SimpleWriter1L("outputTestBody.html");
        Sequence<String> allTerms = new Sequence1L<>();
        allTerms.add(0, "pneumonoultramicroscopicsilicovolcanoconiosis");
        Map<String, String> glossary = new Map1L<>();
        glossary.add("pneumonoultramicroscopicsilicovolcanoconiosis",
                "a pneumoconiosis caused by inhalation of very fine silicate or quartz dust");
        Set<Character> separators = new Set1L<>();
        separators.add(' ');

        Glossary.outputBody(outputIndex, allTerms, glossary, separators);
        SimpleReader inputIndex = new SimpleReader1L("outputTestBody.html");
        assertEquals(inputIndex.nextLine(),
                "        <li><a href=\"pneumonoultramicroscopicsilicovolcanoconiosis.html\">pneumonoultramicroscopicsilicovolcanoconiosis</a></li>");

        inputIndex.close();
        outputIndex.close();
    }

    /**
     * Tests of termHTMLWriter.
     */

    /**
     * boundary.
     */
    @Test
    public void testTermHTMLWriter_test1() {
        SimpleWriter termWriter = new SimpleWriter1L("testTermWriter.html");

        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "book");

        Map<String, String> glossary = new Map1L<>();
        glossary.add("book", "a printed or written literary work");

        int i = 0;

        Set<Character> separators = new Set1L<>();
        separators.add(' ');

        Glossary.termHTMLWriter(termWriter, terms, glossary, i, separators);
        SimpleReader termReader = new SimpleReader1L("testTermWriter.html");

        assertEquals("<html>", termReader.nextLine());
        assertEquals("<head>", termReader.nextLine());
        assertEquals("<title>book</title>", termReader.nextLine());
        assertEquals("</head>", termReader.nextLine());
        assertEquals("<body>", termReader.nextLine());
        assertEquals(
                "    <h1><b><i><font color =\"red\">book</font></i></b></h1>",
                termReader.nextLine());
        assertEquals("    <p>", termReader.nextLine());
        assertEquals("a printed or written literary work",
                termReader.nextLine());
        assertEquals("    </p>", termReader.nextLine());
        assertEquals("    <hr>", termReader.nextLine());
        assertEquals("    <p>Return to <a href=\" index.html\">index</a></p>",
                termReader.nextLine());
        assertEquals("</body>", termReader.nextLine());
        assertEquals("</html>", termReader.nextLine());

        termWriter.close();
        termReader.close();
    }

    /**
     * routine.
     */
    @Test
    public void testTermHTMLWriter_test2() {
        SimpleWriter termWriter = new SimpleWriter1L("testTermWriter.html");

        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "blush");
        terms.add(1, "lipstick");
        terms.add(2, "makeup");
        terms.add(3, "mascara");

        Map<String, String> glossary = new Map1L<>();
        glossary.add("makeup",
                "cosmetics (such as lipstick, mascara, and eye shadow) used to color and beautify the face ");
        glossary.add("lipstick",
                "a waxy solid usually colored cosmetic in stick form for the lips ");
        glossary.add("mascara",
                "a cosmetic especially for making the eyelashes darker and more prominent ");
        glossary.add("blush",
                "a cosmetic applied to the face to give a usually pink color or to accent the cheekbones ");

        int i = 2;

        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('\t');
        separators.add(',');

        Glossary.termHTMLWriter(termWriter, terms, glossary, i, separators);
        SimpleReader termReader = new SimpleReader1L("testTermWriter.html");

        assertEquals("<html>", termReader.nextLine());
        assertEquals("<head>", termReader.nextLine());
        assertEquals("<title>makeup</title>", termReader.nextLine());
        assertEquals("</head>", termReader.nextLine());
        assertEquals("<body>", termReader.nextLine());
        assertEquals(
                "    <h1><b><i><font color =\"red\">makeup</font></i></b></h1>",
                termReader.nextLine());
        assertEquals("    <p>", termReader.nextLine());
        assertEquals(
                "cosmetics (such as <a href=\"lipstick.html\">lipstick</a>, <a href=\"mascara.html\">mascara</a>, and eye shadow) used to color and beautify the face ",
                termReader.nextLine());
        assertEquals("    </p>", termReader.nextLine());
        assertEquals("    <hr>", termReader.nextLine());
        assertEquals("    <p>Return to <a href=\" index.html\">index</a></p>",
                termReader.nextLine());
        assertEquals("</body>", termReader.nextLine());
        assertEquals("</html>", termReader.nextLine());

        termWriter.close();
        termReader.close();
    }

    /**
     * challenging.
     */
    @Test
    public void testTermHTMLWriter_test3() {
        SimpleWriter termWriter = new SimpleWriter1L("testTermWriter.html");

        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "pneumonoultramicroscopicsilicovolcanoconiosis");

        Map<String, String> glossary = new Map1L<>();
        glossary.add("pneumonoultramicroscopicsilicovolcanoconiosis",
                "a pneumoconiosis caused by inhalation of very fine silicate or quartz dust");

        int i = 0;

        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('\t');
        separators.add(',');

        Glossary.termHTMLWriter(termWriter, terms, glossary, i, separators);
        SimpleReader termReader = new SimpleReader1L("testTermWriter.html");

        assertEquals("<html>", termReader.nextLine());
        assertEquals("<head>", termReader.nextLine());
        assertEquals(
                "<title>pneumonoultramicroscopicsilicovolcanoconiosis</title>",
                termReader.nextLine());
        assertEquals("</head>", termReader.nextLine());
        assertEquals("<body>", termReader.nextLine());
        assertEquals(
                "    <h1><b><i><font color =\"red\">pneumonoultramicroscopicsilicovolcanoconiosis</font></i></b></h1>",
                termReader.nextLine());
        assertEquals("    <p>", termReader.nextLine());
        assertEquals(
                "a pneumoconiosis caused by inhalation of very fine silicate or quartz dust",
                termReader.nextLine());
        assertEquals("    </p>", termReader.nextLine());
        assertEquals("    <hr>", termReader.nextLine());
        assertEquals("    <p>Return to <a href=\" index.html\">index</a></p>",
                termReader.nextLine());
        assertEquals("</body>", termReader.nextLine());
        assertEquals("</html>", termReader.nextLine());

        termWriter.close();
        termReader.close();
    }

    /**
     * Tests of nextWordOrSeparator.
     */

    /**
     * boundary.
     */
    @Test
    public void testNextWordOrSeparator_Hello() {
        String str = " Hello ";
        int i = 0;
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('\t');
        separators.add(',');
        separators.add('(');
        separators.add(')');

        String returnedStr = Glossary.nextWordOrSeparator(str, i, separators);
        String returnedStrExpected = " ";
        assertEquals(returnedStrExpected, returnedStr);
    }

    /**
     * routine.
     */
    @Test
    public void testNextWordOrSeparator_HelloMyNameIsJordyn() {
        String str = "Hello my name is Jordyn";
        int i = 14;
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('\t');
        separators.add(',');
        separators.add('(');
        separators.add(')');

        String returnedStr = Glossary.nextWordOrSeparator(str, i, separators);
        String returnedStrExpected = "is";
        assertEquals(returnedStrExpected, returnedStr);
    }

    /**
     * challenging.
     */
    @Test
    public void testNextWordOrSeparator_ReturnPortionOfWord() {
        String str = "cosmetics (such as lipstick, mascara, and eye shadow) used to color and beautify the face";
        int i = 24;
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('\t');
        separators.add(',');
        separators.add('(');
        separators.add(')');

        String returnedStr = Glossary.nextWordOrSeparator(str, i, separators);
        String returnedStrExpected = "ick";
        assertEquals(returnedStrExpected, returnedStr);
    }

    /**
     * Tests of outputFooter.
     */
    @Test
    public void testOutputFooter() {
        SimpleWriter output = new SimpleWriter1L("outputTestFooter.html");
        Glossary.outputFooter(output);
        SimpleReader input = new SimpleReader1L("outputTestFooter.html");

        assertEquals(input.nextLine(), "    </ul>");
        assertEquals(input.nextLine(), "</html>");

        output.close();
        input.close();
    }

}
