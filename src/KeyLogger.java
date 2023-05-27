import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelListener;

import Model.KeyInput;
import Model.MouseInput;

public class KeyLogger extends LogService
        implements NativeKeyListener, NativeMouseListener, NativeMouseMotionListener, NativeMouseWheelListener {

    public KeyLogger(String filePath, long maxSizeMegabytes) {
        super(filePath, maxSizeMegabytes);
    }

    private KeyInput key;
    private MouseInput mouse;

    Settings.Prefs pref = Settings.pref;

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (pref == Settings.Prefs.KEYBOARD_ONLY || pref == Settings.Prefs.BOTH) {
            key = new KeyInput(NativeKeyEvent.getKeyText(e.getKeyCode()), e.getKeyCode(), e.getModifiers(), true,
                    System.currentTimeMillis());
                    log(key.toString());
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (pref == Settings.Prefs.KEYBOARD_ONLY || pref == Settings.Prefs.BOTH) {
            key = new KeyInput(NativeKeyEvent.getKeyText(e.getKeyCode()), e.getKeyCode(), e.getModifiers(), false,
                    System.currentTimeMillis());
                    log(key.toString());
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        if (pref == Settings.Prefs.MOUSE_ONLY || pref == Settings.Prefs.BOTH) {
            mouse = new MouseInput(e.getModifiers(), "NATIVE_MOUSE_DRAGGED", e.getX(), e.getY(), 0,
                    System.currentTimeMillis());
                    log(mouse.toString());
        }
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        if (pref == Settings.Prefs.MOUSE_ONLY || pref == Settings.Prefs.BOTH) {
            mouse = new MouseInput(e.getModifiers(), "NATIVE_MOUSE_MOVED", e.getX(), e.getY(), 0,
                    System.currentTimeMillis());
                    log(mouse.toString());
        }
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        if (pref == Settings.Prefs.MOUSE_ONLY || pref == Settings.Prefs.BOTH) {
            mouse = new MouseInput(e.getModifiers(), "NATIVE_MOUSE_PRESSED", e.getX(), e.getY(), 0,
                    System.currentTimeMillis());
                    log(mouse.toString());
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        if (pref == Settings.Prefs.MOUSE_ONLY || pref == Settings.Prefs.BOTH) {
            mouse = new MouseInput(e.getModifiers(), "NATIVE_MOUSE_RELEASED", e.getX(), e.getY(), 0,
                    System.currentTimeMillis());
                    log(mouse.toString());
        }
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        if (pref == Settings.Prefs.MOUSE_ONLY || pref == Settings.Prefs.BOTH) {
            mouse = new MouseInput(e.getModifiers(), "NATIVE_MOUSE_WHEEL", e.getX(), e.getY(), e.getWheelRotation(),
                    System.currentTimeMillis());
                    log(mouse.toString());
        }
    }
}