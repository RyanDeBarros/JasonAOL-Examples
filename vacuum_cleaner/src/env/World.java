// Environment code for project vacuum_cleaner

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.Random;
import java.util.logging.*;

public class World extends Environment {

    private Logger logger = Logger.getLogger("vacuum_cleaner."+World.class.getName());

    private Object modelLock = new Object();

    // Robot state
    private int posX = 0;
    private int posY = 0;

    // World state
    private boolean dirty[][] = { { true, true }, { true, true } };
    private Random r = new Random();

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
        spawnContinuousDirt();
    }

    private void spawnContinuousDirt() {
        new Thread(() -> {
            while (isRunning()) {
                try {
                    if (r.nextDouble() < 0.2) {
                        dirty[r.nextInt(2)][r.nextInt(2)] = true;
                    }

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void addPercepts() {
        clearPercepts();
        
        if (posX == 0) {
            if (posY == 0) {
                addPercept(Lpos1);
            } else if (posY == 1) {
                addPercept(Lpos3);
            }
        } else if (posX == 1) {
            if (posY == 0) {
                addPercept(Lpos2);
            } else if (posY == 1) {
                addPercept(Lpos4);
            }
        }

        if ((posX & ~1) == 0 && (posY & ~1) == 0) {
            addPercept(dirty[posX][posY] ? Ldirty : Lclean);
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (modelLock) {
            logger.info("executing: "+action);

            if (action.getFunctor().equals("suck")) {
                if (dirty[posX][posY]) {
                    dirty[posX][posY] = false;
                } else {
                    logger.info("suck: location is already clean!");
                }
            } else if (action.getFunctor().equals("left")) {
                if (posX == 1) {
                    posX = 0;
                }
            } else if (action.getFunctor().equals("right")) {
                if (posX == 0) {
                    posX = 1;
                }
            } else if (action.getFunctor().equals("up")) {
                if (posY == 1) {
                    posY = 0;
                }
            } else if (action.getFunctor().equals("down")) {
                if (posY == 0) {
                    posY = 1;
                }
            }
        }

        addPercepts();
        return true;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
