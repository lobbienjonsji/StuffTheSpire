package StuffTheSpire.events;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.blessings.Innocence;
import StuffTheSpire.relics.RedRose;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static StuffTheSpire.StuffTheSpireMod.makeEventPath;

public class FlowerFields extends AbstractImageEvent {
    public static final String ID = StuffTheSpireMod.makeID("FlowerFields");
    public static final String IMG = makeEventPath("FlowerFields.png");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static int dmg = 0;
    private static int heal = 0;

    public FlowerFields() {
        super(NAME, DESCRIPTIONS[0], IMG);
        dmg = 7;
        if (AbstractDungeon.ascensionLevel >= 15) {
            dmg = 10;
        }
        heal = 15;
        if (AbstractDungeon.ascensionLevel >= 15) {
            heal = 10;
        }
        imageEventText.setDialogOption(OPTIONS[0] + dmg + OPTIONS[1], new Innocence());
        imageEventText.setDialogOption(OPTIONS[2], new Regret());
        imageEventText.setDialogOption(OPTIONS[3] + heal + OPTIONS[4]);
    }

    @Override
    protected void buttonEffect(int i) {
        switch (screenNum) {
            case 0:
                switch (i) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Innocence(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        AbstractDungeon.player.damage(new DamageInfo(null, dmg, DamageInfo.DamageType.HP_LOSS));
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, new RedRose());
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Regret(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        screenNum = 1;
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.player.heal(heal, true);
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
