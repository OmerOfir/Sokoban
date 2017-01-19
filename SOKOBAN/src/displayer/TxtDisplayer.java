package displayer;

import java.util.ArrayList;

import levels.Floor;
import levels.GameObject;
import levels.Level;
import levels.Target;

public class TxtDisplayer implements Displayer {

    /**
     * 
     * Display level in ASCII letters
     */
    @Override
    public void display(Level outlevel) {

	{

	    int i = 0, j = 0;

	    for (ArrayList<GameObject> arr : outlevel.getStaticPattern()) {
		for (GameObject go : arr) {

		    GameObject temp = null;
		    if (((go instanceof Floor) || (go instanceof Target))
			    && ((outlevel.getMovables().get(i).get(j)) != null)) {
			temp = (GameObject) outlevel.getMovables().get(i).get(j);

		    } else {

			temp = (GameObject) outlevel.getStaticPattern().get(i).get(j);
		    }
		    System.out.print(temp.getSign());
		    j++;
		}

		System.out.println();
		j = 0;
		i++;
	    }

	}

    }

}