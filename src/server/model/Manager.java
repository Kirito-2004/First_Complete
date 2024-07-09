package server.model;
import shared.util.Subject;

public interface Manager extends Subject {
    public void loadFromFile();
    public <T> void saveToFile(T obj);
}
