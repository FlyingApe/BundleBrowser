package nl.bastiaansierd.bundleb.logic;

import nl.bastiaansierd.bundleb.interfaces.data.DataRouter;
import nl.bastiaansierd.bundleb.interfaces.logic.IntroLogic;
import nl.bastiaansierd.bundleb.logic.helpers.MarkdownParser;

public class BundelBIntroLogic implements IntroLogic {
    public String getIntroTekst(){
        DataRouter dataRouter = BundelBEnvironment.getInstance().getDataRouter();
        return MarkdownParser.parse(dataRouter.getIntroTekst());
    }

}
