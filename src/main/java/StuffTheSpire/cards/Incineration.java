package StuffTheSpire.cards;

import StuffTheSpire.StuffTheSpireMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Incineration extends AbstractDecreasingCard {


    public static final String ID = StuffTheSpireMod.makeID("Incineration");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("Attack.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.RED;
    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int MAGICNUMBER = 3;
    private static final int BLOCK = 0;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    public Incineration() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        if(this.magicNumber == 1)
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
        else
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new FlameBurst(), this.magicNumber));
        if(this.magicNumber > 0) {
            baseMagicNumber--;
            magicNumber = baseMagicNumber;
        }
        if(this.magicNumber == 1)
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
        else
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }

    @Override
    public void recharge(int amount) {
        baseMagicNumber += amount;
        magicNumber = baseMagicNumber;
        if(this.magicNumber == 1)
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
        else
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }
}