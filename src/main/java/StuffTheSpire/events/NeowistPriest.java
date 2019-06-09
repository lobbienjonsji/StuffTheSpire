package StuffTheSpire.events;

import StuffTheSpire.StuffTheSpireMod;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static StuffTheSpire.StuffTheSpireMod.makeEventPath;

public class NeowistPriest extends AbstractImageEvent {
    public static final String ID = StuffTheSpireMod.makeID("NeowistPriest");
    public static final String IMG = makeEventPath("NeowPriest.png");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    public NeowistPriest() {
        super(NAME, DESCRIPTIONS[0], IMG);
        if (AbstractDungeon.player.gold >= 75) {
            imageEventText.setDialogOption(OPTIONS[0] + Math.max(75, AbstractDungeon.player.gold / 2) + OPTIONS[1]);
        } else {
            imageEventText.setDialogOption(OPTIONS[6] + "75" + OPTIONS[7], true);
        }
        if (AbstractDungeon.player.currentHealth > 20) {
            imageEventText.setDialogOption(OPTIONS[2] + Math.max(15, AbstractDungeon.player.currentHealth / 2) + OPTIONS[3]);
        } else {
            imageEventText.setDialogOption(OPTIONS[8] + "16" + OPTIONS[9], true);
        }
        imageEventText.setDialogOption(OPTIONS[4], new Pain());
        imageEventText.setDialogOption(OPTIONS[5], new Shame());
    }

    @Override
    protected void buttonEffect(int i) {
        switch (screenNum) {
            case 0:
                switch (i) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[10]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(StuffTheSpireMod.Blessings.getRandomCard(true).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        AbstractDungeon.player.loseGold(Math.max(75, AbstractDungeon.player.gold / 2));
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[10]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(StuffTheSpireMod.Blessings.getRandomCard(true).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        AbstractDungeon.player.damage(new DamageInfo(null, Math.max(15, AbstractDungeon.player.currentHealth / 4), DamageInfo.DamageType.HP_LOSS));
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[10]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(StuffTheSpireMod.Blessings.getRandomCard(true).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Pain(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        break;
                    case 3:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[10]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Shame(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        break;
                }
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
