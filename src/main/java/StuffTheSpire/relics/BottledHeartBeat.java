package StuffTheSpire.relics;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.patches.relics.BottledPlaceholderField;
import StuffTheSpire.util.TextureLoader;
import basemod.abstracts.CustomBottleRelic;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.function.Predicate;

import static StuffTheSpire.StuffTheSpireMod.makeRelicOutlinePath;
import static StuffTheSpire.StuffTheSpireMod.makeRelicPath;

public class BottledHeartBeat extends CustomRelic implements CustomBottleRelic, CustomSavable<Integer> {
    public static final String ID = StuffTheSpireMod.makeID("BottledHeartBeat");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("heartbeat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("heartbeat.png"));
    private static final RelicTier TIER = RelicTier.RARE;
    private static AbstractCard card;
    private boolean cardSelected = true;

    public BottledHeartBeat() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        CardGroup group = CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck);
        for (AbstractCard c : group.group) {
            if (c.exhaust || c.isEthereal) {
                group.removeCard(c);
            }
        }
        AbstractDungeon.gridSelectScreen.open(group, 1, name, false, false, false, false);
    }

    @Override
    public Predicate<AbstractCard> isOnCard() {
        return BottledPlaceholderField.inBottledPlaceholderField::get;
    }

    @Override
    public Integer onSave() {
        if (card != null) {
            return AbstractDungeon.player.masterDeck.group.indexOf(card);
        } else {
            return -1;
        }
    }

    @Override
    public void update() {
        super.update();

        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            cardSelected = true;
            card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            BottledPlaceholderField.inBottledPlaceholderField.set(card, true);
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.INCOMPLETE) {
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            setDescriptionAfterLoading();
        }
    }


    @Override
    public void onUnequip() {
        if (card != null) {
            AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(card);
            if (cardInDeck != null) {
                BottledPlaceholderField.inBottledPlaceholderField.set(cardInDeck, false);
            }
        }
    }

    @Override
    public void onLoad(Integer cardIndex) {
        if (cardIndex == null) {
            return;
        }
        if (cardIndex >= 0 && cardIndex < AbstractDungeon.player.masterDeck.group.size()) {
            card = AbstractDungeon.player.masterDeck.group.get(cardIndex);
            if (card != null) {
                BottledPlaceholderField.inBottledPlaceholderField.set(card, true);
                setDescriptionAfterLoading();
            }
        }

    }

    public void setDescriptionAfterLoading() {
        this.description = DESCRIPTIONS[1] + FontHelper.colorString(card.name, "y") + DESCRIPTIONS[2];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    public void onExhaust(AbstractCard card) {
        flash();
        if (BottledPlaceholderField.inBottledPlaceholderField.get(card)) {
            AbstractDungeon.player.increaseMaxHp(2, false);
        }
    }
}
