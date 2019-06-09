package StuffTheSpire.cards.blessings;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.AbstractBlessing;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Rapture extends AbstractBlessing {


    public static final String ID = StuffTheSpireMod.makeID("Rapture");
    public static final String IMG = StuffTheSpireMod.makeCardPath("Rapture");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = -2;
    private static final int MAGICNUMBER = 3;

    public Rapture() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TARGET);
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }


    @Override
    public void triggerWhenDrawn() {
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, this.magicNumber, false));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Rapture();
    }
}