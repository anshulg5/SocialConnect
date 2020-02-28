package org.example.configRule;

import org.example.configRule.primitiveType.StringNode;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public enum Operator implements OperatorFactory {

    AND{
        public Node getInstance(Object value, Map<String,Object> symbolTable) {
            return new AndNode((List<Map<Operator, Object>>) value,symbolTable);
        }
    },
    OR ,
    NOT,
    EQ {
        @Override
        public Node getInstance(Object value, Map<String, Object> symbolTable) {
            return new EqualsNode((List<Map<Operator, Object>>) value,symbolTable);
        }
    },
    GT,
    LT,

    PATH{
        @Override
        public Node getInstance(Object value, Map<String, Object> symbolTable) {
            return new PathNode((Map<Operator, Object>) value,symbolTable);
        }
    },

    STR{
        @Override
        public Node getInstance(Object value, Map<String, Object> symbolTable) {
            return new StringNode((String) value);
        }
    },
    BOOL,
    INT,

    STRLST{
        @Override
        public Node getInstance(Object value, Map<String, Object> symbolTable) {
            return new CollectionStringNode((Collection<String>) value);
        }
    },

    DEF,
    LET,
    VAR,
    NAME,
    VALUE,
    BODY,
    FREAD,
    GETMSG;



    /*

    VariableNode
    Allmatch
    AnyMatch
    NoneMatch

    */

    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return null;
    }

}
