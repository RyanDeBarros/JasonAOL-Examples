package example;

// Environment code for project hello_world

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

public class Env extends Environment {

    private Logger logger = Logger.getLogger("hello_world."+Env.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        try {
            if (action.getFunctor().equals("burn")) {
                logger.info(agName + " is burning!");
                addPercept(ASSyntax.parseLiteral("fire"));
                return true;
            } else if (action.getFunctor().equals("run")) {
                logger.info(agName + " runs away!");
                return true;
            } else {
                logger.info("executing: "+action+", but not implemented!");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
