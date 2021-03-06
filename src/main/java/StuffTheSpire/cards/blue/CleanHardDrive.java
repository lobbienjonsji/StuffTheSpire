package StuffTheSpire.cards.blue;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.actions.CleanHardDriveAction;
import StuffTheSpire.cards.AbstractDecreasingCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CleanHardDrive extends AbstractDecreasingCard {


    public static final String ID = StuffTheSpireMod.makeID("CleanHardDrive");
    public static final String IMG = StuffTheSpireMod.makeCardPath("CleanHardDrive.png");
    public static final CardColor COLOR = CardColor.BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 0;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 3;
    private static final int BLOCK = 0;

    public CleanHardDrive() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        if (this.magicNumber == 1) {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
            initializeDescription();
        } else {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new CleanHardDriveAction(this.magicNumber));
        if (this.magicNumber > 0) {
            baseMagicNumber--;
            magicNumber = baseMagicNumber;
        }
        if (this.magicNumber == 1) {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
            initializeDescription();
        } else {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
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
    public void recharge(int amount) {
        baseMagicNumber += amount;
        magicNumber = baseMagicNumber;
        if (this.magicNumber == 1) {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
            initializeDescription();
        } else {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }

    }
}