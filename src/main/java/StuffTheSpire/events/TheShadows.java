package StuffTheSpire.events;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.colorless.Deluminance;
import StuffTheSpire.relics.BlackoutCurse;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.List;

import static StuffTheSpire.StuffTheSpireMod.makeEventPath;

public class TheShadows extends AbstractImageEvent {
    public static final String ID = StuffTheSpireMod.makeID("TheShadows");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("Shadows.png");


    private int screenNum = 0;
    public TheShadows() {
        super(NAME, DESCRIPTIONS[0], IMG);
        imageEventText.setDialogOption(OPTIONS[0], new Deluminance());
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int i) {
        switch (screenNum) {
            case 0:
                switch (i) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        int amount = 5;
                        if (AbstractDungeon.ascensionLevel >= 15) {
                            amount -= 2;
                        }
                        List<String> cards = new ArrayList();
                        for (int j = 0; j < amount; j++)
                        {
                            AbstractCard c = new Deluminance();
                            cards.add(c.cardID);
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        }
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, new BlackoutCurse());
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        break;
                }
                break;
            case 1:
                switch (i) {
                    case 0:
                        openMap();
                        break;
                }
                break;
        }
    }
}
