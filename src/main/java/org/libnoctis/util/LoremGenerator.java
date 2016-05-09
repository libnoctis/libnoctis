/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.
 *
 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.util;

import java.util.Random;

/**
 * The Lorem Generator
 *
 * <p>
 *     Can generate lorem ipsum.
 * </p>
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoremGenerator
{

    protected static final String standard = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private static final String[] words = {"a", "ac", "accumsan", "ad", "adipiscing", "aenean", "aliquam", "aliquet", "amet", "ante", "aptent", "arcu", "at", "auctor", "augue", "bibendum", "blandit", "class", "commodo", "condimentum", "congue", "consectetur", "consequat", "conubia", "convallis", "cras", "cubilia", "cum", "curabitur", "curae", "cursus", "dapibus", "diam", "dictum", "dictumst", "dignissim", "dis", "dolor", "donec", "dui", "duis", "egestas", "eget", "eleifend", "elementum", "elit", "enim", "erat", "eros", "est", "et", "etiam", "eu", "euismod", "facilisi", "facilisis", "fames", "faucibus", "felis", "fermentum", "feugiat", "fringilla", "fusce", "gravida", "habitant", "habitasse", "hac", "hendrerit", "himenaeos", "iaculis", "id", "imperdiet", "in", "inceptos", "integer", "interdum", "ipsum", "justo", "lacinia", "lacus", "laoreet", "lectus", "leo", "libero", "ligula", "litora", "lobortis", "lorem", "luctus", "maecenas", "magna", "magnis", "malesuada", "massa", "mattis", "mauris", "metus", "mi", "molestie", "mollis", "montes", "morbi", "mus", "nam", "nascetur", "natoque", "nec", "neque", "netus", "nibh", "nisi", "nisl", "non", "nostra", "nulla", "nullam", "nunc", "odio", "orci", "ornare", "parturient", "pellentesque", "penatibus", "per", "pharetra", "phasellus", "placerat", "platea", "porta", "porttitor", "posuere", "potenti", "praesent", "pretium", "primis", "proin", "pulvinar", "purus", "quam", "quis", "quisque", "rhoncus", "ridiculus", "risus", "rutrum", "sagittis", "sapien", "scelerisque", "sed", "sem", "semper", "senectus", "sit", "sociis", "sociosqu", "sodales", "sollicitudin", "suscipit", "suspendisse", "taciti", "tellus", "tempor", "tempus", "tincidunt", "torquent", "tortor", "tristique", "turpis", "ullamcorper", "ultrices", "ultricies", "urna", "ut", "varius", "vehicula", "vel", "velit", "venenatis", "vestibulum", "vitae", "vivamus", "viverra", "volutpat", "vulputate"};
    private static final String[] punctuation = {".", "?"};
    private static final String endLine = System.getProperty("line.separator");
    private static Random random = new Random();

    public static String paragraph()
    {
        return paragraph(false);
    }

    /**
     * Get a paragraph
     *
     * @useStandard - get the standard Lorem Ipsum paragraph?
     */
    public static String paragraph(boolean useStandard)
    {
        return useStandard ? standard : sentences(random.nextInt(3) + 2);
    }

    public static String paragraphs(int count)
    {
        return paragraphs(count, false);
    }

    /**
     * Get multiple paragraphs
     *
     * @param count
     *        - the number of paragraphs
     * @useStandard - begin with the standard Lorem Ipsum paragraph?
     */
    public static String paragraphs(int count, boolean useStandard)
    {
        StringBuilder s = new StringBuilder();
        while (count-- > 0)
        {
            s.append(paragraph(useStandard)).append(endLine).append(endLine);
            useStandard = false;
        }
        return s.toString().trim();
    }

    /**
     * Get a random punctuation mark
     */
    public static String randomPunctuation()
    {
        return punctuation[random.nextInt(punctuation.length - 1)];
    }

    /**
     * Get a random word
     */
    public static String randomWord()
    {
        return words[random.nextInt(words.length - 1)];
    }

    /**
     * Get a sentence
     */
    public static String sentence()
    {
        // first word
        String w = randomWord();
        StringBuilder s = new StringBuilder(w.substring(0, 1).toUpperCase()).append(w.substring(1)).append(" ");
        // commas?
        if (random.nextBoolean())
        {
            int r = random.nextInt(3) + 1;
            for (int i = 0; i < r; i++)
            {
                s.append(sentenceFragment()).append(", ");
            }
        }
        // last fragment + punctuation
        return s.append(sentenceFragment()).append(randomPunctuation()).toString();
    }

    /**
     * Get a sentence fragment
     */
    public static String sentenceFragment()
    {
        return words(random.nextInt(10) + 3, true);
    }

    /**
     * Get multiple sentences
     *
     * @param count
     *        - the number of sentences
     */
    public static String sentences(int count)
    {
        StringBuilder s = new StringBuilder();
        while (count-- > 0)
        {
            s.append(sentence()).append("  ");
        }
        return s.toString().trim();
    }

    /**
     * Get a string of words
     *
     * @param count
     *        - the number of words to fetch
     */
    public static String words(int count, boolean cap)
    {
        int start = count;
        StringBuilder s = new StringBuilder();
        while (count-- > 0)
        {
            String word = randomWord();
            if (count == start - 1)
            {
                word = new String(new char[] {word.charAt(0)}).toUpperCase() + word.substring(1);
            }

            s.append(word).append(" ");
        }
        return s.toString().trim();
    }
}