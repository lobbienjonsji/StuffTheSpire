package StuffTheSpire.cards.colorless;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.AbstractDecreasingCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlasmaPulse extends AbstractDecreasingCard {


    public static final String ID = StuffTheSpireMod.makeID("PlasmaPulse");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("PlasmaPulse.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final int COST = 0;
    private static final int BLOCK = 1;
    private static final int MAGICNUMBER = 3;
    private static final int MAGICNUMBER_UPGR = 1;

    public PlasmaPulse() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        tags.add(StuffTheSpire.patches.cards.CardTags.DECREASING);
    }
    @Override
    public void recharge(int amount)
    {
        baseMagicNumber += amount;
        magicNumber = baseMagicNumber;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
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