package StuffTheSpire.events;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.blessings.BrainWave;
import StuffTheSpire.relics.GreaterSneckoEye;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static StuffTheSpire.StuffTheSpireMod.makeEventPath;

public class GreaterSnecko extends AbstractImageEvent {
    public static final String ID = StuffTheSpireMod.makeID("GreaterSnecko");
    public static final String IMG = makeEventPath("GreaterSnecko.png");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static int dmg = 0;
    private static float maxhp = 0;

    public GreaterSnecko() {
        super(NAME, DESCRIPTIONS[0], IMG);
        dmg = 20;
        if (AbstractDungeon.ascensionLevel >= 15) {
            dmg = 30;
        }
        maxhp = 0.1F;
        if (AbstractDungeon.ascensionLevel >= 15) {
            maxhp = 0.15F;
        }
        imageEventText.setDialogOption(OPTIONS[0] + (int) (100 * maxhp) + OPTIONS[1], new BrainWave());
        imageEventText.setDialogOption(OPTIONS[2] + dmg + OPTIONS[3]);
        imageEventText.setDialogOption(OPTIONS[4]);
        noCardsInRewards = true;
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
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new BrainWave(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        AbstractDungeon.player.decreaseMaxHealth((int) (AbstractDungeon.player.maxHealth * maxhp));
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        RewardItem SpecialReward = new RewardItem();
                        SpecialReward.type = RewardItem.RewardType.RELIC;
                        SpecialReward.relic = new GreaterSneckoEye();
                        SpecialReward.text = SpecialReward.relic.name;
                        AbstractDungeon.getCurrRoom().rewards.add(0, SpecialReward);
                        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                        AbstractDungeon.combatRewardScreen.open();
                        AbstractDungeon.player.damage(new DamageInfo(null, dmg, DamageInfo.DamageType.HP_LOSS));
                        screenNum = 1;
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
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
