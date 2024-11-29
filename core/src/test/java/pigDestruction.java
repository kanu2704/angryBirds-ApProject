
import com.ap_project.game.sprites.*;
import com.ap_project.game.states.playState;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class pigDestruction {
    pig<?> pig1;

    @BeforeEach
    public void Initialise(){
        pig1=new pig1(null,null);
    }

    @Test
    public void isDestroyed(){
        pig1.setHits(0);
        assertTrue(pig1.isDestroyed());
    }
    @Test
    public void isNotDestroyed(){
        //block1.setHits(0);
        assertFalse(pig1.isDestroyed());
    }


}
