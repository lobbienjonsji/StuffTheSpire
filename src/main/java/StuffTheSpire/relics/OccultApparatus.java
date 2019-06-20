package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.powers.ApparatusPower;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class OccultApparatus extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("OccultApparatus");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("OccultApparatus.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("OccultApparatus.png"));
    private static final RelicTier TIER = RelicTier.SPECIAL;

    public OccultApparatus() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.type == AbstractCard.CardType.SKILL) {
            this.counter += 1;
            if (this.counter % 3 == 0) {
                this.counter = 0;
                flash();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ApparatusPower(AbstractDungeon.player, 1), 1));
            }
        }
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onVictory() {
        this.counter = -1;
    }

    public void atTurnStart() {
        this.counter = 0;
    }
}
