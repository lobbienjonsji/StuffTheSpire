package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.actions.DecreaseCardCostAction;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class Expresso extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("Expresso");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("expresso.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("expresso.png"));
    private int cardcost = 99;
    private ArrayList<AbstractCard> CheapestCards = new ArrayList();
    public Expresso() {
        super(ID, IMG, RelicTier.RARE, LandingSound.CLINK);
    }
    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0];
    }
    @Override
    public void atTurnStartPostDraw()
    {
        AbstractDungeon.actionManager.addToBottom(new DecreaseCardCostAction());
    }

}
