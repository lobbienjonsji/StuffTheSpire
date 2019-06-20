package StuffTheSpire.events;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.curses.CuriosityCurse;
import StuffTheSpire.relics.OccultApparatus;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.QuestionCard;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

import static StuffTheSpire.StuffTheSpireMod.Blessings;
import static StuffTheSpire.StuffTheSpireMod.makeEventPath;

public class Wunderkammer extends AbstractImageEvent {
    public static final String ID = StuffTheSpireMod.makeID("Wunderkammer");
    public static final String IMG = makeEventPath("wunderkammer.png");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private AbstractRelic CommonRelic;
    private AbstractRelic StarterRelic;

    public Wunderkammer() {
        super(NAME, DESCRIPTIONS[0], IMG);
        ArrayList<AbstractRelic> CommonRelics = new ArrayList<>();
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r.tier == AbstractRelic.RelicTier.COMMON) {
                CommonRelics.add(r);
            }
        }
        if (!CommonRelics.isEmpty()) {
            int r = AbstractDungeon.relicRng.random(0, CommonRelics.size() - 1);
            CommonRelic = CommonRelics.get(r);
            imageEventText.setDialogOption(OPTIONS[0] + CommonRelic.name + OPTIONS[1]);
        } else {
            imageEventText.setDialogOption(OPTIONS[2], true);
        }
        ArrayList<AbstractRelic> StarterRelics = new ArrayList<>();
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r.tier == AbstractRelic.RelicTier.STARTER) {
                StarterRelics.add(r);
            }
        }
        if (!StarterRelics.isEmpty()) {
            int r = AbstractDungeon.relicRng.random(0, StarterRelics.size() - 1);
            StarterRelic = StarterRelics.get(r);
            imageEventText.setDialogOption(OPTIONS[3] + StarterRelic.name + OPTIONS[4]);
        } else {
            imageEventText.setDialogOption(OPTIONS[5], true);
        }
        imageEventText.setDialogOption(OPTIONS[6], new CuriosityCurse());
        imageEventText.setDialogOption(OPTIONS[7]);
        noCardsInRewards = true;
    }

    @Override
    protected void buttonEffect(int i) {
        switch (screenNum) {
            case 0:
                switch (i) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.player.loseRelic(CommonRelic.relicId);
                        RewardItem SpecialReward = new RewardItem();
                        SpecialReward.type = RewardItem.RewardType.CARD;
                        SpecialReward.text = DESCRIPTIONS[5];
                        SpecialReward.cards.clear();
                        int a = 3;
                        if (AbstractDungeon.player.hasRelic(QuestionCard.ID)) {
                            a++;
                        }
                        while (SpecialReward.cards.size() < a) {
                            AbstractCard c = Blessings.getRandomCard(true);
                            boolean addCard = true;
                            for (AbstractCard card : SpecialReward.cards) {
                                if (card.cardID.equals(c.cardID)) {
                                    addCard = false;
                                }
                            }
                            if (addCard) {
                                SpecialReward.cards.add(c.makeCopy());
                            }
                        }
                        AbstractDungeon.getCurrRoom().rewards.add(0, SpecialReward);
                        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                        AbstractDungeon.combatRewardScreen.open();
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                        this.imageEventText.clearRemainingOptions();
                        OccultApparatus o = new OccultApparatus();
                        o.instantObtain(AbstractDungeon.player, AbstractDungeon.player.relics.indexOf(StarterRelic), false);
                        screenNum = 1;
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new CuriosityCurse(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        AbstractRelic relic = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, relic);
                        screenNum = 1;
                        break;
                    case 3:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[8]);
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
