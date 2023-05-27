package Model;

public class KeyInput {
    private String keyString;
    private int keyCode, keyModifiers;
    private boolean pressed;
    private long systemsTimeMillis;

    public KeyInput(String _keyString, int _keyCode, int _keyModifiers, boolean _pressed, long _pressedMillis) {
        keyString = _keyString;
        keyCode = _keyCode;
        keyModifiers = _keyModifiers;
        pressed = _pressed;
        systemsTimeMillis = _pressedMillis;
    }

    public String getKeyString() {
        return keyString;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getKeyModifiers() {
        return keyModifiers;
    }

    public boolean isPressed() {
        return pressed;
    }

    public long getSystemsTimeMillis() {
        return systemsTimeMillis;
    }

    @Override
    public String toString() {
        return "KeyData\t[keyString=" + keyString + ",keycode=" + keyCode + ",modifiers=" + keyModifiers + ",pressed="
                + pressed + ",systemsTimeMillis=" + systemsTimeMillis + "]";
    }

}
