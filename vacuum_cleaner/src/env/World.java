// Environment code for project vacuum_cleaner

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

public class World extends Environment {

    private Logger logger = Logger.getLogger("vacuum_cleaner."+World.class.getName());

    // Robot state
    private int posX = 0;
    private int posY = 0;

    // World state
    private boolean dirty[][] = { { true, true }, { true, true } };

    // Perceptions
    private static final Literal Ldirty = ASSyntax.createLiteral("dirty");
    private static final Literal Lclean = ASSyntax.createLiteral("clean");
    private static final Literal Lpos1 = ASSyntax.createLiteral("pos", ASSyntax.createNumber(1));
    private static final Literal Lpos2 = ASSyntax.createLiteral("pos", ASSyntax.createNumber(2));
    private static final Literal Lpos3 = ASSyntax.createLiteral("pos", ASSyntax.createNumber(3));
    private static final Literal Lpos4 = ASSyntax.createLiteral("pos", ASSyntax.createNumber(4));

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);

        
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: "+action+", but not implemented!");
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
