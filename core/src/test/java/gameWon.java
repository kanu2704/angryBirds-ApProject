
import com.ap_project.game.sprites.*;
import com.ap_project.game.states.playState;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class gameWon {
    private List<pig<?>> pigsDestroyed;
    private ArrayList<pig<?>> pigs;
    private playState playState;
    pig<?> pig1;
    pig<?> pig2;

    @BeforeEach
    public void Initialise(){
        pigs=new ArrayList<>();
        pigsDestroyed=new ArrayList<>();
        playState=new playState(null,1,null,true);
    }

    @Test
    public void shouldWin(){
        pigs.clear();
        pigsDestroyed.clear();
        assertTrue(playState.gameWon(pigs,pigsDestroyed));
    }

    @Test
    public void shouldLose(){
        pigs.add(pig1);
        assertFalse(playState.gameWon(pigs,pigsDestroyed));
    }

    @Test
    public void shouldLose2(){
        pigsDestroyed.add(pig2);
        assertFalse(playState.gameWon(pigs,pigsDestroyed));
    }

}
