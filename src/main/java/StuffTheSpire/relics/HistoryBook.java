package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.actions.SortDrawPile;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;
import static com.evacipated.cardcrawl.mod.stslib.StSLib.getMasterDeckEquivalent;

public class HistoryBook extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("HistoryBook");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
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
        AbstractDungeon.actionManager.addToBottom(new SortDrawPile());
    }

    @Override
    public void onShuffle()
    {
        AbstractDungeon.actionManager.addToTop(new SortDrawPile());
    }

}
