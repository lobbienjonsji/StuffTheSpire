package StuffTheSpire.cards.blessings;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.AbstractBlessing;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BrainWave extends AbstractBlessing implements StartupCard {


    public static final String ID = StuffTheSpireMod.makeID("BrainWave");
    public static final String IMG = StuffTheSpireMod.makeCardPath("BrainWave");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = -2;
    private static final int MAGICNUMBER = 4;

    public BrainWave() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TARGET);
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        this.isEthereal = true;
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
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BrainWave();
    }

    @Override
    public boolean atBattleStartPreDraw() {
        int Hasenoughcards = 0;
        for (AbstractCard a : AbstractDungeon.player.drawPile.group) {
            if (a.costForTurn > 0) {
                Hasenoughcards++;
            }
        }

        if (Hasenoughcards <= this.magicNumber) {
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.costForTurn > 0) {
                    c.cost = 0;
                    c.costForTurn = 0;
                    c.isCostModified = true;
                }
            }
        } else {
            CardGroup selectedcards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            while (selectedcards.size() < magicNumber) {
                AbstractCard a = AbstractDungeon.player.drawPile.getRandomCard(true);
                boolean containscard = false;
                for (AbstractCard c : selectedcards.group) {
                    if (c == a) {
                        containscard = true;
                    }
                }
                if (!containscard && a.costForTurn > 0) {
                    a.cost = 0;
                    a.costForTurn = 0;
                    a.isCostModified = true;
                    selectedcards.addToTop(a);
                }
            }
        }
        return true;
    }
}