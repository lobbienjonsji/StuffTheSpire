package StuffTheSpire.variables;

import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class DiminishingVariable extends DynamicVariable {
    @Override
    public String key() {
        return "StuffTheSpire:Diminishing";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return abstractCard.magicNumber;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return abstractCard.magicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return false;
    }
    @Override
    public Color getNormalColor() {
        return Color.ORANGE;
    }
}
