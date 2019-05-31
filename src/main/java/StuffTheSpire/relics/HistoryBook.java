package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.actions.SortDrawPileAction;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class HistoryBook extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("HistoryBook");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("history_book.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("history_book.png"));
    public static final Logger logger = LogManager.getLogger(StuffTheSpireMod.class.getName());
    public HistoryBook() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void atBattleStartPreDraw()
    {
        AbstractDungeon.actionManager.addToBottom(new SortDrawPileAction());
    }

    @Override
    public void onShuffle()
    {
        AbstractDungeon.actionManager.addToTop(new SortDrawPileAction());
    }

}
