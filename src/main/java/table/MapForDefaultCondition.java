package table;

import handler.Handler;
import handler.defaultCondition.CharOfNewLineHandler;
import handler.defaultCondition.CloseBracketHandler;
import handler.defaultCondition.OpenBracketHandler;
import handler.defaultCondition.SemicolonHandler;
import handler.defaultCondition.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for storage table character and relevant handlers
 * for code in default condition.
 */
public class MapForDefaultCondition {
    /**
     * Map character and relevant handlers.
     */
    private Map<Character, Handler> map;
    /**
     * Constructor.
     * In map put character(key) and its handler(value)
     */
    public MapForDefaultCondition() {
        map = new HashMap<Character, Handler>();
        map.put('\n', new CharOfNewLineHandler());
        map.put('}', new CloseBracketHandler());
        map.put('{', new OpenBracketHandler());
        map.put(';', new SemicolonHandler());
    }
    /**
     * call method map.get(Key k)
     * @param c input character
     * @return handler for input character
     */
    public Handler getHandler(final char c) {
        Handler handler = map.get(c);
        if (handler == null) {
            return new DefaultHandler();
        }
        return handler;
    }
}
