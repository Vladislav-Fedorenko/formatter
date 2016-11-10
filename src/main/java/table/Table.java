package table;

import handler.Handler;
import handler.inheritors.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for storage table character and relevant handlers.
 */
public class Table {
    /**
     * Map character and relevant handlers.
     */
    private Map<Character, Handler> map;

    /**
     * Constructor.
     * In map put character(key) and its handler(value)
     */
    public Table() {
        map = new HashMap<Character, Handler>();
        map.put('\n', new CharOfNewLineHandler());
        map.put('}', new CloseBracketHandler());
        map.put('\"', new DoubleQuoteHandler());
        map.put('{', new OpenBracketHandler());
        map.put(';', new SemicolonHandler());
        map.put('\'', new SingleQuoteHandler());
        map.put('/', new SlashHandler());
        map.put(' ', new SpaceHandler());
        map.put('*', new StarHandler());
    }

    /**
     * call method map.get(Key k)
     * @param c input character
     * @return handler for input character
     */
    public Handler getHandler(final char c) {
        return map.get(c);
    }
}
