package StuffTheSpire.cards.colorless;

import StuffTheSpire.StuffTheSpireMod;
import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomSavable;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static java.lang.Class.forName;

public class Snapshot extends CustomCard implements CustomSavable<ArrayList<String>> {


    public static final String ID = StuffTheSpireMod.makeID("Snapshot");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final int COST = 3;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 0;
    private static final int BLOCK = 0;
    public ArrayList<String> Orbs;

    public Snapshot() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        Orbs = new ArrayList<>();
    }

    public void addOrb(AbstractOrb orb)
    {
        Orbs.add(orb.getClass().toString());
        try {
            String st = orb.getClass().toString().replaceAll("class ", "");
            String O = ((AbstractOrb) forName(st).newInstance()).name;
            this.rawDescription += O + " ";
            this.initializeDescription();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (String s:Orbs) {
            try {
                String st = s.replaceAll("class ", "");
                AbstractOrb O = ((AbstractOrb) forName(st).newInstance());
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(O));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);

            initializeDescription();
        }
    }

    @Override
    public ArrayList<String> onSave() {
        return Orbs;
    }

    @Override
    public void onLoad(ArrayList<String> List) {
        for (String orb : List) {
            Orbs.add(orb);
        }
    }

    @Override
    public Type savedType() {
        return new TypeToken<ArrayList<String>>() {
        }.getType();
    }


    public AbstractCard makeStatEquivalentCopy()
    {
        AbstractCard card = this.makeCopy();
        for (int i = 0; i < this.timesUpgraded; i++) {
            card.upgrade();
        }
        card.name = this.name;
        card.target = this.target;
        card.upgraded = this.upgraded;
        card.timesUpgraded = this.timesUpgraded;
        card.baseDamage = this.baseDamage;
        card.baseBlock = this.baseBlock;
        card.baseMagicNumber = this.baseMagicNumber;
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        card.isCostModified = this.isCostModified;
        card.isCostModifiedForTurn = this.isCostModifiedForTurn;
        card.inBottleLightning = this.inBottleLightning;
        card.inBottleFlame = this.inBottleFlame;
        card.inBottleTornado = this.inBottleTornado;
        card.isSeen = this.isSeen;
        card.isLocked = this.isLocked;
        card.misc = this.misc;
        card.freeToPlayOnce = this.freeToPlayOnce;
        for (String orb : this.Orbs) {
            ((Snapshot)card).Orbs.add(orb);
        }
        for (String s:Orbs) {
            try {
                String st = s.replaceAll("class ", "");
                String O = ((AbstractOrb) forName(st).newInstance()).name;
                card.rawDescription += O + " ";
                card.initializeDescription();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return card;
    }
}