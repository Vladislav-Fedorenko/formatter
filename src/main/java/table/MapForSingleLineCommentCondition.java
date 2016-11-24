package table;

import handler.Handler;
import handler.defaultCondition.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for storage table character and relevant handlers
 * for code in single line comment condition.
 */
public class MapForSingleLineCommentCondition {
    /**
     * Map character and relevant handlers.
     */
    private Map<Character, Handler> map;
    /**
     * Constructor.
     * In map put character(key) and its handler(value)
     */
    public MapForSingleLineCommentCondition() {
        map = new HashMap<Character, Handler>();
        map.put(null, new DefaultHandler());
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
