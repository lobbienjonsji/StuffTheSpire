package StuffTheSpire.cards;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.powers.BloodClotPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodClot extends AbstractDecreasingCard {


    public static final String ID = StuffTheSpireMod.makeID("BloodClot");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.RED;
    private static final int COST = 1;
    private static final int MAGICNUMBER = 6;
    private static final int MAGICNUMBER_UPGR = 2;

    public BloodClot() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new BloodClotPower(p, p,this.magicNumber),this.magicNumber));
        baseMagicNumber--;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGICNUMBER_UPGR);
            initializeDescription();
        }
    }

    @Override
    public void recharge(int amount) {
        baseMagicNumber += amount;
        magicNumber = baseMagicNumber;
    }
}