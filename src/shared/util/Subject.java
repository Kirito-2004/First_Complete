package shared.util;
import java.beans.PropertyChangeListener;

public interface Subject {
    public void addListener(String eventName, PropertyChangeListener listener);
    public void removeListener(String eventName, PropertyChangeListener listener);

}
