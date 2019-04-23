package StuffTheSpire.cards.colorless;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.AbstractDecreasingCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FreeRealEstate extends AbstractDecreasingCard {


    public static final String ID = StuffTheSpireMod.makeID("FreeRealEstate");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final int COST = 0;
    private static final int MAGICNUMBER = 5;
    private static final int MAGICNUMBER_UPGR = 2;

    public FreeRealEstate() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        tags.add(StuffTheSpire.patches.cards.CardTags.DECREASING);
        tags.add(StuffTheSpire.patches.cards.CardTags.UNIQUE);
    }
    @Override
    public void recharge(int amount)
    {
        baseMagicNumber += amount;
        magicNumber = baseMagicNumber;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.gainGold(this.magicNumber);
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
        if(this.magicNumber > 0) {
            baseMagicNumber--;
            magicNumber = baseMagicNumber;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGICNUMBER_UPGR);
            initializeDescription();
        }
    }
}