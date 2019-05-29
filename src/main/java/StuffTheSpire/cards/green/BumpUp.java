package StuffTheSpire.cards.green;

import StuffTheSpire.StuffTheSpireMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BumpUp extends CustomCard {


    public static final String ID = StuffTheSpireMod.makeID("BumpUp");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("Attack.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.GREEN;
    private static final int COST = 2;
    private static final int DAMAGE = 10;
    private static final int MAGICNUMBER = 20;
    private static final int BLOCK = 0;

    public BumpUp() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if(c.type == CardType.ATTACK && c.cost == 2)
            {
                c.baseDamage += c.baseDamage*(float)this.magicNumber/100.0F;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if(c.type == CardType.ATTACK && c.cost == 2)
            {
                c.baseDamage += c.baseDamage*(float)this.magicNumber/100.0F;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if(c.type == CardType.ATTACK && c.cost == 2)
            {
                c.baseDamage += c.baseDamage*(float)this.magicNumber/100.0F;
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
            upgradeMagicNumber(20);
        }
    }
}