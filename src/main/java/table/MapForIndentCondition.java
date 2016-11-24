package table;

import handler.Handler;
import handler.defaultCondition.CloseBracketHandler;
import handler.indentCondition.IndentHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for storage table character and relevant handlers
 * for indent condition..
 */
public class MapForIndentCondition {
    /**
     * Map character and relevant handlers.
     */
    private Map<Character, Handler> map;
    /**
     * Constructor.
     * In map put character(key) and its handler(value)
     */
    public MapForIndentCondition() {
        map = new HashMap<Character, Handler>();
        map.put('}', new CloseBracketHandler());
    }
    /**
     * call method map.get(Key k)
     * @param c input character
     * @return handler for input character
     */
    public Handler getHandler(final char c) {
        Handler handler = map.get(c);
        if (handler == null) {
            return new IndentHandler();
        }
        return handler;
    }
}
