
import com.ap_project.game.sprites.*;
import com.ap_project.game.states.playState;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class checkLevelConstruction {
    boolean levelConstructed;
    private playState playState;
    @BeforeEach
    public void initialise(){
        playState=new playState(null,1,null,true);
    }
    @Test
    public void isConstructed(){

        playState.levelConstructed=true;
        assertTrue(playState.checkLevelConstructed());
    }
    @Test
    public void isNotConstructed(){
        playState.levelConstructed=false;
        assertFalse(playState.checkLevelConstructed());
    }
}
