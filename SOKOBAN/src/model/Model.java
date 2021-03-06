package model;

import model.data.util.Level;

public interface Model {

    public void load(String path);

    public void save(String path);

    public Level getCurrentLevel();

    public void move(String direction);

}
