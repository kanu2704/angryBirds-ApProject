
import com.ap_project.game.sprites.*;
import com.ap_project.game.states.playState;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class blockDestruction {
    block<?> block1;

    @BeforeEach
    public void Initialise(){
        block1=new woodenBlock(null,null);
    }

    @Test
    public void isDestroyed(){
        block1.setHits(0);
        assertTrue(block1.isDestroyed());
    }
    @Test
    public void isNotDestroyed(){
        //block1.setHits(0);
        assertFalse(block1.isDestroyed());
    }


}
