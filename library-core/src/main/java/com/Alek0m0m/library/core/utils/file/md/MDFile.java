package com.Alek0m0m.library.core.utils.file.md;

import com.Alek0m0m.library.core.utils.word.WordHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.Alek0m0m.library.core.utils.file.md.MDFile.Syntax.*;
import static com.Alek0m0m.library.core.utils.file.md.MDFile.Syntax.TOC.*;


public class MDFile {

    private Content content;
    private String exportPath;

    public MDFile(String exportPath) {
        this.content = new Content();
        this.exportPath = exportPath;
    }

    public Content getContent() {
        return content;
    }

    public void export() {
        // TODO export to file, utilize file.io.FileIO
    }

    @Override
    public String toString() {
        return this.content+"";
    }


    // ----------------- static Classes of MDFile -----------------

    public static class Content {

        public static final String SECTION_DIVIDER = "\n" + "---" + "\n";
        private String title;
        private List<String> superRelations;
        private String TOC;
        private Map<String, List<String>> headerAndBulletPoints;
        private String sections;


        // innate relations throughout the file
        private List<String> relations; // throughout file at end, any relations will get appended [[ before and ]] after
        private String exportPath;
        private List<Object> instanceVariables; // gets initialized when needed in getNonNullInstanceVariables()

        public Content() {
            this.title = "";
            this.superRelations = new ArrayList<>();
            this.TOC = "";
            this.relations = new ArrayList<>();
            this.exportPath = "";
        }

        // ----------------- operations -----------------

        public String createTOC(Map<String, List<String>> headerAndBulletPoints) {
            this.headerAndBulletPoints = headerAndBulletPoints;
            StringBuilder TOCBuilder = new StringBuilder(TOC_START).append("\n");

            for (int i = 0; i < headerAndBulletPoints.size(); i++) {
                String header = headerAndBulletPoints.keySet().toArray()[i].toString();

                TOCBuilder.append(i+1+".").append(getLine(header));

                List<String> bulletPoints = headerAndBulletPoints.get(header);
                for (String bulletPoint : bulletPoints) {
                    TOCBuilder.append(TOC_CONTENT_BULLET_INDENT+"-"+getLine(bulletPoint));
                }
            }
            TOCBuilder.append(SECTION_DIVIDER);

            setTOC(TOCBuilder.toString());
            return TOC;
        }

        public void createSection(String header, String content) {
            // TODO
            StringBuilder section = new StringBuilder("## "+header+"\n\n");


            section.append(SECTION_DIVIDER);
            addSection(section.toString());
        }



        // ----------------- Getters and Setters -----------------

        public void setTOC(String toc) {
            this.TOC = toc;
        }

        public void addSection(String section) {
            this.sections += section;
        }


        // getRelations
        public List<String> getRelations() {
            return relations;
        }

        public void setRelations(List<String> relations) {
            this.relations = relations;

            this.TOC = processText(TOC);
            this.sections = processText(sections);
        }




        // ----------------- helper methods -----------------

            // relations
            private String processText(String text) {
                if (text == null) {
                    return "";
                }

                String[] words = text.split(" "); // split by space, if there's a word that has a comma or period, it will be included in the word, so before appending relation syntax, do it between word and comma/period
                for (int i = 0; i < words.length; i++) {
                    String wordRough = words[i];
                    String word = WordHandler.cleanWord(wordRough);

                    if (relations.contains(word)) {
                        words[i] = WordHandler.appendAroundWord(wordRough, RELATION_START, RELATION_END);
                    }
                }
                return String.join(" ", words);
            }

            // TOC
            private String getLine(String text) {
                return TOC_CONTENT_START + text + TOC_CONTENT_MIDDLE + convertToSnakeCasePlus(text) + TOC_CONTENT_END + "\n";
            }

            // toString
            private String getToString() {
                List<String> nonNullInstanceVariables = getNonNullInstanceVariables();
                String text = "";
                for (String nonNullInstanceVariable : nonNullInstanceVariables) {
                    text += nonNullInstanceVariable + "\n";
                }
                return text;
            }

            private List<String> getNonNullInstanceVariables() {
                List<String> nonNullInstanceVariables = new ArrayList<>();
                instanceVariables = new ArrayList<>(List.of(title, superRelations, TOC, sections, relations, exportPath));
                for (Object instanceVariable : instanceVariables) {
                    if (instanceVariable instanceof String && !((String) instanceVariable).isEmpty()) {
                        nonNullInstanceVariables.add(instanceVariable.toString());
                    } else if (instanceVariable instanceof List && !((List<?>) instanceVariable).isEmpty()) {
                        nonNullInstanceVariables.add(instanceVariable.toString());
                    }
                }
                return nonNullInstanceVariables;
            }

        // ----------------- toString -----------------
        @Override
        public String toString() {
            return getToString();
        }

    }

    public static class Syntax {

        public static String RELATION = "[[x]]";
        public static final String RELATION_START = "[[";
        public static final String RELATION_END = "]]";


        public static String convertToSnakeCasePlus(String text) {
            return text.toLowerCase().replace(" ", "-");
        }

        static class Relations {
            // Relations syntax
            public static final String RELATION_START = "[[";
            public static final String RELATION_END = "]]";
        }

        static class TOC {
            // TOC syntax
            public static final String TOC_START = "## Table of Contents";
            // TOC content syntax (sub relations)
            private static String TOC_CONTENT_HEADER = "nr [[x]](#x-in-snake-case)";
            public static final String TOC_CONTENT_START = " [";
            public static final String TOC_CONTENT_MIDDLE = "](#";
            public static final String TOC_CONTENT_END = ")";
            public static final String TOC_CONTENT_BULLET_INDENT = "   ";
        }
    }

}
