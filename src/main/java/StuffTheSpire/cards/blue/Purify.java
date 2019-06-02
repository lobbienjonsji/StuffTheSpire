package StuffTheSpire.cards.blue;

import StuffTheSpire.StuffTheSpireMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Plasma;

public class Purify extends CustomCard {


    public static final String ID = StuffTheSpireMod.makeID("Purify");
    public static final String IMG = StuffTheSpireMod.makeCardPath("Purify.png");
    public static final CardColor COLOR = CardColor.BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 1;
    private static final int BLOCK = 0;

    public Purify() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractOrb O : p.orbs) {
            if (O instanceof Dark) {
                Plasma P = new Plasma();
                p.orbs.set(p.orbs.indexOf(O), P);
                for (int i = 0; i < p.orbs.size(); i++) {
                    p.orbs.get(i).setSlot(i, p.maxOrbs);
                }
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}