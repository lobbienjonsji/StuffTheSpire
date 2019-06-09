package StuffTheSpire.cards.blessings;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.AbstractBlessing;
import StuffTheSpire.powers.WardingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class CelestialWarden extends AbstractBlessing {


    public static final String ID = StuffTheSpireMod.makeID("CelestialWarden");
    public static final String IMG = StuffTheSpireMod.makeCardPath("CelestialWarden");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = 0;
    private static final int MAGICNUMBER = 3;
    private static final int BLOCK = 6;

    public CelestialWarden() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TARGET);
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WardingPower(p, p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeBlock(3);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CelestialWarden();
    }
}