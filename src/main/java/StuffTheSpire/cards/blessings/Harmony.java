package StuffTheSpire.cards.blessings;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.AbstractBlessing;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;

public class Harmony extends AbstractBlessing implements StartupCard {


    public static final String ID = StuffTheSpireMod.makeID("Harmony");
    public static final String IMG = StuffTheSpireMod.makeCardPath("Harmony");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = -2;
    private static final int MAGICNUMBER = 5;

    public Harmony() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TARGET);
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Harmony();
    }

    @Override
    public boolean atBattleStartPreDraw() {
        int amount;
        amount = AbstractDungeon.player.drawPile.getSkills().size();
        if (amount / this.magicNumber > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, amount / this.magicNumber), amount / this.magicNumber));
        }
        return true;
    }
}