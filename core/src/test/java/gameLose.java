
import com.ap_project.game.sprites.*;
import com.ap_project.game.states.playState;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class gameLose {
    private int current;
    private ArrayList<bird<?>> birds;
    private playState playState;
    bird<?> bird1;


    @BeforeEach
    public void Initialise(){
        birds=new ArrayList<>();
        playState=new playState(null,1,null,true);
    }

    @Test
    public void shouldLose(){
        current=1;
        assertTrue(playState.gameLose(current,birds));
    }

    @Test
    public void shouldWin(){
        current=0;
        birds.add(bird1);
        assertFalse(playState.gameLose(current,birds));
    }


}
