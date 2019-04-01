package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class HistoryBook extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("HistoryBook");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public HistoryBook() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void atPreBattle()
    {
        AbstractDungeon.player.drawPile.sortByAcquisition();
    }

    @Override
    public void onShuffle()
    {
        AbstractDungeon.player.drawPile.sortByAcquisition();
    }

    @Override
    public void update()
    {
        AbstractDungeon.player.drawPile.sortByAcquisition();
    }
}
