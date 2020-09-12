package com.eny.taskComposite.task.service;

import com.eny.taskComposite.task.composite.CompositeBasis;
import com.eny.taskComposite.task.composite.type.CompositeType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParsingService {
    private static final Logger logger = LogManager.getLogger();
    private static final CompositeType EXPECTED_SENTENCE_TYPE = CompositeType.TEXT;
    private static final CompositeType EXPECTED_LEXEME_TYPE = CompositeType.LEXEME;

    private void incorrectTypeLog(CompositeType expected, CompositeType current)
    {
        logger.log(Level.ERROR, String.format("Incorrect component type, expected: {%s}, argument type: {%s}",
                expected.name(), current.name()));
    }
    
    public List<CompositeBasis> sortParagraphsBySentencesCount(CompositeBasis component) {
        List<CompositeBasis> result;
        
        if (component.getCompositeType() == EXPECTED_SENTENCE_TYPE) {
            result = component.getChilds().stream().sorted(Comparator.comparingInt(count ->
                    count.getChilds().size())).collect(Collectors.toList());
        } else {
            result = Collections.EMPTY_LIST;
            incorrectTypeLog(EXPECTED_SENTENCE_TYPE, component.getCompositeType());
        }

        return result;
    }

    public List<CompositeBasis> sortSentencesByLexemeLength(CompositeBasis component) {//Lexeme == Word?? idk
        List<CompositeBasis> result;

        if (component.getCompositeType() == EXPECTED_SENTENCE_TYPE) {
            result = new ArrayList<>(component.getChilds());
            result.sort((a1, a2) -> {
                int maxLexemeSize1 = a1.getChilds().stream().max(Comparator.comparing(
                        lexemeComponent -> lexemeComponent.toString().length()))
                        .get().toString().length();
                int maxLexemeSize2 = a2.getChilds().stream().max(Comparator.comparing(
                        lexemeComponent -> lexemeComponent.toString().length()))
                        .get().toString().length();
                return maxLexemeSize1 - maxLexemeSize2;
            });
        } else {
            result = Collections.EMPTY_LIST;
            incorrectTypeLog(EXPECTED_SENTENCE_TYPE, component.getCompositeType());
        }

        return result;
    }

    public List<CompositeBasis> sortLexemesByNumberCharacters(CompositeBasis component, char searchingCharacter) {
        List<CompositeBasis> result;

        if (component.getCompositeType() == EXPECTED_SENTENCE_TYPE) {
            result = new ArrayList<>(component.getChilds());
            result.sort((b1, b2) -> {
                int comparingResult;
                long characterCount1 = b1.getChilds().stream().filter(ch ->
                        ch.toString().equals(Character.toString(searchingCharacter))).count();
                long characterCount2 = b2.getChilds().stream().filter(ch ->
                        ch.toString().equals(Character.toString(searchingCharacter))).count();

                comparingResult = (int) (characterCount1 - characterCount2);
                if (comparingResult == 0) {
                    comparingResult = b1.toString().compareToIgnoreCase(b2.toString());
                }

                return comparingResult;
            });
        } else {
            result = Collections.EMPTY_LIST;
            incorrectTypeLog(EXPECTED_SENTENCE_TYPE, component.getCompositeType());
        }

        return result;
    }
}
