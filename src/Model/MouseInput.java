package Model;

public class MouseInput {
    private int mouseButtonCode, x, y, mouseWheelRotation;
    private String mouseEvent;
    private long systemsTimeMillis;

    public MouseInput(int _mouseButtonCode, String _mouseEvent, int _x, int _y, int _mouseWheelRotation,
            long _systemsTimeMillis) {
        mouseButtonCode = _mouseButtonCode;
        mouseEvent = _mouseEvent;
        x = _x;
        y = _y;
        mouseWheelRotation = _mouseWheelRotation;
        systemsTimeMillis = _systemsTimeMillis;
    }

    public int getMouseButtonCode() {
        return mouseButtonCode;
    }

    public String getMouseEvent() {
        return mouseEvent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMouseWheelRotation() {
        return mouseWheelRotation;
    }

    public long getSystemsTimeMillis() {
        return systemsTimeMillis;
    }

    @Override
    public String toString() {
        return "MouseData\t[mouseButton=" + mouseButtonCode + ",mouseEvent=" + mouseEvent + ",x=" + x + ",y=" + y
                + ",mouseWheelRotation=" + mouseWheelRotation + ",systemsTimeMillis" + systemsTimeMillis + "]";
    }

}
