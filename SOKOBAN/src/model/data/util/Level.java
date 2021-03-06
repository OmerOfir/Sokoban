package model.data.util;

import java.io.Serializable;
import java.util.ArrayList;

import common.Position;

public class Level implements Serializable {

    private ArrayList<ArrayList<GameObject>> staticPattern = new ArrayList<ArrayList<GameObject>>();
    private ArrayList<ArrayList<GeneralMovable>> movables = new ArrayList<ArrayList<GeneralMovable>>();
    private ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();// set
    private ArrayList<Target> targets = new ArrayList<Target>();// set

    public Level() {

    }

    /**
     * 
     * @param targets
     *            Sets ArrayList of Target() to targets
     */
    public void setTargets(ArrayList<Target> targets) {
	this.targets = targets;
    }

    /**
     * 
     * @param targets
     *            Sets ArrayList of GameCharacter() to characters
     */
    public void setCharacters(ArrayList<GameCharacter> characters) {
	this.characters = characters;
    }

    /**
     * 
     * @param targets
     *            Sets ArrayList of Target() to targets
     */

    /**
     * 
     * @return ArrayList<GameCharacters>
     */
    public ArrayList<GameCharacter> getCharacters() {

	if (characters != null)
	    return characters;
	else
	    return null;
    }

    /*
     * public void changeObjectPlace(GeneralMovable obj) { // The function
     * assume the conditions are right to change place
     * 
     * Position newPosition = obj.getPosition(); // Position oldP =
     * obj.getPosition();
     * movables.get(newPosition.getX()).set(newPosition.getY(), obj);
     * 
     * }
     */

    /**
     * 
     * updates "movables" matrix by object (new) position
     * 
     * @param obj
     */
    public void changeMovables(GeneralMovable obj) {
	int x = obj.getPosition().getX();
	int y = obj.getPosition().getY();

	movables.get(x).set(y, obj);

    }

    /**
     * 
     * @param Position
     *            delete GeneralMovable object in Position p
     */
    public void deleteObjInMov(Position p) {
	int x = p.getX();
	int y = p.getY();

	movables.get(x).set(y, null);

    }

    /**
     * 
     * Returns matrix of all static (not GeneralMovables) object - Wall, Floor,
     * Target
     * 
     * @return ArrayList<ArrayList<GameObject>>
     */
    public ArrayList<ArrayList<GameObject>> getStaticPattern() {

	if (staticPattern != null)
	    return staticPattern;
	else
	    return null;
    }

    /**
     * 
     * 
     * @param staticPattern
     *            sets staticPattren - matrix of all static (not
     *            GeneralMovables) object - Wall, Floor, Target
     */
    public void setStaticPattern(ArrayList<ArrayList<GameObject>> staticPattern) {

	this.staticPattern = staticPattern;

    }

    /**
     * 
     * Returns matrix of all GeneralMovables object - Character, Box
     * 
     * @return ArrayList<ArrayList<GameObject>>
     */

    public ArrayList<ArrayList<GeneralMovable>> getMovables() {

	if (movables != null)
	    return movables;
	else
	    return null;

    }

    /**
     * 
     * 
     * @param movables
     *            sets movables - matrix of all GeneralMovables object -
     *            Character, Box
     */
    public void setMovables(ArrayList<ArrayList<GeneralMovable>> movables) {

	this.movables = movables;

    }

    /**
     * 
     * Find GeneralMovable object by position and returns it
     * 
     * @param p
     * @return GeneralMovable
     */
    public GameObject getMovaObj(Position p) {
	GameObject obj = movables.get(p.getX()).get(p.getY());
	return obj;
    }

    /**
     * 
     * Find Static object and returns it
     * 
     * @param p
     * @return GameObject
     */
    public GameObject getStaticObj(Position p) {
	GameObject obj = staticPattern.get(p.getX()).get(p.getY());
	return obj;
    }

    /**
     * returns all targets
     * 
     * @return ArrayList<Target>
     */
    public ArrayList<Target> getTargets() {

	if (targets != null)
	    return targets;
	else
	    return null;
    }

    /**
     * 
     * Update target flag if box on target for each target
     */
    public void updateTargets() {
	for (Target t : targets) {
	    Position p = t.getPosition();
	    if ((getMovaObj(p)) instanceof Box)
		t.setUndrBox(true);
	    else
		t.setUndrBox(false);

	}
    }

    /**
     * 
     * return true if all boxes on target
     * 
     * @return
     */
    public boolean isComplete() {
	boolean flag = false;
	updateTargets();
	for (Target t : targets) {
	    if (t.getUnderBox() == true)
		flag = true;
	    else {
		flag = false;
		break;
	    }
	}
	return flag;
    }

    public char[][] getCharMat() {

	int x = getStaticPattern().size();
	int y = findSize();
	char[][] charDisplayer = new char[x][y];
	int i = 0, j = 0;

	for (ArrayList<GameObject> arr : getStaticPattern()) {
	    for (GameObject go : arr) {

		GameObject temp = null;
		if (((go instanceof Floor) || (go instanceof Target)) && ((getMovables().get(i).get(j)) != null)) {
		    temp = (GameObject) getMovables().get(i).get(j);

		} else {

		    temp = (GameObject) getStaticPattern().get(i).get(j);
		}
		charDisplayer[i][j] = (temp.getSign());
		j++;
	    }

	    j = 0;
	    i++;
	}

	return charDisplayer;

    }

    public int findSize() {

	if (getStaticPattern() != null) {
	    int max = 0;
	    int temp = 0;
	    ArrayList<ArrayList<GameObject>> mat = getStaticPattern();
	    for (ArrayList<GameObject> arr : mat) {
		temp = arr.size();
		if (max < temp)
		    max = temp;
		return max;

	    }

	}

	return 0;
    }

}
