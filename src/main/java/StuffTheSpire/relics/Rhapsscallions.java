package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class Rhapsscallions extends CustomRelic {
    public static final String ID = StuffTheSpireMod.makeID("Rhapsscallions");
    private boolean cardSelected = true;
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Rhapsscallions.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Rhapsscallions.png"));
    private static final RelicTier TIER = RelicTier.UNCOMMON;

    public Rhapsscallions() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        this.cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;

        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
                .getPurgeableCards(), 1, this.DESCRIPTIONS[1], false, false, false, true);
    }

    public void update() {
        super.update();
        if ((!this.cardSelected) &&
                (AbstractDungeon.gridSelectScreen.selectedCards.size() == 1)) {
            this.cardSelected = true;
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0), Settings.WIDTH / 2.0F - 30.0F * Settings.scale - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.player.masterDeck.removeCard(card);
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.increaseMaxHp(60 / AbstractDungeon.player.masterDeck.size(), true);
        }
    }
}
